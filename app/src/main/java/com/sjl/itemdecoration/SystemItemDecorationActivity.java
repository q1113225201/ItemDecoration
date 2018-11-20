package com.sjl.itemdecoration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sjl.itemdecoration.adapter.StringAdapter;
import com.sjl.itemdecoration.util.Util;

import java.util.List;

public class SystemItemDecorationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_item_decoration);

        initView();
    }

    private void initView() {
        //纵向
        StringAdapter adapterVertical = new StringAdapter(Util.buildList());
        RecyclerView recyclerViewVertical = findViewById(R.id.recycler_view_vertical);
        recyclerViewVertical.setAdapter(adapterVertical);
        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //设置间隔图片
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.bg_black));
        recyclerViewVertical.addItemDecoration(dividerItemDecoration);

        //横向
        StringAdapter adapterHorizontal = new StringAdapter(Util.buildList());
        RecyclerView recyclerViewHorizontal = findViewById(R.id.recycler_view_horizontal);
        recyclerViewHorizontal.setAdapter(adapterHorizontal);
        recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHorizontal.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }
}
