package java_project.personal_finance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UpdateTransactionDto {
    @NotNull
    UUID id;
    @NotNull
    Date date;
    @NotNull
    Double amount;
    @NotNull
    String type;
    @NotNull
    UUID categoryId;
    @NotNull
    String description;
}
