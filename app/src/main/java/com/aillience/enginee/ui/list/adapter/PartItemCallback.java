package com.aillience.enginee.ui.list.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

/**
 * ===============================
 * 千万不要出现 B U G ，出现就 G G
 * THE BEST CODE IS NO CODE
 * ===============================
 *
 * @author:yfl
 * @date: 2019-12-27
 * @description: 就是一个普通类
 * @lastUpdateTime 2019-12-27
 * #更新内容
 * ===============================
 **/
public class PartItemCallback extends DiffUtil.ItemCallback<String> {
    /**
     * Called to check whether two objects represent the same item.
     * <p>
     * For example, if your items have unique ids, this method should check their id equality.
     *
     * @param oldItem The item in the old list.
     * @param newItem The item in the new list.
     * @return True if the two items represent the same object or false if they are different.
     */
    @Override
    public boolean areItemsTheSame(String oldItem, String newItem) {
        return TextUtils.equals(oldItem,newItem);
    }

    /**
     * Called to check whether two items have the same data.
     * <p>
     * This information is used to detect if the contents of an item have changed.
     * <p>
     * This method to check equality instead of {@link Object#equals(Object)} so that you can
     * change its behavior depending on your UI.
     * <p>
     * For example, if you are using DiffUtil with a
     * {@link RecyclerView.Adapter RecyclerView.Adapter}, you should
     * return whether the items' visual representations are the same.
     * <p>
     * these items.
     *
     * @param oldItem The item in the old list.
     * @param newItem The item in the new list.
     * @return True if the contents of the items are the same or false if they are different.
     */
    @Override
    public boolean areContentsTheSame(String oldItem, String newItem) {
        return TextUtils.equals(oldItem,newItem);
    }
}
