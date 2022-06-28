package pl.borecki.wallet.category;

import org.springframework.web.bind.annotation.*;
import pl.borecki.wallet.transaction.Transaction;
import pl.borecki.wallet.transaction.TransactionRepository;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository= categoryRepository;
    }

    @GetMapping
    private Iterable<Category> index() {
        Iterable<Category> category= categoryRepository.findAll();

        return category;
    }

    @PostMapping
    public Category store(@RequestBody Category category){
        return categoryRepository.save(category);
    }
}
