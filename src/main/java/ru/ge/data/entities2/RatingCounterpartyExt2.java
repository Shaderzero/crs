package ru.ge.data.entities2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctl_ExternalRatingCounterparty")
public class RatingCounterpartyExt2 {

    @Id
    private RatingCounterpartyExt2Key id;

    public RatingCounterpartyExt2Key getId() {
        return id;
    }

    public void setId(RatingCounterpartyExt2Key id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingCounterpartyExt2 that = (RatingCounterpartyExt2) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "RatingCounterpartyExt2{" +
                "id=" + id +
                '}';
    }

}
