package ru.ge.data.entities2;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ctl_Committees")
public class Committee2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CounterpartyID", referencedColumnName = "Id")
    private Counterparty2 counterparty;

    @ManyToOne
    @JoinColumn(name = "StatusID", referencedColumnName = "Id")
    private CommitteeStatus2 committeeStatus;

    @ManyToOne
    @JoinColumn(name = "LimitationsID", referencedColumnName = "Id")
    private CommitteeLimit2 committeeLimit;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "Comments", length = 4000)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "GroupID", referencedColumnName = "Id")
    private CommitteeGroup2 committeeGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Counterparty2 getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty2 counterparty) {
        this.counterparty = counterparty;
    }

    public CommitteeStatus2 getCommitteeStatus() {
        return committeeStatus;
    }

    public void setCommitteeStatus(CommitteeStatus2 committeeStatus) {
        this.committeeStatus = committeeStatus;
    }

    public CommitteeLimit2 getCommitteeLimit() {
        return committeeLimit;
    }

    public void setCommitteeLimit(CommitteeLimit2 committeeLimit) {
        this.committeeLimit = committeeLimit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommitteeGroup2 getCommitteeGroup() {
        return committeeGroup;
    }

    public void setCommitteeGroup(CommitteeGroup2 committeeGroup) {
        this.committeeGroup = committeeGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Committee2 that = (Committee2) o;

        if (id != that.id) return false;
        if (!counterparty.equals(that.counterparty)) return false;
        if (!committeeStatus.equals(that.committeeStatus)) return false;
        if (committeeLimit != null ? !committeeLimit.equals(that.committeeLimit) : that.committeeLimit != null)
            return false;
        if (!startDate.equals(that.startDate)) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        return committeeGroup != null ? committeeGroup.equals(that.committeeGroup) : that.committeeGroup == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + counterparty.hashCode();
        result = 31 * result + committeeStatus.hashCode();
        result = 31 * result + (committeeLimit != null ? committeeLimit.hashCode() : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (committeeGroup != null ? committeeGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Committee2{" +
                "id=" + id +
                ", counterparty=" + counterparty +
                ", committeeStatus=" + committeeStatus +
                ", committeeLimit=" + committeeLimit +
                ", startDate=" + startDate +
                ", comment='" + comment + '\'' +
                ", committeeGroup=" + committeeGroup +
                '}';
    }
}

