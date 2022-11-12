package com.latutslab_00000053580.foodro_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.latutslab_00000053580.foodro_home.databinding.ActivityMainBinding;

public class Home_Merchant extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeMerchantFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeMerchantFragment());
                    NoIncomingOrderFragment frag1 = new NoIncomingOrderFragment();
                    frag1.setArguments(getIntent().getExtras());

                    FragmentManager fragManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragManager.beginTransaction();

                    fragTransaction.add(R.id.frameHomeMerchant, frag1);
                    fragTransaction.commit();
                    break;
                case R.id.order:
                    replaceFragment(new NoIncomingOrderFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileNavigationFragment());
                    break;
            }

            return true;
        });

        //Fragment Incoming Order
        NoIncomingOrderFragment frag1 = new NoIncomingOrderFragment();
        frag1.setArguments(getIntent().getExtras());

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();

        fragTransaction.add(R.id.frameHomeMerchant, frag1);
        fragTransaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}