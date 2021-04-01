package telegram.keyboards;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import telegram.sql.PostgreSQLJDBC;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyBoard {
    PostgreSQLJDBC comSQL = new PostgreSQLJDBC();

    public InlineKeyboardMarkup getStart() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonSignUp = new InlineKeyboardButton().setText("Начать");
        buttonSignUp.setCallbackData("/start");
        List<InlineKeyboardButton> firstKeyboardButtonRow = new ArrayList<>();
        firstKeyboardButtonRow.add(buttonSignUp);
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        keyboardRows.add(firstKeyboardButtonRow);
        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    }
}
