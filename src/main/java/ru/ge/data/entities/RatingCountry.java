package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RatingCountries")
public class RatingCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "DateStart", nullable = false)
    private LocalDate dateStart;

    @ManyToOne
    @JoinColumn(name = "RatingAgency_id", referencedColumnName = "Id")
    private RatingAgency ratingAgency;

    @ManyToOne
    @JoinColumn(name = "Rating_id", referencedColumnName = "Id")
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "Country_id", referencedColumnName = "Id")
    private Country country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate startDate) {
        this.dateStart = startDate;
    }

    public RatingAgency getRatingAgency() {
        return ratingAgency;
    }

    public void setRatingAgency(RatingAgency ratingAgency) {
        this.ratingAgency = ratingAgency;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating ratingValue) {
        this.rating = ratingValue;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCountry that = (RatingCountry) o;

        if (id != that.id) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (ratingAgency != null ? !ratingAgency.equals(that.ratingAgency) : that.ratingAgency != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (ratingAgency != null ? ratingAgency.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatingCountry{" +
                "id=" + id +
                ", startDate=" + dateStart +
                ", ratingAgency=" + ratingAgency +
                ", ratingValue=" + rating +
                ", country=" + country +
                '}';
    }
}
