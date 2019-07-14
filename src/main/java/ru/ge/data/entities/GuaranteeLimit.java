package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "GuaranteeLimits")
public class GuaranteeLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Guarantor_id", referencedColumnName = "Id")
    private Counterparty guarantor;

    @Column(name = "DateAgreeStart", nullable = false)
    private LocalDate dateAgreeStart;

    @Column(name = "DateAgreeEnd")
    private LocalDate dateAgreeEnd;

    @Column(name = "DateEnd")
    private LocalDate dateEnd;

    @Column(name = "Amount")
    private long amount;

    @ManyToOne
    @JoinColumn(name = "Currency_id", referencedColumnName = "Id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "GuaranteeType_id", referencedColumnName = "Id")
    private GuaranteeType guaranteeType;

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public GuaranteeType getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(GuaranteeType guaranteeType) {
        this.guaranteeType = guaranteeType;
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
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        return guaranteeType != null ? guaranteeType.equals(that.guaranteeType) : that.guaranteeType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (guarantor != null ? guarantor.hashCode() : 0);
        result = 31 * result + (dateAgreeStart != null ? dateAgreeStart.hashCode() : 0);
        result = 31 * result + (dateAgreeEnd != null ? dateAgreeEnd.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (guaranteeType != null ? guaranteeType.hashCode() : 0);
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
                ", currency=" + currency +
                ", guaranteeType=" + guaranteeType +
                '}';
    }
}
