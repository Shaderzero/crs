package ru.ge.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "UserSettings")
public class UserSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public int id;

    @Column(name = "Username", length = 255, nullable = false, unique = true)
    public String username;

    @Column(name = "TableCounterpartyShowId")
    public Boolean tableCounterpartyShowId;

    @Column(name = "TableCounterpartyShowShortName")
    public Boolean tableCounterpartyShowShortName;

    @Column(name = "TableCounterpartyShowSector")
    public Boolean tableCounterpartyShowSector;

    @Column(name = "TableCounterpartyShowCountryOfRisk")
    public Boolean tableCounterpartyShowCountryOfRisk;

    @Column(name = "TableCounterpartyShowCountryOfDomicile")
    public Boolean tableCounterpartyShowCountryOfDomicile;

    @Column(name = "TableCounterpartyShowIntraGroup")
    public Boolean tableCounterpartyShowIntraGroup;

    @Column(name = "TableCounterpartyShowPortfolio")
    public Boolean tableCounterpartyShowPortfolio;

    @Column(name = "TableCounterpartyShowDonor")
    public Boolean tableCounterpartyShowDonor;

    @Column(name = "TableCounterpartyShowStartDate")
    public Boolean tableCounterpartyShowStartDate;

    @Column(name = "TableCounterpartyShowComment")
    public Boolean tableCounterpartyShowComment;

    @Column(name = "TableCounterpartyShowINN")
    public Boolean tableCounterpartyShowINN;

    @Column(name = "TableCounterpartyShowSWIFT")
    public Boolean tableCounterpartyShowSWIFT;

    @Column(name = "TableCounterpartyShowTicker")
    public Boolean tableCounterpartyShowTicker;

    @Column(name = "TableCounterpartyShowLastIntRating")
    public Boolean tableCounterpartyShowLastIntRating;

    @Column(name = "TableCounterpartyShowLastExtRating")
    public Boolean tableCounterpartyShowLastExtRating;

    @Column(name = "TableCounterpartyShowActiveRating")
    public Boolean tableCounterpartyShowActiveRating;

    @Column(name = "TableCounterpartyShowEffectiveRating")
    public Boolean tableCounterpartyShowEffectiveRating;

    public UserSetting() {
    }

    public UserSetting(String username) {
        this.username = username;
        this.tableCounterpartyShowId = true;
        this.tableCounterpartyShowShortName = false;
        this.tableCounterpartyShowSector = false;
        this.tableCounterpartyShowCountryOfRisk = false;
        this.tableCounterpartyShowCountryOfDomicile = false;
        this.tableCounterpartyShowIntraGroup = false;
        this.tableCounterpartyShowPortfolio = false;
        this.tableCounterpartyShowDonor = false;
        this.tableCounterpartyShowStartDate = false;
        this.tableCounterpartyShowComment = false;
        this.tableCounterpartyShowINN = false;
        this.tableCounterpartyShowSWIFT = false;
        this.tableCounterpartyShowTicker = false;
        this.tableCounterpartyShowLastIntRating = false;
        this.tableCounterpartyShowLastExtRating = false;
        this.tableCounterpartyShowActiveRating = false;
        this.tableCounterpartyShowEffectiveRating = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getTableCounterpartyShowId() {
        return tableCounterpartyShowId;
    }

    public void setTableCounterpartyShowId(Boolean tableCounterpartyShowId) {
        this.tableCounterpartyShowId = tableCounterpartyShowId;
    }

    public Boolean getTableCounterpartyShowShortName() {
        return tableCounterpartyShowShortName;
    }

    public void setTableCounterpartyShowShortName(Boolean tableCounterpartyShowShortName) {
        this.tableCounterpartyShowShortName = tableCounterpartyShowShortName;
    }

    public Boolean getTableCounterpartyShowSector() {
        return tableCounterpartyShowSector;
    }

    public void setTableCounterpartyShowSector(Boolean tableCounterpartyShowSector) {
        this.tableCounterpartyShowSector = tableCounterpartyShowSector;
    }

    public Boolean getTableCounterpartyShowCountryOfRisk() {
        return tableCounterpartyShowCountryOfRisk;
    }

    public void setTableCounterpartyShowCountryOfRisk(Boolean tableCounterpartyShowCountryOfRisk) {
        this.tableCounterpartyShowCountryOfRisk = tableCounterpartyShowCountryOfRisk;
    }

    public Boolean getTableCounterpartyShowCountryOfDomicile() {
        return tableCounterpartyShowCountryOfDomicile;
    }

    public void setTableCounterpartyShowCountryOfDomicile(Boolean tableCounterpartyShowCountryOfDomicile) {
        this.tableCounterpartyShowCountryOfDomicile = tableCounterpartyShowCountryOfDomicile;
    }

    public Boolean getTableCounterpartyShowIntraGroup() {
        return tableCounterpartyShowIntraGroup;
    }

    public void setTableCounterpartyShowIntraGroup(Boolean tableCounterpartyShowIntraGroup) {
        this.tableCounterpartyShowIntraGroup = tableCounterpartyShowIntraGroup;
    }

    public Boolean getTableCounterpartyShowPortfolio() {
        return tableCounterpartyShowPortfolio;
    }

    public void setTableCounterpartyShowPortfolio(Boolean tableCounterpartyShowPortfolio) {
        this.tableCounterpartyShowPortfolio = tableCounterpartyShowPortfolio;
    }

    public Boolean getTableCounterpartyShowDonor() {
        return tableCounterpartyShowDonor;
    }

    public void setTableCounterpartyShowDonor(Boolean tableCounterpartyShowDonor) {
        this.tableCounterpartyShowDonor = tableCounterpartyShowDonor;
    }

    public Boolean getTableCounterpartyShowStartDate() {
        return tableCounterpartyShowStartDate;
    }

    public void setTableCounterpartyShowStartDate(Boolean tableCounterpartyShowStartDate) {
        this.tableCounterpartyShowStartDate = tableCounterpartyShowStartDate;
    }

    public Boolean getTableCounterpartyShowComment() {
        return tableCounterpartyShowComment;
    }

    public void setTableCounterpartyShowComment(Boolean tableCounterpartyShowComment) {
        this.tableCounterpartyShowComment = tableCounterpartyShowComment;
    }

    public Boolean getTableCounterpartyShowINN() {
        return tableCounterpartyShowINN;
    }

    public void setTableCounterpartyShowINN(Boolean tableCounterpartyShowINN) {
        this.tableCounterpartyShowINN = tableCounterpartyShowINN;
    }

    public Boolean getTableCounterpartyShowSWIFT() {
        return tableCounterpartyShowSWIFT;
    }

    public void setTableCounterpartyShowSWIFT(Boolean tableCounterpartyShowSWIFT) {
        this.tableCounterpartyShowSWIFT = tableCounterpartyShowSWIFT;
    }

    public Boolean getTableCounterpartyShowTicker() {
        return tableCounterpartyShowTicker;
    }

    public void setTableCounterpartyShowTicker(Boolean tableCounterpartyShowTicker) {
        this.tableCounterpartyShowTicker = tableCounterpartyShowTicker;
    }

    public Boolean getTableCounterpartyShowLastIntRating() {
        return tableCounterpartyShowLastIntRating;
    }

    public void setTableCounterpartyShowLastIntRating(Boolean tableCounterpartyShowLastIntRating) {
        this.tableCounterpartyShowLastIntRating = tableCounterpartyShowLastIntRating;
    }

    public Boolean getTableCounterpartyShowLastExtRating() {
        return tableCounterpartyShowLastExtRating;
    }

    public void setTableCounterpartyShowLastExtRating(Boolean tableCounterpartyShowLastExtRating) {
        this.tableCounterpartyShowLastExtRating = tableCounterpartyShowLastExtRating;
    }

    public Boolean getTableCounterpartyShowActiveRating() {
        return tableCounterpartyShowActiveRating;
    }

    public void setTableCounterpartyShowActiveRating(Boolean tableCounterpartyShowActiveRating) {
        this.tableCounterpartyShowActiveRating = tableCounterpartyShowActiveRating;
    }

    public Boolean getTableCounterpartyShowEffectiveRating() {
        return tableCounterpartyShowEffectiveRating;
    }

    public void setTableCounterpartyShowEffectiveRating(Boolean tableCounterpartyShowEffectiveRating) {
        this.tableCounterpartyShowEffectiveRating = tableCounterpartyShowEffectiveRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSetting that = (UserSetting) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (tableCounterpartyShowId != null ? !tableCounterpartyShowId.equals(that.tableCounterpartyShowId) : that.tableCounterpartyShowId != null)
            return false;
        if (tableCounterpartyShowShortName != null ? !tableCounterpartyShowShortName.equals(that.tableCounterpartyShowShortName) : that.tableCounterpartyShowShortName != null)
            return false;
        if (tableCounterpartyShowSector != null ? !tableCounterpartyShowSector.equals(that.tableCounterpartyShowSector) : that.tableCounterpartyShowSector != null)
            return false;
        if (tableCounterpartyShowCountryOfRisk != null ? !tableCounterpartyShowCountryOfRisk.equals(that.tableCounterpartyShowCountryOfRisk) : that.tableCounterpartyShowCountryOfRisk != null)
            return false;
        if (tableCounterpartyShowCountryOfDomicile != null ? !tableCounterpartyShowCountryOfDomicile.equals(that.tableCounterpartyShowCountryOfDomicile) : that.tableCounterpartyShowCountryOfDomicile != null)
            return false;
        if (tableCounterpartyShowIntraGroup != null ? !tableCounterpartyShowIntraGroup.equals(that.tableCounterpartyShowIntraGroup) : that.tableCounterpartyShowIntraGroup != null)
            return false;
        if (tableCounterpartyShowPortfolio != null ? !tableCounterpartyShowPortfolio.equals(that.tableCounterpartyShowPortfolio) : that.tableCounterpartyShowPortfolio != null)
            return false;
        if (tableCounterpartyShowDonor != null ? !tableCounterpartyShowDonor.equals(that.tableCounterpartyShowDonor) : that.tableCounterpartyShowDonor != null)
            return false;
        if (tableCounterpartyShowStartDate != null ? !tableCounterpartyShowStartDate.equals(that.tableCounterpartyShowStartDate) : that.tableCounterpartyShowStartDate != null)
            return false;
        if (tableCounterpartyShowComment != null ? !tableCounterpartyShowComment.equals(that.tableCounterpartyShowComment) : that.tableCounterpartyShowComment != null)
            return false;
        if (tableCounterpartyShowINN != null ? !tableCounterpartyShowINN.equals(that.tableCounterpartyShowINN) : that.tableCounterpartyShowINN != null)
            return false;
        if (tableCounterpartyShowSWIFT != null ? !tableCounterpartyShowSWIFT.equals(that.tableCounterpartyShowSWIFT) : that.tableCounterpartyShowSWIFT != null)
            return false;
        if (tableCounterpartyShowTicker != null ? !tableCounterpartyShowTicker.equals(that.tableCounterpartyShowTicker) : that.tableCounterpartyShowTicker != null)
            return false;
        if (tableCounterpartyShowLastIntRating != null ? !tableCounterpartyShowLastIntRating.equals(that.tableCounterpartyShowLastIntRating) : that.tableCounterpartyShowLastIntRating != null)
            return false;
        if (tableCounterpartyShowLastExtRating != null ? !tableCounterpartyShowLastExtRating.equals(that.tableCounterpartyShowLastExtRating) : that.tableCounterpartyShowLastExtRating != null)
            return false;
        if (tableCounterpartyShowActiveRating != null ? !tableCounterpartyShowActiveRating.equals(that.tableCounterpartyShowActiveRating) : that.tableCounterpartyShowActiveRating != null)
            return false;
        return tableCounterpartyShowEffectiveRating != null ? tableCounterpartyShowEffectiveRating.equals(that.tableCounterpartyShowEffectiveRating) : that.tableCounterpartyShowEffectiveRating == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowId != null ? tableCounterpartyShowId.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowShortName != null ? tableCounterpartyShowShortName.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowSector != null ? tableCounterpartyShowSector.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowCountryOfRisk != null ? tableCounterpartyShowCountryOfRisk.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowCountryOfDomicile != null ? tableCounterpartyShowCountryOfDomicile.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowIntraGroup != null ? tableCounterpartyShowIntraGroup.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowPortfolio != null ? tableCounterpartyShowPortfolio.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowDonor != null ? tableCounterpartyShowDonor.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowStartDate != null ? tableCounterpartyShowStartDate.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowComment != null ? tableCounterpartyShowComment.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowINN != null ? tableCounterpartyShowINN.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowSWIFT != null ? tableCounterpartyShowSWIFT.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowTicker != null ? tableCounterpartyShowTicker.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowLastIntRating != null ? tableCounterpartyShowLastIntRating.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowLastExtRating != null ? tableCounterpartyShowLastExtRating.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowActiveRating != null ? tableCounterpartyShowActiveRating.hashCode() : 0);
        result = 31 * result + (tableCounterpartyShowEffectiveRating != null ? tableCounterpartyShowEffectiveRating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserSetting{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", tableCounterpartyShowId=" + tableCounterpartyShowId +
                ", tableCounterpartyShowShortName=" + tableCounterpartyShowShortName +
                ", tableCounterpartyShowSector=" + tableCounterpartyShowSector +
                ", tableCounterpartyShowCountryOfRisk=" + tableCounterpartyShowCountryOfRisk +
                ", tableCounterpartyShowCountryOfDomicile=" + tableCounterpartyShowCountryOfDomicile +
                ", tableCounterpartyShowIntraGroup=" + tableCounterpartyShowIntraGroup +
                ", tableCounterpartyShowPortfolio=" + tableCounterpartyShowPortfolio +
                ", tableCounterpartyShowDonor=" + tableCounterpartyShowDonor +
                ", tableCounterpartyShowStartDate=" + tableCounterpartyShowStartDate +
                ", tableCounterpartyShowComment=" + tableCounterpartyShowComment +
                ", tableCounterpartyShowINN=" + tableCounterpartyShowINN +
                ", tableCounterpartyShowSWIFT=" + tableCounterpartyShowSWIFT +
                ", tableCounterpartyShowTicker=" + tableCounterpartyShowTicker +
                ", tableCounterpartyShowLastIntRating=" + tableCounterpartyShowLastIntRating +
                ", tableCounterpartyShowLastExtRating=" + tableCounterpartyShowLastExtRating +
                ", tableCounterpartyShowActiveRating=" + tableCounterpartyShowActiveRating +
                ", tableCounterpartyShowEffectiveRating=" + tableCounterpartyShowEffectiveRating +
                '}';
    }
}
