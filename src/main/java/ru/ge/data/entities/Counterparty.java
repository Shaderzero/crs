package ru.ge.data.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "counterparties")
public class Counterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 450, unique = true)
    private String name;

    @Column(name = "short_name", nullable = false, length = 15, unique = true)
    private String shortName;

    @Column(name = "intra_group", nullable = false)
    private boolean intraGroup;

    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;

    @Column(name = "comment", length = 1000)
    private String comment;

    @Column(name = "monitored", nullable = false)
    private boolean monitored;

    @Column(name = "ticker", length = 50)
    private String ticker;

    @Column(name = "inn", length = 30)
    private String inn;

    @Column(name = "swift", length = 100)
    private String swift;

    @Column(name = "long_term", nullable = false)
    private boolean isLongTerm;

    @Column(name = "etp", nullable = false)
    private boolean isETP;

    @Column(name = "efet", nullable = false)
    private boolean isEFET;

    @Column(name = "gtc")
    private String gtc;

    @ManyToOne
    @JoinColumn(name = "financial_sector_id", referencedColumnName = "id")
    private FinancialSector financialSector;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "country_risk_id", referencedColumnName = "id")
    private Country countryRisk;

    @ManyToOne
    @JoinColumn(name = "rating_donor_id", referencedColumnName = "id")
    private Counterparty ratingDonor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "counterparty_portfolios",
            joinColumns = @JoinColumn(name = "counterparty_id"),
            inverseJoinColumns = @JoinColumn(name = "portfolio_id"))
    @OrderBy("name")
    private List<Portfolio> portfolioList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "counterparty_group_id", referencedColumnName = "id")
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

    public void setFinancialSector(FinancialSector financialSectorP) {
        this.financialSector = financialSectorP;
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

    public void setCountryRisk(Country countryRiskP) {
        this.countryRisk = countryRiskP;
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

    public void setPortfolioList(List<Portfolio> portfolioPList) {
        this.portfolioList = portfolioPList;
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

    public void setRatingInternalList(List<RatingInternal> ratingInternalPList) {
        this.ratingInternalList = ratingInternalPList;
    }

    public List<RatingExternal> getRatingExternalList() {
        return ratingExternalList;
    }

    public void setRatingExternalList(List<RatingExternal> ratingExternalPList) {
        this.ratingExternalList = ratingExternalPList;
    }

    public List<Committee> getCommitteeList() {
        return committeeList;
    }

    public void setCommitteeList(List<Committee> committeePList) {
        this.committeeList = committeePList;
    }

    public boolean isLongTerm() {
        return isLongTerm;
    }

    public void setLongTerm(boolean longTerm) {
        isLongTerm = longTerm;
    }

    public boolean isETP() {
        return isETP;
    }

    public void setETP(boolean ETP) {
        isETP = ETP;
    }

    public boolean isEFET() {
        return isEFET;
    }

    public void setEFET(boolean EFET) {
        isEFET = EFET;
    }

    public String getGtc() {
        return gtc;
    }

    public void setGtc(String gtc) {
        this.gtc = gtc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counterparty that = (Counterparty) o;
        return id == that.id &&
                intraGroup == that.intraGroup &&
                monitored == that.monitored &&
                isLongTerm == that.isLongTerm &&
                isETP == that.isETP &&
                isEFET == that.isEFET &&
                Objects.equals(name, that.name) &&
                Objects.equals(shortName, that.shortName) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(ticker, that.ticker) &&
                Objects.equals(inn, that.inn) &&
                Objects.equals(swift, that.swift) &&
                Objects.equals(gtc, that.gtc) &&
                Objects.equals(financialSector, that.financialSector) &&
                Objects.equals(country, that.country) &&
                Objects.equals(countryRisk, that.countryRisk) &&
                Objects.equals(ratingDonor, that.ratingDonor) &&
                Objects.equals(portfolioList, that.portfolioList) &&
                Objects.equals(counterpartyGroup, that.counterpartyGroup) &&
                Objects.equals(financialStatementList, that.financialStatementList) &&
                Objects.equals(ratingInternalList, that.ratingInternalList) &&
                Objects.equals(ratingExternalList, that.ratingExternalList) &&
                Objects.equals(committeeList, that.committeeList) &&
                Objects.equals(donorList, that.donorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, intraGroup, dateStart, comment, monitored, ticker, inn, swift, isLongTerm, isETP, isEFET, gtc, financialSector, country, countryRisk, ratingDonor, portfolioList, counterpartyGroup, financialStatementList, ratingInternalList, ratingExternalList, committeeList, donorList);
    }

    @Override
    public String toString() {
        return name;
    }
}
