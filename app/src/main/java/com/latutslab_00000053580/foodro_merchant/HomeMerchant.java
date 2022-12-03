package com.latutslab_00000053580.foodro_merchant;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.latutslab_00000053580.foodro.APIHandler;
import com.latutslab_00000053580.foodro.Food;
import com.latutslab_00000053580.foodro.Order;
import com.latutslab_00000053580.foodro.User;
import com.latutslab_00000053580.foodro_home.R;
import com.latutslab_00000053580.recycler.OrderAdapter;
import com.latutslab_00000053580.sqlite.DbUser;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeMerchant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeMerchant extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context mContext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeMerchant() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeMerchantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeMerchant newInstance(String param1, String param2) {
        HomeMerchant fragment = new HomeMerchant();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.merchant_home, container, false);

        RecyclerView merchantRV = view.findViewById(R.id.orderRV);
        APIHandler handler = new APIHandler();
        handler.getOrderMerchant(getContext(), 5, merchantRV);
//        DbUser dbuser = new DbUser(getContext());
//        Cursor cursor = dbuser.getUser();
//
//        int userid = cursor.getInt(0);
        //api.getOrderMerchant(getContext(), userid, false);


        //nyimpen semua order
//        ArrayList<Order> orderArrayList = new ArrayList<Order>();

        //nah disini lu nanti untuk setiap order, lu loop data makannya + quantity yang dipesen
//        ArrayList<Food> foodArrayList = new ArrayList<Food>();
//        foodArrayList.add(new Food(1, "Nasi Goreng", 15000, "0", 0 ));
//        foodArrayList.add(new Food(2, "Nasi Bakar", 21000, "0", 0 ));

//        int[] quantity = new int[] {1, 2};

        //disini ambil data buyer atau user yang beli
//        User user = new User(1, "Uccok", "Siregar", "12", 1, 1, "https://tinypng.com/images/social/website.jpg");


        //diakhir loop masukin data order kedalem array list order

//        Locale locale = new Locale("id", "ID");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss OOOO", locale);
//        orderArrayList.add(new Order(1, user, "15/10/2003", foodArrayList, quantity));

        //ini gk usah disentuh sih yang kebawah mah
//        OrderAdapter orderAdapter = new OrderAdapter(orderArrayList);
//        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
//        merchantRV.setLayoutManager(linearLayoutManager);
//        merchantRV.setAdapter(orderAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}