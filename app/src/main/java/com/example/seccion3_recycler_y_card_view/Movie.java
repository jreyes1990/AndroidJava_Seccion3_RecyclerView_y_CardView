package com.example.seccion3_recycler_y_card_view;

public class Movie {
  public String name;
  public int poster;

  public Movie() {
  }

  public Movie(String name, int poster) {
    this.name = name;
    this.poster = poster;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPoster() {
    return poster;
  }

  public void setPoster(int poster) {
    this.poster = poster;
  }


}