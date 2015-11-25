/**
 * @title
 * @Description
 * @author
 * @date 2015年3月9日 上午10:35:15 
 * @version V1.0  
 */

package com.example.demo_paypal;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xmlpull.v1.XmlPullParserException;

import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Loader.ForceLoadContentObserver;
import android.content.res.XmlResourceParser;
import android.drm.DrmStore.RightsStatus;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paypal.android.sdk.payments.ShippingAddress;

/**
 * @ClassName: Pay_via_Paypal
 * @Description: TODO
 * @author gaonana
 * @date 2015年3月9日 上午10:35:15
 */
public class Pay_via_Paypal {
    boolean debug = true;
    final static String TAG = "Pay_via_Paypal";
    private static final int REQUEST_CODE_PAYMENT = 1;
    Context mContext;
    private static String CONFIG_ENVIRONMENT;
    private static PayPalConfiguration config;
    private static String CONFIG_CLIENT_ID;
    private PayPalPayment payPalPayment;
    private String paymentIntent = PayPalPayment.PAYMENT_INTENT_SALE;

    public Pay_via_Paypal(Context context, boolean isOffical, String merchantName) {
        mContext = context;
        setEnvironment(context, isOffical, merchantName);
        Intent intent = new Intent(context, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, this.config);
        context.startService(intent);
    }

    public void setPaymentIntent(int n) {
        switch (n) {
            case 0:
                this.paymentIntent = PayPalPayment.PAYMENT_INTENT_SALE;

                break;

            default:
                break;
        }
    }

    /**
     * @Title: setEnvironment
     * @author:gaonana
     * @Description: TODO 配置paypal信息
     * @param @param isOffical
     * @return void
     * @throws
     */
    public void setEnvironment(Context context, boolean isOffical, String merchantName) {
        if (isOffical) {
            CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_PRODUCTION;
        } else {
            CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
        }
        //

        if (isOffical) {
            CONFIG_CLIENT_ID = "AVvwkjcCuORILldK8fgt3RtviFos_pCs1h3K7iy8huLHiFNcJGfI1-YoA6VvcbyUJFg7Zsni8ElmqOlT";

        } else {
            CONFIG_CLIENT_ID = "AavcZPxkDlqrHkV-g4qwNUSTopLTIDgsQlN9Wve_Erz8Y5SS0yxHULZp7sL71YTp4lsW_0Zfg4puOJzc";

        }
        // if (isAmerican(context)) {
        // CONFIG_CLIENT_ID =
        // "AZzTwxPQxmKsTwXIBNY8ewC2da8CH5auQG7MNrkorKPasiRt72jsYki4PmCc-4ZJhrVCdiFJgLEKKIrn";
        // } else {
        // CONFIG_CLIENT_ID =
        // "AZzTwxPQxmKsTwXIBNY8ewC2da8CH5auQG7MNrkorKPasiRt72jsYki4PmCc-4ZJhrVCdiFJgLEKKIrn";
        // }
        config = new PayPalConfiguration()
                .environment(CONFIG_ENVIRONMENT)
                .clientId(CONFIG_CLIENT_ID)
                .acceptCreditCards(false)
                .rememberUser(false);
    }

    // currency USD, GBP, CAD, EUR, JPY
    public void setCartInfo(String guid, String invoceNumber, List<Data_TB_StoreInfo> storeInfos, String currency,
            String subShipping,
            String subTax, int n) {
        PayPalItem[] items = new PayPalItem[storeInfos.size()];
        for (int i = 0; i < storeInfos.size(); i++) {
            items[i] = new PayPalItem(storeInfos.get(i).getmStoreProName(), storeInfos.get(i).getmStoreProNum(),
                    new BigDecimal(storeInfos.get(i).getmStoreProPrice() + ""), currency, "sku-"
                            + storeInfos.get(i).getSku());
            if (debug) {
                Log.e(TAG + "---111----", storeInfos.get(i).getmStoreProName() + "--"
                        + storeInfos.get(i).getmStoreProNum()
                        + "----" +
                        new BigDecimal(storeInfos.get(i).getmStoreProPrice() + "") + "--" + currency + "----" + "sku-"
                        + storeInfos.get(i).getSku());
            }

        }

        BigDecimal subtotal = PayPalItem.getItemTotal(items);
        BigDecimal shipping = new BigDecimal(subShipping);
        BigDecimal tax = new BigDecimal(subTax);
        PayPalPaymentDetails paymentDetails = new PayPalPaymentDetails(shipping, subtotal, tax);
        BigDecimal amount = subtotal.add(shipping).add(tax);
        setPaymentIntent(n);
        PayPalPayment payment = new PayPalPayment(amount, currency, "Sum", this.paymentIntent);
        payment.items(items).paymentDetails(paymentDetails);
        payment.invoiceNumber(invoceNumber);
        // --- set other optional fields like invoice_number, custom field, and soft_descriptor
        payment.custom(guid);
        this.payPalPayment = payment;

    }

