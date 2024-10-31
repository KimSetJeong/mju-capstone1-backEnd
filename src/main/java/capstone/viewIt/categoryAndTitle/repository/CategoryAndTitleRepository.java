package capstone.viewIt.categoryAndTitle.repository;

import capstone.viewIt.categoryAndTitle.domain.CategoryAndTitle;
import capstone.viewIt.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryAndTitleRepository extends JpaRepository<CategoryAndTitle, Long> {
    Optional<CategoryAndTitle> findFirstByMemberOrderByCreatedAtDesc(Member member);

    //findFirstByMemberOrderByCreatedAtDesc 는 생성날짜를 기준으로 내림차순을 하여 멤버 id르 바탕으로 그 안에서 가장 첫번째 카테고리,타이틀을 찾는다.
}
