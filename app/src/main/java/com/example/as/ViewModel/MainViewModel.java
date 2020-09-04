package com.example.as.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.as.Model.ResultsEntity;
import com.example.as.Model.spec.Flavor_text_entriesEntity;
import com.example.as.Repository.MainRepository;

import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;

public class MainViewModel extends ViewModel {
    private MainRepository repository;
    private MutableLiveData<List<ResultsEntity>> getPokemons;
    private MutableLiveData<List<Flavor_text_entriesEntity>> Szinszoveg ;

    public MainViewModel() {
        this.repository = new MainRepository();
        getPokemons = repository.getData();
        Szinszoveg=repository.getFlavor();
    }

    public MutableLiveData<List<Flavor_text_entriesEntity>> getSzinszoveg() {
        return Szinszoveg;
    }

    public void setIdCall(int id){
        repository.CallPokereszlet(id);
    }

    public MutableLiveData<List<ResultsEntity>> getGetPokemons() {
        repository.callPokemons();
        return getPokemons;
    }
}
