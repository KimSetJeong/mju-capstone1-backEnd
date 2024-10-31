package capstone.viewIt.question.controller;

import capstone.viewIt.categoryAndTitle.service.CategoryAndTitleService;
import capstone.viewIt.common.response.CustomResponse;
import capstone.viewIt.member.controller.helper.TokenInformation;
import capstone.viewIt.question.domain.Resume;
import capstone.viewIt.question.dto.ResumeRequestDto;
import capstone.viewIt.question.dto.ResumeResponseDto;
import capstone.viewIt.question.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/resume")
    public ResumeResponseDto addResume(@RequestBody ResumeRequestDto requestDto, @TokenInformation Long memberId) {
        return resumeService.addResume(requestDto, memberId);
    }



}
