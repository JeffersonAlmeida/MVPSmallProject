package com.mvpsmallproject.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mvpsmallproject.MVPApplication;
import com.mvpsmallproject.R;
import com.mvpsmallproject.model.Ribot;
import com.mvpsmallproject.presenter.MainPresenter;
import com.mvpsmallproject.remote.Service;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MainPresenter mainPresenter;

    private ProgressBar progressBar;

    @Inject
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MVPApplication) getApplication()).getServiceComponent().inject(this);

        progressBar = (ProgressBar) findViewById(R.id.pb);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mainPresenter = new MainPresenter(service);
        mainPresenter.attachView(this);
        mainPresenter.getRibotsProfiles();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRibotsProfiles(List<Ribot> ribots) {
        mAdapter.loadData(ribots);
    }

    @Override
    public void showEmptyStateRibotsProfiles() {

    }

    @Override
    public void showError() {

    }
}
