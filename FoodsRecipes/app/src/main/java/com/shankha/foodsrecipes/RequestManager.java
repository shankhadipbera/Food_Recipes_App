package com.shankha.foodsrecipes;

import android.content.Context;


import com.shankha.foodsrecipes.Listener.InstructionListener;
import com.shankha.foodsrecipes.Listener.RandomRecipesResponseListener;
import com.shankha.foodsrecipes.Listener.RecipesDetailsListener;
import com.shankha.foodsrecipes.Listener.SimilarRecipesLisetner;
import com.shankha.foodsrecipes.Model.InstructionResponse;
import com.shankha.foodsrecipes.Model.RandomRecipesApiResponse;
import com.shankha.foodsrecipes.Model.RecipesDetailsResponse;
import com.shankha.foodsrecipes.Model.SimilarRecipesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder()

            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public  void getRandomRecipes(RandomRecipesResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes= retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipesApiResponse> call= callRandomRecipes.callRandomRecipes(context.getString(R.string.API_KEY),"30", tags);
        call.enqueue(new Callback<RandomRecipesApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipesApiResponse> call, Response<RandomRecipesApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipesApiResponse> call, Throwable t) {
                            listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipesDetails(RecipesDetailsListener listener, int id){
        CallRecipesDetails callRecipesDetails= retrofit.create(CallRecipesDetails.class);
        Call<RecipesDetailsResponse> call= callRecipesDetails.callRecipesDetails(id,context.getString(R.string.API_KEY));
        call.enqueue(new Callback<RecipesDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipesDetailsResponse> call, Response<RecipesDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RecipesDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimilarRecipesLisetner listener,int id){
        CallSimilarRecipes callSimilarRecipes=retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipesResponse>> call=callSimilarRecipes.callSimilarRecipes(id,"20",context.getString(R.string.API_KEY));
        call.enqueue(new Callback<List<SimilarRecipesResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipesResponse>> call, Response<List<SimilarRecipesResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipesResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });

    }

    public void getInstruction(InstructionListener listener,int id){
        CallInstruction callInstruction=retrofit.create(CallInstruction.class);
        Call<List<InstructionResponse>> call= callInstruction.callInstruction(id,context.getString(R.string.API_KEY));
        call.enqueue(new Callback<List<InstructionResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionResponse>> call, Response<List<InstructionResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipesApiResponse> callRandomRecipes(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    private interface CallRecipesDetails {
        @GET("recipes/{id}/information")

        Call<RecipesDetailsResponse> callRecipesDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );

    }

    private interface CallSimilarRecipes {
        @GET("recipes/{id}/similar")

        Call<List<SimilarRecipesResponse>> callSimilarRecipes(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallInstruction {
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionResponse>> callInstruction(
                @Path("id") int id,
                @Query("apiKey") String apikey

        );
    }
}
