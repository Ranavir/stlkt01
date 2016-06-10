package com.stl.login.paymentgateway;

import android.app.Activity;
import android.os.Bundle;

/**
 * This activity will be started after getting paymentResponse url in webView
 * that means The payment process is over may be Success or Failed
 * So this activity will retrieve the data from server database with applicatio_no and amount
 * then display the payment details in android views
 * Created by Administrator on 09-03-2016.
 */
public class PaymentResponse extends Activity{
    String application_no,amount ;
    //String payment_status ;
    //String payment_id ;
    //String order_no ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.payment_response_view);
        /*
         * Here sleep for 2 seconds for updation of data in server database by Servlet
         * So that updated resultant record can be found
         */
        //Sleep code goes here...
        /*
        *   Get values from previous intent
        */
        application_no = getIntent().getStringExtra("application_no") != null ? getIntent().getStringExtra("application_no") : "";
        System.out.println("application_no =================> " + application_no);
        amount = getIntent().getStringExtra("amount") != null ? getIntent().getStringExtra("amount") : "";
        System.out.println("amount =================> " + amount);
        /*
         * Get data from sever database by calling a sevlet with above two values to get latest
         * record with the same application_no
         */
        //Retrieval code goes here...
        /*
         * Display values is corresponding view
         */
        //Display code goes here...
    }
}
