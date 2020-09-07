package ru.ge.data.entities2;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ctl_Counterparties")
public class Counterparty2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "ShortName", nullable = false, length = 15)
    private String shortName;

    @Column(name = "BloombergTicker", nullable = true, length = 30)
    private String ticker;

    @ManyToOne
    @JoinColumn(name = "SectorID", referencedColumnName = "Id")
    private FinancialSector2 financialSector;

    @ManyToOne
    @JoinColumn(name = "CountryOfDomicileID", referencedColumnName = "Id")
    private Country2 countryDomicile;

    @ManyToOne
    @JoinColumn(name = "CountryOfRiskID", referencedColumnName = "Id")
    private Country2 countryRisk;

    @Column(name = "SAPID", nullable = true)
    private Integer sapId;

    @Column(name = "intraGroup", nullable = false)
    private boolean isIntraGroup;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "Comments", length = 4000)
    private String comment;

    @Column(name = "ToBeMonitored", nullable = false)
    private boolean isMonitored;

    @Column(name = "Longterm", nullable = false)
    private Boolean isLongTerm;

    @Column(name = "Etp", nullable = false)
    private Boolean isETP;

    @Column(name = "Efet", nullable = false)
    private Boolean isEFET;

    @Column(name = "Gtc")
    private String gtc;

    @ManyToOne
    @JoinColumn(name = "PortfolioID", referencedColumnName = "Id")
    private Portfolio2 portfolio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ctl_RatingDonor",
            joinColumns = @JoinColumn(name = "CounterpartyID"),
            inverseJoinColumns = @JoinColumn(name = "DonorID"))
    private Counterparty2 ratingDonor;

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

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public FinancialSector2 getFinancialSector() {
        return financialSector;
    }

    public void setFinancialSector(FinancialSector2 financialSector) {
        this.financialSector = financialSector;
    }

    public Country2 getCountryDomicile() {
        return countryDomicile;
    }

    public void setCountryDomicile(Country2 countryDomicile) {
        this.countryDomicile = countryDomicile;
    }

    public Country2 getCountryRisk() {
        return countryRisk;
    }

    public void setCountryRisk(Country2 countryRisk) {
        this.countryRisk = countryRisk;
    }

    public Integer getSapId() {
        return sapId;
    }

    public void setSapId(Integer sapId) {
        this.sapId = sapId;
    }

    public boolean isIntraGroup() {
        return isIntraGroup;
    }

    public void setIntraGroup(boolean intraGroup) {
        isIntraGroup = intraGroup;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isMonitored() {
        return isMonitored;
    }

    public void setMonitored(boolean monitored) {
        isMonitored = monitored;
    }

    public Portfolio2 getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio2 portfolio) {
        this.portfolio = portfolio;
    }

    public Counterparty2 getRatingDonor() {
        return ratingDonor;
    }

    public void setRatingDonor(Counterparty2 ratingDonor) {
        this.ratingDonor = ratingDonor;
    }

    public Boolean isLongTerm() {
        return isLongTerm;
    }

    public void setLongTerm(Boolean longTerm) {
        isLongTerm = longTerm;
    }

    public Boolean isETP() {
        return isETP;
    }

    public void setETP(Boolean ETP) {
        isETP = ETP;
    }

    public Boolean isEFET() {
        return isEFET;
    }

    public void setEFET(Boolean EFET) {
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

        Counterparty2 that = (Counterparty2) o;

        if (id != that.id) return false;
        if (sapId != that.sapId) return false;
        if (isIntraGroup != that.isIntraGroup) return false;
        if (isMonitored != that.isMonitored) return false;
        if (!name.equals(that.name)) return false;
        if (!shortName.equals(that.shortName)) return false;
        if (ticker != null ? !ticker.equals(that.ticker) : that.ticker != null) return false;
        if (financialSector != null ? !financialSector.equals(that.financialSector) : that.financialSector != null)
            return false;
        if (!countryDomicile.equals(that.countryDomicile)) return false;
        if (!countryRisk.equals(that.countryRisk)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (isLongTerm != that.isLongTerm) return false;
        if (isETP != that.isETP) return false;
        if (isEFET != that.isEFET) return false;
        if (!gtc.equals(that.gtc)) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        return portfolio != null ? portfolio.equals(that.portfolio) : that.portfolio == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + (ticker != null ? ticker.hashCode() : 0);
        result = 31 * result + (financialSector != null ? financialSector.hashCode() : 0);
        result = 31 * result + countryDomicile.hashCode();
        result = 31 * result + countryRisk.hashCode();
        result = 31 * result + sapId;
        result = 31 * result + (isIntraGroup ? 1 : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (isMonitored ? 1 : 0);
        result = 31 * result + (isLongTerm ? 1 : 0);
        result = 31 * result + (isETP ? 1 : 0);
        result = 31 * result + (isEFET ? 1 : 0);
        result = 31 * result + (gtc != null ? gtc.hashCode() : 0);
        result = 31 * result + (portfolio != null ? portfolio.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
