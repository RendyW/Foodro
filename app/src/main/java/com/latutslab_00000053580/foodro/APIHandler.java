package com.latutslab_00000053580.foodro;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.latutslab_00000053580.foodro_home.MainActivity;
import com.latutslab_00000053580.recycler.OrderAdapter;
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
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);

//                    Cursor cursor = dbUser.getUser();
//                    cursor.moveToFirst();
                    // pindah activity
//                    if (user.getRole() == 1) {
//                        getAllMerchant(context);
//                    } else if (user.getRole() == 2) {
//                        //pinda ke tampilan merchant
//                        getOrderMerchant(context, user.getId(), null);
//                    }
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
                params.put("image", "");


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

    // list semua merchant
    public void getAllMerchant(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getAllMerchant.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    ArrayList<User> users = new ArrayList<User>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);

//                        TODO: update ui menu dari json di sini
                        users.add(
                                new User(
                                        a.getInt("user_id"),
                                        a.getString("firstname"),
                                        a.getString("lastname"),
                                        a.getString("email"),
                                        a.getInt("role_id"),
                                        a.getInt("active"),
                                        a.getString("image")

                                )
                        );
                    }


//                    if(isInit){
//                        Intent i = new Intent().setClass(context, MainActivity.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                        context.startActivity(i);
//                    }

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

    // list semua incoming order (diliat sama merchant)
    public void getOrderMerchant(Context context, int merchant_id, RecyclerView merchantRV) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("JALAN", "1");
        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "getOrderMerchant.php", new Response.Listener<String>() {
            ArrayList<Order> orders = new ArrayList<Order>();

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                try {
                    Log.i("JALAN", "2");
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);

                        ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
                        JSONArray details = a.getJSONArray("orderDetail");

                        for (int j = 0; j < details.length(); j++) {
                            JSONObject detail = details.getJSONObject(j);
                            JSONObject foodJson = detail.getJSONObject("food");
                            Food food = new Food(
                                    foodJson.getInt("food_id"),
                                    foodJson.getString("food_name"),
                                    foodJson.getInt("food_price"),
                                    foodJson.getString("food_image"),
                                    foodJson.getInt("merchant_id"),
                                    foodJson.getInt("listed")
                            );
                            orderDetails.add(new OrderDetail(
                                    a.getInt("order_id"),
                                    food,
                                    detail.getInt("quantity")
                            ));
                        }
                        JSONObject user = a.getJSONObject("user");
                        User customer = new User(
                                user.getInt("user_id"),
                                user.getString("firstname"),
                                user.getString("lastname"),
                                user.getString("email"),
                                1,
                                user.getInt("active"),
                                null
                        );

                        orders.add(new Order(a.getInt("order_id"),
                                customer,
                                a.getString("orderDate"),
                                orderDetails
                        ));
                    }
