package com.shankha.foodsrecipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shankha.foodsrecipes.Model.ExtendedIngredient;
import com.shankha.foodsrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredentsAdapter extends RecyclerView.Adapter<IngredentsViewHolder> {
    Context context;
    List<ExtendedIngredient> list;

    public IngredentsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredent,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredentsViewHolder holder, int position) {
                holder.txtV_ingredent_name.setText(list.get(position).name);
                holder.txtV_ingredent_name.setSelected(true);
                holder.txtV_ingredent_quentity.setText(list.get(position).original);
                holder.txtV_ingredent_quentity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.image_ingredent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class IngredentsViewHolder extends RecyclerView.ViewHolder{
    TextView txtV_ingredent_quentity,txtV_ingredent_name;
    ImageView image_ingredent;
    public IngredentsViewHolder(@NonNull View itemView) {
        super(itemView);
        txtV_ingredent_name=itemView.findViewById(R.id.txtV_ingredent_name);
        txtV_ingredent_quentity=itemView.findViewById(R.id.txtV_ingredent_quentity);
        image_ingredent=itemView.findViewById(R.id.image_ingredent);

    }
}
