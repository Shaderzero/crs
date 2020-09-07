package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "committees")
public class Committee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;

    @Column(name = "comment", length = 1000)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "counterparty_id", referencedColumnName = "id")
    private Counterparty counterparty;

    @ManyToOne
    @JoinColumn(name = "committee_status_id", referencedColumnName = "id")
    private CommitteeStatus committeeStatus;

    @ManyToOne
    @JoinColumn(name = "committee_limit_id", referencedColumnName = "id")
    private CommitteeLimit committeeLimit;

    @PreRemove
    private void preRemove() {
//        counterparty.getCommitteeList().remove(this);
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterpartyP) {
        this.counterparty = counterpartyP;
    }

    public CommitteeStatus getStatus() {
        return committeeStatus;
    }

    public void setStatus(CommitteeStatus committeeStatus) {
        this.committeeStatus = committeeStatus;
    }

    public CommitteeLimit getLimit() {
        return committeeLimit;
    }

    public void setLimit(CommitteeLimit committeeLimit) {
        this.committeeLimit = committeeLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Committee committee = (Committee) o;

        if (id != committee.id) return false;
        if (dateStart != null ? !dateStart.equals(committee.dateStart) : committee.dateStart != null) return false;
        if (comment != null ? !comment.equals(committee.comment) : committee.comment != null) return false;
        if (counterparty != null ? !counterparty.equals(committee.counterparty) : committee.counterparty != null)
            return false;
        if (committeeStatus != null ? !committeeStatus.equals(committee.committeeStatus) : committee.committeeStatus != null)
            return false;
        return committeeLimit != null ? committeeLimit.equals(committee.committeeLimit) : committee.committeeLimit == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (counterparty != null ? counterparty.getId() : 0);
        result = 31 * result + (committeeStatus != null ? committeeStatus.hashCode() : 0);
        result = 31 * result + (committeeLimit != null ? committeeLimit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Committee{" +
                "id=" + id +
                ", startDate=" + dateStart +
                ", comment='" + comment + '\'' +
                ", counterparty=" + counterparty +
                ", committeeStatus=" + committeeStatus +
                ", committeeLimit=" + committeeLimit +
                '}';
    }
}
