package com.example.demo_paypal;

public class Data_TB_CartInfo {

//	private String mStoreProImageURL;    //商品图片的链接地址
    private int mStoreProImageURL;
	private String mStoreProName;        //商品名
	private float mStoreProPrice;       //商品价格
	private Boolean isSelected;
	private int num;
	


	public Data_TB_CartInfo(int mStoreProImageURL, String mStoreProName, float mStoreProPrice,int num,Boolean isSelected) {
		super();
		this.mStoreProImageURL = mStoreProImageURL;
		this.mStoreProName = mStoreProName;
		this.mStoreProPrice = mStoreProPrice;
		this.num = num;
		
		this.isSelected = isSelected;
	}

	public Data_TB_CartInfo() {
		super();
	}

	public int getmStoreProImageURL() {
		return mStoreProImageURL;
	}

	public void setmStoreProImageURL(int mStoreProImageURL) {
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

    public void setmStoreProPrice(float mStoreProPrice) {
        this.mStoreProPrice = mStoreProPrice;
    }
    public int getnum() {
        return num;
    }

    public void setnum(int num) {
        this.num = num;
    }


    public Boolean getisSelected() {
        return isSelected;
    }

    public void setisSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

}
