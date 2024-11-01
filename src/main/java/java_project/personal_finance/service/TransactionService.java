package java_project.personal_finance.service;

import java_project.personal_finance.dto.TransactionDto;
import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.model.TransactionModel;
import java_project.personal_finance.model.UserModel;
import java_project.personal_finance.repository.CategoryRepository;
import java_project.personal_finance.repository.TransactionRepository;
import java_project.personal_finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        Optional<CategoryModel> categoryModel = categoryRepository.findById(transactionModel.getCategoryModel().getId());

        if (categoryModel.isPresent()){

            if (categoryModel.get().getAmount() == null) {
                categoryModel.get().setAmount(0.00);
            }

            if(transactionModel.getType().equals("saque")){

                if(transactionModel.getAmount() <= categoryModel.get().getAmount()){
                    categoryModel.get().setAmount(categoryModel.get().getAmount()-transactionModel.getAmount());
                } else {
                    throw new RuntimeException("Transaction not available");
                }

            } else {
                categoryModel.get().setAmount(categoryModel.get().getAmount()+transactionModel.getAmount());
            }

        } else {
            throw new RuntimeException("Category not found");
        }

        categoryRepository.save(categoryModel.get());

        transactionRepository.save(transactionModel);
    }

    public void updateTransaction(TransactionDto transactionDto){
        Optional<TransactionModel> optionalTransaction = transactionRepository.findById(transactionDto.getId());

        if (optionalTransaction.isPresent()) {
            TransactionModel transaction = optionalTransaction.get();
            transaction.setDate(transactionDto.getDate());
            if(transactionDto.getAmount()!=0) {
                transaction.setAmount(transactionDto.getAmount());
            }
            if(!transactionDto.getType().isEmpty()) {
                transaction.setType(transactionDto.getType());
            }
            if(!transactionDto.getDescription().isEmpty()) {
                transaction.setDescription(transactionDto.getDescription());
            }

            if(transactionDto.getCategoryId()!=0){
                Optional<CategoryModel> optionalCategory = categoryRepository.findById(transactionDto.getCategoryId());

                if (optionalCategory.isPresent()) {
                    transaction.setCategoryModel(optionalCategory.get());
                } else {
                    throw new RuntimeException("Category not found");
                }
            }

            transactionRepository.save(transaction);

        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    public Page<TransactionModel> list(int page, int size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.findByUserModel(userModel, pageable);
    }

    public Page<TransactionModel> listByCategory(Long id,  int page, int size){
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);
        Pageable pageable = PageRequest.of(page, size);
        if (optionalCategory.isPresent()) {
            return transactionRepository.findByCategoryModel(optionalCategory, pageable);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public void deleteTransaction(Long id){
        if (transactionRepository.existsById(id)){
            Optional<TransactionModel> transactionModel = transactionRepository.findById(id);
            if (transactionModel.isPresent()) {
                Optional<CategoryModel> categoryModel = categoryRepository.findById(transactionModel.get().getCategoryModel().getId());
                if (categoryModel.isPresent()) {
                   categoryModel.get().setAmount(categoryModel.get().getAmount()-transactionModel.get().getAmount());
                   categoryRepository.save(categoryModel.get());
                }
            }

            transactionRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Transaction not found");
        }
    }

}
