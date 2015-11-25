
package com.example.demo_paypal;

import java.io.Serializable;

public class Data_TB_StoreInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String mStoreProImageURL; // 商品图片
    private String mStoreProName; // 商品名
    private float mStoreProPrice; // 商品价格 paypal
    private int mStoreProNum; // 商品数量 paypal
    private String sku; // sku   paypal
    private int mProductType; // sku   paypal
    private String mVariantId;
    

    public Data_TB_StoreInfo(String id, String mStoreProImageURL, String mStoreProName, float mStoreProPrice,
            int mStoreProNum) {
        super();
        this.id = id;
        this.mStoreProNum = mStoreProNum;
        this.mStoreProImageURL = mStoreProImageURL;
        this.mStoreProName = mStoreProName;
        this.mStoreProPrice = mStoreProPrice;
    }

    public Data_TB_StoreInfo() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getmStoreProImageURL() {
        return mStoreProImageURL;
    }

    public void setmStoreProImageURL(String mStoreProImageURL) {
        this.mStoreProImageURL = mStoreProImageURL;
    }

    public String getmStoreProName() {
        return mStoreProName;
    }

    public void setmStoreProName(String mStoreProName) {
        this.mStoreProName = mStoreProName;
    }

    public float getmStoreProPrice() {
        return mStoreProPrice;
    }

    public void setmStoreProNum(int mStoreProNum) {
        this.mStoreProNum = mStoreProNum;
    }

    public int getmStoreProNum() {
        return mStoreProNum;
    }

    public void setmStoreProPrice(float mStoreProPrice) {
        this.mStoreProPrice = mStoreProPrice;
    }
    public void setSku(String sku) {
        this.sku=sku;
    }
    public String getSku(){
        return this.sku;
    }
    
    public void setProductType(int mProductType) {
        this.mProductType=mProductType;
    }
    public int getProductType(){
        return this.mProductType;
    }
    
    public void setVariantId(String mVariantId) {
        this.mVariantId=mVariantId;
    }
    public String getVariantId(){
        return this.mVariantId;
    }
}
