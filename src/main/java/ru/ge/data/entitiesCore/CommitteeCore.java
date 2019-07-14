package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Committees")
public class CommitteeCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "DateStart")
    private LocalDate dateStart;

    @Column(name = "Comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "CounterpartyId", referencedColumnName = "Id")
    private CounterpartyCore counterparty;

    @ManyToOne
    @JoinColumn(name = "CommitteeStatusId", referencedColumnName = "Id")
    private CommitteeStatusCore commiitteeStatus;

    @ManyToOne
    @JoinColumn(name = "CommitteeLimitId", referencedColumnName = "Id")
    private CommitteeLimitCore committeeLimit;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CounterpartyCore getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(CounterpartyCore counterparty) {
        this.counterparty = counterparty;
    }

    public CommitteeStatusCore getCommiitteeStatus() {
        return commiitteeStatus;
    }

    public void setCommiitteeStatus(CommitteeStatusCore commiitteeStatus) {
        this.commiitteeStatus = commiitteeStatus;
    }

    public CommitteeLimitCore getCommitteeLimit() {
        return committeeLimit;
    }

    public void setCommitteeLimit(CommitteeLimitCore committeeLimit) {
        this.committeeLimit = committeeLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommitteeCore that = (CommitteeCore) o;

        if (id != that.id) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (counterparty != null ? !counterparty.equals(that.counterparty) : that.counterparty != null) return false;
        if (commiitteeStatus != null ? !commiitteeStatus.equals(that.commiitteeStatus) : that.commiitteeStatus != null)
            return false;
        return committeeLimit != null ? committeeLimit.equals(that.committeeLimit) : that.committeeLimit == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (counterparty != null ? counterparty.hashCode() : 0);
        result = 31 * result + (commiitteeStatus != null ? commiitteeStatus.hashCode() : 0);
        result = 31 * result + (committeeLimit != null ? committeeLimit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommitteeCore{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", comment='" + comment + '\'' +
                ", counterparty=" + counterparty +
                ", commiitteeStatus=" + commiitteeStatus +
                ", committeeLimit=" + committeeLimit +
                '}';
    }
}
