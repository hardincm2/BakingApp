package com.brassbeluga.bakingapp.data;

import android.util.Log;

import com.brassbeluga.bakingapp.model.Recipe;
import com.brassbeluga.bakingapp.task.GetHttpResponseTask;
import com.brassbeluga.bakingapp.util.NetworkUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.URL;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RecipesDataAccessor implements GetHttpResponseTask.HttpResponseListener {
    private static final String TAG = RecipesDataAccessor.class.getSimpleName();

    private static final String RECIPES_RESOURCE_URL
            = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Inject
    public RecipesDataAccessor() {}

    /**
     * Fetches the recipes.
     */
    public void getRecipes() {
        URL recipesUrl = NetworkUtil.buildUrl(RECIPES_RESOURCE_URL);
        new GetHttpResponseTask(this).execute(recipesUrl);
    }


    @Override
    public void onHttpResponseFinished(String response) {
        try {
            // Convert the response into the appropriate model provided in the input
            Recipe[] recipesResponse = new Gson().fromJson(response, Recipe[].class);
            int temp = recipesResponse.length;
        } catch (JsonSyntaxException ex) {
            Log.e(TAG, "Error reading JSON response", ex);
        }
    }
}
