package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "guarantee_approval_docs")
public class GuaranteeApprovalDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "guarantee_id", referencedColumnName = "id")
    private Guarantee guaranteeP;

    @Column(name = "date_approval", nullable = false)
    private LocalDate dateApproval;

    @ManyToOne
    @JoinColumn(name = "guarantee_approval_doc_type_id", referencedColumnName = "id")
    private GuaranteeApprovalDocType docType;

    @Column(name = "number", length = 1000, nullable = false)
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Guarantee getGuaranteeP() {
        return guaranteeP;
    }

    public void setGuaranteeP(Guarantee guaranteeP) {
        this.guaranteeP = guaranteeP;
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

    public void setDocType(GuaranteeApprovalDocType guaranteeApprovalDocTypeP) {
        this.docType = guaranteeApprovalDocTypeP;
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
        if (guaranteeP != null ? !guaranteeP.equals(that.guaranteeP) : that.guaranteeP != null) return false;
        if (dateApproval != null ? !dateApproval.equals(that.dateApproval) : that.dateApproval != null) return false;
        if (docType != null ? !docType.equals(that.docType) : that.docType != null)
            return false;
        return number != null ? number.equals(that.number) : that.number == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (guaranteeP != null ? guaranteeP.hashCode() : 0);
        result = 31 * result + (dateApproval != null ? dateApproval.hashCode() : 0);
        result = 31 * result + (docType != null ? docType.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GuaranteeApprovalDoc{" +
                "id=" + id +
                ", guarantee=" + guaranteeP +
                ", approvalDate=" + dateApproval +
                ", guaranteeApprovalDocType=" + docType +
                ", number='" + number + '\'' +
                '}';
    }
}
