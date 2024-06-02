package com.shankha.foodsrecipes.Listener;

import com.shankha.foodsrecipes.Model.RandomRecipesApiResponse;

public interface RandomRecipesResponseListener {
    void didFetch(RandomRecipesApiResponse response, String message);
    void didError(String message);
}
