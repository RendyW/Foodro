package com.latutslab_00000053580.recycler;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.latutslab_00000053580.foodro.Food;
import com.latutslab_00000053580.foodro_home.R;

import java.util.ArrayList;

public class MenuUserAdapter extends RecyclerView.Adapter<MenuUserAdapter.ViewHolder>{

    private final ArrayList<Food> foodArrayList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView menuAddHarga;
        private final TextView menuAddNama;
        private final ImageView menuAddImg;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            menuAddNama = (TextView) view.findViewById(R.id.menuAddName);
            menuAddHarga = (TextView) view.findViewById(R.id.menuAddPrice);
            menuAddImg = (ImageView) view.findViewById(R.id.menuAddImg);
        }
    }

    public MenuUserAdapter(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MenuUserAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout_menu_add  , viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Food model = foodArrayList.get(position);
        viewHolder.menuAddNama.setText(model.getName());
        viewHolder.menuAddHarga.setText(model.getPrice());

        Uri uri = Uri.parse(model.getImage());
        viewHolder.menuAddImg.setImageURI(uri);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }
}
