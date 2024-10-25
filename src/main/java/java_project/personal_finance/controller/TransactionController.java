package java_project.personal_finance.controller;

import java_project.personal_finance.dto.TransactionDto;
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
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateTransaction (@RequestBody TransactionDto transactionDto){
        transactionService.updateTransaction(transactionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<TransactionModel>> listAll(){
        return ResponseEntity.ok(transactionService.listAll());
    }

    @GetMapping("/listByCategory/{id}")
    public ResponseEntity<List<TransactionModel>> listByCategory(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.listByCategory(id));
    }

}
