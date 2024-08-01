package java_project.personal_finance.service;

import java_project.personal_finance.dto.TransactionDto;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserModel user = (UserModel) userRepository.findByEmail(userEmail);
        transactionModel.setUserModel(user);
        transactionRepository.save(transactionModel);

/*        CategoryModel categoryModel = transactionModel.getCategoryModel();

        if(transactionModel.getType().equals("saque")){
            if(transactionModel.getAmount() > categoryModel.getAmount()){
                categoryModel.setAmount(categoryModel.getAmount()-transactionModel.getAmount());
            } else {
                throw new RuntimeException("Transaction not available");
            }
        } else {
            categoryModel.setAmount(categoryModel.getAmount()+transactionModel.getAmount());
        }*/
    }

    public void updateTransaction(TransactionDto transactionDto){
        Optional<TransactionModel> optionalTransaction = transactionRepository.findById(transactionDto.getId());

        if (optionalTransaction.isPresent()) {
            TransactionModel transaction = optionalTransaction.get();
            transaction.setDate(transactionDto.getDate());
            transaction.setAmount(transactionDto.getAmount());
            transaction.setType(transactionDto.getType());
            transaction.setDescription(transactionDto.getDescription());

            Optional<CategoryModel> optionalCategory = categoryRepository.findById(transactionDto.getCategoryId());
            if (optionalCategory.isPresent()) {
                transaction.setCategoryModel(optionalCategory.get());
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

    public List<TransactionModel> listByCategory(Long id){
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return transactionRepository.findByCategoryModel(optionalCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

/*    public Double amountByCategory(Long id){
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {

            List<TransactionModel> transactions = transactionRepository.findByCategoryModel(optionalCategory);

            Double totalValue = 0.00;
            for (TransactionModel transaction : transactions) {

                if (transaction.getType().equals("saque")) {
                    totalValue -= transaction.getAmount();
                } else {
                    totalValue += transaction.getAmount();
                }

            }
            return totalValue;

        } else {
            throw new RuntimeException("Category not found");
        }
    }*/

    public void deleteTransaction(Long id){
        if (transactionRepository.existsById(id)){
            transactionRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Transaction not found");
        }
    }

}
