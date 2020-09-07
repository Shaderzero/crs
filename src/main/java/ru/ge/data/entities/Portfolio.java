package ru.ge.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;

    @ManyToMany
    @JoinTable(name = "counterparty_portfolios",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "counterparty_id"))
    private List<Counterparty> counterpartyPList = new ArrayList<>();

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

    public List<Counterparty> getCounterpartyPList() {
        return counterpartyPList;
    }

    public void setCounterpartyPList(List<Counterparty> counterpartyPList) {
        this.counterpartyPList = counterpartyPList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Portfolio portfolio = (Portfolio) o;

        if (id != portfolio.id) return false;
        if (name != null ? !name.equals(portfolio.name) : portfolio.name != null) return false;
        return counterpartyPList != null ? counterpartyPList.equals(portfolio.counterpartyPList) : portfolio.counterpartyPList == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (counterpartyPList != null ? counterpartyPList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
