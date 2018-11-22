package com.sjl.itemdecoration.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjl.itemdecoration.R;

import java.util.List;

/**
 * StringAdapter
 *
 * @author 林zero
 * @date 2018/11/20
 */
public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {
    private int res;
    private List<String> list;

    public StringAdapter(int res,List<String> list) {
        this.res = res;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(res, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvItemName.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
