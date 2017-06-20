package com.jssj.jiangye.module.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseFragment;
import com.jssj.jiangye.module.starts.CharmFragment;
import com.jssj.jiangye.module.starts.InformationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cc on 17-5-23.
 */
public class StarsFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.iv_title_menu)
    TextView    mIvTitleMenu;
    @BindView(R.id.rb_information)
    RadioButton mRbInformation;
    @BindView(R.id.rb_charm)
    RadioButton mRbCharm;
    @BindView(R.id.tab_bar)
    RadioGroup  mTabBar;
    @BindView(R.id.toolbar)
    Toolbar     mToolbar;
    @BindView(R.id.view_pager)
    ViewPager   mViewpager;
    public List<Fragment> fragmentList;
    public List<String>   titleList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_starts;
    }

    @Override
    protected void initView() {
        mIvTitleMenu.setVisibility(View.VISIBLE);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("咨询");
        titleList.add("魅力榜");
        fragmentList.add(new InformationFragment());
        fragmentList.add(new CharmFragment());
        mViewpager.setOffscreenPageLimit(fragmentList.size());
        mViewpager.setAdapter(new TabAdapter(getFragmentManager()));
        mTabBar.setOnCheckedChangeListener(this);
        mTabBar.check(R.id.rb_information);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        mTabBar.check(R.id.rb_information);
                        break;
                    case 1:
                        mTabBar.check(R.id.rb_charm);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        switch (i) {
            case 0:
                mViewpager.setCurrentItem(0);
                break;
            case 1:
                mViewpager.setCurrentItem(1);
                break;
        }
    }

    public class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    @OnClick(R.id.rb_information)
    public void clickone(){ mViewpager.setCurrentItem(0);}

    @OnClick(R.id.rb_charm)
    public void clicktwo(){ mViewpager.setCurrentItem(1);}

}

