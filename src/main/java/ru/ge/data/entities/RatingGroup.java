package ru.ge.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RatingGroups")
public class RatingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Number", nullable = false, unique = true)
    private int number;

    @Column(name = "Limit", nullable = false)
    private long limit;

    @Column(name = "LimitBank", nullable = false)
    private long limitBank;

    @OneToMany(mappedBy = "ratingGroup")
    private List<Rating> ratingList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getLimitBank() {
        return limitBank;
    }

    public void setLimitBank(long limitBank) {
        this.limitBank = limitBank;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingGroup that = (RatingGroup) o;

        if (id != that.id) return false;
        if (number != that.number) return false;
        if (limit != that.limit) return false;
        if (limitBank != that.limitBank) return false;
        return ratingList != null ? ratingList.equals(that.ratingList) : that.ratingList == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number;
        result = 31 * result + (int) (limit ^ (limit >>> 32));
        result = 31 * result + (int) (limitBank ^ (limitBank >>> 32));
        result = 31 * result + (ratingList != null ? ratingList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "" + number + " (" + limit / 100 + ")";
    }
}
