package com.latutslab_00000053580.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.latutslab_00000053580.foodro.Order;
import com.latutslab_00000053580.foodro.User;
import com.latutslab_00000053580.foodro_home.R;
import android.net.Uri;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private final ArrayList<Order> orderArrayList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderBuyer;
        private final TextView orderItem;
        private final TextView orderID;
        private final TextView orderTotal;
        private final Button btnReady;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            orderBuyer = (TextView) view.findViewById(R.id.orderBuyer);
            orderItem = (TextView) view.findViewById(R.id.orderItem);
            orderID = (TextView) view.findViewById(R.id.orderID);
            orderTotal = (TextView) view.findViewById(R.id.orderTotal);
            btnReady = (Button) view.findViewById(R.id.btnReady);

        }
    }

    public OrderAdapter(ArrayList<Order> orderArrayList) {
        this.orderArrayList = orderArrayList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_incoming_order_fragment, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Order model = orderArrayList.get(position);
        viewHolder.orderBuyer.setText(model.getUser().getUsername());
        viewHolder.orderItem.setText(model.getAllFood());
        viewHolder.orderTotal.setText("Total: Rp" + model.getTotal());
        viewHolder.orderID.setText(model.getId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }
}

