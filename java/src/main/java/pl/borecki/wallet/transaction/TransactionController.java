package pl.borecki.wallet.transaction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.borecki.wallet.category.Category;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    private Iterable<Transaction> index() {
        Iterable<Transaction> transactions = transactionRepository.findAll();

        return transactions;
    }

    @PostMapping
    public Transaction store(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @GetMapping("{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie ma tranzakcji z numerem ID:" +id));
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("{id}")
    public ResponseEntity<Transaction> update(@PathVariable long id,@RequestBody Transaction transactionDetails){
        Transaction update = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie ma tranzakcji z numerem ID:" +id));

        update.setName(transactionDetails.getName());
        update.setValue(transactionDetails.getValue());
        update.setType(transactionDetails.getType());


        transactionRepository.save(update);

        return ResponseEntity.ok(update);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete (@PathVariable long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie ma tranzakcji z numerem ID:" +id));

        transactionRepository.delete(transaction);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




