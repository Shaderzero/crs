package ru.ge.data.entities2;

import javax.persistence.*;

@Entity
@Table(name = "ctl_Sectors")
public class FinancialSector2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "SectorName", nullable = false, length = 30)
    private String name;

    @Column(name = "RussianName", nullable = false, length = 50)
    private String ruName;

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

    public String getRuName() {
        return ruName;
    }

    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinancialSector2 that = (FinancialSector2) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        return ruName.equals(that.ruName);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + ruName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
