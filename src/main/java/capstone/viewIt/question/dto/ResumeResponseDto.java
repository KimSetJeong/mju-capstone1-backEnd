package capstone.viewIt.question.dto;

import java.util.List;

public record ResumeResponseDto(List<String> resume_items, String job_type) {}
