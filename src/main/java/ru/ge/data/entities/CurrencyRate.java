package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "CurrencyRates",
        uniqueConstraints = {@UniqueConstraint(columnNames = {
                "CurrencyFrom_id",
                "CurrencyTo_id",
                "DateReport"
        })}
)
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CurrencyFrom_id", referencedColumnName = "Id")
    private Currency fromCurrency;

    @ManyToOne
    @JoinColumn(name = "CurrencyTo_id", referencedColumnName = "Id")
    private Currency toCurrency;

    @Column(name = "Rate")
    private float rate;

    @Column(name = "DateReport", nullable = false)
    private LocalDate dateReport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyRate that = (CurrencyRate) o;

        if (id != that.id) return false;
        if (Float.compare(that.rate, rate) != 0) return false;
        if (fromCurrency != null ? !fromCurrency.equals(that.fromCurrency) : that.fromCurrency != null) return false;
        if (toCurrency != null ? !toCurrency.equals(that.toCurrency) : that.toCurrency != null) return false;
        return dateReport != null ? dateReport.equals(that.dateReport) : that.dateReport == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fromCurrency != null ? fromCurrency.hashCode() : 0);
        result = 31 * result + (toCurrency != null ? toCurrency.hashCode() : 0);
        result = 31 * result + (rate != +0.0f ? Float.floatToIntBits(rate) : 0);
        result = 31 * result + (dateReport != null ? dateReport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "id=" + id +
                ", fromCurrency=" + fromCurrency +
                ", toCurrency=" + toCurrency +
                ", rate=" + rate +
                ", dateReport=" + dateReport +
                '}';
    }
}
