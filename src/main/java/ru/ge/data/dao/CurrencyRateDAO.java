package ru.ge.data.dao;

import java.time.LocalDate;

public class CurrencyRateDAO {
    private String from;
    private String to;
    private float rate;
    private LocalDate date;

    public CurrencyRateDAO(String from, String to, float rate, LocalDate date) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CurrencyRateDAO{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", rate=" + rate +
                ", date=" + date +
                '}';
    }
}
