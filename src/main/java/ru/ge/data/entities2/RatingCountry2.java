package ru.ge.data.entities2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctl_RatingCountry")
public class RatingCountry2 {

    @Id
    private RatingCountry2Key id;

    public RatingCountry2Key getId() {
        return id;
    }

    public void setId(RatingCountry2Key id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCountry2 that = (RatingCountry2) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RatingCountry2{" +
                "id=" + id +
                '}';
    }
}
