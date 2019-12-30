package com.yfl.library.listeners;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * ===============================
 * 千万不要出现 B U G ，出现就 G G
 * THE BEST CODE IS NO CODE
 * ===============================
 *
 * @author: yfl
 * @date: 2019-12-25
 * @description: 就是一个普通类
 * @lastUpdateTime 2019-12-25
 * #更新内容
 * ===============================
 **/
public class DiffCallBack<T> extends DiffUtil.Callback{

    private List<T> mOldDatas, mNewDatas;

    public DiffCallBack(List<T> mOldData, List<T> mNewData) {
        this.mOldDatas = mOldData;
        this.mNewDatas = mNewData;
    }
    /**
     * Returns the size of the old list.
     *
     * @return The size of the old list.
     */
    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    /**
     * Returns the size of the new list.
     *
     * @return The size of the new list.
     */
    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }

    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     *  被DiffUtil调用，用来判断 两个对象是否是相同的Item。
     * For example, if your items have unique ids, this method should check their id equality.
     *  例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等。
     *  本例判断name字段是否一致
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return True if the two items represent the same object or false if they are different.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    /**
     * Called by the DiffUtil when it wants to check whether two items have the same data.
     * DiffUtil uses this information to detect if the contents of an item has changed.
     * <p>
     * DiffUtil uses this method to check equality instead of {@link Object#equals(Object)}
     * so that you can change its behavior depending on your UI.
     * For example, if you are using DiffUtil with a
     * {@link RecyclerView.Adapter RecyclerView.Adapter}, you should
     * return whether the items' visual representations are the same.
     * <p>
     * This method is called only if {@link #areItemsTheSame(int, int)} returns
     * {@code true} for these items.
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list which replaces the
     *                        oldItem
     * @return True if the contents of the items are the same or false if they are different.
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
