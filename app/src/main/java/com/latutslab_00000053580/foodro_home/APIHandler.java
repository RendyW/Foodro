package com.latutslab_00000053580.foodro_home;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.latutslab_00000053580.foodro.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIHandler {
    private String endpoint = "https://rajasaweek11.000webhostapp.com/api/";

    public User login(Context context, String email, String password){
        RequestQueue queue = Volley.newRequestQueue(context);
        User user = new User();

        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try{
                    JSONObject respObj = new JSONObject(response);

                    String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);
                        user.setUser_id(a.getInt("user_id"));
                        user.setFirstname(a.getString("firstname"));
                        user.setLastname(a.getString("lastname"));
                        user.setEmail(a.getString("email"));
                        user.setRole(a.getInt("role_id"));
                        user.setActive(a.getInt("active"));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("user",email);
                params.put("pass",password);

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
        return user;
    }
}
