package com.miaowmere.animelist_bottomnav;

import android.os.Parcel;
import android.os.Parcelable;

public class Anime implements Parcelable {
  private String title;
  private String description;
  private String genre;
  private int poster;

  public Anime() {}

  protected Anime(Parcel in) {
    title = in.readString();
    description = in.readString();
    genre = in.readString();
    poster = in.readInt();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeString(description);
    dest.writeString(genre);
    dest.writeInt(poster);
  }

  public static final Creator<Anime> CREATOR = new Creator<Anime>() {
    @Override
    public Anime createFromParcel(Parcel in) {
      return new Anime(in);
    }

    @Override
    public Anime[] newArray(int size) {
      return new Anime[size];
    }
  };

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGenre() {
    return "Genre: " + genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public int getPoster() {
    return poster;
  }

  public void setPoster(int poster) {
    this.poster = poster;
  }
}
