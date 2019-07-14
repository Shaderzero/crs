package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "GuaranteeApprovalDocs")
public class GuaranteeApprovalDocCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "GuaranteeId", referencedColumnName = "Id")
    private GuaranteeCore guarantee;

    @Column(name = "DateApproval")
    private LocalDate dateApproval;

    @ManyToOne
    @JoinColumn(name = "GuaranteeApprovalDocTypeId", referencedColumnName = "Id")
    private GuaranteeApprovalDocTypeCore guaranteeApprovalDocType;

    @Column(name = "Number")
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GuaranteeCore getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(GuaranteeCore guarantee) {
        this.guarantee = guarantee;
    }

    public LocalDate getDateApproval() {
        return dateApproval;
    }

    public void setDateApproval(LocalDate dateApproval) {
        this.dateApproval = dateApproval;
    }

    public GuaranteeApprovalDocTypeCore getGuaranteeApprovalDocType() {
        return guaranteeApprovalDocType;
    }

    public void setGuaranteeApprovalDocType(GuaranteeApprovalDocTypeCore guaranteeApprovalDocType) {
        this.guaranteeApprovalDocType = guaranteeApprovalDocType;
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

        GuaranteeApprovalDocCore that = (GuaranteeApprovalDocCore) o;

        if (id != that.id) return false;
        if (guarantee != null ? !guarantee.equals(that.guarantee) : that.guarantee != null) return false;
        if (dateApproval != null ? !dateApproval.equals(that.dateApproval) : that.dateApproval != null) return false;
        if (guaranteeApprovalDocType != null ? !guaranteeApprovalDocType.equals(that.guaranteeApprovalDocType) : that.guaranteeApprovalDocType != null)
            return false;
        return number != null ? number.equals(that.number) : that.number == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (guarantee != null ? guarantee.hashCode() : 0);
        result = 31 * result + (dateApproval != null ? dateApproval.hashCode() : 0);
        result = 31 * result + (guaranteeApprovalDocType != null ? guaranteeApprovalDocType.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GuaranteeApprovalDocCore{" +
                "id=" + id +
                ", guarantee=" + guarantee +
                ", dateApproval=" + dateApproval +
                ", guaranteeApprovalDocType=" + guaranteeApprovalDocType +
                ", number='" + number + '\'' +
                '}';
    }
}
