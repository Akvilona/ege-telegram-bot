/**
 * Создал Андрей Антонов 2/5/2024 8:56 AM.
 **/

package mytelegram.bot;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class QuestionStore {
    private final List<Question> questions;
    private QuestionStore() {
        Question question1 = Question.builder()
                .id(1L)
                .question("Когда газ образуется при взаимодействии кислорода с водородом?")
                .solution("2H2 + O2 = 2H2О")
                .ansver("Вода")
                .build();

        Question question2 = Question.builder()
                .id(2L)
                .question("Когда газ образуется при взаимодействии кислорода с углеродом?")
                .solution("С+ O2 = СО2")
                .ansver("Углекислый газ")
                .build();

        Question questionNo = Question.builder()
                .id(0L)
                .question("Такого вопроса у нас еще нет")
                .solution("Нам здесь нужно доработать программу.")
                .ansver("Спасибо")
                .build();

        this.questions = List.of(question1, question2, questionNo);
    }

    public Question getQuestionById(Long id) {
        return questions.stream()
                .filter(question ->  question.getId().equals(id))
                .findFirst()
                .orElse(questions.getLast());
    }
}
