package capstone.viewIt.question.dto;


import jakarta.validation.constraints.NotEmpty;

import java.util.List;


public record ResumeRequestDto (

        @NotEmpty(message = "질문과 답변이 각각 3개이상 적어주세요")
        List<OneQnARequestDto> Resume
        ){}

