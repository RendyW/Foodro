package com.latutslab_00000053580.recycler;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.latutslab_00000053580.foodro.Food;
import com.latutslab_00000053580.foodro.Order;
import com.latutslab_00000053580.foodro_home.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final ArrayList<Food> foodArrayList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView menuNama;
        private final TextView menuHarga;
        private final ImageView menuImage;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            menuNama = (TextView) view.findViewById(R.id.orderBuyer);
            menuHarga = (TextView) view.findViewById(R.id.menuHarga);
            menuImage = (ImageView) view.findViewById(R.id.menuImage);
        }
    }

    public MenuAdapter(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout_menu_merchant, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Food model = foodArrayList.get(position);
        viewHolder.menuNama.setText(model.getName());
        viewHolder.menuHarga.setText(model.getPrice());

        Uri uri = Uri.parse(model.getImage());
        viewHolder.menuImage.setImageURI(uri);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }
}


