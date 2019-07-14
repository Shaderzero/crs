package ru.ge.data.entities2;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class RatingCountry2Key implements Serializable {

    @ManyToOne
    @JoinColumn(name = "RatingAgencyID", referencedColumnName = "Id")
    private RatingAgency2 ratingAgency;

    @ManyToOne
    @JoinColumn(name = "RatingValueID", referencedColumnName = "Id")
    private RatingValue2 ratingValue;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "CountryID", referencedColumnName = "Id")
    private Country2 country;

    public RatingAgency2 getRatingAgency() {
        return ratingAgency;
    }

    public void setRatingAgency(RatingAgency2 ratingAgency) {
        this.ratingAgency = ratingAgency;
    }

    public RatingValue2 getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(RatingValue2 ratingValue) {
        this.ratingValue = ratingValue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Country2 getCountry() {
        return country;
    }

    public void setCountry(Country2 country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCountry2Key that = (RatingCountry2Key) o;

        if (ratingAgency != null ? !ratingAgency.equals(that.ratingAgency) : that.ratingAgency != null) return false;
        if (ratingValue != null ? !ratingValue.equals(that.ratingValue) : that.ratingValue != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;

    }

    @Override
    public int hashCode() {
        int result = ratingAgency != null ? ratingAgency.hashCode() : 0;
        result = 31 * result + (ratingValue != null ? ratingValue.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatingCountry2Key{" +
                "ratingAgency=" + ratingAgency +
                ", ratingValue=" + ratingValue +
                ", startDate=" + startDate +
                ", country=" + country +
                '}';
    }
}