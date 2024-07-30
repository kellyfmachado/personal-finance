package java_project.personal_finance.service;

import java_project.personal_finance.dto.UpdateTransactionDto;
import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.model.TransactionModel;
import java_project.personal_finance.model.UserModel;
import java_project.personal_finance.repository.CategoryRepository;
import java_project.personal_finance.repository.TransactionRepository;
import java_project.personal_finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void addTransaction(TransactionModel transactionModel){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserModel user = (UserModel) userRepository.findByEmail(userEmail);
        transactionModel.setUserModel(user);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        return transactionRepository.findByUserModel(userModel);
    }

    public void deleteTransaction(Long id){
        if (transactionRepository.existsById(id)){
            transactionRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Transaction not found");
        }
    }

}
