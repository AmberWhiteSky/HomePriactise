package com.zhongduan.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
	/**
	 * 所有的Fragment的集合
	 */
	private List<Fragment> list;

	public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list = list;
	}
	/**
	 * 返回被选中的Fragment
	 */
	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}
	/**
	 * 返回Fragment的长度
	 */
	@Override
	public int getCount() {
		return list.size();
	}

}
