package ru.ge.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "FinancialSectors")
public class FinancialSector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "NameRu", nullable = false, length = 50, unique = true)
    private String nameRu;

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

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinancialSector that = (FinancialSector) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return nameRu != null ? nameRu.equals(that.nameRu) : that.nameRu == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
