package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.health.life.R;
import com.health.life.adapter.CookListAdapter;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.input.CookClassifyListInfoInput;
import com.health.life.model.bean.output.CookClassifyListInfoOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligang967 on 16/2/23.
 */
public class CookDisplayFragment extends BaseFragment implements BaseViewInterface<CookClassifyListInfoOutput>{

    private View self;

    private static Map<String, CookDisplayFragment> instance;

    private int id;

    private String title;

    private ListView listView;

    private CookClassifyListInfoInput infoInput;

    private BasePresenter basePresenter;


    public static CookDisplayFragment getInstance(String title,int id) {
        if (instance == null) {
            instance = new HashMap<String, CookDisplayFragment>();
        }

        return getFragmentByTitle(title,id);
    }

    private static CookDisplayFragment getFragmentByTitle(String title,int id) {

        CookDisplayFragment firstFragment = instance.get(title);

        if (firstFragment == null) {
            firstFragment = new CookDisplayFragment();

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
        Log.e("soar" , "request id "+id);
        infoInput = new CookClassifyListInfoInput(1,20,id);
        infoInput.setShowDialog(false);
        basePresenter=new BasePresenter().setBaseViewInterface(this).setRequestListener(this);

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

        listView = (ListView)this.self.findViewById(R.id.list_view);


        return this.self;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void updateView(CookClassifyListInfoOutput cookClassifyListInfoOutput) {
        Log.e("soar" , "updat ---- "+cookClassifyListInfoOutput.tngou.get(0).food);
        listView.setAdapter(new CookListAdapter(cookClassifyListInfoOutput.tngou , getActivity()));
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void requestData() {



        basePresenter.setInput(infoInput).load();
    }
}
