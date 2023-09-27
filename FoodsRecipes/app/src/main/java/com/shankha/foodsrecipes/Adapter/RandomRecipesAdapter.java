package com.shankha.foodsrecipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shankha.foodsrecipes.Listener.RecipesClickedListener;
import com.shankha.foodsrecipes.Model.Recipe;
import com.shankha.foodsrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipesAdapter extends RecyclerView.Adapter<RandomRecipesViewHolder>{
    Context context;
    List<Recipe> list;
    RecipesClickedListener listener;

    public RandomRecipesAdapter(Context context, List<Recipe> list,RecipesClickedListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RandomRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipesViewHolder holder, int position) {
        holder.txtV_title.setText(list.get(position).title);
        holder.txtV_title.setSelected(true);
        holder.txtV_like.setText(list.get(position).aggregateLikes+" Likes");
        holder.txtV_group.setText(list.get(position).servings+" Serving");
        holder.txtV_timer.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.food_image);

        holder.random_list_contener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipesClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomRecipesViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_contener;
    TextView txtV_title;
    ImageView food_image;
    TextView txtV_like;
    TextView txtV_group;
    TextView txtV_timer;


    public RandomRecipesViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_contener=itemView.findViewById(R.id.random_list_contener);
        txtV_group=itemView.findViewById(R.id.txtV_group);
        txtV_like=itemView.findViewById(R.id.txtV_like);
        txtV_timer=itemView.findViewById(R.id.txtV_timer);
        txtV_title=itemView.findViewById(R.id.txtV_title);
        food_image=itemView.findViewById(R.id.food_image);
    }
}