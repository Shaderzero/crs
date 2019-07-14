package ru.ge.data.entities2;

import javax.persistence.*;

@Entity
@Table(name = "ctl_Countries")
public class Country2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "RussianName", nullable = false, insertable = true, updatable = true, length = 50, unique = true)
    private String nameRu;

    @Column(name = "Name", nullable = false, insertable = true, updatable = true, length = 50, unique = true)
    private String name;

    @Column(name = "ShortName", nullable = false, insertable = true, updatable = true, length = 2, unique = true)
    private String shortName;

    @Column(name = "BloombergTicker", length = 30)
    private String ticker;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String name) {
        this.nameRu = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameEn) {
        this.name = nameEn;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country2 that = (Country2) o;

        if (id != that.id) return false;
        if (!nameRu.equals(that.nameRu)) return false;
        if (!name.equals(that.name)) return false;
        if (!shortName.equals(that.shortName)) return false;
        return ticker != null ? ticker.equals(that.ticker) : that.ticker == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nameRu.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + (ticker != null ? ticker.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
