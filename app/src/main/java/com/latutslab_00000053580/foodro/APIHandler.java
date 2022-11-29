package com.latutslab_00000053580.foodro;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.latutslab_00000053580.foodro_home.MainActivity;
import com.latutslab_00000053580.sqlite.DbUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIHandler {
        private String endpoint = "https://rajasaweek11.000webhostapp.com/api/";


    public void login(Context context, String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    JSONObject a = data.getJSONObject(0);

                    User user = new User(
                            a.getInt("user_id"),
                            a.getString("firstname"),
                            a.getString("lastname"),
                            a.getString("email"),
                            a.getInt("role_id"),
                            a.getInt("active"),
                            a.getString("image")
                            );

                    DbUser dbUser = new DbUser(context);
                    dbUser.open();
                    dbUser.addUser(user);

//                    Cursor cursor = dbUser.getUser();
//                    cursor.moveToFirst();
                    // pindah activity
                    if (user.getRole() == 1) {
                        // TODO: Pindah activity
                        getAllFoodMerchant(context, true);
                    } else if (user.getRole() == 2) {
                        //pinda ke tampilan merchant
                        getOrderMerchant(context, user.getId(), true);
                    }
//                    Log.i("LOGIN", cursor.getString(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("LOGIN", e.toString());
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
    }

    public void register(Context context, int role, String firstname, String lastname, String password, String email) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final User[] user = new User[1];

        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    JSONObject a = data.getJSONObject(0);
//                    TODO: Put these data into sqlite
//                            a.getInt("user_id"),
//                            a.getString("firstname"),
//                            a.getString("lastname"),
//                            a.getString("email"),
//                            a.getInt("role_id"),
//                            a.getInt("active")
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
    }

    public void getAllFoodMerchant(Context context, boolean isInit) {
        RequestQueue queue = Volley.newRequestQueue(context);
        List<User> users = new ArrayList<>();
        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getAllFoodMerchant.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);

//                        TODO: put these into adapter for recycler
//                                    a.getInt("user_id"),
//                                    a.getString("firstname"),
//                                    a.getString("lastname"),
//                                    a.getString("email"),
//                                    User.Role.MERCHANT,
//                                    a.getInt("active")
                    }
                    if(isInit){
                        Intent i = new Intent().setClass(context, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        context.startActivity(i);
                    }

                    Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
                    Log.i("VOLLEYDONE", "DONE");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("VOLLEYERROCATCH", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("VOLLEY", String.valueOf(error.networkResponse.statusCode));
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(sr);
    }

    public void getOrderMerchant(Context context, int merchant_id, boolean isInit) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getOrderMerchant.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);

//                        TODO: put these into adapter for recycler

//                        a.getInt("order_id")
//                        a.getInt("merchant_id")
//                        a.getInt("food_id")
//                        a.getString("food_name")
//                        a.getInt("food_price")
//                        a.getInt("quantity")
//                        a.getInt("total")
//                        a.getInt("status_id")
//                        a.getInt("user_id")
//                        a.getString("firstname")
//                        a.getString("lastname")
//                        a.getString("email")
//                        a.getInt("active")
//                        a.getInt("orderDate")
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date birthDate = sdf.parse(a.getString("orderDate"));

                    }
                    if(isInit){
                        Intent i = new Intent().setClass(context, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        context.startActivity(i);
                    }
                    Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
                    Log.i("VOLLEYDONE", "DONE");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("VOLLEYERROCATCH", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("VOLLEY", String.valueOf(error.networkResponse.statusCode));
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(sr);
    }

    public void getOrderByCustomer(Context context, int user_id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "getOrderByCustomer.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);



//                        TODO: put these into adapter for recycler
//                        Order order = new Order(a.getInt("order_id"), )
//
//                        a.getInt("merchant_id")
//                        a.getInt("food_id")
//                        a.getString("food_name")
//                        a.getInt("food_price")
//                        a.getInt("quantity")
//                        a.getInt("total")
//                        a.getInt("status_id")
//                        a.getInt("user_id")
//                        a.getString("firstname")
//                        a.getString("lastname")
//                        a.getString("email")
//                        a.getInt("active")
//                        a.getInt("orderDate")
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date birthDate = sdf.parse(a.getString("orderDate"));
//
                    }
                    Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
                    Log.i("VOLLEYDONE", "DONE");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("VOLLEYERROCATCH", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("VOLLEY", String.valueOf(error.networkResponse.statusCode));
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(sr);
    }

    public void createFood(Context context, int role, String name, int price, String image, int merchant_id) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "createFood.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    JSONObject a = data.getJSONObject(0);
//                    TODO: Ga tau ini data diapain
//                    a.getInt("food_id"),
//                            a.getString("food_name"),
//                            a.getInt("food_price"),
//                            a.getString("food_image"),
//                            a.getInt("merchant_id"),
//                    a.getInt("listed")
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fail to create food = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("food_name", name);
                params.put("food_price", Integer.toString(price));
                params.put("food_image", image);
                params.put("merchant_id", Integer.toString(merchant_id));
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
    }

    public void createOrder(Context context, int userid, int[] foodsid, int[] quantity, String proof) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "createOrder.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    JSONObject a = data.getJSONObject(0);
