package com.example.as;

import android.app.Application;

import com.example.as.Component.DaggerPokemonComponent;
import com.example.as.Component.PokemonComponent;
import com.example.as.Const.Url;
import com.example.as.Module.RetrofitModule;

public class app extends Application {
    private  static app apps;
    private PokemonComponent pokemonCOmponent;
    public app() {
        apps=this;
   pokemonCOmponent= DaggerPokemonComponent.builder().retrofitModule(new RetrofitModule(Url.BASEURL)).build();
    }

    public static app getApps() {
        return apps;
    }

    public PokemonComponent getPokemonCOmponent() {
        return pokemonCOmponent;
    }
}
