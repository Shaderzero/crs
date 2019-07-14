package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Counterparties")
public class CounterpartyCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "ShortName")
    private String shortName;

    @Column(name = "IntraGroup")
    private boolean intraGroup;

    @Column(name = "DateStart")
    private LocalDate dateStart;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Monitored")
    private boolean monitored;

    @Column(name = "Ticker")
    private String ticker;

    @Column(name = "Inn")
    private String inn;

    @Column(name = "Swift")
    private String swift;

    @ManyToOne
    @JoinColumn(name = "FinancialSectorId", referencedColumnName = "Id")
    private FinancialSectorCore financialSector;

    @ManyToOne
    @JoinColumn(name = "CountryDomicileId", referencedColumnName = "Id")
    private CountryCore countryDomicile;

    @ManyToOne
    @JoinColumn(name = "CountryRiskId", referencedColumnName = "Id")
    private CountryCore countryRisk;

    @ManyToOne
    @JoinColumn(name = "RatingDonorId", referencedColumnName = "Id")
    private CounterpartyCore ratingDonor;

    @ManyToOne
    @JoinColumn(name = "CounterpartyGroupId", referencedColumnName = "Id")
    private CounterpartyGroupCore counterpartyGroup;

    @ManyToMany()
    @JoinTable(name = "CounterpartyPortfolios",
            joinColumns = @JoinColumn(name = "CounterpartyId"),
            inverseJoinColumns = @JoinColumn(name = "PortfolioId"))
    @OrderBy("name")
    private Set<PortfolioCore> portfolioSet = new HashSet<>();

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public boolean isIntraGroup() {
        return intraGroup;
    }

    public void setIntraGroup(boolean intraGroup) {
        this.intraGroup = intraGroup;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(boolean monitored) {
        this.monitored = monitored;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public FinancialSectorCore getFinancialSector() {
        return financialSector;
    }

    public void setFinancialSector(FinancialSectorCore financialSector) {
        this.financialSector = financialSector;
    }

    public CountryCore getCountryDomicile() {
        return countryDomicile;
    }

    public void setCountryDomicile(CountryCore countryDomicile) {
        this.countryDomicile = countryDomicile;
    }

    public CountryCore getCountryRisk() {
        return countryRisk;
    }

    public void setCountryRisk(CountryCore countryRisk) {
        this.countryRisk = countryRisk;
    }

    public CounterpartyCore getRatingDonor() {
        return ratingDonor;
    }

    public void setRatingDonor(CounterpartyCore ratingDonor) {
        this.ratingDonor = ratingDonor;
    }

    public CounterpartyGroupCore getCounterpartyGroup() {
        return counterpartyGroup;
    }

    public void setCounterpartyGroup(CounterpartyGroupCore counterpartyGroup) {
        this.counterpartyGroup = counterpartyGroup;
    }

    public Set<PortfolioCore> getPortfolioSet() {
        return portfolioSet;
    }

    public void setPortfolioSet(Set<PortfolioCore> portfolioSet) {
        this.portfolioSet = portfolioSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CounterpartyCore that = (CounterpartyCore) o;

        if (id != that.id) return false;
        if (intraGroup != that.intraGroup) return false;
        if (monitored != that.monitored) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (ticker != null ? !ticker.equals(that.ticker) : that.ticker != null) return false;
        if (inn != null ? !inn.equals(that.inn) : that.inn != null) return false;
        if (swift != null ? !swift.equals(that.swift) : that.swift != null) return false;
        if (financialSector != null ? !financialSector.equals(that.financialSector) : that.financialSector != null)
            return false;
        if (countryDomicile != null ? !countryDomicile.equals(that.countryDomicile) : that.countryDomicile != null)
            return false;
        if (countryRisk != null ? !countryRisk.equals(that.countryRisk) : that.countryRisk != null) return false;
        if (ratingDonor != null ? !ratingDonor.equals(that.ratingDonor) : that.ratingDonor != null) return false;
        return counterpartyGroup != null ? counterpartyGroup.equals(that.counterpartyGroup) : that.counterpartyGroup == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (intraGroup ? 1 : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (monitored ? 1 : 0);
        result = 31 * result + (ticker != null ? ticker.hashCode() : 0);
        result = 31 * result + (inn != null ? inn.hashCode() : 0);
        result = 31 * result + (swift != null ? swift.hashCode() : 0);
        result = 31 * result + (financialSector != null ? financialSector.hashCode() : 0);
        result = 31 * result + (countryDomicile != null ? countryDomicile.hashCode() : 0);
        result = 31 * result + (countryRisk != null ? countryRisk.hashCode() : 0);
        result = 31 * result + (ratingDonor != null ? ratingDonor.hashCode() : 0);
        result = 31 * result + (counterpartyGroup != null ? counterpartyGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
