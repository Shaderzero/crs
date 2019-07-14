package ru.ge.data.entities2;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class RatingCounterpartyInt2Key implements Serializable {

    @ManyToOne
    @JoinColumn(name = "RatingValueID", referencedColumnName = "Id", nullable = false)
    private RatingValue2 ratingValue;

    @Column(name = "IsConservative", nullable = false)
    private boolean IsConservative;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "CounterpartyID", referencedColumnName = "Id", nullable = false)
    private Counterparty2 counterparty;

    @Column(name = "Analyst", length = 30, nullable = true)
    private String analyst;

    @Column(name = "Comments", length = 4000, nullable = true)
    private String comment;

    public RatingValue2 getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(RatingValue2 ratingValue) {
        this.ratingValue = ratingValue;
    }

    public boolean isConservative() {
        return IsConservative;
    }

    public void setConservative(boolean conservative) {
        IsConservative = conservative;
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

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCounterpartyInt2Key that = (RatingCounterpartyInt2Key) o;

        if (IsConservative != that.IsConservative) return false;
        if (ratingValue != null ? !ratingValue.equals(that.ratingValue) : that.ratingValue != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (counterparty != null ? !counterparty.equals(that.counterparty) : that.counterparty != null) return false;
        if (analyst != null ? !analyst.equals(that.analyst) : that.analyst != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;

    }

    @Override
    public int hashCode() {
        int result = ratingValue != null ? ratingValue.hashCode() : 0;
        result = 31 * result + (IsConservative ? 1 : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (counterparty != null ? counterparty.hashCode() : 0);
        result = 31 * result + (analyst != null ? analyst.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatingCounterpartyInt2Key{" +
                "ratingValue=" + ratingValue +
                ", IsConservative=" + IsConservative +
                ", startDate=" + startDate +
                ", counterparty=" + counterparty +
                ", analyst='" + analyst + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}