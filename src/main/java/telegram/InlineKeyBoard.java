package telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;

public class InlineKeyBoard {
    PostgreSQLJDBC comSQL = new PostgreSQLJDBC();

    public ArrayList<KeyboardRow> Keyboard1() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(false);
        keyboardMarkup.setResizeKeyboard(false);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("Student");
        keyboardFirstRow.add("Teacher");
        keyboardSecondRow.add("/exit");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        return keyboard;
    }

    public ArrayList<KeyboardRow> Keyboard2() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow_1 = new KeyboardRow();
        KeyboardRow keyboardRow_2 = new KeyboardRow();
        KeyboardRow keyboardRow_3 = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        keyboardRow_1.add("/start");
        keyboardRow_2.add("/help");
        keyboardRow_3.add("/commands");

        keyboard.add(keyboardRow_1);
        keyboard.add(keyboardRow_2);
        keyboard.add(keyboardRow_3);
        return keyboard;
    }

    public ArrayList<KeyboardRow> Keyboard3() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(false);
        keyboardMarkup.setResizeKeyboard(false);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("Да");
        keyboardFirstRow.add("Нет");

        keyboard.add(keyboardFirstRow);
        return keyboard;
    }

    public ArrayList<KeyboardRow> Keyboard4() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow_1 = new KeyboardRow();
        KeyboardRow keyboardRow_2 = new KeyboardRow();
        KeyboardRow keyboardRow_3 = new KeyboardRow();
        KeyboardRow keyboardRow_4 = new KeyboardRow();
        KeyboardRow keyboardRow_5 = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        keyboardRow_1.add("/start");
        keyboardRow_2.add("/help");
        keyboardRow_3.add("/commands");
        keyboardRow_4.add("/translate");
        keyboardRow_5.add("/history");

        keyboard.add(keyboardRow_1);
        keyboard.add(keyboardRow_2);
        keyboard.add(keyboardRow_3);
        keyboard.add(keyboardRow_4);
        keyboard.add(keyboardRow_5);
        return keyboard;
    }

    public ArrayList<KeyboardRow> Keyboard5() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow_1 = new KeyboardRow();
        KeyboardRow keyboardRow_2 = new KeyboardRow();
        KeyboardRow keyboardRow_3 = new KeyboardRow();
        KeyboardRow keyboardRow_4 = new KeyboardRow();
        KeyboardRow keyboardRow_5 = new KeyboardRow();
        KeyboardRow keyboardRow_6 = new KeyboardRow();
        KeyboardRow keyboardRow_7 = new KeyboardRow();
        KeyboardRow keyboardRow_8 = new KeyboardRow();
        KeyboardRow keyboardRow_9 = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        keyboardRow_1.add("/start");
        keyboardRow_2.add("/help");
        keyboardRow_3.add("/commands");
        keyboardRow_4.add("/translate");
        keyboardRow_5.add("/history");
        keyboardRow_6.add("/clear");
        keyboardRow_7.add("/show");
        keyboardRow_8.add("/delete");
        keyboardRow_9.add("/update");

        keyboard.add(keyboardRow_1);
        keyboard.add(keyboardRow_2);
        keyboard.add(keyboardRow_3);
        keyboard.add(keyboardRow_4);
        keyboard.add(keyboardRow_5);
        keyboard.add(keyboardRow_6);
        keyboard.add(keyboardRow_7);
        keyboard.add(keyboardRow_8);
        keyboard.add(keyboardRow_9);
        return keyboard;
    }

    public ArrayList<KeyboardRow> Keyboard6() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        for (int i = 0; i < comSQL.getListOfAll().size(); i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(comSQL.getListOfAll().get(i));
            keyboard.add(keyboardRow);
        }
        return keyboard;
    }

    public SendMessage setKeyBoard(int number_of_keyboard, String text, long id) {
        SendMessage messages = new SendMessage()
                .setChatId(id)
                .setText(text);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> newKeyboard = new ArrayList<>();
        switch (number_of_keyboard) {
            case 1:
                newKeyboard = Keyboard1();
                break;
            case 2:
                newKeyboard = Keyboard2();
                break;
            case 3:
                newKeyboard = Keyboard3();
                break;
            case 4:
                newKeyboard = Keyboard4();
                break;
            case 5:
                newKeyboard = Keyboard5();
                break;
            case 6:
                newKeyboard = Keyboard6();
                break;
        }

        keyboardMarkup.setKeyboard(newKeyboard);
        keyboardMarkup.setResizeKeyboard(true);
        messages.setReplyMarkup(keyboardMarkup);
        return messages;
    }
}
