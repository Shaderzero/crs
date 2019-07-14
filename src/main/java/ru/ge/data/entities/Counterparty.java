package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Counterparties")
public class Counterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name", nullable = false, length = 450, unique = true)
    private String name;

    @Column(name = "ShortName", nullable = false, length = 15, unique = true)
    private String shortName;

    @Column(name = "IntraGroup", nullable = false)
    private boolean intraGroup;

    @Column(name = "DateStart", nullable = false)
    private LocalDate dateStart;

    @Column(name = "Comment", length = 1000)
    private String comment;

    @Column(name = "Monitored", nullable = false)
    private boolean monitored;

    @Column(name = "Ticker", length = 50)
    private String ticker;

    @Column(name = "Inn", length = 30)
    private String inn;

    @Column(name = "Swift", length = 100)
    private String swift;

    @ManyToOne
    @JoinColumn(name = "FinancialSector_id", referencedColumnName = "Id")
    private FinancialSector financialSector;

    @ManyToOne
    @JoinColumn(name = "Country_id", referencedColumnName = "Id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "CountryRisk_id", referencedColumnName = "Id")
    private Country countryRisk;

    @ManyToOne
    @JoinColumn(name = "RatingDonor_id", referencedColumnName = "Id")
    private Counterparty ratingDonor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CounterpartyPortfolios",
            joinColumns = @JoinColumn(name = "Counterparty_id"),
            inverseJoinColumns = @JoinColumn(name = "Portfolio_id"))
    @OrderBy("name")
    private List<Portfolio> portfolioList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CounterpartyGroup_id", referencedColumnName = "Id")
    private CounterpartyGroup counterpartyGroup;

    @OneToMany(mappedBy = "counterparty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("dateStart DESC")
    private List<FinancialStatement> financialStatementList = new ArrayList<>();

    @OneToMany(mappedBy = "counterparty", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dateStart DESC")
    private List<RatingInternal> ratingInternalList = new ArrayList<>();

    @OneToMany(mappedBy = "counterparty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("dateStart DESC")
    private List<RatingExternal> ratingExternalList = new ArrayList<>();

    @OneToMany(mappedBy = "counterparty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("dateStart DESC")
    private List<Committee> committeeList = new ArrayList<>();

    @OneToMany(mappedBy = "ratingDonor")
    private List<Counterparty> donorList = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        for (Counterparty c : donorList) {
            c.setRatingDonor(null);
        }
    }

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

    public void setDateStart(LocalDate startDate) {
        this.dateStart = startDate;
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

    public FinancialSector getFinancialSector() {
        return financialSector;
    }

    public void setFinancialSector(FinancialSector financialSector) {
        this.financialSector = financialSector;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountryRisk() {
        return countryRisk;
    }

    public void setCountryRisk(Country countryRisk) {
        this.countryRisk = countryRisk;
    }

    public Counterparty getRatingDonor() {
        return ratingDonor;
    }

    public void setRatingDonor(Counterparty ratingDonor) {
        this.ratingDonor = ratingDonor;
    }

    public List<Portfolio> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<Portfolio> portfolioList) {
        this.portfolioList = portfolioList;
    }

    public CounterpartyGroup getCounterpartyGroup() {
        return counterpartyGroup;
    }

    public void setCounterpartyGroup(CounterpartyGroup counterpartyGroup) {
        this.counterpartyGroup = counterpartyGroup;
    }

    public List<FinancialStatement> getFinancialStatementList() {
        return financialStatementList;
    }

    public void setFinancialStatementList(List<FinancialStatement> financialStatementList) {
        this.financialStatementList = financialStatementList;
    }

    public List<RatingInternal> getRatingInternalList() {
        return ratingInternalList;
    }

    public void setRatingInternalList(List<RatingInternal> ratingInternalList) {
        this.ratingInternalList = ratingInternalList;
    }

    public List<RatingExternal> getRatingExternalList() {
        return ratingExternalList;
    }

    public void setRatingExternalList(List<RatingExternal> ratingExternalList) {
        this.ratingExternalList = ratingExternalList;
    }

    public List<Committee> getCommitteeList() {
        return committeeList;
    }

    public void setCommitteeList(List<Committee> committeeList) {
        this.committeeList = committeeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Counterparty that = (Counterparty) o;

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
        if (country != null ? !country.equals(that.country) : that.country != null)
            return false;
        if (countryRisk != null ? !countryRisk.equals(that.countryRisk) : that.countryRisk != null) return false;
        if (ratingDonor != null ? !ratingDonor.equals(that.ratingDonor) : that.ratingDonor != null) return false;
        if (portfolioList != null ? !portfolioList.equals(that.portfolioList) : that.portfolioList != null)
            return false;
        if (counterpartyGroup != null ? !counterpartyGroup.equals(that.counterpartyGroup) : that.counterpartyGroup != null)
            return false;
        if (financialStatementList != null ? !financialStatementList.equals(that.financialStatementList) : that.financialStatementList != null)
            return false;
        if (ratingInternalList != null ? !ratingInternalList.equals(that.ratingInternalList) : that.ratingInternalList != null)
            return false;
        if (ratingExternalList != null ? !ratingExternalList.equals(that.ratingExternalList) : that.ratingExternalList != null)
            return false;
        if (committeeList != null ? !committeeList.equals(that.committeeList) : that.committeeList != null)
            return false;
        return donorList != null ? donorList.equals(that.donorList) : that.donorList == null;
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
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (countryRisk != null ? countryRisk.hashCode() : 0);
        result = 31 * result + (ratingDonor != null ? ratingDonor.hashCode() : 0);
        result = 31 * result + (portfolioList != null ? portfolioList.hashCode() : 0);
        result = 31 * result + (counterpartyGroup != null ? counterpartyGroup.hashCode() : 0);
        result = 31 * result + (financialStatementList != null ? financialStatementList.hashCode() : 0);
        result = 31 * result + (ratingInternalList != null ? ratingInternalList.hashCode() : 0);
        result = 31 * result + (ratingExternalList != null ? ratingExternalList.hashCode() : 0);
        result = 31 * result + (committeeList != null ? committeeList.hashCode() : 0);
        result = 31 * result + (donorList != null ? donorList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
