package com.latutslab_00000053580.foodro;

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
import com.latutslab_00000053580.foodro.Payment;
import com.latutslab_00000053580.foodro.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIHandler {
    private String endpoint = "https://rajasaweek11.000webhostapp.com/api/";

    private List<User> allUser;

    public User login(Context context, String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final User[] user = new User[1];
        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);
                        user[0] = new User(
                                a.getInt("user_id"),
                                a.getString("firstname"),
                                a.getString("lastname"),
                                a.getString("email"),
                                User.Role.values()[a.getInt("role_id")],
                                a.getInt("active")
                        );

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse.statusCode == 400) {
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (error.networkResponse.statusCode == 401) {
                    Toast.makeText(context, "Wrong email or password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Uknown Error:" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        queue.add(sr);
        return user[0];
    }

    public User register(Context context, int role, String firstname, String lastname, String password, String email) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final User[] user = new User[1];

        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    JSONObject a = data.getJSONObject(0);
                    user[0] = new User(
                            a.getInt("user_id"),
                            a.getString("firstname"),
                            a.getString("lastname"),
                            a.getString("email"),
                            User.Role.values()[a.getInt("role_id")],
                            a.getInt("active")
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("role", String.format("%d", role));
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("email", email);
                params.put("password", password);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
        return user[0];
    }

    public List<User> getAllUser(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        List<User> users = new ArrayList<>();
        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getAllMerchant.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);

                        if(a.getInt("Role") == 1){
                            users.add(new User(
                                    a.getInt("merchant_id"),
                                    a.getString("firstname"),
                                    a.getString("lastname"),
                                    a.getString("email"),
                                    User.Role.CUSTOMER,
                                    a.getInt("active")
                            ));
                        }else if (a.getInt("Role") == 2){
                            users.add(new User(
                                    a.getInt("merchant_id"),
                                    a.getString("firstname"),
                                    a.getString("lastname"),
                                    a.getString("email"),
                                    User.Role.MERCHANT,
                                    a.getInt("active")
                            ));
                        }


                    }
                } catch (JSONException e) {
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
        return users;
    }

    public List<Food> getAllFOod(Context context, List<User> users) {
        RequestQueue queue = Volley.newRequestQueue(context);
        List<Food> foods = new ArrayList<>();
        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getAllMerchantFood.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);
                        foods.add(new Food());
                        foods.get(i).setId(a.getInt("food_id"));
                        foods.get(i).setName(a.getString("name"));
                        foods.get(i).setPrice(a.getInt("food_price"));
                        foods.get(i).setImage(a.getString("food_image"));
                        foods.get(i).setListed(a.getInt("listed"));

                        int merchid = a.getInt("merchant_id");
                        // TODO: search class in a list using its property

                    }
                } catch (JSONException e) {
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
        return foods;
    }

    public Payment getPaymentById(Context context, int payment_id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Payment payment = new Payment();

        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getFoodByMerchant.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);
                        payment.setPayment_id(a.getInt("payment_id"));
                        payment.setTotalPayment(a.getInt("totalPayment"));
                        payment.setProofImage(a.getString("proofImage"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("payment_id", Integer.toString(payment_id));

                return params;
            }
        };
        queue.add(sr);
        return payment;
    }

}



