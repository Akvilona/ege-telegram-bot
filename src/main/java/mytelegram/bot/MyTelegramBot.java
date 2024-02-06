/**
 * Создал Андрей Антонов 2/4/2024 7:51 PM.
 **/

package mytelegram.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Slf4j
public class MyTelegramBot extends TelegramLongPollingBot {
    private final QuestionStore questionStore;

    public MyTelegramBot(final QuestionStore questionStores) {
        super("6703773191:AAFokiduN_H8R7EHr--sNmr1IzJai2ygOjs");
        this.questionStore = questionStores;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Пришло сообщение");
        log.info(update.getMessage().getFrom().getFirstName());
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        if (text.contains("/start")) {
            sendMessage.setText("Привет! " +
                    "\nЯ бот который помогает готовиться к экзамену ЕГЭ." +
                    "\nНапиши номер вопроса в формате: 'Вопрос: 123' " +
                    "\nчтобы начать.  ");
        } else if (text.toUpperCase().contains("ВОПРОС")) {
            Long questionNumber = Long.parseLong(text.substring(7).trim());
            sendMessage.setText("Вы выбрали вопрос: '" + questionNumber + "' \n\n" +
                    "Вот его текст: " + questionStore.getQuestionById(questionNumber).getQuestion() + "\n\n" +
                    "Решение: " + questionStore.getQuestionById(questionNumber).getSolution() + "\n\n" +
                    "Ответ: " + questionStore.getQuestionById(questionNumber).getAnsver() + "\n"
            );
        } else {
            sendMessage.setText("Вопрос: '" + text.trim() + "'\n\nв нашей безе ответов не найден.");
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Ошибка при отправке сообщения в Telegram.", e);
            throw new TelegramBotException("Не удалось отправить сообщение в Telegram.", e);

        }
    }

    @Override
    public String getBotUsername() {
        return "@examEgeMybot";
    }
}
