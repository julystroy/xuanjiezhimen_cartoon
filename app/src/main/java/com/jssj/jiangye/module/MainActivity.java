package com.jssj.jiangye.module;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jssj.jiangye.module.manager.AppManager;
import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.module.main.ActionFragment;
import com.jssj.jiangye.module.main.BookFragment;
import com.jssj.jiangye.module.main.HomeFragment;
import com.jssj.jiangye.module.main.MineFragment;
import com.jssj.jiangye.module.main.StarsFragment;
import com.jssj.jiangye.widget.swipbackhelper.SwipeBackHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.viewpager)
    ViewPager      mViewpager;
    @BindView(R.id.rb_home)
    RadioButton    mRbHome;
    @BindView(R.id.rb_activity)
    RadioButton    mRbActivity;
    @BindView(R.id.iv_activity_message)
    ImageView      mIvActivityMessage;
    @BindView(R.id.rl_activity)
    RelativeLayout mRlActivity;
    @BindView(R.id.rb_book)
    RadioButton    mRbBook;
    @BindView(R.id.iv_book_message)
    ImageView      mIvBookMessage;
    @BindView(R.id.rl_book)
    RelativeLayout mRlBook;
    @BindView(R.id.iv_new_message)
    ImageView      mIvNewMessage;
    @BindView(R.id.rb_mine)
    RadioButton    mRbMine;
    @BindView(R.id.rl_mine)
    RelativeLayout mRlMine;
    @BindView(R.id.tab_bar)
    RadioGroup     mTabBar;

    public List<Fragment> fragmentList;
    public List<String>   titleList;
    public static final String FOLAT           = "hide_show";
    public static final int  SHOW           = 0;
    public static final int  HIDE           = 1;


    @Override
    public int getLayoutId() {return R.layout.activity_main;}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
    }

    @Override
    public void initView() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("主页");
        titleList.add("星影");
        titleList.add("书友圈");
        titleList.add("活动");
        titleList.add("我的");

        fragmentList.add(new HomeFragment());
        fragmentList.add(new StarsFragment());
        fragmentList.add(new BookFragment());
        fragmentList.add(new ActionFragment());
        fragmentList.add(new MineFragment());

        mViewpager.setOffscreenPageLimit(fragmentList.size());
        mViewpager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mTabBar.setOnCheckedChangeListener(this);
        mTabBar.check(R.id.rb_home);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTabBar.check(R.id.rb_home);
                        break;
                    case 1:
                        mTabBar.check(R.id.rb_starts);
                        break;

                    case 2:
                        mTabBar.check(R.id.rb_book);
                        break;
                    case 3:
                        mTabBar.check(R.id.rb_activity);
                        break;
                    case 4:
                        mTabBar.check(R.id.rb_mine);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.rl_activity, R.id.rb_activity})
    public void onClickActivity() {
        mViewpager.setCurrentItem(3);
    }

    @OnClick({R.id.rl_mine, R.id.rb_mine})
    public void onClickMine() {
        mViewpager.setCurrentItem(4);
    }

    @OnClick({R.id.rb_book, R.id.rl_book})
    public void onClickBook() {
        mViewpager.setCurrentItem(2);
    }
    @OnClick({R.id.rb_starts})
    public void onClickStarts() {
        mViewpager.setCurrentItem(1);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_home:
                mViewpager.setCurrentItem(0);
                break;
            case R.id.rb_starts:
            mViewpager.setCurrentItem(1);
                break;
            case R.id.rb_book:
                mViewpager.setCurrentItem(2);
                break;
            case R.id.rb_activity:
            case R.id.rl_activity:
            mViewpager.setCurrentItem(3);
            break;
            case R.id.rl_mine:
            case R.id.rb_mine:
                mViewpager.setCurrentItem(4);
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


    /*//使用eventbus
    @Override
    protected boolean useEventBus() {
        return true;
    }*/


   /* *//**
     * 菜单显示隐藏动画
     *Eventbus通知
     * @param message
     *//*
    @Subscriber(tag = FOLAT)
    public void onReceive(Message message) {
        switch (message.what) {
            case SHOW:
                MyUtils.show0rHide(mFab,true);
                break;
            case HIDE:
                MyUtils.show0rHide(mFab,false);
                break;
        }
    }*/



    // 双击退出功能实现
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(false);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().AppExit(this,false);
    }
}
