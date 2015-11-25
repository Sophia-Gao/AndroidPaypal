/**
 * 
 */

package com.example.demo_paypal;

import com.paypal.android.sdk.payments.ShippingAddress;

/**
 * @author zhaoyongguang
 * @category 地址
 */
public class Data_TB_Address {

    public String CustomerId;// 用户Id

    public String FullName; //
    public String StreetAddress;// 街道
    public String Address2;// 地址2（可选）
    public String ZipCode;// 邮编
    public String State;// 省（州）
    public String City;// 城市
    public String Company;// 公司
    public String PhoneNumber;// 电话
    public String Email;// 邮箱
    public int ChangeType = 0;// 操作类型
    public long TS = 0;// 时间戳
    public String AdressId;// 地址Id

    public String CountryCode;// 国家（不用于云）

    public Data_TB_Address() {
        // TODO Auto-generated constructor stub
        this.CustomerId = new String();
        this.FullName = new String();
        this.StreetAddress = new String();
        this.Address2 = new String();
        this.ZipCode = new String();
        this.State = new String();
        this.City = new String();
        this.Company = new String();
        this.PhoneNumber = new String();
        this.Email = new String();
        this.AdressId = new String();
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCustomerId() {
        return this.CustomerId;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getFullName() {
        return this.FullName;
    }

    public void setStreetAddress(String StreetAddress) {
        this.StreetAddress = StreetAddress;
    }

    public String getStreetAddress() {
        return this.StreetAddress;
    }

    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }

    public String getAddress2() {
        return this.Address2;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getZipCode() {
        return this.ZipCode;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getState() {
        return this.State;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCity() {
        return this.City;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getCompany() {
        return this.Company;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getPhoneNumber() {
        return this.PhoneNumber;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setChangeType(int ChangeType) {
        this.ChangeType = ChangeType;
    }

    public int getChangeType() {
        return this.ChangeType;
    }

    public void setTS(long TS) {
        this.TS = TS;
    }

    public long getTS() {
        return this.TS;
    }

    public void setAdressId(String AdressId) {
        this.AdressId = AdressId;
    }

    public String getAdressId() {
        return this.AdressId;
    }

    public void setCountryCode(String countryCode) {
        this.CountryCode = countryCode;
    }

    public String getCountryCode() {
        return this.CountryCode;
    }
}
