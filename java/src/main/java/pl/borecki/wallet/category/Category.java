package pl.borecki.wallet.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.borecki.wallet.transaction.Transaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
@Data
@NoArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    public String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List <Transaction> transactions = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}