    private BigDecimal priceTo2Decimal(String price) {
        BigDecimal bg = new BigDecimal(price);
        return bg.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public void setSum(String guid, String invoceNumber, String sum, String currency, String describe) {
        setPaymentIntent(0);
        this.payPalPayment = new PayPalPayment(new BigDecimal(sum), currency, describe,
                paymentIntent);
        this.payPalPayment.invoiceNumber(invoceNumber);
        this.payPalPayment.custom(guid);
    }

    public void setShippingAddress(Data_TB_Address data_shippingAddress) {
        String recipienName = data_shippingAddress.getFullName();
        String line1 = data_shippingAddress.getStreetAddress();
        String line2 = data_shippingAddress.getAddress2();
        if (recipienName.length() > 50) {
            recipienName = recipienName.substring(0, 49);
        }
        if (line1.length() > 100) {
            line1 = line1.substring(0, 99);
        }
        if (line2.length() > 100) {
            line2 = line2.substring(0, 99);
        }
        ShippingAddress shippingAddress =
                new ShippingAddress().recipientName(recipienName)
                        .line1(line1).line2(line2)
                        .city(data_shippingAddress.getCity()).state(data_shippingAddress.getState())
                        .postalCode(data_shippingAddress.getZipCode())
                        .countryCode(data_shippingAddress.getCountryCode());
        payPalPayment.providedShippingAddress(shippingAddress);
    }

    public Intent checkOut(Context context) {
        Intent intent = new Intent(context, PaymentActivity.class);
        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        // startActivityForResult(intent, REQUEST_CODE_PAYMENT);
        return intent;
    }

    public boolean resualtOfPayment(int requestCode, int resultCode, Intent data) {
        boolean resualtPay = false;
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm =
                    data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i(TAG, confirm.toJSONObject().toString(4));
                    Log.d(TAG, confirm.getPayment().toJSONObject().toString(4));
                    Log.i(TAG, confirm.toJSONObject().toString(4));
                    Log.d(TAG, confirm.getPayment().toJSONObject().toString(4));
                    String returnData = confirm.toJSONObject().toString();
                    JSONTokener jsonParser = new JSONTokener(returnData);
                    JSONObject returnJsonObject = (JSONObject) jsonParser.nextValue();
                    String id = returnJsonObject.getJSONObject("response").getString("id");
                    Log.e(TAG, "id=" + id);
                    resualtPay = true;
                    Toast.makeText(
                            mContext.getApplicationContext(),
                            mContext.getResources().getString(R.string.pay_sucessfull), Toast.LENGTH_LONG)
                            .show();

                } catch (JSONException e) {
                    Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i(TAG, "The user canceled.");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i(
                    TAG,
                    "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
        return resualtPay;
    }

    private boolean isAmerican(Context context) {

        boolean isAmerican = true, isStop = false;
        String country = "";

        country = Locale.getDefault().getCountry();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        XmlResourceParser xrp = context.getResources().getXml(R.xml.country);
        try {
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT && !isStop) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("country")) {
                        // 通过属性名来获取属性值
                        String countryname = xrp.getAttributeValue(0);// 通过属性索引来获取属性值
                        if (country.equals(countryname)) {
                            isAmerican = false;
                            isStop = true;
                        }
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isAmerican;
    }

}
