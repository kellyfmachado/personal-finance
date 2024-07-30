package java_project.personal_finance.controller;

import java_project.personal_finance.dto.UpdateTransactionDto;
import java_project.personal_finance.model.TransactionModel;
import java_project.personal_finance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<Object> addTransaction (@RequestBody TransactionModel transactionModel){
        transactionService.addTransaction(transactionModel);
        return ResponseEntity.ok().body("Transaction added");
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateTransaction (@RequestBody UpdateTransactionDto updateTransactionDto){
        transactionService.updateTransaction(updateTransactionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteTransaction(Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().body("Transaction deleted");
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<TransactionModel>> listAll(){
        return ResponseEntity.ok(transactionService.listAll());
    }

}
