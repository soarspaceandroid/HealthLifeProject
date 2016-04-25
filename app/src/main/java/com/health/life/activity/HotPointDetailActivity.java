package com.health.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.health.life.R;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.HotPointDetailInput;
import com.health.life.model.bean.output.HotPointDetailOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.utils.Config;
import com.health.life.view.pulltorefresh.PullToRefreshLayout;
import com.squareup.picasso.Picasso;

import org.xml.sax.XMLReader;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaofei on 16/2/23.
 * 废弃类  ,没有用的
 */
public class HotPointDetailActivity extends BaseActivity implements BaseViewInterface<HotPointDetailOutput> {

    private final static String ID = "hot_id";
    private final static String TITLE = "hot_title";
    private final static String PATH = "hot_apth";
    @Bind(R.id.hot_image)
    ImageView hotImage;
    @Bind(R.id.hot_name)
    TextView hotName;
    @Bind(R.id.hot_message)
    TextView hotMessage;
    @Bind(R.id.layout_parent)
    PullToRefreshLayout layoutParent;
    @Bind(R.id.hottime)
    TextView hottime;
    private BasePresenter basePresenter;

    private int id;
    private String title;
    private String path;

    public static void showActivity(Activity activity, int id, String title, View shareview, String path) {
        Intent intent = new Intent(activity, HotPointDetailActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(TITLE, title);
        intent.putExtra(PATH, path);
//        if (Build.VERSION.SDK_INT >= 21) {
//            ActivityOptions options = ActivityOptions
//                    .makeSceneTransitionAnimation(activity, shareview, "shareview3");
//            activity.startActivity(intent, options.toBundle());
//        } else {
            activity.startActivity(intent);
//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.activity_transition));
//        }
        id = getIntent().getIntExtra(ID, 0);
        title = getIntent().getStringExtra(TITLE);
        path = getIntent().getStringExtra(PATH);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_point_detail);
        ButterKnife.bind(this);
        controlMenu(true);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) hotImage.getLayoutParams();
        params.height = getResources().getDisplayMetrics().widthPixels * 4 / 5;
        hotImage.setLayoutParams(params);
        Picasso.with(this)
                .load(Config.BASE_IMAGE_URL + path)
//                .placeholder(R.drawable.contact_picture_placeholder)
                .tag(this)
                .into(hotImage);

        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
        layoutParent.setPullUpEnable(false);
        layoutParent.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                doRequest();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });

        layoutParent.autoRefresh();

    }

    @Override
    public void updateView(final HotPointDetailOutput hotPointDetailOutput) {
        layoutParent.refreshFinish(PullToRefreshLayout.SUCCEED);
        layoutParent.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        hottime.setText("时间 :" + format.format(new Date(hotPointDetailOutput.time)));
        hotName.setText(hotPointDetailOutput.title);
        hotMessage.setText(Html.fromHtml(hotPointDetailOutput.message, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                try {
                    return new BitmapDrawable(Picasso.with(HotPointDetailActivity.this).load(source).get());
                }catch (Exception e){
                    return null;
                }

            }
        }, new Html.TagHandler() {
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

            }
        }));
    }

    @Override
    public void showError(String msg) {
        layoutParent.refreshFinish(PullToRefreshLayout.FAIL);
        layoutParent.loadmoreFinish(PullToRefreshLayout.FAIL);
    }

    @Override
    protected void requestData() {

    }

    private void doRequest() {
        HotPointDetailInput hotPointDetailInput = new HotPointDetailInput(id);
        hotPointDetailInput.setShowDialog(false);
        basePresenter.setInput(hotPointDetailInput).load();
    }

    @Override
    protected String currActivityName() {
        return title;
    }


}
