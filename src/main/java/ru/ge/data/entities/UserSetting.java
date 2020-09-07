package ru.ge.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_settings")
public class UserSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "username", length = 255, nullable = false, unique = true)
    public String username;

    @Column(name = "table_counterparty_show_id")
    public Boolean table_counterparty_show_Id;

    @Column(name = "table_counterparty_show_short_name")
    public Boolean table_counterparty_show_ShortName;

    @Column(name = "table_counterparty_show_sector")
    public Boolean table_counterparty_show_Sector;

    @Column(name = "table_counterparty_show_country_of_risk")
    public Boolean table_counterparty_show_CountryOfRisk;

    @Column(name = "table_counterparty_show_country_of_domicile")
    public Boolean table_counterparty_show_CountryOfDomicile;

    @Column(name = "table_counterparty_show_intra_group")
    public Boolean table_counterparty_show_IntraGroup;

    @Column(name = "table_counterparty_show_portfolio")
    public Boolean table_counterparty_show_Portfolio;

    @Column(name = "table_counterparty_show_donor")
    public Boolean table_counterparty_show_Donor;

    @Column(name = "table_counterparty_show_start_date")
    public Boolean table_counterparty_show_StartDate;

    @Column(name = "table_counterparty_show_comment")
    public Boolean table_counterparty_show_Comment;

    @Column(name = "table_counterparty_show_inn")
    public Boolean table_counterparty_show_INN;

    @Column(name = "table_counterparty_show_swift")
    public Boolean table_counterparty_show_SWIFT;

    @Column(name = "table_counterparty_show_ticker")
    public Boolean table_counterparty_show_Ticker;

    @Column(name = "table_counterparty_show_last_int_rating")
    public Boolean table_counterparty_show_LastIntRating;

    @Column(name = "table_counterparty_show_last_ext_rating")
    public Boolean table_counterparty_show_LastExtRating;

    @Column(name = "table_counterparty_show_active_rating")
    public Boolean table_counterparty_show_ActiveRating;

    @Column(name = "table_counterparty_show_effective_rating")
    public Boolean table_counterparty_show_EffectiveRating;

    public UserSetting() {
    }

    public UserSetting(String username) {
        this.username = username;
        this.table_counterparty_show_Id = true;
        this.table_counterparty_show_ShortName = false;
        this.table_counterparty_show_Sector = false;
        this.table_counterparty_show_CountryOfRisk = false;
        this.table_counterparty_show_CountryOfDomicile = false;
        this.table_counterparty_show_IntraGroup = false;
        this.table_counterparty_show_Portfolio = false;
        this.table_counterparty_show_Donor = false;
        this.table_counterparty_show_StartDate = false;
        this.table_counterparty_show_Comment = false;
        this.table_counterparty_show_INN = false;
        this.table_counterparty_show_SWIFT = false;
        this.table_counterparty_show_Ticker = false;
        this.table_counterparty_show_LastIntRating = false;
        this.table_counterparty_show_LastExtRating = false;
        this.table_counterparty_show_ActiveRating = false;
        this.table_counterparty_show_EffectiveRating = false;
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

    public Boolean gettable_counterparty_show_Id() {
        return table_counterparty_show_Id;
    }

    public void settable_counterparty_show_Id(Boolean table_counterparty_show_Id) {
        this.table_counterparty_show_Id = table_counterparty_show_Id;
    }

    public Boolean gettable_counterparty_show_ShortName() {
        return table_counterparty_show_ShortName;
    }

    public void settable_counterparty_show_ShortName(Boolean table_counterparty_show_ShortName) {
        this.table_counterparty_show_ShortName = table_counterparty_show_ShortName;
    }

    public Boolean gettable_counterparty_show_Sector() {
        return table_counterparty_show_Sector;
    }

    public void settable_counterparty_show_Sector(Boolean table_counterparty_show_Sector) {
        this.table_counterparty_show_Sector = table_counterparty_show_Sector;
    }

    public Boolean gettable_counterparty_show_CountryOfRisk() {
        return table_counterparty_show_CountryOfRisk;
    }

    public void settable_counterparty_show_CountryOfRisk(Boolean table_counterparty_show_CountryOfRisk) {
        this.table_counterparty_show_CountryOfRisk = table_counterparty_show_CountryOfRisk;
    }

    public Boolean gettable_counterparty_show_CountryOfDomicile() {
        return table_counterparty_show_CountryOfDomicile;
    }

    public void settable_counterparty_show_CountryOfDomicile(Boolean table_counterparty_show_CountryOfDomicile) {
        this.table_counterparty_show_CountryOfDomicile = table_counterparty_show_CountryOfDomicile;
    }

    public Boolean gettable_counterparty_show_IntraGroup() {
        return table_counterparty_show_IntraGroup;
    }

    public void settable_counterparty_show_IntraGroup(Boolean table_counterparty_show_IntraGroup) {
        this.table_counterparty_show_IntraGroup = table_counterparty_show_IntraGroup;
    }

    public Boolean gettable_counterparty_show_Portfolio() {
        return table_counterparty_show_Portfolio;
    }

    public void settable_counterparty_show_Portfolio(Boolean table_counterparty_show_Portfolio) {
        this.table_counterparty_show_Portfolio = table_counterparty_show_Portfolio;
    }

    public Boolean gettable_counterparty_show_Donor() {
        return table_counterparty_show_Donor;
    }

    public void settable_counterparty_show_Donor(Boolean table_counterparty_show_Donor) {
        this.table_counterparty_show_Donor = table_counterparty_show_Donor;
    }

    public Boolean gettable_counterparty_show_StartDate() {
        return table_counterparty_show_StartDate;
    }

    public void settable_counterparty_show_StartDate(Boolean table_counterparty_show_StartDate) {
        this.table_counterparty_show_StartDate = table_counterparty_show_StartDate;
    }

    public Boolean gettable_counterparty_show_Comment() {
        return table_counterparty_show_Comment;
    }

    public void settable_counterparty_show_Comment(Boolean table_counterparty_show_Comment) {
        this.table_counterparty_show_Comment = table_counterparty_show_Comment;
    }

    public Boolean gettable_counterparty_show_INN() {
        return table_counterparty_show_INN;
    }

    public void settable_counterparty_show_INN(Boolean table_counterparty_show_INN) {
        this.table_counterparty_show_INN = table_counterparty_show_INN;
    }

    public Boolean gettable_counterparty_show_SWIFT() {
        return table_counterparty_show_SWIFT;
    }

    public void settable_counterparty_show_SWIFT(Boolean table_counterparty_show_SWIFT) {
        this.table_counterparty_show_SWIFT = table_counterparty_show_SWIFT;
    }

    public Boolean gettable_counterparty_show_Ticker() {
        return table_counterparty_show_Ticker;
    }

    public void settable_counterparty_show_Ticker(Boolean table_counterparty_show_Ticker) {
        this.table_counterparty_show_Ticker = table_counterparty_show_Ticker;
    }

    public Boolean gettable_counterparty_show_LastIntRating() {
        return table_counterparty_show_LastIntRating;
    }

    public void settable_counterparty_show_LastIntRating(Boolean table_counterparty_show_LastIntRating) {
        this.table_counterparty_show_LastIntRating = table_counterparty_show_LastIntRating;
    }

    public Boolean gettable_counterparty_show_LastExtRating() {
        return table_counterparty_show_LastExtRating;
    }

    public void settable_counterparty_show_LastExtRating(Boolean table_counterparty_show_LastExtRating) {
        this.table_counterparty_show_LastExtRating = table_counterparty_show_LastExtRating;
    }

    public Boolean gettable_counterparty_show_ActiveRating() {
        return table_counterparty_show_ActiveRating;
    }

    public void settable_counterparty_show_ActiveRating(Boolean table_counterparty_show_ActiveRating) {
        this.table_counterparty_show_ActiveRating = table_counterparty_show_ActiveRating;
    }

    public Boolean gettable_counterparty_show_EffectiveRating() {
        return table_counterparty_show_EffectiveRating;
    }

    public void settable_counterparty_show_EffectiveRating(Boolean table_counterparty_show_EffectiveRating) {
        this.table_counterparty_show_EffectiveRating = table_counterparty_show_EffectiveRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSetting that = (UserSetting) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (table_counterparty_show_Id != null ? !table_counterparty_show_Id.equals(that.table_counterparty_show_Id) : that.table_counterparty_show_Id != null)
            return false;
        if (table_counterparty_show_ShortName != null ? !table_counterparty_show_ShortName.equals(that.table_counterparty_show_ShortName) : that.table_counterparty_show_ShortName != null)
            return false;
        if (table_counterparty_show_Sector != null ? !table_counterparty_show_Sector.equals(that.table_counterparty_show_Sector) : that.table_counterparty_show_Sector != null)
            return false;
        if (table_counterparty_show_CountryOfRisk != null ? !table_counterparty_show_CountryOfRisk.equals(that.table_counterparty_show_CountryOfRisk) : that.table_counterparty_show_CountryOfRisk != null)
            return false;
        if (table_counterparty_show_CountryOfDomicile != null ? !table_counterparty_show_CountryOfDomicile.equals(that.table_counterparty_show_CountryOfDomicile) : that.table_counterparty_show_CountryOfDomicile != null)
            return false;
        if (table_counterparty_show_IntraGroup != null ? !table_counterparty_show_IntraGroup.equals(that.table_counterparty_show_IntraGroup) : that.table_counterparty_show_IntraGroup != null)
            return false;
        if (table_counterparty_show_Portfolio != null ? !table_counterparty_show_Portfolio.equals(that.table_counterparty_show_Portfolio) : that.table_counterparty_show_Portfolio != null)
            return false;
        if (table_counterparty_show_Donor != null ? !table_counterparty_show_Donor.equals(that.table_counterparty_show_Donor) : that.table_counterparty_show_Donor != null)
            return false;
        if (table_counterparty_show_StartDate != null ? !table_counterparty_show_StartDate.equals(that.table_counterparty_show_StartDate) : that.table_counterparty_show_StartDate != null)
            return false;
        if (table_counterparty_show_Comment != null ? !table_counterparty_show_Comment.equals(that.table_counterparty_show_Comment) : that.table_counterparty_show_Comment != null)
            return false;
        if (table_counterparty_show_INN != null ? !table_counterparty_show_INN.equals(that.table_counterparty_show_INN) : that.table_counterparty_show_INN != null)
            return false;
        if (table_counterparty_show_SWIFT != null ? !table_counterparty_show_SWIFT.equals(that.table_counterparty_show_SWIFT) : that.table_counterparty_show_SWIFT != null)
            return false;
        if (table_counterparty_show_Ticker != null ? !table_counterparty_show_Ticker.equals(that.table_counterparty_show_Ticker) : that.table_counterparty_show_Ticker != null)
            return false;
        if (table_counterparty_show_LastIntRating != null ? !table_counterparty_show_LastIntRating.equals(that.table_counterparty_show_LastIntRating) : that.table_counterparty_show_LastIntRating != null)
            return false;
        if (table_counterparty_show_LastExtRating != null ? !table_counterparty_show_LastExtRating.equals(that.table_counterparty_show_LastExtRating) : that.table_counterparty_show_LastExtRating != null)
            return false;
        if (table_counterparty_show_ActiveRating != null ? !table_counterparty_show_ActiveRating.equals(that.table_counterparty_show_ActiveRating) : that.table_counterparty_show_ActiveRating != null)
            return false;
        return table_counterparty_show_EffectiveRating != null ? table_counterparty_show_EffectiveRating.equals(that.table_counterparty_show_EffectiveRating) : that.table_counterparty_show_EffectiveRating == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_Id != null ? table_counterparty_show_Id.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_ShortName != null ? table_counterparty_show_ShortName.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_Sector != null ? table_counterparty_show_Sector.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_CountryOfRisk != null ? table_counterparty_show_CountryOfRisk.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_CountryOfDomicile != null ? table_counterparty_show_CountryOfDomicile.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_IntraGroup != null ? table_counterparty_show_IntraGroup.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_Portfolio != null ? table_counterparty_show_Portfolio.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_Donor != null ? table_counterparty_show_Donor.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_StartDate != null ? table_counterparty_show_StartDate.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_Comment != null ? table_counterparty_show_Comment.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_INN != null ? table_counterparty_show_INN.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_SWIFT != null ? table_counterparty_show_SWIFT.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_Ticker != null ? table_counterparty_show_Ticker.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_LastIntRating != null ? table_counterparty_show_LastIntRating.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_LastExtRating != null ? table_counterparty_show_LastExtRating.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_ActiveRating != null ? table_counterparty_show_ActiveRating.hashCode() : 0);
        result = 31 * result + (table_counterparty_show_EffectiveRating != null ? table_counterparty_show_EffectiveRating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserSetting{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", table_counterparty_show_Id=" + table_counterparty_show_Id +
                ", table_counterparty_show_ShortName=" + table_counterparty_show_ShortName +
                ", table_counterparty_show_Sector=" + table_counterparty_show_Sector +
                ", table_counterparty_show_CountryOfRisk=" + table_counterparty_show_CountryOfRisk +
                ", table_counterparty_show_CountryOfDomicile=" + table_counterparty_show_CountryOfDomicile +
                ", table_counterparty_show_IntraGroup=" + table_counterparty_show_IntraGroup +
                ", table_counterparty_show_Portfolio=" + table_counterparty_show_Portfolio +
                ", table_counterparty_show_Donor=" + table_counterparty_show_Donor +
                ", table_counterparty_show_StartDate=" + table_counterparty_show_StartDate +
                ", table_counterparty_show_Comment=" + table_counterparty_show_Comment +
                ", table_counterparty_show_INN=" + table_counterparty_show_INN +
                ", table_counterparty_show_SWIFT=" + table_counterparty_show_SWIFT +
                ", table_counterparty_show_Ticker=" + table_counterparty_show_Ticker +
                ", table_counterparty_show_LastIntRating=" + table_counterparty_show_LastIntRating +
                ", table_counterparty_show_LastExtRating=" + table_counterparty_show_LastExtRating +
                ", table_counterparty_show_ActiveRating=" + table_counterparty_show_ActiveRating +
                ", table_counterparty_show_EffectiveRating=" + table_counterparty_show_EffectiveRating +
                '}';
    }
}
