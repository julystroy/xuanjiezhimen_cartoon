package com.jssj.jiangye.module.someui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.utils.BitmapUtils;
import com.jssj.jiangye.utils.Compressor;
import com.jssj.jiangye.utils.DisplayUtil;
import com.jssj.jiangye.widget.SDCardUtil;
import com.jssj.jiangye.widget.richtext.RichTextEditor;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.iwf.photopicker.PhotoPicker;

/**
 */

public class ComposeActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tvtitle_left)
    TextView             mTvtitleLeft;
    @BindView(R.id.tv_title)
    TextView             mTvTitle;
    @BindView(R.id.tvtitle_right)
    TextView             mTvtitleRight;
    @BindView(R.id.et_new_content)
    RichTextEditor       mEtNewContent;
    @BindView(R.id.content_new)
    LinearLayout         mContentNew;
    @BindView(R.id.fab_new)
    FloatingActionButton mFabNew;

    private boolean whichUI;


    @Override
    public int getLayoutId() {
        return R.layout.compose_activity;
    }

    @Override
    public void initView() {
        int intExtra = getIntent().getIntExtra(ApiConstants.COMPOSE, 0);
        whichUI = intExtra == ApiConstants.SEND ? true : false;
        mTvtitleLeft.setText("取消");
        if (whichUI) {
            mTvTitle.setText("发帖");
            mTvtitleRight.setText("发送");
            mContentNew.setVisibility(View.VISIBLE);
            mFabNew.setVisibility(View.VISIBLE);
            mFabNew.setOnClickListener(this);
        } else {
            mTvTitle.setText("发便签");
            mTvtitleRight.setText("制作");
            mContentNew.setVisibility(View.GONE);
            mFabNew.setVisibility(View.GONE);
        }
        mTvtitleRight.setOnClickListener(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


 /*   @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("images", images);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (null != savedInstanceState) {
            images = savedInstanceState.getStringArray("images");
            if (null != images && images.length > 0) {
                for (String image : images) {

                }

            }
        }

    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_new:
                callGallery();
                break;
            case R.id.tvtitle_right:
                if (whichUI)
                    sendText();
                else
                //note
                break;
        }

    }

    private void sendText() {//// FIXME: 17-6-5
        String editData = getEditData();

    }

    /**
     * 调用图库选择
     */
    private void callGallery() {
        //        //调用系统图库
        //        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");// 相片类型
        //        startActivityForResult(intent, 1);

        //调用第三方图库选择
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(false)//是否可以预览
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == 1) {
                    //处理调用系统图库
                } else if (requestCode == PhotoPicker.REQUEST_CODE) {
                    //异步方式插入图片
                    insertImagesSync(data);
                }
            }
        }
    }

    private void insertImagesSync(Intent data) {

        // try{
        mEtNewContent.measure(0, 0);
        int width = DisplayUtil.getScreenWidth(ComposeActivity.this);
        int height = DisplayUtil.getScreenHeight(ComposeActivity.this);
        ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);

        //可以同时插入多张图片
        for (String imagePath : photos) {
            Bitmap bitmap = BitmapUtils.getSmallBitmap(imagePath, width, height);
           // bitmap = BitmapFactory.decodeFile(imagePath);
            imagePath = SDCardUtil.saveToSdCard(bitmap);
            File fileOrigin = new File(imagePath);
            File compressedImage;

                compressedImage = new Compressor.Builder(ComposeActivity.this)
                        .setMaxWidth(720)
                        .setMaxHeight(1080)
                        .setQuality(85)
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .build()
                        .compressToFile(fileOrigin);
               PostImagin(imagePath, compressedImage.getName(), compressedImage);
                //

       /* }catch (Exception e){
            e.printStackTrace();
        }*/
        }

    }

    final  PostFormBuilder postFormBuilder =
            BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.POST_IMAGIN);

    //send imagin  to server


    public  void PostImagin(String imagePath, String name, File compressedImage) {
        postFormBuilder.addFile(imagePath,name,compressedImage);
        postFormBuilder.build().execute(new BaseCallBack<String>() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(String response) {
               // mEtNewContent.insertImage(imagePath, mEtNewContent.getMeasuredWidth());
            }

            @Override
            public String parseNetworkResponse(String response) throws Exception {

                return response;
            }
        });
    }


    //处理数据编辑提交
    private String getEditData() {
        List<RichTextEditor.EditData> editList = mEtNewContent.buildEditData();
        StringBuffer content = new StringBuffer();
        for (RichTextEditor.EditData itemData : editList) {
            if (itemData.inputStr != null) {
                content.append(itemData.inputStr);
                //Log.d("RichEditor", "commit inputStr=" + itemData.inputStr);
            } else if (itemData.imagePath != null) {
                content.append("<img src=\"").append(itemData.imagePath).append("\"/>");
                //Log.d("RichEditor", "commit imgePath=" + itemData.imagePath);
                //imageList.add(itemData.imagePath);
            }
        }
        return content.toString();
    }
}
