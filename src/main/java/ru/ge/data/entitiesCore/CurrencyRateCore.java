package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CurrencyRates")
public class CurrencyRateCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CurrencyFromId", referencedColumnName = "Id")
    private CurrencyCore currencyFrom;

    @ManyToOne
    @JoinColumn(name = "CurrencyToId", referencedColumnName = "Id")
    private CurrencyCore currencyTo;

    @Column(name = "Rate")
    private float rate;

    @Column(name = "DateRate")
    private LocalDate dateRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CurrencyCore getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(CurrencyCore currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public CurrencyCore getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(CurrencyCore currencyTo) {
        this.currencyTo = currencyTo;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public LocalDate getDateRate() {
        return dateRate;
    }

    public void setDateRate(LocalDate dateRate) {
        this.dateRate = dateRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyRateCore that = (CurrencyRateCore) o;

        if (id != that.id) return false;
        if (Float.compare(that.rate, rate) != 0) return false;
        if (currencyFrom != null ? !currencyFrom.equals(that.currencyFrom) : that.currencyFrom != null) return false;
        if (currencyTo != null ? !currencyTo.equals(that.currencyTo) : that.currencyTo != null) return false;
        return dateRate != null ? dateRate.equals(that.dateRate) : that.dateRate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (currencyFrom != null ? currencyFrom.hashCode() : 0);
        result = 31 * result + (currencyTo != null ? currencyTo.hashCode() : 0);
        result = 31 * result + (rate != +0.0f ? Float.floatToIntBits(rate) : 0);
        result = 31 * result + (dateRate != null ? dateRate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrencyRateCore{" +
                "id=" + id +
                ", currencyFrom=" + currencyFrom +
                ", currencyTo=" + currencyTo +
                ", rate=" + rate +
                ", dateRate=" + dateRate +
                '}';
    }
}
