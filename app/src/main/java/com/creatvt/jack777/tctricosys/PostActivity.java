package com.creatvt.jack777.tctricosys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_post );
    }

    public void Add(View view) {
        Intent intent = new Intent(PostActivity.this,ProductDetailsActivity.class);
        startActivity(intent);
    }
}
