package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RatingCountries")
public class RatingCountryCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "DateStart")
    private LocalDate dateStart;

    @ManyToOne
    @JoinColumn(name = "RatingAgencyId", referencedColumnName = "Id")
    private RatingAgencyCore ratingAgency;

    @ManyToOne
    @JoinColumn(name = "RatingId", referencedColumnName = "Id")
    private RatingCore rating;

    @ManyToOne
    @JoinColumn(name = "CountryId", referencedColumnName = "Id")
    private CountryCore country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public RatingAgencyCore getRatingAgency() {
        return ratingAgency;
    }

    public void setRatingAgency(RatingAgencyCore ratingAgency) {
        this.ratingAgency = ratingAgency;
    }

    public RatingCore getRating() {
        return rating;
    }

    public void setRating(RatingCore rating) {
        this.rating = rating;
    }

    public CountryCore getCountry() {
        return country;
    }

    public void setCountry(CountryCore country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCountryCore that = (RatingCountryCore) o;

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
        return "RatingCountryCore{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", ratingAgency=" + ratingAgency +
                ", rating=" + rating +
                ", country=" + country +
                '}';
    }
}
