package mytelegram.bot;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionStore {
    private final List<Question> questions;

    private QuestionStore() {
        Question question1 = Question.builder()
            .id(1L)
            .description("Когда газ образуется при взаимодействии кислорода с водородом?")
            .solution("2H2 + O2 = 2H2О")
            .answer("Вода")
            .build();

        Question question2 = Question.builder()
            .id(2L)
            .description("Когда газ образуется при взаимодействии кислорода с углеродом?")
            .solution("С+ O2 = СО2")
            .answer("Углекислый газ")
            .build();

        Question questionNo = Question.builder()
            .id(0L)
            .description("Такого вопроса у нас еще нет")
            .solution("Нам здесь нужно доработать программу.")
            .answer("Спасибо")
            .build();

        this.questions = List.of(question1, question2, questionNo);
    }

    public Question getQuestionById(Long id) {
        return questions.stream()
            .filter(question -> question.getId().equals(id))
            .findFirst()
            .orElse(questions.getLast());
    }

    public List<Question> getQuestionList() {
        return questions;
    }
}
