package com.example.as.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.as.Model.Pokemon;
import com.example.as.Model.ResultsEntity;
import com.example.as.Model.spec.Flavor_text_entriesEntity;
import com.example.as.Model.spec.Species;
import com.example.as.Service.PokeApi;
import com.example.as.app;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainRepository {

    @Inject
    Retrofit retrofit;
    private PokeApi pokeApi;

    private MutableLiveData<List<ResultsEntity>> data = new MutableLiveData<>();
    private MutableLiveData<List<Flavor_text_entriesEntity>> Flavor=new MutableLiveData<>();

    public MainRepository() {
        app.getApps().getPokemonCOmponent().inject(this);
        this.pokeApi = retrofit.create(PokeApi.class);

    }

    public MutableLiveData<List<ResultsEntity>> getData() {
        return data;
    }

    public MutableLiveData<List<Flavor_text_entriesEntity>> getFlavor() {
        return Flavor;
    }

    public void CallPokereszlet(int id){
        pokeApi.PokemonDatas(id).enqueue(new Callback<Species>() {
            @Override
            public void onResponse(Call<Species> call, Response<Species> response) {
                if (!response.isSuccessful()){
                    List<Flavor_text_entriesEntity> hiba=new ArrayList<>();
                    hiba.add(new Flavor_text_entriesEntity(response.message()));
                    Flavor.setValue(hiba);
                    return;
                }
                Flavor.setValue(response.body().getFlavor_text_entries());
            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {
                List<Flavor_text_entriesEntity> hiba=new ArrayList<>();
                hiba.add(new Flavor_text_entriesEntity(t.getMessage()));
                Flavor.setValue(hiba);
            }
        });
    }

    public void callPokemons() {
    pokeApi.POKEMON_CALL().enqueue(new Callback<Pokemon>() {
        @Override
        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (!response.isSuccessful()){
                    List<ResultsEntity> hiba=new ArrayList<>();
                    hiba.add(new ResultsEntity(null,null));
                    data.setValue(hiba);
                    return;
                }
                data.setValue(response.body().getResults());
        }

        @Override
        public void onFailure(Call<Pokemon> call, Throwable t) {
            List<ResultsEntity> hiba=new ArrayList<>();
            hiba.add(new ResultsEntity(null,null));
            data.setValue(hiba);

        }
    });
    }
}
