package com.latutslab_00000053580.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.latutslab_00000053580.foodro.User;
import com.latutslab_00000053580.foodro_home.R;
import com.squareup.picasso.Picasso;

import android.net.Uri;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder> {

    private final ArrayList<User> userArrList;
    Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView merchantName;
        private final ImageView merchantImg;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            merchantName = (TextView) view.findViewById(R.id.merchantName);
            merchantImg = (ImageView) view.findViewById(R.id.merchantImage);

        }
    }

    public MerchantAdapter(ArrayList<User> userArrList) {
        this.userArrList = userArrList;
//        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout_merchant, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        User model = userArrList.get(position);
        viewHolder.merchantName.setText(model.getFullName());

        Picasso.get().load(model.getImage()).into(viewHolder.merchantImg);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return userArrList.size();
    }
}


