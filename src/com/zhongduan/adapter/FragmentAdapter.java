package com.zhongduan.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
	/**
	 * ���е�Fragment�ļ���
	 */
	private List<Fragment> list;

	public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list = list;
	}
	/**
	 * ���ر�ѡ�е�Fragment
	 */
	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}
	/**
	 * ����Fragment�ĳ���
	 */
	@Override
	public int getCount() {
		return list.size();
	}

}
