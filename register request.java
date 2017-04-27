package com.example.kamran.shoppin;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kamran on 4/27/2017.
 */

public class SellerRegisterRequest extends StringRequest {

    private static final String SELLER_REGISTER_REQUEST_URL="http://abdullahhashmi24.000webhostapp.com/seller registeration data.php" ;
    private Map<String,String> params;


    public SellerRegisterRequest(String name, String shopname, String email, int mobile, String address, String dealsin, String password, Response.Listener<String> listener){

        super(Method.POST,SELLER_REGISTER_REQUEST_URL, listener, null);

        params=new HashMap<>();
        params.put("name" ,name);
        params.put("shopname",shopname);
        params.put("email",email);
        params.put("mobile",mobile+"");
        params.put("address",address);
        params.put("dealsin",dealsin);
        params.put("password",password);


    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}

