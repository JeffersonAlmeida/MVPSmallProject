package com.mvpsmallproject.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mvpsmallproject.R;

public class RibotDetailActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context){
        return new Intent(context, RibotDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ribot_detail);
    }
}
