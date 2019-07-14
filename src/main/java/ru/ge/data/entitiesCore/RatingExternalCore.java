package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RatingExternals")
public class RatingExternalCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "DateStart")
    private LocalDate dateStart;

    @ManyToOne
    @JoinColumn(name = "CounterpartyId", referencedColumnName = "Id")
    private CounterpartyCore counterparty;

    @ManyToOne
    @JoinColumn(name = "RatingAgencyId", referencedColumnName = "Id")
    private RatingAgencyCore ratingAgency;

    @ManyToOne
    @JoinColumn(name = "RatingId", referencedColumnName = "Id")
    private RatingCore ratingValue;

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

    public CounterpartyCore getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(CounterpartyCore counterparty) {
        this.counterparty = counterparty;
    }

    public RatingAgencyCore getRatingAgency() {
        return ratingAgency;
    }

    public void setRatingAgency(RatingAgencyCore ratingAgency) {
        this.ratingAgency = ratingAgency;
    }

    public RatingCore getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(RatingCore ratingValue) {
        this.ratingValue = ratingValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingExternalCore that = (RatingExternalCore) o;

        if (id != that.id) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (counterparty != null ? !counterparty.equals(that.counterparty) : that.counterparty != null) return false;
        if (ratingAgency != null ? !ratingAgency.equals(that.ratingAgency) : that.ratingAgency != null) return false;
        return ratingValue != null ? ratingValue.equals(that.ratingValue) : that.ratingValue == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (counterparty != null ? counterparty.hashCode() : 0);
        result = 31 * result + (ratingAgency != null ? ratingAgency.hashCode() : 0);
        result = 31 * result + (ratingValue != null ? ratingValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatingExternalCore{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", counterparty=" + counterparty +
                ", ratingAgency=" + ratingAgency +
                ", ratingValue=" + ratingValue +
                '}';
    }
}
