package capstone.viewIt.question.service;

import capstone.viewIt.categoryAndTitle.domain.CategoryAndTitle;
import capstone.viewIt.categoryAndTitle.repository.CategoryAndTitleRepository;
import capstone.viewIt.common.exception.CustomException;
import capstone.viewIt.common.exception.ErrorCode;
import capstone.viewIt.member.domain.Member;
import capstone.viewIt.member.repository.MemberRepository;
import capstone.viewIt.question.domain.Resume;
import capstone.viewIt.question.dto.ResumeRequestDto;
import capstone.viewIt.question.dto.ResumeResponseDto;
import capstone.viewIt.question.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final CategoryAndTitleRepository categoryAndTitleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResumeResponseDto addResume(ResumeRequestDto requestDto, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        CategoryAndTitle categoryAndTitle = categoryAndTitleRepository.findFirstByMemberOrderByCreatedAtDesc(member)
                .orElseThrow(()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));

        List<Resume> resumes = requestDto.Resume().stream()
                .map(oneByOne ->{
                    Resume resume = Resume.of(
                            oneByOne.question(),
                            oneByOne.answer(),
                            categoryAndTitle
                    );
                    categoryAndTitle.addQuestion(resume);
                    return resume;
                }).toList(); //list 로 반환인데 불변임 ,List<Resume> 얘와 관련 없게 만든다.

        for(int i=0; i<resumes.size(); i++) {
            if(resumes.get(i).getQuestion().isEmpty() || resumes.get(i).getAnswer().isEmpty()) {
                throw new CustomException(ErrorCode.BAD_REQUEST);
            }
        }

        if (resumes.size() < 3 || resumes.size() > 5) {
            throw new CustomException(ErrorCode.OVER_RESUME);
        }
        List<Resume> saveResumes = resumeRepository.saveAll(resumes);

        List<String> resumeItems = saveResumes.stream().map(Resume::getAnswer).toList();

        String jobType = saveResumes.get(0).getCategoryAndTitle().getCategoryName();

        return new ResumeResponseDto(resumeItems, jobType);


    }

}