//                    if(isInit){
//                        Intent i = new Intent().setClass(context, MainActivity.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                        context.startActivity(i);
//                    }
                    OrderAdapter orderAdapter = new OrderAdapter(orders);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    merchantRV.setLayoutManager(linearLayoutManager);
                    merchantRV.setAdapter(orderAdapter);

                    Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
                    Log.i("VOLLEYDONE", "DONE");
                } catch (JSONException e) {
                    Log.i("JALAN", "3");
                    e.printStackTrace();
                    Log.i("VOLLEYERROCATCH", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("JALAN", "4");

//                Log.i("VOLLEY", String.valueOf(error.networkResponse.statusCode));
                Toast.makeText(context, "Fail to get response = " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
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

    // list semua order yang dibikin oleh customer (diliat sama customer)
    public void getOrderByCustomer(Context context, int user_id, RecyclerView orderRV) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "getOrderByCustomer.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                ArrayList<Order> orders = new ArrayList<Order>();
                try {
                    Log.i("JALAN", "2");
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = data.getJSONObject(i);

                        ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
                        JSONArray details = a.getJSONArray("orderDetail");

                        for (int j = 0; j < details.length(); j++) {
                            JSONObject detail = details.getJSONObject(j);
                            JSONObject foodJson = detail.getJSONObject("food");
                            Food food = new Food(
                                    foodJson.getInt("food_id"),
                                    foodJson.getString("food_name"),
                                    foodJson.getInt("food_price"),
                                    foodJson.getString("food_image"),
                                    foodJson.getInt("merchant_id"),
                                    foodJson.getInt("listed")
                            );
                            orderDetails.add(new OrderDetail(
                                    a.getInt("order_id"),
                                    food,
                                    detail.getInt("quantity")
                            ));
                        }
                        JSONObject user = a.getJSONObject("user");
                        User customer = new User(
                                user.getInt("user_id"),
                                user.getString("firstname"),
                                user.getString("lastname"),
                                user.getString("email"),
                                1,
                                user.getInt("active"),
                                null
                        );

                        orders.add(new Order(a.getInt("order_id"),
                                customer,
                                a.getString("orderDate"),
                                orderDetails
                        ));
                    }
//                    if(isInit){
//                        Intent i = new Intent().setClass(context, MainActivity.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                        context.startActivity(i);
//                    }
                    OrderAdapter orderAdapter = new OrderAdapter(orders);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    orderRV.setLayoutManager(linearLayoutManager);
                    orderRV.setAdapter(orderAdapter);

                    Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
                    Log.i("VOLLEYDONE", "DONE");
                } catch (JSONException e) {
                    Log.i("JALAN", "3");
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
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", Integer.toString(user_id));
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

    // list semua makanan yg ada dijual merchant tertentu (diliat sama customer)
    public void getFoodByMerchant(Context context, int merchant_id, RecyclerView foodRV) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.GET, endpoint + "getFoodByMerchant.php?merchant_id="+merchant_id, new Response.Listener<String>() {
            ArrayList<Food> foods = new ArrayList<Food>();

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    //String success = respObj.getString("success");
                    JSONObject data = respObj.getJSONObject("data");
                    JSONArray foodJson = data.getJSONArray("food");
                    JSONObject merchantJson = respObj.getJSONObject("merchant");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject a = foodJson.getJSONObject(i);

//                        TODO: masukin ke adapter recyclerview
                        foods.add(
                                new Food(
                                        a.getInt("food_id"),
                                        a.getString("food_name"),
                                        a.getInt("food_price"),
                                        a.getString("food_image"),
                                        a.getInt("merchant_id"),
                                        a.getInt("listed")
                                )
                        );
                    }
                    // klo butu aja
                    User merchant = new User(
                            merchantJson.getInt("user_id"),
                            merchantJson.getString("firstname"),
                            merchantJson.getString("lastname"),
                            merchantJson.getString("email"),
                            2,
                            merchantJson.getInt("active"),
                            merchantJson.getString("image")
                    );

                    Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
                    Log.i("VOLLEYDONE", "DONE");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("VOLLEYERROCATCH", e.toString());
                }
                // TODO: seting recyclerviewnya
//                OrderAdapter orderAdapter = new OrderAdapter(foods);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//                foodRV.setLayoutManager(linearLayoutManager);
//                foodRV.setAdapter(orderAdapter);
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

    // bikin listing makanan baru (diliat sama merchant)
    public void createFood(Context context, String name, int price, String image, int merchant_id) {
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
            protected Map<String, String> getParams() {
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

    // bikin orderan  baru (diliat sama customer)
    public void createOrder(Context context, int userid, ArrayList<Integer> foodsid, ArrayList<Integer> quantity, String proof) {
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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", Integer.toString(userid));
                for (int i = 0; i < foodsid.size(); i++) {
                    params.put("food_id[]", Integer.toString(foodsid.get(i)));
                    params.put("quantity[]", Integer.toString(quantity.get(i)));
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

    // update status makanan  (diliat sama merchant)
    // 1 = pending (otomatis 1 waktu ada order masuk)
    // 2 = ready
    // 3 = finished
    public void updateStatus(Context context, int order_id, int food_id, int newStatus) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST, endpoint + "updateOrderStatus.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    //String success = respObj.getString("success");
                    JSONArray data = respObj.getJSONArray("data");
                    JSONObject a = data.getJSONObject(0);
//                    TODO: Ga tau ini data diapain
//                      $detail = array();
//                      $detail["order_id"] = $r["order_id"];
//                      $detail["status_id"] = $r["status_id"];
//                      $detail["food_id"] = $r["food_id"];
//                      $detail["quantity"] = $r["quantity"];
//                      $detail["total"] = $r["total"];
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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("order_id", Integer.toString(order_id));
                params.put("food_id", Integer.toString(food_id));
                params.put("status", Integer.toString(newStatus));
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

}



