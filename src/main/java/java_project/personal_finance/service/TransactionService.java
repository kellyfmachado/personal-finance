package java_project.personal_finance.service;

import java_project.personal_finance.dto.UpdateTransactionDto;
import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.model.TransactionModel;
import java_project.personal_finance.repository.CategoryRepository;
import java_project.personal_finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void addTransaction(TransactionModel transactionModel){
        transactionRepository.save(transactionModel);
    }

    public void updateTransaction(UpdateTransactionDto updateTransactionDto){
        Optional<TransactionModel> optionalTransaction = transactionRepository.findById(updateTransactionDto.getId());

        if (optionalTransaction.isPresent()) {
            TransactionModel transaction = optionalTransaction.get();
            transaction.setDate(updateTransactionDto.getDate());
            transaction.setAmount(updateTransactionDto.getAmount());
            transaction.setType(updateTransactionDto.getType());
            transaction.setDescription(updateTransactionDto.getDescription());

            Optional<CategoryModel> optionalCategory = categoryRepository.findById(updateTransactionDto.getCategoryId());
            if (optionalCategory.isPresent()) {
                transaction.setCategory(optionalCategory.get());
            } else {
                throw new RuntimeException("Category not found");
            }

            transactionRepository.save(transaction);

        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    public List<TransactionModel> listAll(){
        return transactionRepository.findAll();
    }

    public void deleteTransaction(UUID id){
        if (transactionRepository.existsById(id)){
            transactionRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Transaction not found");
        }
    }

}