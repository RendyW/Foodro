package com.latutslab_00000053580.foodro_home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.latutslab_00000053580.foodro.Food;
import com.latutslab_00000053580.foodro.Order;
import com.latutslab_00000053580.foodro.User;
import com.latutslab_00000053580.recycler.MerchantAdapter;
import com.latutslab_00000053580.recycler.OrderAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeMerchantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeMerchantFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context mContext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeMerchantFragment() {
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
    public static HomeMerchantFragment newInstance(String param1, String param2) {
        HomeMerchantFragment fragment = new HomeMerchantFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_merchant, container, false);

        RecyclerView merchantRV = view.findViewById(R.id.orderRV);

        //nyimpen semua order
        ArrayList<Order> orderArrayList = new ArrayList<Order>();

        //nah disini lu nanti untuk setiap order, lu loop data makannya + quantity yang dipesen
        ArrayList<Food> foodArrayList = new ArrayList<Food>();
        foodArrayList.add(new Food("1", "nasi goreng", 15000, 4));
        foodArrayList.add(new Food("2", "nasi Bakar", 12000, 5));

        //disini ambil data buyer atau user yang beli
        User user = new User("yanto", "https://nos.jkt-1.neo.id/mcdonalds/assets/ico/richlink.jpg", 2);


        //diakhir loop masukin data order kedalem array list order
        orderArrayList.add(new Order("1", foodArrayList, user, "2"));

        //ini gk usah disentuh sih yang kebawah mah
        OrderAdapter orderAdapter = new OrderAdapter(orderArrayList);
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        merchantRV.setLayoutManager(linearLayoutManager);
        merchantRV.setAdapter(orderAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}