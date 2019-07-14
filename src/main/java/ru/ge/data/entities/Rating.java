package ru.ge.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "Ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Score", nullable = false, unique = true)
    private int score;

    @Column(name = "Name", length = 5, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "RatingGroup_id", referencedColumnName = "Id")
    private RatingGroup ratingGroup;

    public RatingGroup getRatingGroup() {
        return ratingGroup;
    }

    public void setRatingGroup(RatingGroup ratingGroup) {
        this.ratingGroup = ratingGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int value) {
        this.score = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating that = (Rating) o;

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
