package com.shankha.foodsrecipes.Listener;

import com.shankha.foodsrecipes.Model.SimilarRecipesResponse;

import java.util.List;

public interface SimilarRecipesLisetner {
    void didFetch(List<SimilarRecipesResponse> responses, String message);
    void didError(String message);
}
