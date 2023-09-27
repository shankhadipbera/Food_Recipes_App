package com.shankha.foodsrecipes.Listener;

import com.shankha.foodsrecipes.Model.InstructionResponse;
import com.shankha.foodsrecipes.Model.RecipesDetailsResponse;

import java.util.List;

public interface InstructionListener {
    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);
}
