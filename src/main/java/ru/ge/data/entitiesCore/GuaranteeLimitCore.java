package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "GuaranteeLimits")
public class GuaranteeLimitCore {

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

    @Column(name = "DateAgreeStart")
    private LocalDate dateAgreeStart;

    @Column(name = "DateAgreeEnd")
    private LocalDate dateAgreeEnd;

    @Column(name = "DateGuaranteeEnd")
    private LocalDate dateGuaranteeEnd;

    @Column(name = "Amount")
    private long amount;

    @ManyToOne
    @JoinColumn(name = "CurrencyId", referencedColumnName = "Id")
    private CurrencyCore currency;

    @ManyToOne
    @JoinColumn(name = "GuaranteeNameId", referencedColumnName = "Id")
    private GuaranteeNameCore guaranteeName;

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

    public LocalDate getDateGuaranteeEnd() {
        return dateGuaranteeEnd;
    }

    public void setDateGuaranteeEnd(LocalDate dateGuaranteeEnd) {
        this.dateGuaranteeEnd = dateGuaranteeEnd;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public CurrencyCore getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCore currency) {
        this.currency = currency;
    }

    public GuaranteeNameCore getGuaranteeName() {
        return guaranteeName;
    }

    public void setGuaranteeName(GuaranteeNameCore guaranteeName) {
        this.guaranteeName = guaranteeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuaranteeLimitCore that = (GuaranteeLimitCore) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (counterparty != null ? !counterparty.equals(that.counterparty) : that.counterparty != null) return false;
        if (guarantor != null ? !guarantor.equals(that.guarantor) : that.guarantor != null) return false;
        if (dateAgreeStart != null ? !dateAgreeStart.equals(that.dateAgreeStart) : that.dateAgreeStart != null)
            return false;
        if (dateAgreeEnd != null ? !dateAgreeEnd.equals(that.dateAgreeEnd) : that.dateAgreeEnd != null) return false;
        if (dateGuaranteeEnd != null ? !dateGuaranteeEnd.equals(that.dateGuaranteeEnd) : that.dateGuaranteeEnd != null)
            return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        return guaranteeName != null ? guaranteeName.equals(that.guaranteeName) : that.guaranteeName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (counterparty != null ? counterparty.hashCode() : 0);
        result = 31 * result + (guarantor != null ? guarantor.hashCode() : 0);
        result = 31 * result + (dateAgreeStart != null ? dateAgreeStart.hashCode() : 0);
        result = 31 * result + (dateAgreeEnd != null ? dateAgreeEnd.hashCode() : 0);
        result = 31 * result + (dateGuaranteeEnd != null ? dateGuaranteeEnd.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (guaranteeName != null ? guaranteeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GuaranteeLimitCore{" +
                "id=" + id +
                ", counterparty=" + counterparty +
                ", guarantor=" + guarantor +
                ", dateAgreeStart=" + dateAgreeStart +
                ", dateAgreeEnd=" + dateAgreeEnd +
                ", dateGuaranteeEnd=" + dateGuaranteeEnd +
                ", amount=" + amount +
                ", currency=" + currency +
                ", guaranteeName=" + guaranteeName +
                '}';
    }
}
