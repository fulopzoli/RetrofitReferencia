package com.example.as.Service;

import com.example.as.Model.Pokemon;
import com.example.as.Model.spec.Species;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApi {

    @GET("pokemon/?offset=0&limit=807")
    Call<Pokemon>POKEMON_CALL();

    @GET("pokemon-species/{id}")
    Call<Species>PokemonDatas(@Path("id") int id);



}
