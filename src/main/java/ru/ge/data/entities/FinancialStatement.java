package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FinancialStatements")
public class FinancialStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "DateStart", nullable = false)
    private LocalDate dateStart;

    @Column(name = "Comment", length = 1000)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "Counterparty_id", referencedColumnName = "Id")
    private Counterparty counterparty;

    @ManyToOne
    @JoinColumn(name = "Standard_id", referencedColumnName = "Id")
    private FinancialStatementStandard financialStatementStandard;

    @OneToMany(mappedBy = "financialStatement", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @OrderBy("dateStart DESC")
    private List<RatingInternal> ratingInternalList = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        for (RatingInternal r : ratingInternalList) {
            r.setFinancialStatement(null);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate startDate) {
        this.dateStart = startDate;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public FinancialStatementStandard getStandard() {
        return financialStatementStandard;
    }

    public void setStandard(FinancialStatementStandard financialStatementStandard) {
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

        FinancialStatement that = (FinancialStatement) o;

        if (id != that.id) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (!(counterparty.getId() == that.counterparty.getId())) return false;
        return financialStatementStandard.equals(that.financialStatementStandard);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + counterparty.getId();
        result = 31 * result + financialStatementStandard.getId();
        return result;
    }

    @Override
    public String toString() {
        String result = dateStart + " | " + financialStatementStandard;
        return result;
    }
}
