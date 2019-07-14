package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FinancialStatements")
public class FinancialStatementCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "DateStart")
    private LocalDate dateStart;

    @Column(name = "Comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "CounterpartyId", referencedColumnName = "Id")
    private CounterpartyCore counterparty;

    @ManyToOne
    @JoinColumn(name = "StandardId", referencedColumnName = "Id")
    private FinancialStatementStandardCore Standard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CounterpartyCore getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(CounterpartyCore counterparty) {
        this.counterparty = counterparty;
    }

    public FinancialStatementStandardCore getStandard() {
        return Standard;
    }

    public void setStandard(FinancialStatementStandardCore standard) {
        Standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinancialStatementCore that = (FinancialStatementCore) o;

        if (id != that.id) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (counterparty != null ? !counterparty.equals(that.counterparty) : that.counterparty != null) return false;
        return Standard != null ? Standard.equals(that.Standard) : that.Standard == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (counterparty != null ? counterparty.hashCode() : 0);
        result = 31 * result + (Standard != null ? Standard.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FinancialStatementCore{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", comment='" + comment + '\'' +
                ", counterparty=" + counterparty +
                ", Standard=" + Standard +
                '}';
    }
}
