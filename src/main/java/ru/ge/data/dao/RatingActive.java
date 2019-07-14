package ru.ge.data.dao;

import ru.ge.data.entities.Counterparty;
import ru.ge.data.entities.Rating;

import java.time.LocalDate;

public class RatingActive {
    private Counterparty counterparty;
    private Rating rating;
    private String source;
    private LocalDate startDate;
    private Counterparty donor;

    public RatingActive(Counterparty counterparty, Rating rating, LocalDate startDate, String source, Counterparty donor) {
        this.counterparty = counterparty;
        this.rating = rating;
        this.source = source;
        this.startDate = startDate;
        this.donor = donor;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Counterparty getDonor() {
        return donor;
    }

    public void setDonor(Counterparty donor) {
        this.donor = donor;
    }
}
