package com.jssj.jiangye.module.detail;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.data.StartsHotData;
import com.jssj.jiangye.module.starts.HotInformationFragment;
import com.jssj.jiangye.module.starts.HotPictureFragment;
import com.jssj.jiangye.utils.ImageLoaderUtils;
import com.jssj.jiangye.widget.NormalTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cc on 17-6-12.
 */
public class StarHotDetailActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.title)
    NormalTitleBar mTitle;
    @BindView(R.id.itemiv_hot_star)
    ImageView      mItemivHotStar;
    @BindView(R.id.star_hot_name)
    TextView       mStarHotName;
    @BindView(R.id.star_hot_charm_num)
    TextView       mStarHotCharmNum;
    @BindView(R.id.item_hot_star_desc)
    LinearLayout mItemHotStarDesc;
    @BindView(R.id.star_top_desc)
    TextView     mStarTopDesc;
    @BindView(R.id.toolbar)
    Toolbar      mToolbar;
    @BindView(R.id.iv_title_menu)
    TextView     mIvTitleMenu;
    @BindView(R.id.rb_information)
    RadioButton  mRbInformation;
    @BindView(R.id.rb_charm)
    RadioButton  mRbCharm;
    @BindView(R.id.tab_bar)
    RadioGroup   mTabBar;
    @BindView(R.id.tv_title)
    AppBarLayout mTvTitle;
    @BindView(R.id.view_pager)
    ViewPager   mViewpager;
    private List<Fragment> fragmentList;

    private StartsHotData data;
    @Override
    public int getLayoutId() {
        return R.layout.activity_starhot;
    }

    @Override
    public void initView() {
         data= getIntent().getParcelableExtra(ApiConstants.TYPE_CHARM_STAR);
        if (data != null) {
            ImageLoaderUtils.display(this,mItemivHotStar,data.getStarPhotoUrl());
            mStarHotName.setText(data.getStarName());
            mStarHotCharmNum.setText("魅力值："+data.getCharmValues());
            mStarTopDesc.setText(Html.fromHtml(data.getStarDesc()));
            mTitle.setTitleText(data.getStarName());
        }
        mRbCharm.setText("图片");
        fragmentList = new ArrayList<>();

        fragmentList.add(new HotInformationFragment());
        fragmentList.add(new HotPictureFragment());
        mViewpager.setOffscreenPageLimit(fragmentList.size());
        mViewpager.setAdapter(new TabAdapter(getSupportFragmentManager()));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
