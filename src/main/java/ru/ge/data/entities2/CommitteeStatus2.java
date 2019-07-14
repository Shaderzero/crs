package ru.ge.data.entities2;

import javax.persistence.*;

@Entity
@Table(name = "ctl_CommitteeStatuses")
public class CommitteeStatus2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name", length = 20)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        CommitteeStatus2 that = (CommitteeStatus2) o;

        if (id != that.id) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CommitteeLimit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
