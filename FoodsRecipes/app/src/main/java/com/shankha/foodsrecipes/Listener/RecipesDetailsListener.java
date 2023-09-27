package com.shankha.foodsrecipes.Listener;

import com.shankha.foodsrecipes.Model.RecipesDetailsResponse;

public interface RecipesDetailsListener {
    void didFetch(RecipesDetailsResponse response, String message);
    void didError(String message);
}
