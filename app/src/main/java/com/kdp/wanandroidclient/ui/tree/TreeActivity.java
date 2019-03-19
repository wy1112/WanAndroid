package com.kdp.wanandroidclient.ui.tree;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.kdp.wanandroidclient.R;
import com.kdp.wanandroidclient.bean.TreeBean;
import com.kdp.wanandroidclient.common.Const;
import com.kdp.wanandroidclient.ui.adapter.TreeFragPageAdapter;
import com.kdp.wanandroidclient.ui.base.BaseActivity;
import com.kdp.wanandroidclient.ui.base.BaseTabActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识体系二级分类
 * author: 康栋普
 * date: 2018/3/20
 */

public class TreeActivity extends BaseTabActivity {
    private String mTitle;
    private List<TreeBean.ChildrenBean> mTreeDatas = new ArrayList<>();
    private int mAction, mChapterId;
    private String mChapterName;
    @Override
    protected boolean initToolbar() {
        mToolbar.setTitle(mTitle);
        return true;
    }

    @Override
    protected void getIntent(Intent intent) {
        mAction = intent.getIntExtra(Const.BUNDLE_KEY.INTENT_ACTION_TYPE, 0);
        if (mAction == Const.BUNDLE_KEY.INTENT_ACTION_TREE) {
            Bundle bundle = intent.getExtras();
            TreeBean mTreeBean = null;
            if (bundle != null) {
                mTreeBean = (TreeBean) bundle.getSerializable(Const.BUNDLE_KEY.OBJ);
            }
            if (mTreeBean != null) {
                mTitle = mTreeBean.getName();
                mTreeDatas = mTreeBean.getChildren();
            }
        } else {
            mChapterId = intent.getIntExtra(Const.BUNDLE_KEY.CHAPTER_ID, 0);
            mChapterName = intent.getStringExtra(Const.BUNDLE_KEY.CHAPTER_NAME);
            mTitle = mChapterName;
        }


    }

    @Override
    protected FragmentPagerAdapter createFragPagerAdapter() {
        TreeFragPageAdapter mAdapter;
        if (mAction == Const.BUNDLE_KEY.INTENT_ACTION_TREE) {
            mAdapter = new TreeFragPageAdapter(getSupportFragmentManager(), mAction, mTreeDatas);
        } else {
            mAdapter = new TreeFragPageAdapter(getSupportFragmentManager(), mAction, mChapterId, mChapterName);
        }
        return mAdapter;
    }

}
