package ru.ge.data.entities2;

import javax.persistence.*;

@Entity
@Table(name = "ctl_GroupLimits")
public class RatingGroup2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "GroupNumber", nullable = false)
    private int groupNumber;

    @Column(name = "GroupLimit", nullable = false)
    private long groupLimit;

    @Column(name = "BankLimit", nullable = false)
    private long bankLimit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public long getGroupLimit() {
        return groupLimit;
    }

    public void setGroupLimit(long groupLimit) {
        this.groupLimit = groupLimit;
    }

    public long getBankLimit() {
        return bankLimit;
    }

    public void setBankLimit(long bankLimit) {
        this.bankLimit = bankLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingGroup2 that = (RatingGroup2) o;

        if (id != that.id) return false;
        if (groupNumber != that.groupNumber) return false;
        if (groupLimit != that.groupLimit) return false;
        return bankLimit == that.bankLimit;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + groupNumber;
        result = 31 * result + (int) (groupLimit ^ (groupLimit >>> 32));
        result = 31 * result + (int) (bankLimit ^ (bankLimit >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "RatingGroup2{" +
                "id=" + id +
                ", groupNumber=" + groupNumber +
                ", groupLimit=" + groupLimit +
                ", bankLimit=" + bankLimit +
                '}';
    }
}
