package com.latutslab_00000053580.recycler;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.latutslab_00000053580.foodro.Cart;
import com.latutslab_00000053580.foodro.Food;
import com.latutslab_00000053580.foodro.OrderDetail;
import com.latutslab_00000053580.foodro_home.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final ArrayList<Cart> cartArrayList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView basketNama;
        private final TextView basketHarga;
        private final ImageView basketImage;
        private final TextView basketTotal;
        private final TextView basketQty;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            basketNama = (TextView) view.findViewById(R.id.basketName);
            basketHarga = (TextView) view.findViewById(R.id.basketPrice);
            basketImage = (ImageView) view.findViewById(R.id.basketImage);
            basketTotal = (TextView) view.findViewById(R.id.basketTotal);
            basketQty = (TextView) view.findViewById(R.id.basketQty);

        }
    }

    public CartAdapter(ArrayList<Cart> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout_menu_in_basket, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Cart model = cartArrayList.get(position);
        viewHolder.basketHarga.setText(model.getPrice());
        viewHolder.basketNama.setText(model.getName());
        viewHolder.basketQty.setText(model.getQuantity());
        viewHolder.basketTotal.setText(model.getTotal());

        Uri uri = Uri.parse(model.getImage());
        viewHolder.basketImage.setImageURI(uri);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }
}
