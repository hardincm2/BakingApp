package com.brassbeluga.bakingapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brassbeluga.bakingapp.R;
import com.brassbeluga.bakingapp.data.RecipesDataAccessor;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    RecipesDataAccessor recipesDataAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipesDataAccessor.getRecipes();
    }
}
