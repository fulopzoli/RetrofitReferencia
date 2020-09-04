package com.example.as.UI.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as.Adapter.PokemonRecyAdapter;
import com.example.as.Model.ResultsEntity;
import com.example.as.R;
import com.example.as.ViewModel.MainViewModel;
import com.example.as.databinding.ActivityMainBinding;

import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private Context context;
    private PokemonRecyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;


        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getGetPokemons().observe(this, new Observer<List<ResultsEntity>>() {
            @Override
            public void onChanged(List<ResultsEntity> list) {
                Recycle(context, list);
                Inittouchhelper(list);


            }
        });


    }

    private void Recycle(Context context, List<ResultsEntity> lista) {
        binding.recy.setLayoutManager(new GridLayoutManager(context, 3));
        adapter = new PokemonRecyAdapter(context);
        adapter.setLista(lista);
        binding.recy.setAdapter(adapter);
    }

    private void Inittouchhelper(final List<ResultsEntity> lista) {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int eredeti = viewHolder.getAdapterPosition();
                int helyett = target.getAdapterPosition();
                Collections.swap(lista, eredeti, helyett);
                adapter.notifyItemMoved(eredeti, helyett);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        helper.attachToRecyclerView(binding.recy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.keresmenu, menu);
        MenuItem kereses = menu.findItem(R.id.kereses);
        SearchView searchView = (SearchView) kereses.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}