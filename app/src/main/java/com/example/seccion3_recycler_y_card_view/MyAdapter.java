package com.example.seccion3_recycler_y_card_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
  private List<Movie> movies;
  private int layout;
  private OnItemClickListener itemClickListener;

  public MyAdapter(List<Movie> movies, int layout, OnItemClickListener itemClickListener) {
    this.movies = movies;
    this.layout = layout;
    this.itemClickListener = itemClickListener;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    ViewHolder vh = new ViewHolder(v);

    return vh;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(movies.get(position), itemClickListener);
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewName;
    public ImageView imageViewPoster;

    public ViewHolder(@NonNull View itemView) {
      /*  Recive la view completa. La pasa al constructor padre y enlazamos referencias UI
          con nuestras propiedades ViewHolder declaramos justo arriba */
      super(itemView);
      this.textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
      this.imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
    }

    public void bind(final Movie movie, final OnItemClickListener listener){
      //this.textViewName.setText(movie);
      // Procesamos los datos a renderizar
      this.textViewName.setText(movie.getName());
      Picasso.get().load(movie.getPoster()).fit().into(this.imageViewPoster);
      //this.imageViewPoster.setImageResource(movie.getPoster());

      /* Definimos que por cada elemento de nuestro recycler view, tenemos un click listener
         que se comporta de la siguiente manera */
      itemView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
          // Pasasmos nuestro objeto modelo (este caso Strin) y posicion
          listener.onItemClick(movie, getAdapterPosition());
        }
      });
    }
  }

  // Declaramos nuestra interfaz con el/los metodo/s a implementar
  public interface OnItemClickListener{
    void onItemClick(Movie movie, int position);
  }
}
