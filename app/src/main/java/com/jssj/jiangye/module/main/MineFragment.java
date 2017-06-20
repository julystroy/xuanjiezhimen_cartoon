package com.jssj.jiangye.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.BuildConfig;
import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseFragment;
import com.jssj.jiangye.data.AppVersion;
import com.jssj.jiangye.module.main.mine.MinePresenterImpl;
import com.jssj.jiangye.module.main.mine.MineView;
import com.jssj.jiangye.module.main.mine.PackageActivity;
import com.jssj.jiangye.module.main.mine.StoreActivity;
import com.jssj.jiangye.module.user.UserInfoEditActivity;
import com.jssj.jiangye.utils.ImageLoaderUtils;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.utils.UIutils;

import butterknife.BindView;

/**
 * Created by cc on 17-5-15.
 */
public class MineFragment extends BaseFragment implements  MineView, View.OnClickListener {
    @BindView(R.id.tvtitle_left)
    TextView     mTvtitleLeft;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tvtitle_right)
    TextView     mTvtitleRight;
    @BindView(R.id.ll_container_1)
    LinearLayout llContainer1;
    @BindView(R.id.ll_container_2)
    LinearLayout llContainer2;
    @BindView(R.id.edit_userinfo)
    TextView     mEditUserinfo;
    @BindView(R.id.iv_mine_user_icon)
    ImageView    mIvMineUserIcon;
    @BindView(R.id.btn_mine_login)
    Button       mBtnMineLogin;
    @BindView(R.id.tv_mine_user_name)
    TextView     mTvMineUserName;
    @BindView(R.id.tv_mine_user_bonuspoint)
    TextView     mTvMineUserBonuspoint;
    @BindView(R.id.tv_mine_user_stonenum)
    TextView     mTvMineUserStonenum;
    @BindView(R.id.tv_mine_user_action_info)
    TextView     mTvMineUserActionInfo;
    @BindView(R.id.tv_state_count)
    TextView     mTvStateCount;
    @BindView(R.id.ll_state)
    LinearLayout mLlState;
    @BindView(R.id.tv_package_count)
    TextView     mTvPackageCount;
    @BindView(R.id.ll_package)
    LinearLayout mLlPackage;
    @BindView(R.id.tv_task_count)
    TextView     mTvTaskCount;
    @BindView(R.id.ll_task)
    LinearLayout mLlTask;
    @BindView(R.id.tv_charts_count)
    TextView     mTvChartsCount;
    @BindView(R.id.ll_sort)
    LinearLayout mLlSort;
    @BindView(R.id.ll_item)
    LinearLayout mLlItem;
    @BindView(R.id.tv_logout)
    TextView     mTvLogout;
    @BindView(R.id.ll_logout)
    LinearLayout mLlLogout;
    private TextView  tvCacheSize;
    private TextView  textPoint;
    private ImageView ivNewMessage;
    private MinePresenterImpl minePresenter;

    private boolean isTips = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         minePresenter = new MinePresenterImpl(this, isTips);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_mine;
    }

    @Override
    protected void initView() {
        mTvtitleLeft.setText(R.string.big_event);
        mTvTitle.setText(R.string.tab_mine);
        mTvtitleRight.setText(R.string.store);
        mTvtitleRight.setOnClickListener(this);
        mLlPackage.setOnClickListener(this);
        mTvLogout.setOnClickListener(this);
        mEditUserinfo.setOnClickListener(this);
        mBtnMineLogin.setOnClickListener(this);
        ImageLoaderUtils.displayRound(mActivity,mIvMineUserIcon,"");
       // UIutils.startImageActivity(mActivity,0,);
        setupView();
    }

    private void setupView() {
        int[] titleArr1, titleArr2;
        int[] imgId1, imgId2;
        titleArr1 = new int[]{
                R.string.my_message,
                R.string.my_download,
        };
        titleArr2 = new int[]{
                R.string.user_feedback,
                R.string.clear_cache,
                R.string.my_upadte};

        imgId1 = new int[]{
                R.mipmap.icon_mine_message,
                R.mipmap.icon_mine_download,
        };
        imgId2 = new int[]{
                R.mipmap.icon_mine_feedback,
                R.mipmap.icon_mine_cache,
                R.mipmap.icon_mine_update};

        for (int i = 0; i < imgId1.length; i++) {
            View subView = buildItemView(titleArr1[i], imgId1, i);
            llContainer1.addView(subView);
        }
        for (int i = 0; i < imgId2.length; i++) {
            View subView = buildItemView(titleArr2[i], imgId2, i);
            llContainer2.addView(subView);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        setUpUseinfo();
    }

    //登录状态
    private void setUpUseinfo() {

        if (isLogin()) {
            mBtnMineLogin.setVisibility(View.GONE);
            mTvMineUserName.setVisibility(View.VISIBLE);
            mTvMineUserName.setText(BaseApplication.getAppContext().getLoginUser().getNickName());
            mTvLogout.setVisibility(View.VISIBLE);

        } else {
            mBtnMineLogin.setVisibility(View.VISIBLE);
            mTvMineUserName.setVisibility(View.GONE);
            mTvLogout.setVisibility(View.GONE);
        }
    }

    //显示fragment时被调用
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @NonNull
    private View buildItemView(int resid, int[] imgId1, int i) {
        View subView = LayoutInflater.from(getContext()).inflate(R.layout.part_mine_item, null);
        RelativeLayout item = (RelativeLayout) subView.findViewById(R.id.rl_item);
        item.setId(imgId1[i]);
        item.setOnClickListener(this);
        ImageView icon = (ImageView) subView.findViewById(R.id.iv_icon);
        ImageView arrow = (ImageView) subView.findViewById(R.id.iv_arrow);
        TextView textView = (TextView) subView.findViewById(R.id.tv_text);
        TextView textExtra = (TextView) subView.findViewById(R.id.tv_extra);
        View line = subView.findViewById(R.id.line);

        icon.setImageResource(imgId1[i]);
        textView.setText(resid);
        if (i == imgId1.length - 1) {
            line.setVisibility(View.GONE);
        }

        if (imgId1[i] == R.mipmap.icon_mine_message) {
            ivNewMessage = (ImageView) subView.findViewById(R.id.iv_new_message);
            textPoint = (TextView) subView.findViewById(R.id.point);

        }
        if (imgId1[i] == R.mipmap.icon_mine_cache) {
            textExtra.setVisibility(View.VISIBLE);
            arrow.setVisibility(View.GONE);
            textExtra.setText("...");
            tvCacheSize = textExtra;
        }
        if (imgId1[i] == R.mipmap.icon_mine_update) {
            textExtra.setVisibility(View.VISIBLE);
            arrow.setVisibility(View.GONE);
            textExtra.setText("当前版本 : " + BuildConfig.VERSION_NAME);
        }
        return subView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_userinfo://更改名称
                if (isLogin())
                startActivity(new Intent(mActivity,UserInfoEditActivity.class));
                else
                UIutils.StartLoginActivity(mActivity);
                break;
            case R.id.tv_mine_user_bonuspoint://点击等级
                break;
            case R.mipmap.icon_mine_message:
                break;
            case R.mipmap.icon_mine_download:
                break;

            case R.mipmap.icon_mine_feedback:
                break;
            case R.mipmap.icon_mine_cache:

                break;
            case R.mipmap.icon_mine_update:

                break;
            case R.id.btn_mine_login:
                UIutils.StartLoginActivity(mActivity);
                break;
            case R.id.tv_logout:
                ToastUitl.showShort("click");
                minePresenter.logout();
                mTvMineUserName.setVisibility(View.GONE);
                mTvMineUserActionInfo.setVisibility(View.GONE);
                break;
            case R.id.ll_state:

                break;
            case R.id.ll_package://背包
                ToastUitl.showLong("package");
                if (isLogin()) {
                    startActivity(new Intent(mActivity,PackageActivity.class));
                }else{
                    UIutils.StartLoginActivity(mActivity);
                }
                break;
            case R.id.ll_task:

                break;
            case R.id.ll_sort:

                break;
            case R.id.tvtitle_right://商店
                if (isLogin()) {
                    startActivity(new Intent(mActivity,StoreActivity.class));
                }else{
                    UIutils.StartLoginActivity(mActivity);
                }
                break;

        }
    }


    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void updateNickName(String name) {

    }

    @Override
    public void updateGender(int gender) {

    }

    @Override
    public void showTips(String message) {

        ToastUitl.showLong(message);
    }

    @Override
    public void updateAvatar(String avatarUrl) {

    }

    @Override
    public void logoutSuccess() {
        mBtnMineLogin.setVisibility(View.VISIBLE);
        mTvMineUserName.setVisibility(View.GONE);
        mTvLogout.setVisibility(View.GONE);
        BaseApplication.getAppContext().cleanLoginInfo();

    }

    @Override
    public void processUpdate(AppVersion appVersion) {

    }
}


