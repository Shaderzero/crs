package ru.ge.data.entities2;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class RatingCounterpartyExt2Key implements Serializable {

    @ManyToOne
    @JoinColumn(name = "RatingAgencyID", referencedColumnName = "Id")
    private RatingAgency2 ratingAgency;

    @ManyToOne
    @JoinColumn(name = "RatingValueID", referencedColumnName = "Id")
    private RatingValue2 ratingValue;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "CounterpartyID", referencedColumnName = "Id")
    private Counterparty2 counterparty;

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

    public Counterparty2 getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty2 counterparty) {
        this.counterparty = counterparty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCounterpartyExt2Key that = (RatingCounterpartyExt2Key) o;

        if (!ratingAgency.equals(that.ratingAgency)) return false;
        if (!ratingValue.equals(that.ratingValue)) return false;
        if (!startDate.equals(that.startDate)) return false;
        return counterparty.equals(that.counterparty);

    }

    @Override
    public int hashCode() {
        int result = ratingAgency.hashCode();
        result = 31 * result + ratingValue.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + counterparty.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RatingCounterpartyExt2Key{" +
                "ratingAgency=" + ratingAgency +
                ", ratingValue=" + ratingValue +
                ", startDate=" + startDate +
                ", counterparty=" + counterparty +
                '}';
    }
}