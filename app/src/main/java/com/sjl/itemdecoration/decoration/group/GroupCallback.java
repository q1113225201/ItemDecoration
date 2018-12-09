package com.sjl.itemdecoration.decoration.group;

import android.view.View;

/**
 * GroupCallback
 *
 * @author 林zero
 * @date 2018/11/27
 */
public interface GroupCallback {
    GroupBean getGroupBean(int position);

    View getDividerView(int position);
}
