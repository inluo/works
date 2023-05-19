package works.entity.Dto;

import lombok.Data;
import works.entity.Students;

@Data
public class StudentsDto extends Students {
    private String trainerName;
}
