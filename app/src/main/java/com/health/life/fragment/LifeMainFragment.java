package com.health.life.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.health.life.R;
import com.health.life.activity.CookActivity;
import com.health.life.activity.HotPointListActivity;
import com.health.life.activity.PicClassActivity;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.utils.Log;
import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class LifeMainFragment extends BaseFragment implements BaseViewInterface<CookClassfyOutput> {


    @Bind(R.id.cell_content_view1)
    FrameLayout cellContentView1;
    @Bind(R.id.hot)
    LinearLayout hot;
    @Bind(R.id.cell_title_view1)
    FrameLayout cellTitleView1;
    @Bind(R.id.folding_cell1)
    FoldingCell foldingCell1;
    @Bind(R.id.cell_content_view2)
    FrameLayout cellContentView2;
    @Bind(R.id.pic)
    LinearLayout pic;
    @Bind(R.id.cell_title_view2)
    FrameLayout cellTitleView2;
    @Bind(R.id.folding_cell2)
    FoldingCell foldingCell2;
    @Bind(R.id.cell_content_view3)
    FrameLayout cellContentView3;
    @Bind(R.id.cook)
    LinearLayout cook;
    @Bind(R.id.cell_title_view3)
    FrameLayout cellTitleView3;
    @Bind(R.id.folding_cell3)
    FoldingCell foldingCell3;
    @Bind(R.id.cell_content_view4)
    FrameLayout cellContentView4;
    @Bind(R.id.food)
    LinearLayout food;
    @Bind(R.id.cell_title_view4)
    FrameLayout cellTitleView4;
    @Bind(R.id.folding_cell4)
    FoldingCell foldingCell4;
    @Bind(R.id.image11)
    ImageView image11;
    @Bind(R.id.button1)
    CardView button1;
    @Bind(R.id.image1)
    ImageView image1;
    @Bind(R.id.image12)
    ImageView image12;
    @Bind(R.id.button2)
    CardView button2;
    @Bind(R.id.image2)
    ImageView image2;
    @Bind(R.id.image13)
    ImageView image13;
    @Bind(R.id.button3)
    CardView button3;
    @Bind(R.id.image3)
    ImageView image3;
    @Bind(R.id.image14)
    ImageView image14;
    @Bind(R.id.button4)
    CardView button4;
    @Bind(R.id.image4)
    ImageView image4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_main_fragment, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        // 显示图片
        Picasso.with(getActivity())
                .load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1461134115&di=a37663e627be6dc4fd1ddbec63a10de0&src=http://www.0730news.com/uploads/allimg/150330/0R542B27-0.png")
//                .placeholder(R.drawable.contact_picture_placeholder)
                .resize(100, 100)
                .tag(this)
                .into(image1);

        Picasso.with(getActivity())
                .load("http://g.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c6633bb7af9f2b21193138a81.jpg")
//                .placeholder(R.drawable.contact_picture_placeholder)
                .resize(100, 100)
                .tag(this)
                .into(image2);

        Picasso.with(getActivity())
                .load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1461134445&di=e1179a1e7db4c2cddcc4e5fb51570a27&src=http://imgsrc.baidu.com/baike/pic/item/969cbf4405dce9ceb3b7dc12.jpg")
//                .placeholder(R.drawable.contact_picture_placeholder)
                .resize(100, 100)
                .tag(this)
                .into(image3);

        Picasso.with(getActivity())
                .load("http://picapi.ooopic.com/10/61/66/22b1OOOPICcd.jpg")
//                .placeholder(R.drawable.contact_picture_placeholder)
                .resize(100, 100)
                .tag(this)
                .into(image4);


        Picasso.with(getActivity())
                .load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1461134115&di=a37663e627be6dc4fd1ddbec63a10de0&src=http://www.0730news.com/uploads/allimg/150330/0R542B27-0.png")
//                .placeholder(R.drawable.contact_picture_placeholder)
                .tag(this)
                .into(image11);

        Picasso.with(getActivity())
                .load("http://g.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c6633bb7af9f2b21193138a81.jpg")
//                .placeholder(R.drawable.contact_picture_placeholder)
                .tag(this)
                .into(image12);

        Picasso.with(getActivity())
                .load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1461134445&di=e1179a1e7db4c2cddcc4e5fb51570a27&src=http://imgsrc.baidu.com/baike/pic/item/969cbf4405dce9ceb3b7dc12.jpg")
//                .placeholder(R.drawable.contact_picture_placeholder)
                .tag(this)
                .into(image13);

        Picasso.with(getActivity())
                .load("http://picapi.ooopic.com/10/61/66/22b1OOOPICcd.jpg")
//                .placeholder(R.drawable.contact_picture_placeholder)
                .tag(this)
                .into(image14);

        foldingCell1.initialize(1000, Color.TRANSPARENT, 3);
        foldingCell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell1.toggle(false);
            }
        });
        foldingCell2.initialize(1000, Color.TRANSPARENT, 3);
        foldingCell2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell2.toggle(false);
            }
        });

        foldingCell3.initialize(1000, Color.TRANSPARENT, 3);
        foldingCell3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell3.toggle(false);
            }
        });

        foldingCell4.initialize(1000, Color.TRANSPARENT, 3);
        foldingCell4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell4.toggle(false);
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotPointListActivity.showActivity(getActivity());
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicClassActivity.showActivity(getActivity());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookActivity.showActivity(getActivity());
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("you click 4");
            }
        });


    }


    @Override
    public void updateView(CookClassfyOutput cookClassfyOutput) {

    }

    @Override
    protected void requestData() {

    }

    @Override
    public void showError(String msg) {

    }


    @Override
    protected String currentTitle() {
        return "生活";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
