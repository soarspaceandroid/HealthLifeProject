package com.health.life.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.life.R;
import com.health.life.adapter.BaseAdapterTools.BaseAdapterHelper;
import com.health.life.adapter.BaseAdapterTools.QuickAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.CookDetailInput;
import com.health.life.model.bean.output.CookDetailOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.utils.Config;
import com.health.life.view.AppBar;
import com.health.life.view.pulltorefresh.PullToRefreshLayout;
import com.health.life.view.pulltorefresh.PullableListView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaofei on 2016/4/19.
 */
public class CookInfoDetailActivity extends BaseActivity implements BaseViewInterface<CookDetailOutput> {

    public final static String COOK_NAME = "cook_name";
    public final static String IMAGE_PATH = "image_path";
    @Bind(R.id.listview)
    PullableListView listview;
    @Bind(R.id.layout_parent)
    PullToRefreshLayout layoutParent;
    private String name;
    private String imagePath;
    private BasePresenter basePresenter;


    private CookDetailOutput cookDetailOutput;

    public static void showActivity(Activity classActivity, String name, View shareView, String path) {
        Intent intent = new Intent(classActivity, CookInfoDetailActivity.class);
        intent.putExtra(COOK_NAME, name);
        intent.putExtra(IMAGE_PATH, path);
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(classActivity, shareView, "shareview");
            classActivity.startActivity(intent, options.toBundle());
        } else {
            classActivity.startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.activity_transition));
        }
        name = getIntent().getStringExtra(COOK_NAME);
        imagePath = getIntent().getStringExtra(IMAGE_PATH);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);
        ButterKnife.bind(this);
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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FoodDetailActivity.showActivity(CookInfoDetailActivity.this, cookDetailOutput.tngou.get(position).name, view.findViewById(R.id.image), cookDetailOutput.tngou.get(position).id, cookDetailOutput.tngou.get(position).img);
            }
        });
        layoutParent.autoRefresh();
    }

    @Override
    protected String currActivityName() {
        return name;
    }


    @Override
    public void controlAppBar(AppBar appbar) {
        View view = LayoutInflater.from(this).inflate(R.layout.bar_middle_img_text, null);
        ImageView image = (ImageView) view.findViewById(R.id.image_title);
        Picasso.with(this)
                .load(Config.BASE_IMAGE_URL + imagePath)
//                .placeholder(R.drawable.contact_picture_placeholder)
                .resize(50, 50)
                .tag(this)
                .into(image);
        TextView title = (TextView) view.findViewById(R.id.title_text);
        title.setText(name);
        appbar.setMiddleCustom(view);
        super.controlAppBar(appbar);
    }

    @Override
    protected void requestData() {

    }

    private void doRequest() {
        CookDetailInput cookDetailInput = new CookDetailInput(name);
        cookDetailInput.setShowDialog(false);
        basePresenter.setInput(cookDetailInput).load();
    }

    @Override
    public void updateView(CookDetailOutput cookDetailOutput) {
        // 使用quickAdapter
        layoutParent.refreshFinish(PullToRefreshLayout.SUCCEED);
        layoutParent.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        this.cookDetailOutput = cookDetailOutput;
        listview.setAdapter(new QuickAdapter<CookDetailOutput.TngouEntity>(this, R.layout.cook_info_list_adapter, cookDetailOutput.tngou) {
            @Override
            protected void convert(BaseAdapterHelper helper, CookDetailOutput.TngouEntity item) {
                CardView cardView = ((CardView) helper.getView(R.id.card_parent));
                if(Build.VERSION.SDK_INT >= 21) {
                    cardView.setElevation(15);
                }
                helper.setText(R.id.des, item.description);
                helper.setText(R.id.name, item.name);
                Picasso.with(context)
                        .load(Config.BASE_IMAGE_URL + item.img)
//                .placeholder(R.drawable.contact_picture_placeholder)
                        .resize(100, 100)
                        .tag(context)
                        .into((ImageView) helper.getView(R.id.image));
            }

        });

    }

    @Override
    public void showError(String msg) {
        layoutParent.refreshFinish(PullToRefreshLayout.FAIL);
        layoutParent.loadmoreFinish(PullToRefreshLayout.FAIL);
    }
}
