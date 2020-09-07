package ru.ge.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "score", nullable = false, unique = true)
    private int score;

    @Column(name = "name", length = 5, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "rating_group_id", referencedColumnName = "id")
    private RatingGroup ratingGroupP;

    public RatingGroup getRatingGroupP() {
        return ratingGroupP;
    }

    public void setRatingGroupP(RatingGroup ratingGroupP) {
        this.ratingGroupP = ratingGroupP;
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
        return ratingGroupP != null ? ratingGroupP.equals(that.ratingGroupP) : that.ratingGroupP == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + score;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ratingGroupP != null ? ratingGroupP.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
