package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RatingInternals")
public class RatingInternalCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "DateStart")
    private LocalDate dateStart;

    @Column(name = "Conservative", columnDefinition = "bit default 0")
    private boolean conservative;

    @Column(name = "Analyst")
    private String analyst;

    @Column(name = "Comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "RatingId", referencedColumnName = "Id")
    private RatingCore rating;

    @ManyToOne
    @JoinColumn(name = "RatingWcId", referencedColumnName = "Id")
    private RatingCore ratingWc;

    @ManyToOne
    @JoinColumn(name = "RiskClassId", referencedColumnName = "Id")
    private RiskClassCore riskClass;

    @ManyToOne
    @JoinColumn(name = "FinancialStatementId", referencedColumnName = "Id")
    private FinancialStatementCore financialStatement;

    @ManyToOne
    @JoinColumn(name = "CounterpartyId", referencedColumnName = "Id")
    private CounterpartyCore counterparty;

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

    public boolean isConservative() {
        return conservative;
    }

    public void setConservative(boolean conservative) {
        this.conservative = conservative;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RatingCore getRating() {
        return rating;
    }

    public void setRating(RatingCore rating) {
        this.rating = rating;
    }

    public RatingCore getRatingWc() {
        return ratingWc;
    }

    public void setRatingWc(RatingCore ratingWc) {
        this.ratingWc = ratingWc;
    }

    public RiskClassCore getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(RiskClassCore riskClass) {
        this.riskClass = riskClass;
    }

    public FinancialStatementCore getFinancialStatement() {
        return financialStatement;
    }

    public void setFinancialStatement(FinancialStatementCore financialStatement) {
        this.financialStatement = financialStatement;
    }

    public CounterpartyCore getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(CounterpartyCore counterparty) {
        this.counterparty = counterparty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingInternalCore that = (RatingInternalCore) o;

        if (id != that.id) return false;
        if (conservative != that.conservative) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (analyst != null ? !analyst.equals(that.analyst) : that.analyst != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (ratingWc != null ? !ratingWc.equals(that.ratingWc) : that.ratingWc != null) return false;
        if (riskClass != null ? !riskClass.equals(that.riskClass) : that.riskClass != null) return false;
        return financialStatement != null ? financialStatement.equals(that.financialStatement) : that.financialStatement == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (conservative ? 1 : 0);
        result = 31 * result + (analyst != null ? analyst.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (ratingWc != null ? ratingWc.hashCode() : 0);
        result = 31 * result + (riskClass != null ? riskClass.hashCode() : 0);
        result = 31 * result + (financialStatement != null ? financialStatement.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatingInternalCore{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", conservative=" + conservative +
                ", analyst='" + analyst + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", ratingWc=" + ratingWc +
                ", riskClass=" + riskClass +
                ", financialStatement=" + financialStatement +
                '}';
    }
}
