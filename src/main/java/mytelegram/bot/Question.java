/**
 * Создал Андрей Антонов 2/5/2024 8:51 AM.
 **/

package mytelegram.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    private Long id;
    private String question;
    private String solution;
    private String ansver;
    private String result;

}
