package com.example.kamran.shoppin;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SellerRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);

        final EditText etSName=(EditText)findViewById(R.id.etSName);
        final EditText etShopname=(EditText)findViewById(R.id.etSShopname);
        final EditText etSEmail=(EditText)findViewById(R.id.etSEmail);
        final EditText etSAddress=(EditText)findViewById(R.id.etSAddress);
        final EditText etSMobile=(EditText)findViewById(R.id.etSMobile);
        final EditText etSDealsin=(EditText)findViewById(R.id.etSDealsin);
        final EditText etSPassword=(EditText)findViewById(R.id.etSPassword);

        final Button bSRegister=(Button)findViewById(R.id.bSRegister);

        bSRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name= etSName.getText().toString();
                final String shopname= etShopname.getText().toString();
                final String email= etSEmail.getText().toString();
                final String address= etSAddress.getText().toString();
                final int mobile= Integer.parseInt(etSMobile.getText().toString());
                final String dealsin= etSDealsin.getText().toString();
                final String password=etSPassword.getText().toString();


                final Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent=new Intent(SellerRegistrationActivity.this,SellerActivity.class);
                                SellerRegistrationActivity.this.startActivity(intent);
                                Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(SellerRegistrationActivity.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                                Toast.makeText(getApplicationContext(),"Registration unsuccessful",Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                SellerRegisterRequest sellerRegisterRequest=new SellerRegisterRequest(name, shopname,email,mobile,address,dealsin,password,responseListener);
                RequestQueue queue= Volley.newRequestQueue(SellerRegistrationActivity.this);
                queue.add(sellerRegisterRequest);

            }
        });

    }
}
