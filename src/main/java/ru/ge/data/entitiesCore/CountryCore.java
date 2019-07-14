package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Countries")
public class CountryCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "NameRu")
    private String nameRu;

    @Column(name = "ShortName")
    private String shortName;

    @Column(name = "Ticker")
    private String ticker;

    @OneToMany(mappedBy = "country")
    private List<RatingCountryCore> ratingCountryList = new ArrayList<>();

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

    public List<RatingCountryCore> getRatingCountryList() {
        return ratingCountryList;
    }

    public void setRatingCountryList(List<RatingCountryCore> ratingCountryList) {
        this.ratingCountryList = ratingCountryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryCore country = (CountryCore) o;

        if (id != country.id) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        if (nameRu != null ? !nameRu.equals(country.nameRu) : country.nameRu != null) return false;
        if (shortName != null ? !shortName.equals(country.shortName) : country.shortName != null) return false;
        if (ticker != null ? !ticker.equals(country.ticker) : country.ticker != null) return false;
        return ratingCountryList != null ? ratingCountryList.equals(country.ratingCountryList) : country.ratingCountryList == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (ticker != null ? ticker.hashCode() : 0);
        result = 31 * result + (ratingCountryList != null ? ratingCountryList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
