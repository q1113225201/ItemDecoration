package com.sjl.itemdecoration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_system).setOnClickListener(this);
        findViewById(R.id.btn_grid).setOnClickListener(this);
        findViewById(R.id.btn_group).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_system:
                startActivity(new Intent(this,LinearItemDecorationActivity.class));
                break;
            case R.id.btn_grid:
                startActivity(new Intent(this,GridItemDecorationActivity.class));
                break;
            case R.id.btn_group:
                startActivity(new Intent(this,GridItemDecorationActivity.class));
                break;
        }
    }
}
