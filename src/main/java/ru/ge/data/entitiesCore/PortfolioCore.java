package ru.ge.data.entitiesCore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Portfolios")
public class PortfolioCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;

    @ManyToMany
    @JoinTable(name = "CounterpartyPortfolios",
            joinColumns = @JoinColumn(name = "PortfolioId"),
            inverseJoinColumns = @JoinColumn(name = "CounterpartyId"))
    private Set<CounterpartyCore> counterpartyList = new HashSet<>();

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

    public Set<CounterpartyCore> getCounterpartyList() {
        return counterpartyList;
    }

    public void setCounterpartyList(Set<CounterpartyCore> counterpartyList) {
        this.counterpartyList = counterpartyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortfolioCore that = (PortfolioCore) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return counterpartyList != null ? counterpartyList.equals(that.counterpartyList) : that.counterpartyList == null;
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
        return "PortfolioCore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", counterpartyList=" + counterpartyList +
                '}';
    }
}
