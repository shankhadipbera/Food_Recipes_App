package com.shankha.foodsrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.shankha.foodsrecipes.Adapter.RandomRecipesAdapter;
import com.shankha.foodsrecipes.Listener.RandomRecipesResponseListener;
import com.shankha.foodsrecipes.Listener.RecipesClickedListener;
import com.shankha.foodsrecipes.Model.RandomRecipesApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    RequestManager requestManager;
    RandomRecipesAdapter randomRecipesAdapter;
    Spinner spinner;
    List<String> tags=new ArrayList<>();
    InputMethodManager imm;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading ...");
        searchView=findViewById(R.id.search_view_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

                tags.clear();
                tags.add(query);
                requestManager.getRandomRecipes(randomRecipesResponseListener,tags);
                progressDialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        spinner =findViewById(R.id.spinner_tag);
        ArrayAdapter arrayAdapter= ArrayAdapter.createFromResource(this,R.array.tags,R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);

        requestManager=new RequestManager(this);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);
       // requestManager.getRandomRecipes(randomRecipesResponseListener);
      //  progressDialog.show();
    }
    private final RandomRecipesResponseListener randomRecipesResponseListener= new RandomRecipesResponseListener() {
        @Override
        public void didFetch(RandomRecipesApiResponse response, String message) {
            progressDialog.dismiss();
            recyclerView=findViewById(R.id.recycle_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomRecipesAdapter =new RandomRecipesAdapter(MainActivity.this,response.recipes,recipesClickedListener);
            recyclerView.setAdapter(randomRecipesAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            requestManager.getRandomRecipes(randomRecipesResponseListener,tags);
            progressDialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final RecipesClickedListener recipesClickedListener =new RecipesClickedListener() {
        @Override
        public void onRecipesClicked(String id) {
            startActivity(new Intent(MainActivity.this,DetailsRecipeActivity.class).putExtra("id",id));

        }
    };
}