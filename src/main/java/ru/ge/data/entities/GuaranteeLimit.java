package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "guarantee_limits")
public class GuaranteeLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "guarantor_id", referencedColumnName = "id")
    private Counterparty guarantor;

    @Column(name = "date_agree_start", nullable = false)
    private LocalDate dateAgreeStart;

    @Column(name = "date_agree_end")
    private LocalDate dateAgreeEnd;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "amount")
    private long amount;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currencyP;

    @ManyToOne
    @JoinColumn(name = "guarantee_type_id", referencedColumnName = "id")
    private GuaranteeType guaranteeTypeP;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Counterparty getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(Counterparty guarantor) {
        this.guarantor = guarantor;
    }

    public LocalDate getDateAgreeStart() {
        return dateAgreeStart;
    }

    public void setDateAgreeStart(LocalDate dateAgreeStart) {
        this.dateAgreeStart = dateAgreeStart;
    }

    public LocalDate getDateAgreeEnd() {
        return dateAgreeEnd;
    }

    public void setDateAgreeEnd(LocalDate dateAgreeEnd) {
        this.dateAgreeEnd = dateAgreeEnd;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Currency getCurrencyP() {
        return currencyP;
    }

    public void setCurrencyP(Currency currencyP) {
        this.currencyP = currencyP;
    }

    public GuaranteeType getGuaranteeTypeP() {
        return guaranteeTypeP;
    }

    public void setGuaranteeTypeP(GuaranteeType guaranteeTypeP) {
        this.guaranteeTypeP = guaranteeTypeP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuaranteeLimit that = (GuaranteeLimit) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (guarantor != null ? !guarantor.equals(that.guarantor) : that.guarantor != null) return false;
        if (dateAgreeStart != null ? !dateAgreeStart.equals(that.dateAgreeStart) : that.dateAgreeStart != null)
            return false;
        if (dateAgreeEnd != null ? !dateAgreeEnd.equals(that.dateAgreeEnd) : that.dateAgreeEnd != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (currencyP != null ? !currencyP.equals(that.currencyP) : that.currencyP != null) return false;
        return guaranteeTypeP != null ? guaranteeTypeP.equals(that.guaranteeTypeP) : that.guaranteeTypeP == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (guarantor != null ? guarantor.hashCode() : 0);
        result = 31 * result + (dateAgreeStart != null ? dateAgreeStart.hashCode() : 0);
        result = 31 * result + (dateAgreeEnd != null ? dateAgreeEnd.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (currencyP != null ? currencyP.hashCode() : 0);
        result = 31 * result + (guaranteeTypeP != null ? guaranteeTypeP.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GuaranteeLimit{" +
                "id=" + id +
                ", guarantor=" + guarantor +
                ", dateAgreeStart=" + dateAgreeStart +
                ", dateAgreeEnd=" + dateAgreeEnd +
                ", dateEnd=" + dateEnd +
                ", amount=" + amount +
                ", currency=" + currencyP +
                ", guaranteeType=" + guaranteeTypeP +
                '}';
    }
}
