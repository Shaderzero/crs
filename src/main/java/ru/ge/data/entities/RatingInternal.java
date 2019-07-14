package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RatingInternals")
public class RatingInternal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "DateStart", nullable = false)
    private LocalDate dateStart;

    @Column(name = "Conservative", nullable = false, columnDefinition = "bit default 0")
    private boolean conservative;

    @Column(name = "Analyst", nullable = false, length = 30)
    private String analyst;

    @Column(name = "Comment", length = 1000)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "Rating_id", referencedColumnName = "Id")
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "RatingWc_id", referencedColumnName = "Id")
    private Rating ratingWc;

    @ManyToOne
    @JoinColumn(name = "RiskClass_id", referencedColumnName = "Id")
    private RiskClass riskClass;

    @ManyToOne
    @JoinColumn(name = "FinancialStatement_id", referencedColumnName = "Id")
    private FinancialStatement financialStatement;

    @ManyToOne
    @JoinColumn(name = "Counterparty_id", nullable = false, referencedColumnName = "Id")
//    @OneToOne (mappedBy="ratingInternalList",  fetch = FetchType.LAZY)
    private Counterparty counterparty;

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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating ratingValue) {
        this.rating = ratingValue;
    }

    public Rating getRatingWC() {
        return ratingWc;
    }

    public void setRatingWc(Rating ratingValueWithoutCountry) {
        this.ratingWc = ratingValueWithoutCountry;
    }

    public RiskClass getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(RiskClass riskClass) {
        this.riskClass = riskClass;
    }

    public FinancialStatement getFinancialStatement() {
        return financialStatement;
    }

    public void setFinancialStatement(FinancialStatement financialStatement) {
        this.financialStatement = financialStatement;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingInternal that = (RatingInternal) o;

        if (id != that.id) return false;
        if (conservative != that.conservative) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (analyst != null ? !analyst.equals(that.analyst) : that.analyst != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (ratingWc != null ? !ratingWc.equals(that.ratingWc) : that.ratingWc != null)
            return false;
        if (riskClass != null ? !riskClass.equals(that.riskClass) : that.riskClass != null) return false;
        if (financialStatement != null ? !financialStatement.equals(that.financialStatement) : that.financialStatement != null)
            return false;
        return counterparty != null ? counterparty.equals(that.counterparty) : that.counterparty == null;
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
        result = 31 * result + (counterparty != null ? counterparty.getId() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatingInternal{" +
                "id=" + id +
                ", startDate=" + dateStart +
                ", conservative=" + conservative +
                ", analyst='" + analyst + '\'' +
                ", comment='" + comment + '\'' +
                ", ratingValue=" + rating +
                ", ratingValueWithoutCountry=" + ratingWc +
                ", riskClass=" + riskClass +
                ", financialStatement=" + financialStatement +
                ", counterparty=" + counterparty +
                '}';
    }
}
