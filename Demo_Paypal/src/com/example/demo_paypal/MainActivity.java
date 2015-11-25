
package com.example.demo_paypal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.TunnelRefusedException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.paypal.android.sdk.payments.LoginActivity;
import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paypal.android.sdk.payments.ShippingAddress;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    final static String TAG = "mainAct";
    // private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
    private static final int REQUEST_CODE_PAYMENT = 0;// myApplication
    String merchantName = "iHealth"; // myApplication
    Pay_via_Paypal paypalPay;
    Data_TB_Address shippingAddress;
    boolean payResualt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // paypal 支付
        paypalPay = new Pay_via_Paypal(this, false, merchantName);
        // Intent intent = new Intent(this, PayPalService.class);
        // intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalPay.config);
        // startService(intent);

    }

    public void onBuyPressed(View pressed) {
        // paypalPay.setSum("17", "USD", "ihel");
        ArrayList<Data_TB_StoreInfo> cartInfos = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Data_TB_StoreInfo storeInfo = new Data_TB_StoreInfo();
            storeInfo.setId("11");
            storeInfo.setmStoreProName("clothes");
            storeInfo.setmStoreProNum(1);
            storeInfo.setmStoreProPrice(100f);
            cartInfos.add(storeInfo);
        }
        paypalPay.setCartInfo("222", "11223344", cartInfos, "USD", "0", "0", 0);
        // paypalPay.getStuffToBuy(cartInfos);
        // shippingAddress = new Data_TB_Address();
        // shippingAddress.setFullName("");
        // shippingAddress.setStreetAddress("1");
        // shippingAddress.setAddress2("");
        // shippingAddress.setCity("San Jose");
        // shippingAddress.setZipCode("95131");
        // shippingAddress.setState("CA");
        // shippingAddress.setCountryCode("US");
        // paypalPay.setShippingAddress(shippingAddress);
        startActivityForResult(paypalPay.checkOut(this), REQUEST_CODE_PAYMENT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            payResualt = paypalPay.resualtOfPayment(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        // Stop service when done
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
