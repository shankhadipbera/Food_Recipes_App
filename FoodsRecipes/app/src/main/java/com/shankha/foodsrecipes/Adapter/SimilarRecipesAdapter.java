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
import com.shankha.foodsrecipes.Model.SimilarRecipesResponse;
import com.shankha.foodsrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipesAdapter extends RecyclerView.Adapter<SimilarRecipesViewHolder> {
    Context context;
    List<SimilarRecipesResponse> list;
    RecipesClickedListener listener;

    public SimilarRecipesAdapter(Context context, List<SimilarRecipesResponse> list, RecipesClickedListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipesViewHolder holder, int position) {
        holder.txtV_similar_title.setText(list.get(position).title);
        holder.txtV_similar_title.setSelected(true);
        holder.txtV_similar_serving.setText(list.get(position).servings+" Person");
        holder.txtV_similar_serving.setSelected(true);
        Picasso.get().load("https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.similar_image);

        holder.cardV_similar.setOnClickListener(new View.OnClickListener() {
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

class SimilarRecipesViewHolder extends RecyclerView.ViewHolder{
    TextView txtV_similar_title, txtV_similar_serving;
    ImageView similar_image;
    CardView cardV_similar;
    public SimilarRecipesViewHolder(@NonNull View itemView) {
        super(itemView);
        txtV_similar_title=itemView.findViewById(R.id.txtV_similar_title);
                txtV_similar_serving=itemView.findViewById(R.id.txtV_similar_serving);
        similar_image=itemView.findViewById(R.id.similar_image);
        cardV_similar=itemView.findViewById(R.id.cardV_similar);
    }
}
