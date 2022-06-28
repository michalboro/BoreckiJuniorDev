package pl.borecki.wallet.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.borecki.wallet.category.Category;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity

@NoArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private Long value;

    private String type;

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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction(String name, Long value, String type, Category category) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.category = category;
    }
    public Category getCategory() {return category;}

//    public void setCategory(Category category) {
//        this.category = category;
//    }

}
