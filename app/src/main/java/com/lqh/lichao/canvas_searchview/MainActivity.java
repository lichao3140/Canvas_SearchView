package com.lqh.lichao.canvas_searchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MySearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchView = (MySearchView)findViewById(R.id.sv);
        mSearchView.setController(new Controller());
    }

    public void start(View v){
        mSearchView.startAnimation();
    }
    public void reset(View v){
        mSearchView.resetAnimation();
    }
}
