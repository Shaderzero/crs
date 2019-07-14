package ru.ge.data.entities2;

import javax.persistence.*;

@Entity
@Table(name = "ctl_InternalRatingCounterparty")
public class RatingCounterpartyInt2 {

    @Id
    private RatingCounterpartyInt2Key id;

    @ManyToOne
    @JoinColumn(name = "RatingValueWithoutCountryID", referencedColumnName = "Id", nullable = true)
    private RatingValue2 ratingValueWihoutCountry;

    @ManyToOne
    @JoinColumn(name = "RiskClassID", referencedColumnName = "Id", nullable = true)
    private RiskClass2 riskClass;


    @ManyToOne
    @JoinColumn(name = "FinancialStatementID", referencedColumnName = "Id", nullable = true)
    private CounterpartyFinancialStatement2 financialStatement = new CounterpartyFinancialStatement2();

    public RatingCounterpartyInt2Key getId() {
        return id;
    }

    public void setId(RatingCounterpartyInt2Key id) {
        this.id = id;
    }

    public RatingValue2 getRatingValueWihoutCountry() {
        return ratingValueWihoutCountry;
    }

    public void setRatingValueWihoutCountry(RatingValue2 ratingValueWihoutCountry) {
        this.ratingValueWihoutCountry = ratingValueWihoutCountry;
    }

    public RiskClass2 getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(RiskClass2 riskClass) {
        this.riskClass = riskClass;
    }

    public CounterpartyFinancialStatement2 getFinancialStatement() {
        return financialStatement;
    }

    public void setFinancialStatement(CounterpartyFinancialStatement2 financialStatement) {
        this.financialStatement = financialStatement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCounterpartyInt2 that = (RatingCounterpartyInt2) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ratingValueWihoutCountry != null ? !ratingValueWihoutCountry.equals(that.ratingValueWihoutCountry) : that.ratingValueWihoutCountry != null)
            return false;
        if (riskClass != null ? !riskClass.equals(that.riskClass) : that.riskClass != null) return false;
        return financialStatement != null ? financialStatement.equals(that.financialStatement) : that.financialStatement == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ratingValueWihoutCountry != null ? ratingValueWihoutCountry.hashCode() : 0);
        result = 31 * result + (riskClass != null ? riskClass.hashCode() : 0);
        result = 31 * result + (financialStatement != null ? financialStatement.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatingCounterpartyInt2{" +
                "id=" + id +
                ", ratingValueWihoutCountry=" + ratingValueWihoutCountry +
                ", riskClass=" + riskClass +
                ", financialStatement=" + financialStatement +
                '}';
    }
}
