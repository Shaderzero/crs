package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "GuaranteeReports")
public class GuaranteeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Guarantee_id", referencedColumnName = "Id")
    private Guarantee guarantee;

    @Column(name = "DateReport", nullable = false)
    private LocalDate dateReport;

    @Column(name = "DateExpiration")
    private LocalDate dateExpiration;

    @Column(name = "AmountStart")
    private long amountStart;

    @Column(name = "AmountEnd")
    private long amountEnd;

    @Column(name = "AmountOperation")
    private long amountOperation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Guarantee getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Guarantee guarantee) {
        this.guarantee = guarantee;
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDate reportDate) {
        this.dateReport = reportDate;
    }

    public long getAmountStart() {
        return amountStart;
    }

    public void setAmountStart(long amount) {
        this.amountStart = amount;
    }

    public long getAmountOperation() {
        return amountOperation;
    }

    public void setAmountOperation(long operationalAmount) {
        this.amountOperation = operationalAmount;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public long getAmountEnd() {
        return amountEnd;
    }

    public void setAmountEnd(long amountEnd) {
        this.amountEnd = amountEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuaranteeReport that = (GuaranteeReport) o;

        if (id != that.id) return false;
        if (amountStart != that.amountStart) return false;
        if (amountEnd != that.amountEnd) return false;
        if (amountOperation != that.amountOperation) return false;
        if (guarantee != null ? !guarantee.equals(that.guarantee) : that.guarantee != null) return false;
        if (dateReport != null ? !dateReport.equals(that.dateReport) : that.dateReport != null) return false;
        return dateExpiration != null ? dateExpiration.equals(that.dateExpiration) : that.dateExpiration == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (guarantee != null ? guarantee.hashCode() : 0);
        result = 31 * result + (dateReport != null ? dateReport.hashCode() : 0);
        result = 31 * result + (dateExpiration != null ? dateExpiration.hashCode() : 0);
        result = 31 * result + (int) (amountStart ^ (amountStart >>> 32));
        result = 31 * result + (int) (amountEnd ^ (amountEnd >>> 32));
        result = 31 * result + (int) (amountOperation ^ (amountOperation >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "GuaranteeAmount{" +
                "id=" + id +
                ", guarantee=" + guarantee +
                ", reportDate=" + dateReport +
                ", amount=" + amountStart +
                ", operationalAmount=" + amountOperation +
                '}';
    }
}
