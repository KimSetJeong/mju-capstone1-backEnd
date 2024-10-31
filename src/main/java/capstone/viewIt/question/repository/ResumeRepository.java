package capstone.viewIt.question.repository;

import capstone.viewIt.question.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
}
