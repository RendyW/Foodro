package com.latutslab_00000053580.foodro_home;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.latutslab_00000053580.foodro.Food;
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
                if(error.networkResponse.statusCode == 400){
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else if(error.networkResponse.statusCode == 401){
                    Toast.makeText(context, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Uknown Error:" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("password",password);

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

    public User register(Context context, int role, String firstname, String lastname, String password, String email){
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
                params.put("role", String.format("%d",role));
                params.put("firstname",firstname);
                params.put("lastname",lastname);
                params.put("email",email);
                params.put("password",password);


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

    public Food[] getAllFood(Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        final Food[][] foods = new Food[1][1];

        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getAllFood.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try{
                    JSONObject respObj = new JSONObject(response);
                    String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    foods[0] = new Food[data.length()];

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);
                        foods[0][i].setId(a.getInt("food_id"));
                        foods[0][i].setName(a.getString("name"));
                        foods[0][i].setId(a.getInt("food_price"));
                        foods[0][i].setName(a.getString("food_image"));
                        foods[0][i].setId(a.getInt("merchant_id"));
                        foods[0][i].setId(a.getInt("listed"));
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
        });
        queue.add(sr);
        return foods[0];
    }

    public Food getFoodById(Context context, int id){

        RequestQueue queue = Volley.newRequestQueue(context);
        Food food = new Food();

        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getFoodById.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject respObj = new JSONObject(response);

                    String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);
                        food.setId(a.getInt("food_id"));
                        food.setName(a.getString("name"));
                        food.setId(a.getInt("food_price"));
                        food.setName(a.getString("food_image"));
                        food.setId(a.getInt("merchant_id"));
                        food.setId(a.getInt("listed"));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                Toast.makeText(context, "getFoodById Success", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse.statusCode == 400){
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else if(error.networkResponse.statusCode == 401){
                    Toast.makeText(context, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Uknown Error:" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("food_id", Integer.toString(id));


                return params;
            }
        };
        return food;
    }

    public Food[] getFoodByMerchant(Context context, int merchant_id){
        RequestQueue queue = Volley.newRequestQueue(context);
        final Food[][] foods = new Food[1][1];

        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getFoodByMerchant.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try{
                    JSONObject respObj = new JSONObject(response);
                    String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    foods[0] = new Food[data.length()];

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);
                        foods[0][i].setId(a.getInt("food_id"));
                        foods[0][i].setName(a.getString("name"));
                        foods[0][i].setId(a.getInt("food_price"));
                        foods[0][i].setName(a.getString("food_image"));
                        foods[0][i].setId(a.getInt("merchant_id"));
                        foods[0][i].setId(a.getInt("listed"));
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
                params.put("merchant_id", Integer.toString(merchant_id));

                return params;
            }
        };
        queue.add(sr);
        return foods[0];
    } 
}



