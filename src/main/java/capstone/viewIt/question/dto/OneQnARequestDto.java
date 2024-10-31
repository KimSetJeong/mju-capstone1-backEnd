package capstone.viewIt.question.dto;

import jakarta.validation.constraints.NotEmpty;

public record OneQnARequestDto(
        @NotEmpty(message = "질문은 필수 입력값입니다.")
        String question,

        @NotEmpty(message = "답변은 필수 입력값입니다.")
        String answer
) {}