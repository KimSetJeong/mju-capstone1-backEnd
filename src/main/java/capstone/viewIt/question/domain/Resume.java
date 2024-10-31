package capstone.viewIt.question.domain;

import capstone.viewIt.categoryAndTitle.domain.CategoryAndTitle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @Column(nullable = false) private String question;

    @Setter @Column(nullable = false) private String answer;

    @Setter @ManyToOne(optional = false) private CategoryAndTitle categoryAndTitle;

    protected Resume() {}

    private Resume(String question, String answer, CategoryAndTitle categoryAndTitle) {
        this.question = question;
        this.answer = answer;
        this.categoryAndTitle = categoryAndTitle;
    }

    public static Resume of(String question, String answer, CategoryAndTitle categoryAndTitle) {
        return new Resume(question, answer, categoryAndTitle);
    }
}
