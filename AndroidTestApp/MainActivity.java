package com.stl.login.paymentgateway;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_application_no,et_amount ;
    Button btn_pay ;
    Intent intent;
    String application_no,amount ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * Initialize the views
        */
        et_application_no = (EditText) findViewById(R.id.et_application_no) ;
        et_amount = (EditText) findViewById(R.id.et_amount) ;
        btn_pay = (Button) findViewById(R.id.btn_pay) ;
        btn_pay.setOnClickListener(this);
    }
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_pay:
                application_no = et_application_no.getText().toString() ;
                amount = et_amount.getText().toString() ;
                if(checkInternetAvailability(getApplicationContext())) {
                    if (application_no != "" && validAmount(amount)) {
                        intent = new Intent(MainActivity.this, PaymentWebActivity.class);
                        intent.putExtra("application_no", application_no);
                        intent.putExtra("amount", amount);
                        startActivity(intent);
                    } else {//Generate Toast Message
                        Toast.makeText(MainActivity.this, "Enter all valid data!!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Check your Internet Connection!!!", Toast.LENGTH_SHORT).show();
                }
                break;



            default:
        }
    }
    private boolean validAmount(String amount){
        boolean flag = false ;
        Double dblAmount = null ;
        try {
            dblAmount = Double.parseDouble(amount);
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
        }
        if(null != dblAmount && dblAmount >= 0.0){
            flag = true ;
        }
        return flag ;
    }
    private boolean checkInternetAvailability(Context context){

        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isAvailable() && netInfo.isConnected()) {
            return true;
        } else {
            System.out.println("Internet Connection Not Present");
            return false;
        }

    }
}
