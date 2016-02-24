package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.health.life.R;
import com.health.life.adapter.BookListAdapter;
import com.health.life.base.BaseFragment;
import com.health.life.interfaces.DoRequest;
import com.health.life.model.bean.BookListInfo;
import com.health.life.model.enity.BookListEnity;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by ligang967 on 16/2/23.
 */
public class FirstFragment extends BaseFragment implements BaseViewInterface<BookListInfo>{

    private View self;

    private static Map<String, FirstFragment> instance;

    private int id;

    private String title;

    private BasePresenter  basePresenter;

    private ListView listView;


    public static FirstFragment getInstance(String title,int id) {
        if (instance == null) {
            instance = new HashMap<String, FirstFragment>();
        }

        return getFragmentByTitle(title,id);
    }

    private static FirstFragment getFragmentByTitle(String title,int id) {

        FirstFragment firstFragment = instance.get(title);

        if (firstFragment == null) {
            firstFragment = new FirstFragment();

            Bundle bundle = new Bundle();

            bundle.putString("title",title);

            bundle.putInt("id",id);

            firstFragment.setArguments(bundle);

            instance.put(title, firstFragment);
        }

        return firstFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getInt("id");

        basePresenter = new BasePresenter(this , this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        instance.remove(title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(R.layout.first_fragment, null);
        }

        listView = (ListView)this.self.findViewById(R.id.list_view);


        return this.self;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.basePresenter.getRequestResult(BookListEnity.class, new DoRequest<BookListInfo>() {
            @Override
            public Observable<BookListInfo> doRequest(Object t) {
                return ((BookListEnity)t).getListById(id , 10 , 1);
            }
        });
    }

    @Override
    public void updateView(BookListInfo bookListInfo) {
        listView.setAdapter(new BookListAdapter(bookListInfo.getTngou(),getActivity()));
    }

    @Override
    public void showError(String msg) {

    }
}
