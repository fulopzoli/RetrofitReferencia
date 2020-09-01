package com.example.as.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.as.Model.ResultsEntity;
import com.example.as.Repository.MainRepository;


import java.util.List;

public class MainViewModel extends ViewModel {
    private MainRepository repository;
    private MutableLiveData<List<ResultsEntity>> getPokemons;

    public MainViewModel() {
        this.repository=new MainRepository();
        getPokemons=repository.getData();
    }



    public MutableLiveData<List<ResultsEntity>> getGetPokemons() {
        repository.callPokemons();
        return getPokemons;
    }
}