//                    TODO: Ga tau ini data diapain
//                        a.getInt("order_id")
//                        a.getInt("merchant_id")
//                        a.getInt("food_id")
//                        a.getString("food_name")
//                        a.getInt("food_price")
//                        a.getInt("quantity")
//                        a.getInt("total")
//                        a.getInt("status_id")
//                        a.getInt("user_id")
//                        a.getInt("orderDate")
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date birthDate = sdf.parse(a.getString("orderDate"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fail to create food = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", Integer.toString(userid));
                for(int i = 0; i < foodsid.length;i++){
                    params.put("food_id[]", Integer.toString(foodsid[i]));
                    params.put("quantity[]", Integer.toString(quantity[i]));
                }
                params.put("proof", proof);
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
    }
//    public List<User> getAllUser(Context context) {
//        RequestQueue queue = Volley.newRequestQueue(context);
//        List<User> users = new ArrayList<>();
//        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getAllUser.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject respObj = new JSONObject(response);
//                    //String success = respObj.getString("success");
//                    JSONArray data = respObj.getJSONArray("data");
//                    for (int i = 0; i < data.length(); i++) {
//                        JSONObject a = data.getJSONObject(i);
//
//                        if(a.getInt("role_id") == 1){
//                            users.add(new User(
//                                    a.getInt("user_id"),
//                                    a.getString("firstname"),
//                                    a.getString("lastname"),
//                                    a.getString("email"),
//                                    User.Role.CUSTOMER,
//                                    a.getInt("active")
//                            ));
//                        }else if (a.getInt("role_id") == 2){
//                            users.add(new User(
//                                    a.getInt("user_id"),
//                                    a.getString("firstname"),
//                                    a.getString("lastname"),
//                                    a.getString("email"),
//                                    User.Role.MERCHANT,
//                                    a.getInt("active")
//                            ));
//                        }
//                    }
//                    Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
//                    Log.i("VOLLEYDONE", users.get(0).getFirstname());
//                    Log.i("VOLLEYDONE", "DONE");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.i("VOLLEYERROCATCH", e.toString());
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("VOLLEY", String.valueOf(error.networkResponse.statusCode));
//                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(sr);
//        return users;
//    }
//
//    public List<Food> getAllFood(Context context, List<User> users) {
//        RequestQueue queue = Volley.newRequestQueue(context);
//        List<Food> foods = new ArrayList<>();
//        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getAllFood.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(context, "Loading Food", Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject respObj = new JSONObject(response);
//                    //String success = respObj.getString("success");
//                    JSONArray data = respObj.getJSONArray("data");
//                    for (int i = 0; i < data.length(); i++) {
//                        JSONObject a = data.getJSONObject(i);
//                        foods.add(new Food());
//                        foods.get(i).setId(a.getInt("food_id"));
//                        foods.get(i).setName(a.getString("food_name"));
//                        foods.get(i).setPrice(a.getInt("food_price"));
//                        foods.get(i).setImage(a.getString("food_image"));
//                        foods.get(i).setListed(a.getInt("listed"));
//
//                        int merchid = a.getInt("merchant_id");
//                        for (User users1 : users){
//                            if (users1.getId() == merchid){
//                                foods.get(i).setMerchant(users1);
//                            }
//                        }
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(sr);
//        return foods;
//    }

}



