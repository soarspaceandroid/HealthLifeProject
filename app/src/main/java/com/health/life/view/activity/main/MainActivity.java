package com.health.life.view.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.health.life.R;
import com.health.life.model.bean.BookKindListBean;
import com.health.life.model.view.BookKindViewInterface;
import com.health.life.presenter.BookKindPresenter;

public class MainActivity extends AppCompatActivity implements BookKindViewInterface {

    private BookKindPresenter bookKindPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookKindPresenter = new BookKindPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        bookKindPresenter.getClassify();
    }

    @Override
    public void updateView(BookKindListBean bookList) {
        Toast.makeText(this,bookList.getStatus(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(String msg) {

    }
}
