package telegram.keyboards;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import telegram.sql.PostgreSQLJDBC;
import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboard {
    PostgreSQLJDBC comSQL = new PostgreSQLJDBC();

    public ReplyKeyboardMarkup Keyboard1() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("Student");
        keyboardFirstRow.add("Teacher");
        keyboardSecondRow.add("/exit");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup Keyboard2() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardRow.add("/exit");
        keyboard.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        for (int i = 0; i < comSQL.getListOfId().size(); i++) {
            keyboardRow.add("/" + comSQL.getListOfId().get(i));
            keyboard.add(keyboardRow);
            keyboardRow = new KeyboardRow();
        }

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup Keyboard3() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("Да");
        keyboardFirstRow.add("Нет");
        keyboardSecondRow.add("/exit");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup Keyboard4(String type) {
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
        KeyboardRow keyboardRow_10 = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        if (type.equals("Teacher")) {
            keyboardRow_1.add("/start");
            keyboardRow_2.add("/help");
            keyboardRow_3.add("/commands");
            keyboardRow_4.add("/time");
            keyboardRow_5.add("/translate");
            keyboardRow_6.add("/history");
            keyboardRow_7.add("/clear");
            keyboardRow_8.add("/show");
            keyboardRow_9.add("/delete");
            keyboardRow_10.add("/update");

            keyboard.add(keyboardRow_1);
            keyboard.add(keyboardRow_2);
            keyboard.add(keyboardRow_3);
            keyboard.add(keyboardRow_4);
            keyboard.add(keyboardRow_5);
            keyboard.add(keyboardRow_6);
            keyboard.add(keyboardRow_7);
            keyboard.add(keyboardRow_8);
            keyboard.add(keyboardRow_9);
            keyboard.add(keyboardRow_10);
        }
        if (type.equals("Student")) {
            keyboardRow_1.add("/start");
            keyboardRow_2.add("/help");
            keyboardRow_3.add("/commands");
            keyboardRow_4.add("/time");
            keyboardRow_5.add("/translate");
            keyboardRow_6.add("/history");
            keyboardRow_7.add("/clear");


            keyboard.add(keyboardRow_1);
            keyboard.add(keyboardRow_2);
            keyboard.add(keyboardRow_3);
            keyboard.add(keyboardRow_4);
            keyboard.add(keyboardRow_5);
            keyboard.add(keyboardRow_6);
            keyboard.add(keyboardRow_7);
        }


        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup Keyboard5() {
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
        KeyboardRow keyboardRow_10 = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardRow_1.add("/start");
        keyboardRow_2.add("/help");
        keyboardRow_3.add("/commands");
        keyboardRow_4.add("/time");
        keyboardRow_5.add("/translate");
        keyboardRow_6.add("/history");
        keyboardRow_7.add("/clear");
        keyboardRow_8.add("/show");
        keyboardRow_9.add("/delete");
        keyboardRow_10.add("/update");

        keyboard.add(keyboardRow_1);
        keyboard.add(keyboardRow_2);
        keyboard.add(keyboardRow_3);
        keyboard.add(keyboardRow_4);
        keyboard.add(keyboardRow_5);
        keyboard.add(keyboardRow_6);
        keyboard.add(keyboardRow_7);
        keyboard.add(keyboardRow_8);
        keyboard.add(keyboardRow_9);
        keyboard.add(keyboardRow_10);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup Keyboard6() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow_1 = new KeyboardRow();
        KeyboardRow keyboardRow_2 = new KeyboardRow();
        KeyboardRow keyboardRow_3 = new KeyboardRow();
        KeyboardRow keyboardRow_4 = new KeyboardRow();
        KeyboardRow keyboardRow_5 = new KeyboardRow();
        KeyboardRow keyboardRow_6 = new KeyboardRow();
        KeyboardRow keyboardRow_7 = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardRow_1.add("/start");
        keyboardRow_2.add("/help");
        keyboardRow_3.add("/commands");
        keyboardRow_4.add("/time");
        keyboardRow_5.add("/translate");
        keyboardRow_6.add("/history");
        keyboardRow_7.add("/clear");

        keyboard.add(keyboardRow_1);
        keyboard.add(keyboardRow_2);
        keyboard.add(keyboardRow_3);
        keyboard.add(keyboardRow_4);
        keyboard.add(keyboardRow_5);
        keyboard.add(keyboardRow_6);
        keyboard.add(keyboardRow_7);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup Keyboard70() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        for (int i = 0; i < comSQL.getListOfAll().size(); i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(comSQL.getListOfAll().get(i));
            keyboard.add(keyboardRow);
        }
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup Keyboard7() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow_1 = new KeyboardRow();
        KeyboardRow keyboardRow_2 = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardRow_1.add("/change");
        keyboardRow_2.add("/exit");

        keyboard.add(keyboardRow_1);
        keyboard.add(keyboardRow_2);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }



    public ReplyKeyboardMarkup Keyboard8() {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow_1 = new KeyboardRow();
        KeyboardRow keyboardRow_2 = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        keyboardRow_1.add("/exit");
        keyboardRow_2.add("/change");

        keyboard.add(keyboardRow_1);
        keyboard.add(keyboardRow_2);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

}
