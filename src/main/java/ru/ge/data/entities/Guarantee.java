package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Guarantees")
public class Guarantee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Counterparty_id", referencedColumnName = "Id")
    private Counterparty counterparty;

    @ManyToOne
    @JoinColumn(name = "Guarantor_id", referencedColumnName = "Id")
    private Counterparty guarantor;

    @ManyToOne
    @JoinColumn(name = "Beneficiar_id", referencedColumnName = "Id")
    private Counterparty beneficiar;

    @ManyToOne
    @JoinColumn(name = "Currency_id", referencedColumnName = "Id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "GuaranteeType_id", referencedColumnName = "Id")
    private GuaranteeType type;

    @Column(name = "AmountInitial", nullable = false)
    private long amount;

    @Column(name = "DateStart", nullable = false)
    private LocalDate dateStart;

    @Column(name = "Number", length = 100)
    private String number;

    @ManyToOne
    @JoinColumn(name = "Subsidiary_id", referencedColumnName = "Id")
    private Subsidiary subsidiary;

    @Column(name = "Comment", length = 1000)
    private String comment;

    @OneToMany(mappedBy = "guarantee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dateReport desc")
    private List<GuaranteeReport> reportList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public Counterparty getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(Counterparty guarantor) {
        this.guarantor = guarantor;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public GuaranteeType getType() {
        return type;
    }

    public void setType(GuaranteeType guaranteeType) {
        this.type = guaranteeType;
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

    public void setDateStart(LocalDate startDate) {
        this.dateStart = startDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String guaranteeNumber) {
        this.number = guaranteeNumber;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Counterparty getBeneficiar() {
        return beneficiar;
    }

    public void setBeneficiar(Counterparty beneficiar) {
        this.beneficiar = beneficiar;
    }

    public List<GuaranteeReport> getReportList() {
        return reportList;
    }

    public void setReportList(List<GuaranteeReport> reportList) {
        this.reportList = reportList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guarantee guarantee = (Guarantee) o;

        if (id != guarantee.id) return false;
        if (amount != guarantee.amount) return false;
        if (counterparty != null ? !counterparty.equals(guarantee.counterparty) : guarantee.counterparty != null)
            return false;
        if (guarantor != null ? !guarantor.equals(guarantee.guarantor) : guarantee.guarantor != null) return false;
        if (currency != null ? !currency.equals(guarantee.currency) : guarantee.currency != null) return false;
        if (type != null ? !type.equals(guarantee.type) : guarantee.type != null)
            return false;
        if (dateStart != null ? !dateStart.equals(guarantee.dateStart) : guarantee.dateStart != null) return false;
        if (number != null ? !number.equals(guarantee.number) : guarantee.number != null)
            return false;
        if (subsidiary != null ? !subsidiary.equals(guarantee.subsidiary) : guarantee.subsidiary != null) return false;
        return comment != null ? comment.equals(guarantee.comment) : guarantee.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (counterparty != null ? counterparty.hashCode() : 0);
        result = 31 * result + (guarantor != null ? guarantor.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (subsidiary != null ? subsidiary.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Guarantee{" +
                "id=" + id +
                ", counterparty=" + counterparty +
                ", guarantor=" + guarantor +
                ", currency=" + currency +
                ", guaranteeType=" + type +
                ", amount=" + amount +
                ", startDate=" + dateStart +
                ", guaranteeNumber='" + number + '\'' +
                ", subsidiary=" + subsidiary +
                ", comment='" + comment + '\'' +
                '}';
    }
}
