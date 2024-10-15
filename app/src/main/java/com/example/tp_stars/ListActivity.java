package com.example.tp_stars;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_stars.adapter.StarAdapter;
import com.example.tp_stars.beans.Star;
import com.example.tp_stars.service.StarService;

import tp.ensa.ma.stargallery.R;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StarAdapter starAdapter;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Configuration de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycle_view);
        service = StarService.getInstance();

        // Initialisation des données
        init(service);

        // Configuration du RecyclerView
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialiser les données de démonstration
    private void init(StarService service) {
        service.create(new Star("Kate Bosworth", R.drawable.kate_bosworth, 3.5f));
        service.create(new Star("George Clooney", R.drawable.george_clooney, 3.0f));
        service.create(new Star("Michelle Rodriguez", R.drawable.michelle_rodriguez, 5.0f));
        service.create(new Star("Jennifer Lawrence", R.drawable.jennifer_lawrence, 4.0f));
        service.create(new Star("Leonardo DiCaprio", R.drawable.leonardo_dicaprio, 4.5f));
        service.create(new Star("Scarlett Johansson", R.drawable.scarlett_johansson, 4.2f));
        service.create(new Star("Robert Downey Jr.", R.drawable.robert_downey_jr, 3.8f));
        service.create(new Star("Emma Stone", R.drawable.emma_stone, 4.1f));
        service.create(new Star("Tom Hanks", R.drawable.tom_hanks, 4.6f));
        service.create(new Star("Angelina Jolie", R.drawable.angelina_jolie, 3.9f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Configuration de la recherche
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Rechercher une star");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                starAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                starAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Gérer les éléments du menu
        int id = item.getItemId();
        if (id == R.id.action_share) {
            shareApp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Méthode pour partager l'application
    private void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Découvrez l'application TP Stars pour explorer les célébrités et leurs évaluations !");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Partager via"));
    }
}
