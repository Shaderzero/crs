package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "GuaranteeAmounts")
public class GuaranteeAmountCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "GuaranteeId", referencedColumnName = "Id")
    private GuaranteeCore guarantee;

    @Column(name = "DateReport")
    private LocalDate dateRaport;

    @Column(name = "Amount")
    private long amount;

    @Column(name = "AmountOperational")
    private long amountOperational;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GuaranteeCore getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(GuaranteeCore guarantee) {
        this.guarantee = guarantee;
    }

    public LocalDate getDateRaport() {
        return dateRaport;
    }

    public void setDateRaport(LocalDate dateRaport) {
        this.dateRaport = dateRaport;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmountOperational() {
        return amountOperational;
    }

    public void setAmountOperational(long amountOperational) {
        this.amountOperational = amountOperational;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuaranteeAmountCore that = (GuaranteeAmountCore) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (amountOperational != that.amountOperational) return false;
        if (guarantee != null ? !guarantee.equals(that.guarantee) : that.guarantee != null) return false;
        return dateRaport != null ? dateRaport.equals(that.dateRaport) : that.dateRaport == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (guarantee != null ? guarantee.hashCode() : 0);
        result = 31 * result + (dateRaport != null ? dateRaport.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (int) (amountOperational ^ (amountOperational >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "GuaranteeAmountCore{" +
                "id=" + id +
                ", guarantee=" + guarantee +
                ", dateRaport=" + dateRaport +
                ", amount=" + amount +
                ", amountOperational=" + amountOperational +
                '}';
    }
}
