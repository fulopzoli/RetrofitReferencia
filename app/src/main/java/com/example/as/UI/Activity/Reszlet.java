package com.example.as.UI.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.as.Const.Url;
import com.example.as.Model.ResultsEntity;
import com.example.as.Model.spec.Flavor_text_entriesEntity;
import com.example.as.ViewModel.MainViewModel;
import com.example.as.databinding.ActivityReszletBinding;

import java.util.List;

public class Reszlet extends AppCompatActivity {
    private ActivityReszletBinding binding;
    private MainViewModel mainViewModel;
    public static final String POKEMONID = "POKEMONIDKID";
    private String[] idd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReszletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final String id = getIntent().getStringExtra(POKEMONID);

        getSupportActionBar().setTitle("Részletek");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idd = id.split("/");

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Glide.with(this).load(Url.BASEPICURL + idd[idd.length - 1] + ".png").into(binding.Nagyobbkep);

        mainViewModel.setIdCall(Integer.parseInt(idd[idd.length - 1]));
        mainViewModel.getSzinszoveg().observe(this, new Observer<List<Flavor_text_entriesEntity>>() {
            @Override
            public void onChanged(List<Flavor_text_entriesEntity> flavor_text_entriesEntities) {
                String szoveg = "";
                String version;

                for (int i = 0; i < flavor_text_entriesEntities.size(); i++) {
                    if (flavor_text_entriesEntities.get(i).getLanguage().getName().equals("en")) {
                        char first = Character.toUpperCase(flavor_text_entriesEntities.get(i).getVersion().getName().charAt(0));
                        version = flavor_text_entriesEntities.get(i).getVersion().getName().replace(flavor_text_entriesEntities.get(i).getVersion().getName().charAt(0), first);
                        szoveg += "\n" + version + " : \n\n" + flavor_text_entriesEntities.get(i).getFlavor_text() + "\n";
                    }
                }
                binding.Reszletes.setText(szoveg);

            }
        });
        mainViewModel.getGetPokemons().observe(this, new Observer<List<ResultsEntity>>() {
            @Override
            public void onChanged(List<ResultsEntity> list) {
                binding.Textnev.setText(list.get(Integer.parseInt(idd[idd.length - 1]) - 1).getName()
                        .replace(list.get(Integer.parseInt(idd[idd.length - 1]) - 1).getName().charAt(0),
                                Character.toUpperCase(list.get(Integer.parseInt(idd[idd.length - 1]) - 1).getName().charAt(0))));
            }
        });


    }

}