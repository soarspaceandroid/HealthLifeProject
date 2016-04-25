package com.health.life.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.health.life.R;
import com.health.life.base.BaseActivity;
import com.health.life.interfaces.CommentPostListener;
import com.health.life.interfaces.RequestListener;
import com.health.life.model.bean.input.CommentInput;
import com.health.life.model.bean.input.FoodDetailInput;
import com.health.life.model.bean.output.CommentOutput;
import com.health.life.model.bean.output.FoodDetailOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.utils.Config;
import com.health.life.utils.PopWindowUIUtils;
import com.health.life.view.pulltorefresh.PullToRefreshLayout;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaofei on 2016/4/19.
 */
public class FoodDetailActivity extends BaseActivity implements BaseViewInterface<FoodDetailOutput> {

    public final static String COOK_NAME = "cook_name";
    public final static String FOOD_ID = "food_id";
    public final static String IMAGE_PATH = "image_path";
    @Bind(R.id.food_image)
    ImageView foodImage;
    @Bind(R.id.food_name)
    TextView foodName;
    @Bind(R.id.food_message)
    TextView foodMessage;
    @Bind(R.id.layout_parent)
    PullToRefreshLayout layoutParent;
    private String name;
    private String path;
    private int foodId;
    private BasePresenter basePresenter;

    private FoodDetailOutput foodDetailOutput;

    public static void showActivity(Activity classActivity, String name, View shareView, int id, String path) {
        Intent intent = new Intent(classActivity, FoodDetailActivity.class);
        intent.putExtra(COOK_NAME, name);
        intent.putExtra(FOOD_ID, id);
        intent.putExtra(IMAGE_PATH, path);
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(classActivity, shareView, "shareview1");
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
        foodId = getIntent().getIntExtra(FOOD_ID, 0);
        path = getIntent().getStringExtra(IMAGE_PATH);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);
        controlMenu(true);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) foodImage.getLayoutParams();
        params.height = getResources().getDisplayMetrics().widthPixels * 4 / 5;
        foodImage.setLayoutParams(params);
        Picasso.with(this)
                .load(Config.BASE_IMAGE_URL + path)
//                .placeholder(R.drawable.contact_picture_placeholder)
                .tag(this)
                .into(foodImage);

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
    protected String currActivityName() {
        return name;
    }


    @Override
    protected void requestData() {

    }

    private void doRequest() {
        FoodDetailInput foodDetailInput = new FoodDetailInput(foodId);
        foodDetailInput.setShowDialog(false);
        basePresenter.setInput(foodDetailInput).load();
    }

    @Override
    public void updateView(FoodDetailOutput foodDetailOutput) {
        this.foodDetailOutput = foodDetailOutput;
        // 使用quickAdapter
        layoutParent.refreshFinish(PullToRefreshLayout.SUCCEED);
        layoutParent.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        foodName.setText(foodDetailOutput.name);
        foodMessage.setText(Html.fromHtml(foodDetailOutput.message));
    }

    @Override
    public void showError(String msg) {
        layoutParent.refreshFinish(PullToRefreshLayout.FAIL);
        layoutParent.loadmoreFinish(PullToRefreshLayout.FAIL);
    }

    @Override
    public void commentRequest() {
        if(this.foodDetailOutput == null){
            return;
        }
        PopWindowUIUtils.commentPop(this, foodDetailOutput.name, new CommentPostListener() {
            @Override
            public void submit(String comment) {
                basePresenter.setBaseViewInterface(new BaseViewInterface<CommentOutput>() {
                    @Override
                    public void updateView(CommentOutput o) {
                        Toast.makeText(FoodDetailActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void showError(String msg) {

                    }
                }).setRequestListener(new RequestListener() {
                    @Override
                    public void showProgressDialog() {
                        showLoadDialog(FoodDetailActivity.this);
                    }

                    @Override
                    public void hideProgressDialog() {

                        FoodDetailActivity.this.hideProgressDialog();
                    }

                    @Override
                    public void errorDisplay(String errorMsg) {

                    }

                    @Override
                    public void errorHide() {

                    }
                }).setInput(new CommentInput(share.getValueString(Config.LOGIN_KEY_TOKEN, ""), foodDetailOutput.id, "cook", foodDetailOutput.name, comment)).load();
            }
        });
    }
}
