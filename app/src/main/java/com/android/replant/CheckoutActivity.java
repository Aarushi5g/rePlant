package com.android.replant;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import okhttp3.OkHttpClient;

public class CheckoutActivity extends AppCompatActivity {
    
    private OkHttpClient httpClient = new OkHttpClient();
    private Stripe stripe;
    private static Context mContext;
    private TextView price;
    private String paymentIntentClientSecret;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main));
        
        price = findViewById(R.id.text_item_price);
        price.setText("Rs. 5 ");

        mContext = this;
        
        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_ukVFEk9tOFrf0yHBMlxXTDhk00vWdBDKjT"
        );

        startCheckout();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void startCheckout() {

        Button pay = findViewById(R.id.pay_btn);
        pay.setOnClickListener((View view) -> {
            CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
            cardInputWidget.bringToFront();
            PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
            if (params != null) {
                ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                        .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
                stripe = new Stripe(
                        this,
                        PaymentConfiguration.getInstance(this).getPublishableKey()
                );
                stripe.confirmPayment(this, confirmParams);
            }else{
                Toast.makeText(mContext, "Card details not complete", Toast.LENGTH_SHORT).show();
            }
        });

    }

}



