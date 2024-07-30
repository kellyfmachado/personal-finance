package java_project.personal_finance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryDto {
    @NotNull
    Long id;
    @NotNull
    String name;

}
