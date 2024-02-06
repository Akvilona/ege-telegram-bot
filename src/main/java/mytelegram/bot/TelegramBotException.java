/**
 * Создал Андрей Антонов 2/5/2024 2:49 PM.
 **/

package mytelegram.bot;

public class TelegramBotException extends RuntimeException{
    public TelegramBotException(String message, Throwable cause) {
        super(message, cause);
    }
}
