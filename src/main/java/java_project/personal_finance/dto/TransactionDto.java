package java_project.personal_finance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDto {
    @NotNull
    Long id;
    @NotNull
    Date date;
    @NotNull
    Double amount;
    @NotNull
    String type;
    @NotNull
    Long categoryId;
    @NotNull
    String description;
}
