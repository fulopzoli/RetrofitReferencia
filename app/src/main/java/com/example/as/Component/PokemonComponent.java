package com.example.as.Component;

import com.example.as.MainActivity;
import com.example.as.Module.RetrofitModule;
import com.example.as.Repository.MainRepository;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = RetrofitModule.class)
@Singleton
public interface PokemonComponent {
    void inject(MainRepository repository);
}

