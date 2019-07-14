package ru.ge.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;

    @ManyToMany
    @JoinTable(name = "CounterpartyPortfolios",
            joinColumns = @JoinColumn(name = "Portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "Counterparty_id"))
    private List<Counterparty> counterpartyList = new ArrayList<>();

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

    public List<Counterparty> getCounterpartyList() {
        return counterpartyList;
    }

    public void setCounterpartyList(List<Counterparty> counterpartyList) {
        this.counterpartyList = counterpartyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Portfolio portfolio = (Portfolio) o;

        if (id != portfolio.id) return false;
        if (name != null ? !name.equals(portfolio.name) : portfolio.name != null) return false;
        return counterpartyList != null ? counterpartyList.equals(portfolio.counterpartyList) : portfolio.counterpartyList == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (counterpartyList != null ? counterpartyList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
