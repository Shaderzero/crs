package ru.ge.data.entitiesCore;

import javax.persistence.*;

@Entity
@Table(name = "Ratings")
public class RatingCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Score")
    private int score;

    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "RatingGroupId", referencedColumnName = "Id")
    private RatingGroupCore ratingGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RatingGroupCore getRatingGroup() {
        return ratingGroup;
    }

    public void setRatingGroup(RatingGroupCore ratingGroup) {
        this.ratingGroup = ratingGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCore that = (RatingCore) o;

        if (id != that.id) return false;
        if (score != that.score) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return ratingGroup != null ? ratingGroup.equals(that.ratingGroup) : that.ratingGroup == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + score;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ratingGroup != null ? ratingGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
