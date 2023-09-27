package com.shankha.foodsrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shankha.foodsrecipes.Adapter.IngredentsAdapter;
import com.shankha.foodsrecipes.Adapter.InstructionAdapter;
import com.shankha.foodsrecipes.Adapter.SimilarRecipesAdapter;
import com.shankha.foodsrecipes.Listener.InstructionListener;
import com.shankha.foodsrecipes.Listener.RecipesClickedListener;
import com.shankha.foodsrecipes.Listener.RecipesDetailsListener;
import com.shankha.foodsrecipes.Listener.SimilarRecipesLisetner;
import com.shankha.foodsrecipes.Model.InstructionResponse;
import com.shankha.foodsrecipes.Model.RecipesDetailsResponse;
import com.shankha.foodsrecipes.Model.SimilarRecipesResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsRecipeActivity extends AppCompatActivity {
    int id;
    TextView txtV_mealName,txtV_meal_summary,txtV_meal_source;
    RecyclerView recycle_meal_ingredents,recycle_meal_similar, recycle_meal_instruction;
    ImageView recipes_food_image;
    RequestManager requestManager;
    ProgressDialog progressDialog;
    IngredentsAdapter ingredentsAdapter;
    InstructionAdapter instructionAdapter;
    SimilarRecipesAdapter similarRecipesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_recipe);
        id=Integer.parseInt(getIntent().getStringExtra("id"));
        findView();

        requestManager=new RequestManager(this);
        requestManager.getRecipesDetails(recipesDetailsListener,id);
        requestManager.getSimilarRecipes(similarRecipesLisetner,id);
        requestManager.getInstruction(instructionListener,id);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Lodding . . . ");
        progressDialog.show();
    }

    private void findView() {
        txtV_mealName=findViewById(R.id.txtV_mealName);
        txtV_meal_summary=findViewById(R.id.txtV_meal_summary);
        txtV_meal_source=findViewById(R.id.txtV_meal_source);
        recipes_food_image=findViewById(R.id.recipes_food_image);
        recycle_meal_ingredents=findViewById(R.id.recycle_meal_ingredents);
        recycle_meal_similar=findViewById(R.id.recycle_meal_similar);
        recycle_meal_instruction=findViewById(R.id.recycle_meal_instruction);
    }

    private final RecipesDetailsListener recipesDetailsListener= new RecipesDetailsListener() {
        @Override
        public void didFetch(RecipesDetailsResponse response, String message) {
            progressDialog.dismiss();
            txtV_mealName.setText(response.title);
            txtV_meal_source.setText(response.sourceName);
            txtV_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(recipes_food_image);
            recycle_meal_ingredents.setHasFixedSize(true);
            recycle_meal_ingredents.setLayoutManager(new LinearLayoutManager(DetailsRecipeActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredentsAdapter =new IngredentsAdapter(DetailsRecipeActivity.this,response.extendedIngredients);
            recycle_meal_ingredents.setAdapter(ingredentsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(DetailsRecipeActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipesLisetner similarRecipesLisetner= new SimilarRecipesLisetner() {
        @Override
        public void didFetch(List<SimilarRecipesResponse> responses, String message) {
                recycle_meal_similar.setHasFixedSize(true);
                recycle_meal_similar.setLayoutManager(new LinearLayoutManager(DetailsRecipeActivity.this,LinearLayoutManager.HORIZONTAL,false));
                similarRecipesAdapter= new SimilarRecipesAdapter(DetailsRecipeActivity.this,responses,recipesClickedListener);
                recycle_meal_similar.setAdapter(similarRecipesAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
    
    private final RecipesClickedListener recipesClickedListener=new RecipesClickedListener() {
        @Override
        public void onRecipesClicked(String id) {
           startActivity(new Intent(DetailsRecipeActivity.this,DetailsRecipeActivity.class).putExtra("id",id));

        }
    };

    private final InstructionListener instructionListener=new InstructionListener() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
            recycle_meal_instruction.setHasFixedSize(true);
            recycle_meal_instruction.setLayoutManager(new LinearLayoutManager(DetailsRecipeActivity.this,LinearLayoutManager.VERTICAL,false));
            instructionAdapter=new InstructionAdapter(DetailsRecipeActivity.this,response);
            recycle_meal_instruction.setAdapter(instructionAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };

}