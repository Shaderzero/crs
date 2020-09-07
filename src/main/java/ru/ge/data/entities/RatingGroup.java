package ru.ge.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rating_groups")
public class RatingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "g_number", nullable = false, unique = true)
    private int number;

    @Column(name = "g_limit", nullable = false)
    private long limit;

    @Column(name = "g_limit_bank", nullable = false)
    private long limitBank;

    @OneToMany(mappedBy = "ratingGroupP")
    private List<Rating> ratingPList = new ArrayList<>();

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

    public List<Rating> getRatingPList() {
        return ratingPList;
    }

    public void setRatingPList(List<Rating> ratingPList) {
        this.ratingPList = ratingPList;
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
        return ratingPList != null ? ratingPList.equals(that.ratingPList) : that.ratingPList == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number;
        result = 31 * result + (int) (limit ^ (limit >>> 32));
        result = 31 * result + (int) (limitBank ^ (limitBank >>> 32));
        result = 31 * result + (ratingPList != null ? ratingPList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "" + number + " (" + limit / 100 + ")";
    }
}
