package ru.ge.data.entities2;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ctl_Guarantees")
public class Guarantee2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CounterpartyID", referencedColumnName = "Id")
    private Counterparty2 counterparty;

    @ManyToOne
    @JoinColumn(name = "GuarantorID", referencedColumnName = "Id")
    private Counterparty2 guarantor;

    @ManyToOne
    @JoinColumn(name = "CurrencyID", referencedColumnName = "Id")
    private Currency2 currency;

    @ManyToOne
    @JoinColumn(name = "TypeID", referencedColumnName = "Id")
    private GuaranteeType2 type;

    @Column(name = "Amount")
    private long amount;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "GuaranteeNumber", length = 100)
    private String guaranteeNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Counterparty2 getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty2 counterparty) {
        this.counterparty = counterparty;
    }

    public Counterparty2 getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(Counterparty2 guarantor) {
        this.guarantor = guarantor;
    }

    public Currency2 getCurrency() {
        return currency;
    }

    public void setCurrency(Currency2 currency) {
        this.currency = currency;
    }

    public GuaranteeType2 getType() {
        return type;
    }

    public void setType(GuaranteeType2 type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getGuaranteeNumber() {
        return guaranteeNumber;
    }

    public void setGuaranteeNumber(String guaranteeNumber) {
        this.guaranteeNumber = guaranteeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guarantee2 that = (Guarantee2) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (!counterparty.equals(that.counterparty)) return false;
        if (!guarantor.equals(that.guarantor)) return false;
        if (!currency.equals(that.currency)) return false;
        if (!type.equals(that.type)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        return guaranteeNumber != null ? guaranteeNumber.equals(that.guaranteeNumber) : that.guaranteeNumber == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + counterparty.hashCode();
        result = 31 * result + guarantor.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (guaranteeNumber != null ? guaranteeNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Guarantee2{" +
                "id=" + id +
                ", counterparty=" + counterparty +
                ", guarantor=" + guarantor +
                ", currency=" + currency +
                ", type=" + type +
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", guaranteeNumber='" + guaranteeNumber + '\'' +
                '}';
    }
}
