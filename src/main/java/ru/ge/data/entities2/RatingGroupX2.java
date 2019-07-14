package ru.ge.data.entities2;

import javax.persistence.*;

@Entity
@Table(name = "ctl_RatingGroups")
public class RatingGroupX2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "GroupID", nullable = false)
    private int group;

    @Column(name = "RatingID", nullable = false)
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingGroupX2 that = (RatingGroupX2) o;

        if (id != that.id) return false;
        if (group != that.group) return false;
        return rating == that.rating;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + group;
        result = 31 * result + rating;
        return result;
    }
}
