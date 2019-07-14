package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "GuaranteeApprovalDocs")
public class GuaranteeApprovalDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Guarantee_id", referencedColumnName = "Id")
    private Guarantee guarantee;

    @Column(name = "DateApproval", nullable = false)
    private LocalDate dateApproval;

    @ManyToOne
    @JoinColumn(name = "GuaranteeApprovalDocType_id", referencedColumnName = "Id")
    private GuaranteeApprovalDocType docType;

    @Column(name = "Number", length = 1000, nullable = false)
    private String number;

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

    public LocalDate getDateApproval() {
        return dateApproval;
    }

    public void setDateApproval(LocalDate approvalDate) {
        this.dateApproval = approvalDate;
    }

    public GuaranteeApprovalDocType getDocType() {
        return docType;
    }

    public void setDocType(GuaranteeApprovalDocType guaranteeApprovalDocType) {
        this.docType = guaranteeApprovalDocType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuaranteeApprovalDoc that = (GuaranteeApprovalDoc) o;

        if (id != that.id) return false;
        if (guarantee != null ? !guarantee.equals(that.guarantee) : that.guarantee != null) return false;
        if (dateApproval != null ? !dateApproval.equals(that.dateApproval) : that.dateApproval != null) return false;
        if (docType != null ? !docType.equals(that.docType) : that.docType != null)
            return false;
        return number != null ? number.equals(that.number) : that.number == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (guarantee != null ? guarantee.hashCode() : 0);
        result = 31 * result + (dateApproval != null ? dateApproval.hashCode() : 0);
        result = 31 * result + (docType != null ? docType.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GuaranteeApprovalDoc{" +
                "id=" + id +
                ", guarantee=" + guarantee +
                ", approvalDate=" + dateApproval +
                ", guaranteeApprovalDocType=" + docType +
                ", number='" + number + '\'' +
                '}';
    }
}
