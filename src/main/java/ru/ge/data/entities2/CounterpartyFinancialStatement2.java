package ru.ge.data.entities2;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ctl_FinancialStatements")
public class CounterpartyFinancialStatement2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Date", nullable = false)
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "CounterpartyID", referencedColumnName = "Id")
    private Counterparty2 counterparty;

    @Column(name = "Standards", nullable = false)
    private String financialStatementStandard;

    @Column(name = "Comments", nullable = true)
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Counterparty2 getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty2 counterparty) {
        this.counterparty = counterparty;
    }

    public String getFinancialStatementStandard() {
        return financialStatementStandard;
    }

    public void setFinancialStatementStandard(String financialStatementStandard) {
        this.financialStatementStandard = financialStatementStandard;
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

        CounterpartyFinancialStatement2 that = (CounterpartyFinancialStatement2) o;

        if (id != that.id) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!counterparty.equals(that.counterparty)) return false;
        if (!financialStatementStandard.equals(that.financialStatementStandard)) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + startDate.hashCode();
        result = 31 * result + counterparty.hashCode();
        result = 31 * result + financialStatementStandard.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String result = financialStatementStandard + " | " + startDate;
        return result;
    }
}
