package ru.ge.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "name_ru", nullable = false, length = 100, unique = true)
    private String nameRu;

    @Column(name = "short_name", nullable = false, length = 2, unique = true)
    private String shortName;

    @Column(name = "ticker", length = 20)
    private String ticker;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @OrderBy("name")
    private List<Counterparty> counterpartyList = new ArrayList<>();

    @OneToMany(mappedBy = "countryRisk", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @OrderBy("name")
    private List<Counterparty> counterpartyRiskList = new ArrayList<>();

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("dateStart desc")
    private List<RatingCountry> ratingCountryList = new ArrayList<>();

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

    public List<RatingCountry> getRatingCountryList() {
        return ratingCountryList;
    }

    public void setRatingCountryList(List<RatingCountry> ratingCountryList) {
        this.ratingCountryList = ratingCountryList;
    }

    public List<Counterparty> getCounterpartyList() {
        return counterpartyList;
    }

    public void setCounterpartyList(List<Counterparty> counterpartyList) {
        this.counterpartyList = counterpartyList;
    }

    public List<Counterparty> getCounterpartyRiskList() {
        return counterpartyRiskList;
    }

    public void setCounterpartyRiskList(List<Counterparty> counterpartyRiskList) {
        this.counterpartyRiskList = counterpartyRiskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

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
