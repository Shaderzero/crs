package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "rating_externals",
        uniqueConstraints = {@UniqueConstraint(columnNames = {
                "date_start",
                "counterparty_id",
                "rating_agency_id"
        })}
)
public class RatingExternal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;

    @ManyToOne
    @JoinColumn(name = "counterparty_id", referencedColumnName = "id", nullable = false)
    private Counterparty counterparty;

    @ManyToOne
    @JoinColumn(name = "rating_agency_id", referencedColumnName = "id", nullable = false)
    private RatingAgency ratingAgency;

    @ManyToOne
    @JoinColumn(name = "rating_Id", referencedColumnName = "id", nullable = false)
    private Rating rating;

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

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
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

    public void setRating(Rating ratingValueP) {
        this.rating = ratingValueP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingExternal that = (RatingExternal) o;

        if (id != that.id) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (counterparty != null ? !counterparty.equals(that.counterparty) : that.counterparty != null) return false;
        if (ratingAgency != null ? !ratingAgency.equals(that.ratingAgency) : that.ratingAgency != null) return false;
        return rating != null ? rating.equals(that.rating) : that.rating == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (counterparty != null ? counterparty.getId() : 0);
        result = 31 * result + (ratingAgency != null ? ratingAgency.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatingExternal{" +
                "id=" + id +
                ", startDate=" + dateStart +
                ", counterparty=" + counterparty +
                ", ratingAgency=" + ratingAgency +
                ", ratingValue=" + rating +
                '}';
    }
}
