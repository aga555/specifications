package specification.demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private double rating;
    private double watchTime;

    private int year;

    public Movie() {
    }

    public Movie(String title, String genre, double rating, double watchTime, int year) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.watchTime = watchTime;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(double watchTime) {
        this.watchTime = watchTime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
