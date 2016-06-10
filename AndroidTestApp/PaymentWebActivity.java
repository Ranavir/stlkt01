package com.stl.login.paymentgateway;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 08-03-2016.
 */
public class PaymentWebActivity extends Activity{
    String application_no,amount ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_webview);
        final WebView paymentView = (WebView)findViewById(R.id.webview);
        final String URL = "http://182.156.93.61:8080/pg/initiatePayment" ;
        //final String URL = "http://192.168.0.91:8080/pg/TestServlet" ;
        /*
        *   Get values from previous intent
        */
        application_no = getIntent().getStringExtra("application_no") != null ? getIntent().getStringExtra("application_no") : "";
        System.out.println("application_no =================> " + application_no);
        amount = getIntent().getStringExtra("amount") != null ? getIntent().getStringExtra("amount") : "";
        System.out.println("amount =================> " + amount);

        String postData = "application_no="+application_no+"&amount="+amount ;

        paymentView.postUrl(URL, EncodingUtils.getBytes(postData, "BASE64"));
        paymentView.getSettings().setJavaScriptEnabled(true);
        paymentView.setWebViewClient(new MyWebViewClient());

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getPath().indexOf("/PostPayment.jsp") != -1) {//This is the Final URL
                System.out.println("Going to Activity");
                Intent intent = new Intent(PaymentWebActivity.this, PaymentResponse.class);
                intent.putExtra("application_no", application_no);
                intent.putExtra("amount", amount);
                startActivity(intent);
                finish();
                return true;
            }else{
                return false ;
            }

        }
    }
}
