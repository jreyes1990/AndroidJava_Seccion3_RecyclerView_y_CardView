package com.example.seccion3_recycler_y_card_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private List<Movie> movies;
  private RecyclerView mRecyclerView;
  // Puede ser declarado como 'RecyclerView.Adapter' o como nuestra clase adaptador 'MyAdapter'
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  private int counter = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    movies = this.getAllMovies();

    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    mLayoutManager = new LinearLayoutManager(this);
    mLayoutManager = new GridLayoutManager(this, 2);
    mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

    /*  Implementamos nuestro OnItemClickListener propio, sobreescribiendo el metodo que nosotros
        definimos en el adaptador, y recibiendo los parametros que necesitamos.
     */
    mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(Movie movie, int position) {
        //deleteName(position);
        // Toast.makeText(MainActivity.this, name+" - "+position, Toast.LENGTH_SHORT).show();
      }
    });

    // Lo usamos en caso de que sepamos que el layout no va a cambiar de tamanio, mejorando la performance
    mRecyclerView.setHasFixedSize(true);
    // Aniade un efecto por defecto, si le pasamos null lo desactivamos por completo
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    // Enlazamos el layout manager y adaptador directamente al recycler_view
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()){
      case R.id.add_name:
        //this.addName(0);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
/*
  private void addName(int position){
    names.add(position, "New Name "+(++counter));
    mAdapter.notifyItemInserted(position);
    mLayoutManager.scrollToPosition(position);
  }

  private void deleteName(int position){
    names.remove(position);
    mAdapter.notifyItemRemoved(position);
  }
*/
  private List<Movie> getAllMovies(){
    return new ArrayList<Movie>(){
      {
        add(new Movie("Ben Hur", R.drawable.benhur));
        add(new Movie("DeadPool", R.drawable.deadpool));
        add(new Movie("Guardians of the Galaxy", R.drawable.guardians));
        add(new Movie("Warcreaft", R.drawable.warcraft));
      }
    };
  }
}