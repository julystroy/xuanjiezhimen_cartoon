package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-14.
 */
public class StoreData implements Parcelable {
    /*"productId": "3514d2cda8eb422ca23d69e421d64af1", //道具id
                                            "productType": 1, //道具大类（1=经验类 2=答题类 3=魅力鲜花 4=改名字）
                                            "productCode": "c228de4a05534740a3535180e589976e", //道具小类id
                                            "productName": "三日双倍经验卡", //道具名称
                                            "marketPrice": null,
                                            "salePrice  ": 20, //道具价格
                                            "repertory  ": null,
                                            "discount   ": null,
                                            "validTime  ": 3,
                                            "coverImgUrl ": "http://oqjj5mrzr.bkt.clouddn.com/BBHQbq1YcdHp1496917134597.png", //道具图片
                                            "rate        ": 2,
                                            "productDesc ": "使用后三天可以翻双倍", //道具描述
                                            "sellStartTime    ": null, //销售开始时间（优惠活动期间）
                                            "sellEndTime      ": null, //销售结束时间（优惠活动期间）
                                            "isActivityProduct": 0, //是否在优惠活动期间（1=是 0=否）
                                            "isOnLine          ": 1, //是否上架
                                            "recordStatus      ": 1, //是否删除
                                            "createUserId      ": "1111111",
                                            "createTime        ": "2017-06-08 18:32:33",
                                            "updateUserId      ": "1111111",
                                            "lastModifyTime     ": "2017-06-08 18:32:34",
                                            "goldCoin           ": 20*/

    private String productId;
    private String productType;
    private String productCode;
    private String productName;
    private String marketPrice;
    private String salePrice  ;
    private String repertory  ;
    private String discount   ;
    private String validTime  ;
    private String coverImgUrl;
    private String rate       ;
    private String productDesc;
    private String sellStartTime ;
    private String sellEndTime    ;
    private String isActivityProduct;
    private String isOnLine         ;
    private String recordStatus     ;
    private String createUserId     ;
    private String createTime       ;
    private String updateUserId         ;
    private String lastModifyTime       ;
    private String goldCoin             ;

    public StoreData() {}

    protected StoreData(Parcel in) {
        productId = in.readString();
        productType = in.readString();
        productCode = in.readString();
        productName = in.readString();
        marketPrice = in.readString();
        salePrice = in.readString();
        repertory = in.readString();
        discount = in.readString();
        validTime = in.readString();
        coverImgUrl = in.readString();
        rate = in.readString();
        productDesc = in.readString();
        sellStartTime = in.readString();
        sellEndTime = in.readString();
        isActivityProduct = in.readString();
        isOnLine = in.readString();
        recordStatus = in.readString();
        createUserId = in.readString();
        createTime = in.readString();
        updateUserId = in.readString();
        lastModifyTime = in.readString();
        goldCoin = in.readString();
    }

    public static final Creator<StoreData> CREATOR = new Creator<StoreData>() {
        @Override
        public StoreData createFromParcel(Parcel in) {
            return new StoreData(in);
        }

        @Override
        public StoreData[] newArray(int size) {
            return new StoreData[size];
        }
    };

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getRepertory() {
        return repertory;
    }

    public void setRepertory(String repertory) {
        this.repertory = repertory;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getSellStartTime() {
        return sellStartTime;
    }

    public void setSellStartTime(String sellStartTime) {
        this.sellStartTime = sellStartTime;
    }

    public String getSellEndTime() {
        return sellEndTime;
    }

    public void setSellEndTime(String sellEndTime) {
        this.sellEndTime = sellEndTime;
    }

    public String getIsActivityProduct() {
        return isActivityProduct;
    }

    public void setIsActivityProduct(String isActivityProduct) {
        this.isActivityProduct = isActivityProduct;
    }

    public String getIsOnLine() {
        return isOnLine;
    }

    public void setIsOnLine(String isOnLine) {
        this.isOnLine = isOnLine;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(String goldCoin) {
        this.goldCoin = goldCoin;
    }

    @Override
    public String toString() {
        return "StoreData{" +
                "productId='" + productId + '\'' +
                ", productType='" + productType + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", repertory='" + repertory + '\'' +
                ", discount='" + discount + '\'' +
                ", validTime='" + validTime + '\'' +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", rate='" + rate + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", sellStartTime='" + sellStartTime + '\'' +
                ", sellEndTime='" + sellEndTime + '\'' +
                ", isActivityProduct='" + isActivityProduct + '\'' +
                ", isOnLine='" + isOnLine + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", lastModifyTime='" + lastModifyTime + '\'' +
                ", goldCoin='" + goldCoin + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productId);
        parcel.writeString(productType);
        parcel.writeString(productCode);
        parcel.writeString(productName);
        parcel.writeString(marketPrice);
        parcel.writeString(salePrice);
        parcel.writeString(repertory);
        parcel.writeString(discount);
        parcel.writeString(validTime);
        parcel.writeString(coverImgUrl);
        parcel.writeString(rate);
        parcel.writeString(productDesc);
        parcel.writeString(sellStartTime);
        parcel.writeString(sellEndTime);
        parcel.writeString(isActivityProduct);
        parcel.writeString(isOnLine);
        parcel.writeString(recordStatus);
        parcel.writeString(createUserId);
        parcel.writeString(createTime);
        parcel.writeString(updateUserId);
        parcel.writeString(lastModifyTime);
        parcel.writeString(goldCoin);
    }
}
