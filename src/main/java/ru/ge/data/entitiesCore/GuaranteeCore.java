package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Guarantees")
public class GuaranteeCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CounterpartyId", referencedColumnName = "Id")
    private CounterpartyCore counterparty;

    @ManyToOne
    @JoinColumn(name = "GuarantorId", referencedColumnName = "Id")
    private CounterpartyCore guarantor;

    @ManyToOne
    @JoinColumn(name = "CurrencyId", referencedColumnName = "Id")
    private CurrencyCore currency;

    @ManyToOne
    @JoinColumn(name = "GuaranteeTypeId", referencedColumnName = "Id")
    private GuaranteeTypeCore guaranteeType;

    @Column(name = "Amount")
    private long amount;

    @Column(name = "DateStart")
    private LocalDate dateStart;

    @Column(name = "Number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "SubsidiaryId", referencedColumnName = "Id")
    private SubsidiaryCore subsidiary;

    @Column(name = "Comment")
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CounterpartyCore getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(CounterpartyCore counterparty) {
        this.counterparty = counterparty;
    }

    public CounterpartyCore getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(CounterpartyCore guarantor) {
        this.guarantor = guarantor;
    }

    public CurrencyCore getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCore currency) {
        this.currency = currency;
    }

    public GuaranteeTypeCore getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(GuaranteeTypeCore guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public SubsidiaryCore getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(SubsidiaryCore subsidiary) {
        this.subsidiary = subsidiary;
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

        GuaranteeCore that = (GuaranteeCore) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (counterparty != null ? !counterparty.equals(that.counterparty) : that.counterparty != null) return false;
        if (guarantor != null ? !guarantor.equals(that.guarantor) : that.guarantor != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (guaranteeType != null ? !guaranteeType.equals(that.guaranteeType) : that.guaranteeType != null)
            return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (subsidiary != null ? !subsidiary.equals(that.subsidiary) : that.subsidiary != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (counterparty != null ? counterparty.hashCode() : 0);
        result = 31 * result + (guarantor != null ? guarantor.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (guaranteeType != null ? guaranteeType.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (subsidiary != null ? subsidiary.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GuaranteeCore{" +
                "id=" + id +
                ", counterparty=" + counterparty +
                ", guarantor=" + guarantor +
                ", currency=" + currency +
                ", guaranteeType=" + guaranteeType +
                ", amount=" + amount +
                ", dateStart=" + dateStart +
                ", number='" + number + '\'' +
                ", subsidiary=" + subsidiary +
                ", comment='" + comment + '\'' +
                '}';
    }
}
