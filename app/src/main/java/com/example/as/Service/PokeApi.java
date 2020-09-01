package com.example.as.Service;

import com.example.as.Model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

    @GET("pokemon")
    Call<Pokemon>POKEMON_CALL();
}
