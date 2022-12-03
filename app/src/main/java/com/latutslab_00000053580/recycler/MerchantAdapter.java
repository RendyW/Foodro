package com.latutslab_00000053580.recycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.latutslab_00000053580.foodro.User;
import com.latutslab_00000053580.foodro_home.R;
import com.latutslab_00000053580.foodro_home.MenuUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder>{

    private final ArrayList<User> userArrList;
//    private static ClickListener clickListener;
    private final Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView merchantName;
        private final ImageView merchantImg;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            merchantName = (TextView) view.findViewById(R.id.merchantName);
            merchantImg = (ImageView) view.findViewById(R.id.merchantImage);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
//            clickListener.onItemClick(getAbsoluteAdapterPosition(), v);

            int position = getAbsoluteAdapterPosition();

            Log.i("TESTON", "position: " + position);
            User user = userArrList.get(position);
            Intent intent = new Intent(context, MenuUser.class);
            intent.putExtra("id", user.getId());
            intent.putExtra("name", user.getFullName());
            intent.putExtra("image", user.getImage());
            context.startActivity(intent);
        }
    }

//    public void setOnItemClickListener(ClickListener clickListener){
//        MerchantAdapter.clickListener = clickListener;
//    }
//
//    public interface ClickListener {
//        void onItemClick(int position, View v);
//    }

    public MerchantAdapter(ArrayList<User> userArrList, Context context) {
        this.userArrList = userArrList;
        this.context = context;
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
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return userArrList.size();
    }
}


