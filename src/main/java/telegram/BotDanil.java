package telegram;

import lombok.SneakyThrows;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import telegram.keyboards.InlineKeyBoard;
import telegram.keyboards.ReplyKeyboard;
import telegram.sql.PostgreSQLJDBC;
import telegram.translate.TranslateParsing;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class BotDanil extends TelegramLongPollingBot {

    TranslateParsing Parsing = new TranslateParsing();
    PostgreSQLJDBC comSQL = new PostgreSQLJDBC();
    ReplyKeyboard Reply = new ReplyKeyboard();
    InlineKeyBoard Inline = new InlineKeyBoard();
    //static Logger log = Logger.getLogger(BotDanil.class);
    String language_from = "";
    String language_to = "";

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String month = Integer.toString(date.getMonthValue());
        String day = Integer.toString(date.getDayOfMonth());
        String hour = Integer.toString(time.getHour());
        String minute = Integer.toString(time.getMinute());
        String second = Integer.toString(time.getSecond());
        int id = update.getMessage().getChatId().intValue();

        //sendMsgWithButton("Hello", update.getMessage().getChatId());
        //sendMsgWithInlineButton("Hello", update.getMessage().getChatId());

        if (!(comSQL.getListOfId().contains(id))) {
            //log.info(comSQL.getListOfId());
            comSQL.insertData(id, update.getMessage().getFrom().getFirstName(), update.getMessage().getFrom().getLastName(), "Student");
            comSQL.createTimeTable(id);
        }

        if (comSQL.getTypeOfMsg(id).equals("delete_1")) {
            if (message.equals("/exit")) {
                sendMsg("Вы решили никого не удалять☺", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            comSQL.setLastid(id, "");
            for (int i = 1; i < update.getMessage().getText().length(); i++) {
                comSQL.setLastid(id, comSQL.getLastId(id) + update.getMessage().getText().toCharArray()[i]);
            }

            sendMsg("Вы точно хотите удалить пользователя?\nId: " + comSQL.getLastId(id) + "\nFirstname: " + comSQL.getFirstName(Integer.parseInt(comSQL.getLastId(id))) + "\nLastname: " + comSQL.getLastName(Integer.parseInt(comSQL.getLastId(id))) + "\nRole:" + comSQL.getRole(Integer.parseInt(comSQL.getLastId(id))), update.getMessage().getChatId());
            try {
                execute(Reply.setKeyBoard(3, "Да или Нет?", update.getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            comSQL.setTypeOfMsg(id, "delete_2");
        }
        if (comSQL.getTypeOfMsg(id).equals("delete_2")) {
            if (message.equals("/exit")) {
                sendMsg("Вы решили никого не удалять☺", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            if (message.equals("Да")) {
                //log.info(Integer.parseInt(comSQL.getLastId(id)) + "1");
                try {
                    //log.info(Integer.parseInt(comSQL.getLastId(id)) + "2");
                    comSQL.deleteData(Integer.parseInt(comSQL.getLastId(id)));
                    //log.info(Integer.parseInt(comSQL.getLastId(id)) + "3");
                    sendMsg("Вы успешно удалили пользователя с id: " + comSQL.getLastId(id), update.getMessage().getChatId());
                    comSQL.setTypeOfMsg(id, "normal");
                    comSQL.setLastid(id, "");
                } catch (Exception e) {
                    sendMsg("Попробуйте ещё раз!\n/exit - вернуться к начальным функциям", update.getMessage().getChatId());
                }
            }
            if (message.equals("Нет")) {
                sendMsg("Вы решили никого не удалять☺", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
        }

        if (comSQL.getTypeOfMsg(id).equals("update_1")) {
            if (message.equals("/exit")) {
                sendMsg("Вы вернулись к начальным фунциям", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            comSQL.setLastid(id, "");
            for (int i = 1; i < update.getMessage().getText().length(); i++) {
                comSQL.setLastid(id, comSQL.getLastId(id) + update.getMessage().getText().toCharArray()[i]);
            }
            sendMsg("Вы точно хотите изменить данные этого пользователя?\nId: " + comSQL.getLastId(id) + "\nFirstname: " + comSQL.getFirstName(Integer.parseInt(comSQL.getLastId(id))) + "\nLastname: " + comSQL.getLastName(Integer.parseInt(comSQL.getLastId(id))) + "\nRole:" + comSQL.getRole(Integer.parseInt(comSQL.getLastId(id))), update.getMessage().getChatId());
            try {
                execute(Reply.setKeyBoard(3, "Да или Нет?", update.getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            comSQL.setTypeOfMsg(id, "update_2");
        }
        if (comSQL.getTypeOfMsg(id).equals("update_2")) {
            if (message.equals("/exit")) {
                sendMsg("Вы вернулись к начальным фунциям", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            if (message.equals("Да")) {
                try {
                    execute(Reply.setKeyBoard(1, "Какая будет новая роль этого пользователя?", update.getMessage().getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                comSQL.setTypeOfMsg(id, "update_3");
            }
            if (message.equals("Нет")) {
                sendMsg("Вы вернулись к начальным фунциям", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
        }
        if (comSQL.getTypeOfMsg(id).equals("update_3")) {
            if (message.equals("/exit")) {
                sendMsg("Вы вернулись к начальным фунциям", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            if (message.equals("Student")) {
                comSQL.updateData(Integer.parseInt(comSQL.getLastId(id)), "Student");
                sendMsg("Вы изменили роль пользователя с id: " + comSQL.getLastId(id) + "\nНовая роль: Student", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            if (message.equals("Teacher")) {
                comSQL.updateData(Integer.parseInt(comSQL.getLastId(id)), "Teacher");
                sendMsg("Вы изменили роль пользователя с id: " + comSQL.getLastId(id) + "\nНовая роль: Teacher", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
        }


        if (comSQL.getTypeOfMsg(id).equals("translate_3")) {
            if (message.equals("/exit")) {
                sendMsg("Вы вернулись к начальным функциям", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
            } else if (message.equals("/change")) {
                sendMsg("Введите код языка, с которого хотите начать переводить текст\n/exit - выход.\n/change - сменить язык перевода", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "translate_1");
            } else {
                try {
                    if (Parsing.TranslateText("Hello", language_to, language_from).get("error_code").equals(200)) {
                        sendMsg(Parsing.TranslateText(message, language_to, language_from).get("message_new").toString(), update.getMessage().getChatId());
                        sendMsg("/exit - выход\n/change - сменить язык перевода", update.getMessage().getChatId());
                    } else {
                        sendMsg("Мы обнаружили ошибку, введите текст заново", update.getMessage().getChatId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (comSQL.getTypeOfMsg(id).equals("translate_2")) {
            if (message.equals("/exit")) {
                sendMsg("Вы вернулись к начальным функциям", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
            } else if (message.equals("/change")) {
                sendMsg("Введите код языка, с которого хотите начать переводить текст\n/exit - выход.\n/change - сменить язык перевода", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "translate_1");
            } else {
                language_to = message.toLowerCase(Locale.ROOT);
                try {
                    if (Parsing.TranslateText("Hello", language_to, language_from).get("error_code").equals(200)) {
                        sendMsg("Перевод будет происходить на язык: " + language_to, update.getMessage().getChatId());
                        comSQL.setTypeOfMsg(id, "translate_3");
                    } else {
                        sendMsg("Мы не смогли найти язык " + language_to + ", попробуйте ввести его заново", update.getMessage().getChatId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (comSQL.getTypeOfMsg(id).equals("translate_1")) {
            if (message.equals("/exit")) {
                sendMsg("Вы вернулись к начальным функциям", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
            } else if (message.equals("/change")) {
                sendMsg("У вас ещё не выбран язык", update.getMessage().getChatId());
            } else {
                language_from = message.toLowerCase(Locale.ROOT);
                System.out.println(language_from);
                try {
                    if (Parsing.TranslateText("Hello", "en", language_from).get("error_code").equals(200)) {
                        sendMsg("Перевод будет происходить с языка: " + language_from + "\nВведите код языка, на который будет происходить перевод", update.getMessage().getChatId());
                        comSQL.setTypeOfMsg(id, "translate_2");
                    } else {
                        sendMsg("Мы не смогли найти язык " + language_from + ", попробуйте ввести его заново", update.getMessage().getChatId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (comSQL.getTypeOfMsg(id).equals("normal")) {
            if (message.equals("/start")) {
                if (comSQL.getRole(id).equals("Teacher")) {
                    //sendMsg("Здравствуйте, " + update.getMessage().getFrom().getFirstName() + "\nВот команды, которые я могу для вас выполнить:\n/start - Начать\n/help - Помощь для работы с ботом\n/commands - Показать список всех команд\n/time - Показать текущую дату и время\n/translate - Начать переводить текст\n/history - История запросов на время\n/clear - Очистить историю времени\n\nКоманды для Учителей:\n/show - оказать список пользователей\n/delete - удалить пользователя из БЗ\n/update - обновить роль пользователя", update.getMessage().getChatId());
                    sendMsgWithReplyButton(Reply.Keyboard5(), "Здравствуйте, " + update.getMessage().getFrom().getFirstName() + "\nВот команды, которые я могу для вас выполнить:\n/start - Начать\n/help - Помощь для работы с ботом\n/commands - Показать список всех команд\n/time - Показать текущую дату и время\n/translate - Начать переводить текст\n/history - История запросов на время\n/clear - Очистить историю времени\n\nКоманды для Учителей:\n/show - оказать список пользователей\n/delete - удалить пользователя из БЗ\n/update - обновить роль пользователя", update.getMessage().getChatId());
                } else {
                    //sendMsg("Здравствуйте, " + update.getMessage().getFrom().getFirstName() + "\nВот команды, которые я могу для вас выполнить:\n/start - Начать\n/help - Помощь для работы с ботом\n/commands - Показать список всех команд\n/time - Показать текущую дату и время\n/translate - Начать переводить текст\n/history - История запросов на время\n/clear - Очистить историю времени", update.getMessage().getChatId());
                    sendMsgWithReplyButton(Reply.Keyboard6(), "Здравствуйте, " + update.getMessage().getFrom().getFirstName() + "\nВот команды, которые я могу для вас выполнить:\n/start - Начать\n/help - Помощь для работы с ботом\n/commands - Показать список всех команд\n/time - Показать текущую дату и время\n/translate - Начать переводить текст\n/history - История запросов на время\n/clear - Очистить историю времени", update.getMessage().getChatId());

                }
                //log.info(comSQL.getRole(id));
            }
            if (message.equals("/help")) {
                //sendMsg("Для того чтобы начать переводить текст, напишите /translate, далее напишите текст нужный для перевода. Для завершения работы переводчика, наишите /exit. Приятного пользования TranslatorBot!", update.getMessage().getChatId());
                sendMsgWithReplyButton(Reply.Keyboard7(), "Для того чтобы начать переводить текст, напишите /translate, далее напишите текст нужный для перевода. Для завершения работы переводчика, наишите /exit. Приятного пользования TranslatorBot!", update.getMessage().getChatId());
            }
            if (message.equals("/translate")) {
                //sendMsg("Введите код языка, с которого хотите начать переводить текст\n/exit - выход.\n/change - сменить язык перевода", update.getMessage().getChatId());
                sendMsgWithReplyButton(Reply.Keyboard7(), "Введите код языка, с которого хотите начать переводить текст\n/exit - выход.\n/change - сменить язык перевода", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "translate_1");
            }
            if (message.equals("/commands")) {
                if (comSQL.getRole(id).equals("Teacher")) {
                    sendMsg("/start - Начать\n/help - Помощь\n/commands - Все команды\n/time - Время и дата\n/translate - Начать перевод\n/exit - Остановить перевод\n/history - История просмотра времени\n/clear - Очистить историю времени\n/show - Показать список пользователей\n/delete - Удалить пользователя из БЗ\n/update - Обновить роль пользователя", update.getMessage().getChatId());
                } else {
                    sendMsg("/start - Начать\n/help - Помощь\n/commands - Все команды\n/time - Время и дата\n/translate - Начать перевод\n/exit - Остановить перевод\n/history - История просмотра времени\n/clear - Очистить историю времени", update.getMessage().getChatId());
                }
            }

            if (comSQL.getRole(id).equals("Teacher")) {
                if (message.equals("/update")) {
                    sendMsg("Выберите id пользователя, которого хотите изменить", update.getMessage().getChatId());
                    sendMsg("Вот списк всех пользователей:\n" + getInfo(), update.getMessage().getChatId());
                    sendMsg("В любой момент вы можете вернуться к начальным функциям командой /exit", update.getMessage().getChatId());
                    comSQL.setTypeOfMsg(id, "update_1");
                }
                if (message.equals("/show")) {
                    sendMsg("Вот списк всех пользователей:\n" + getInfo(), update.getMessage().getChatId());
                }
                if (message.equals("/delete")) {
                    sendMsg("Введите id пользователя, которого хотите удалить", update.getMessage().getChatId());
                    sendMsg("Вот списк всех пользователей:\n" + getInfo(), update.getMessage().getChatId());
                    comSQL.setTypeOfMsg(id, "delete_1");
                }
            }
            if (message.equals("/admin")) {
                comSQL.updateData(Integer.parseInt(update.getMessage().getChatId().toString()), "Teacher");
            }

            if (message.equals("/time")) {

                if (date.getMonthValue() < 10) {
                    month = "0" + Integer.toString(date.getMonthValue());
                }
                if (date.getDayOfMonth() < 10) {
                    day = "0" + Integer.toString(date.getDayOfMonth());
                }
                if (time.getHour() < 10) {
                    hour = "0" + Integer.toString(time.getHour());
                }
                if (time.getMinute() < 10) {
                    minute = "0" + Integer.toString(time.getMinute());
                }
                if (time.getSecond() < 10) {
                    second = "0" + Integer.toString(time.getSecond());
                }
                String tume = date.getYear() + "-" + month + "-" + day + " | " + hour + ":" + minute + ":" + second;
                sendMsg(date.getYear() + "-" + month + "-" + day + "\n" + hour + ":" + minute + ":" + second, update.getMessage().getChatId());
                comSQL.setToTimeTable(id, "'" + tume + "'");
            }
            if (message.equals("/history")) {
                String final_history = "";
                if (comSQL.getListOfTimeTable(id).size() != 0) {
                    for (int i = 0; i < comSQL.getListOfTimeTable(id).size(); i++) {
                        final_history = final_history + (i + 1) + ") | " + comSQL.getListOfTimeTable(id).get(i) + "\n";
                    }
                    final_history = final_history + "/clear";
                } else {
                    final_history = "Ваша история времени пуста.";
                }
                sendMsg(final_history + "", update.getMessage().getChatId());
            }
            if (message.equals("/clear")) {
                comSQL.deleteTimeTable(id);
                comSQL.createTimeTable(id);
                sendMsg("История успешно очищена", update.getMessage().getChatId());
            }
        }

    }

    public String getInfo() {
        String newMessage = "";
        for (int i = 0; i < comSQL.getListOfAll().size(); i++) {
            newMessage += comSQL.getListOfAll().get(i) + "\n";
        }
        return newMessage;
    }



    public synchronized void sendMsg(String s, long chat_id) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(s);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //log.info( "Exception: " + e.toString());
        }
    }

    public synchronized void sendMsgWithReplyButton(ReplyKeyboardMarkup keyboardMarkup, String s, long chat_id) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(s);
        sendMessage.setReplyMarkup(keyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //log.info("Exception: " + e.toString());
        }
    }

    public synchronized void sendMsgWithInlineButton(String text, long chat_id) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(Inline.getStart());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //log.info("Exception: " + e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return System.getenv("TELEGRAMBOT_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("TOKEN");
    }
}