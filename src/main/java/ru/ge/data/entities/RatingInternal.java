package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rating_internals")
public class RatingInternal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;

    @Column(name = "conservative", nullable = false, columnDefinition = "bit default 0")
    private boolean conservative;

    @Column(name = "analyst", nullable = false, length = 30)
    private String analyst;

    @Column(name = "comment", length = 1000)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "rating_id", referencedColumnName = "id")
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "rating_wc_id", referencedColumnName = "id")
    private Rating ratingWc;

    @ManyToOne
    @JoinColumn(name = "risk_class_id", referencedColumnName = "id")
    private RiskClass riskClass;

    @ManyToOne
    @JoinColumn(name = "financial_statement_id", referencedColumnName = "id")
    private FinancialStatement financialStatement;

    @ManyToOne
    @JoinColumn(name = "counterparty_id", nullable = false, referencedColumnName = "id")
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

    public void setRating(Rating ratingValueP) {
        this.rating = ratingValueP;
    }

    public Rating getRatingWC() {
        return ratingWc;
    }

    public void setRatingWc(Rating ratingValueWithoutCountryP) {
        this.ratingWc = ratingValueWithoutCountryP;
    }

    public RiskClass getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(RiskClass riskClassP) {
        this.riskClass = riskClassP;
    }

    public FinancialStatement getFinancialStatement() {
        return financialStatement;
    }

    public void setFinancialStatement(FinancialStatement financialStatementP) {
        this.financialStatement = financialStatementP;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterpartyP) {
        this.counterparty = counterpartyP;
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
