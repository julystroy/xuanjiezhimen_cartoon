package com.jssj.jiangye.module.user;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.utils.BitmapUtils;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.widget.NormalTitleBar;
import com.jssj.jiangye.widget.dialog.TwoLineDialog;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;

/**
 * Created by cc on 17-5-25.
 */
public class UserInfoEditActivity extends BaseActivity implements View.OnClickListener, TwoLineDialog.buttonClick {
    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.iv_avatar)
    ImageView      mIvAvatar;
    @BindView(R.id.ll_change_avatar)
    LinearLayout   mLlChangeAvatar;
    @BindView(R.id.tv_nickname)
    TextView       mTvNickname;
    @BindView(R.id.rl_nickname)
    RelativeLayout mRlNickname;
    @BindView(R.id.tv_gender)
    TextView       mTvGender;
    @BindView(R.id.rl_gender)
    RelativeLayout mRlGender;
    private String pickupPhotoFile;
    private static final int PICK_IMAGE     = 2;
    @Override
    public int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initView() {
        mTitlebar.setTitleText(R.string.edit_Name);
        mTitlebar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mLlChangeAvatar.setOnClickListener(this);
        mRlNickname.setOnClickListener(this);
        mRlGender.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_gender:
                showChooseGenderDialog();
                break;
            case R.id.rl_nickname:
                startActivity(new Intent(this,NameEditActivity.class));
                break;
            case R.id.ll_change_avatar:
                choosePhoto();
                break;
        }
    }

    private void choosePhoto() {
        pickupPhotoFile = getCacheDir().getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            processSelectedPhoto(data);
        }
    }
    private void processSelectedPhoto(Intent data) {
        if (null != data) {
            Uri selectedImage = data.getData();
            String[] projection = {MediaStore.MediaColumns.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, projection, null, null, null);

            if (cursor == null || !cursor.moveToFirst()) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (null == bitmap) {
                    ToastUitl.showLong("无效图片");
                } else {
                    BitmapUtils.saveBitmapToFile(bitmap, pickupPhotoFile, ApiConstants.MAX_PHOTO_SIZE);
                    //presenter.uploadAvatar(new File(pickupPhotoFile));
                }
            } else {
                int columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                String filePath = cursor.getString(columnIndex);
                cursor.close();

                Log.d("", "filePath = " + filePath);

                if (TextUtils.isEmpty(filePath)) {
                    ToastUitl.showLong("无效的图片");
                } else {
                    Bitmap bitmap = BitmapUtils.decodeFile(filePath, 1024, 1024);
                    if (bitmap == null) {
                        ToastUitl.showLong("无效的图片");
                    } else {
                        BitmapUtils.saveBitmapToFile(bitmap, pickupPhotoFile, ApiConstants.MAX_PHOTO_SIZE);
                        //presenter.uploadAvatar(new File(pickupPhotoFile));
                    }
                }
            }
        }
    }
    //show dialog
    private void showChooseGenderDialog() {

        TwoLineDialog dialog = new TwoLineDialog(this,"男","女");
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnButtonClickListener(this);
        dialog.show();
    }

    @Override
    public void onButtonClickListener(String text0) {
        mTvGender.setText(text0);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (null != savedInstanceState) {
            pickupPhotoFile = savedInstanceState.getString("pickupPhotoFile");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("pickupPhotoFile", pickupPhotoFile);
        super.onSaveInstanceState(outState);
    }
}
