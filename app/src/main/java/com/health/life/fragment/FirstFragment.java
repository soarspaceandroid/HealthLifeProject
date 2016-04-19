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
import com.health.life.model.bean.input.BookListInfoInput;
import com.health.life.model.bean.output.BookListInfoOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class FirstFragment extends BaseFragment implements BaseViewInterface<BookListInfoOutput> {

    @Bind(R.id.list_view)
    ListView listView;
    private View self;

    private static Map<String, FirstFragment> instance;

    private int id;

    private String title;


    private BookListInfoInput infoInput;

    private BasePresenter basePresenter;


    public static FirstFragment getInstance(String title, int id) {
        if (instance == null) {
            instance = new HashMap<String, FirstFragment>();
        }

        return getFragmentByTitle(title, id);
    }

    private static FirstFragment getFragmentByTitle(String title, int id) {

        FirstFragment firstFragment = instance.get(title);

        if (firstFragment == null) {
            firstFragment = new FirstFragment();

            Bundle bundle = new Bundle();

            bundle.putString("title", title);

            bundle.putInt("id", id);

            firstFragment.setArguments(bundle);

            instance.put(title, firstFragment);
        }

        return firstFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getInt("id");


        infoInput = new BookListInfoInput(1, 20, id);

        infoInput.setShowDialog(false);

        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);

    }

    @Override
    protected String currentTitle() {
        return "";
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
        ButterKnife.bind(this, this.self);
        return this.self;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void updateView(BookListInfoOutput bookListInfo) {
        listView.setAdapter(new BookListAdapter(bookListInfo.getTngou(), getActivity()));
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void requestData() {


        basePresenter.setInput(infoInput).load();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
