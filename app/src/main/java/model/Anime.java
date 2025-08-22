package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Anime implements Parcelable {
    private int gambar;
    private String judul;
    private int tahun;
    private String genre;
    private String sinopsis;
    private double rating;
    private int jumlahEpisode;

    
    public Anime(int gambar, String judul, int tahun, String genre, String sinopsis, double rating, int jumlahEpisode) {
        this.gambar = gambar;
        this.judul = judul;
        this.tahun = tahun;
        this.genre = genre;
        this.sinopsis = sinopsis;
        this.rating = rating;
        this.jumlahEpisode = jumlahEpisode;
    }

    
    public int getGambar() { return gambar; }
    public String getJudul() { return judul; }
    public int getTahun() { return tahun; }
    public String getGenre() { return genre; }
    public String getSinopsis() { return sinopsis; }
    public double getRating() { return rating; }
    public int getJumlahEpisode() { return jumlahEpisode; }

    

    protected Anime(Parcel in) {
        gambar = in.readInt();
        judul = in.readString();
        tahun = in.readInt();
        genre = in.readString();
        sinopsis = in.readString();
        rating = in.readDouble();
        jumlahEpisode = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gambar);
        dest.writeString(judul);
        dest.writeInt(tahun);
        dest.writeString(genre);
        dest.writeString(sinopsis);
        dest.writeDouble(rating);
        dest.writeInt(jumlahEpisode);
    }

    @Override
    public int describeContents() {
        return 0;
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
}