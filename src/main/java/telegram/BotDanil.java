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
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ???????????? ???????????? ???? ?????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            } else {
                comSQL.setLastid(id, "");
                for (int i = 1; i < update.getMessage().getText().length(); i++) {
                    comSQL.setLastid(id, comSQL.getLastId(id) + update.getMessage().getText().toCharArray()[i]);
                }

                sendMsg("???? ?????????? ???????????? ?????????????? ?????????????????????????\nId: " + comSQL.getLastId(id) + "\nFirstname: " + comSQL.getFirstName(Integer.parseInt(comSQL.getLastId(id))) + "\nLastname: " + comSQL.getLastName(Integer.parseInt(comSQL.getLastId(id))) + "\nRole:" + comSQL.getRole(Integer.parseInt(comSQL.getLastId(id))), update.getMessage().getChatId());
                sendMsgWithReplyButton(Reply.Keyboard3(), "???? ?????? ???????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "delete_2");
            }
        }
        if (comSQL.getTypeOfMsg(id).equals("delete_2")) {
            if (message.equals("/exit")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ???????????? ???????????? ???? ?????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            if (message.equals("????")) {
                //log.info(Integer.parseInt(comSQL.getLastId(id)) + "1");

                    //log.info(Integer.parseInt(comSQL.getLastId(id)) + "2");

                    //log.info(Integer.parseInt(comSQL.getLastId(id)) + "3");
                    sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ?????????????? ?????????????? ???????????????????????? ?? id: " + comSQL.getLastId(id), update.getMessage().getChatId());
                    comSQL.deleteData(Integer.parseInt(comSQL.getLastId(id)));
                    comSQL.setLastid(id, "");
                    comSQL.setTypeOfMsg(id, "normal");

            }
            if (message.equals("??????")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ???????????? ???????????? ???? ?????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
        }

        if (comSQL.getTypeOfMsg(id).equals("update_1")) {
            if (message.equals("/exit")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ?????????????????? ?? ?????????????????? ??????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            } else {
                comSQL.setLastid(id, "");
                for (int i = 1; i < update.getMessage().getText().length(); i++) {
                    comSQL.setLastid(id, comSQL.getLastId(id) + update.getMessage().getText().toCharArray()[i]);
                }
                sendMsg("???? ?????????? ???????????? ???????????????? ???????????? ?????????? ?????????????????????????\nId: " + comSQL.getLastId(id) + "\nFirstname: " + comSQL.getFirstName(Integer.parseInt(comSQL.getLastId(id))) + "\nLastname: " + comSQL.getLastName(Integer.parseInt(comSQL.getLastId(id))) + "\nRole:" + comSQL.getRole(Integer.parseInt(comSQL.getLastId(id))), update.getMessage().getChatId());
                sendMsgWithReplyButton(Reply.Keyboard3(), "???? ?????? ???????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "update_2");
            }
        }
        if (comSQL.getTypeOfMsg(id).equals("update_2")) {
            if (message.equals("/exit")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ?????????????????? ?? ?????????????????? ??????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            if (message.equals("????")) {
                sendMsgWithReplyButton(Reply.Keyboard1(),"?????????? ?????????? ?????????? ???????? ?????????? ?????????????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "update_3");
            }
            if (message.equals("??????")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ?????????????????? ?? ?????????????????? ??????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
        }
        if (comSQL.getTypeOfMsg(id).equals("update_3")) {
            if (message.equals("/exit")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ?????????????????? ?? ?????????????????? ??????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            if (message.equals("Student")) {
                comSQL.updateData(Integer.parseInt(comSQL.getLastId(id)), "Student");
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ???????????????? ???????? ???????????????????????? ?? id: " + comSQL.getLastId(id) + "\n?????????? ????????: Student", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
            if (message.equals("Teacher")) {
                comSQL.updateData(Integer.parseInt(comSQL.getLastId(id)), "Teacher");
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ???????????????? ???????? ???????????????????????? ?? id: " + comSQL.getLastId(id) + "\n?????????? ????????: Teacher", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
                comSQL.setLastid(id, "");
            }
        }


        if (comSQL.getTypeOfMsg(id).equals("translate_3")) {
            if (message.equals("/exit")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ?????????????????? ?? ?????????????????? ????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
            } else if (message.equals("/change")) {
                sendMsgWithReplyButton(Reply.Keyboard7(),"?????????????? ?????? ??????????, ?? ???????????????? ???????????? ???????????? ???????????????????? ??????????\n/exit - ??????????.\n/change - ?????????????? ???????? ????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "translate_1");
            } else {
                try {
                    if (Parsing.TranslateText("Hello", language_to, language_from).get("error_code").equals(200)) {
                        sendMsg(Parsing.TranslateText(message, language_to, language_from).get("message_new").toString(), update.getMessage().getChatId());
                        sendMsgWithReplyButton(Reply.Keyboard7(),"/exit - ??????????\n/change - ?????????????? ???????? ????????????????", update.getMessage().getChatId());
                    } else {
                        sendMsg("???? ???????????????????? ????????????, ?????????????? ?????????? ????????????", update.getMessage().getChatId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (comSQL.getTypeOfMsg(id).equals("translate_2")) {
            if (message.equals("/exit")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ?????????????????? ?? ?????????????????? ????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
            } else if (message.equals("/change")) {
                sendMsgWithReplyButton(Reply.Keyboard7(),"?????????????? ?????? ??????????, ?? ???????????????? ???????????? ???????????? ???????????????????? ??????????\n/exit - ??????????.\n/change - ?????????????? ???????? ????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "translate_1");
            } else {
                language_to = message.toLowerCase(Locale.ROOT);
                try {
                    if (Parsing.TranslateText("Hello", language_to, language_from).get("error_code").equals(200)) {
                        sendMsgWithReplyButton(Reply.Keyboard7(),"?????????????? ?????????? ?????????????????????? ???? ????????: " + language_to, update.getMessage().getChatId());
                        comSQL.setTypeOfMsg(id, "translate_3");
                    } else {
                        sendMsgWithReplyButton(Reply.Keyboard7(),"???? ???? ???????????? ?????????? ???????? " + language_to + ", ???????????????????? ???????????? ?????? ????????????", update.getMessage().getChatId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (comSQL.getTypeOfMsg(id).equals("translate_1")) {
            if (message.equals("/exit")) {
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)),"???? ?????????????????? ?? ?????????????????? ????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "normal");
            } else if (message.equals("/change")) {
                sendMsg("?? ?????? ?????? ???? ???????????? ????????", update.getMessage().getChatId());
            } else {
                language_from = message.toLowerCase(Locale.ROOT);
                System.out.println(language_from);
                try {
                    if (Parsing.TranslateText("Hello", "en", language_from).get("error_code").equals(200)) {
                        sendMsg("?????????????? ?????????? ?????????????????????? ?? ??????????: " + language_from + "\n?????????????? ?????? ??????????, ???? ?????????????? ?????????? ?????????????????????? ??????????????", update.getMessage().getChatId());
                        comSQL.setTypeOfMsg(id, "translate_2");
                    } else {
                        sendMsg("???? ???? ???????????? ?????????? ???????? " + language_from + ", ???????????????????? ???????????? ?????? ????????????", update.getMessage().getChatId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (comSQL.getTypeOfMsg(id).equals("normal")) {
            if (message.equals("/start")) {
                if (comSQL.getRole(id).equals("Teacher")) {
                    //sendMsg("????????????????????????, " + update.getMessage().getFrom().getFirstName() + "\n?????? ??????????????, ?????????????? ?? ???????? ?????? ?????? ??????????????????:\n/start - ????????????\n/help - ???????????? ?????? ???????????? ?? ??????????\n/commands - ???????????????? ???????????? ???????? ????????????\n/time - ???????????????? ?????????????? ???????? ?? ??????????\n/translate - ???????????? ???????????????????? ??????????\n/history - ?????????????? ???????????????? ???? ??????????\n/clear - ???????????????? ?????????????? ??????????????\n\n?????????????? ?????? ????????????????:\n/show - ?????????????? ???????????? ??????????????????????????\n/delete - ?????????????? ???????????????????????? ???? ????\n/update - ???????????????? ???????? ????????????????????????", update.getMessage().getChatId());
                    sendMsgWithReplyButton(Reply.Keyboard5(), "????????????????????????, " + update.getMessage().getFrom().getFirstName() + "\n?????? ??????????????, ?????????????? ?? ???????? ?????? ?????? ??????????????????:\n/start - ????????????\n/help - ???????????? ?????? ???????????? ?? ??????????\n/commands - ???????????????? ???????????? ???????? ????????????\n/time - ???????????????? ?????????????? ???????? ?? ??????????\n/translate - ???????????? ???????????????????? ??????????\n/history - ?????????????? ???????????????? ???? ??????????\n/clear - ???????????????? ?????????????? ??????????????\n\n?????????????? ?????? ????????????????:\n/show - ???????????????? ???????????? ??????????????????????????\n/delete - ?????????????? ???????????????????????? ???? ????\n/update - ???????????????? ???????? ????????????????????????", update.getMessage().getChatId());
                } else {
                    //sendMsg("????????????????????????, " + update.getMessage().getFrom().getFirstName() + "\n?????? ??????????????, ?????????????? ?? ???????? ?????? ?????? ??????????????????:\n/start - ????????????\n/help - ???????????? ?????? ???????????? ?? ??????????\n/commands - ???????????????? ???????????? ???????? ????????????\n/time - ???????????????? ?????????????? ???????? ?? ??????????\n/translate - ???????????? ???????????????????? ??????????\n/history - ?????????????? ???????????????? ???? ??????????\n/clear - ???????????????? ?????????????? ??????????????", update.getMessage().getChatId());
                    sendMsgWithReplyButton(Reply.Keyboard6(), "????????????????????????, " + update.getMessage().getFrom().getFirstName() + "\n?????? ??????????????, ?????????????? ?? ???????? ?????? ?????? ??????????????????:\n/start - ????????????\n/help - ???????????? ?????? ???????????? ?? ??????????\n/commands - ???????????????? ???????????? ???????? ????????????\n/time - ???????????????? ?????????????? ???????? ?? ??????????\n/translate - ???????????? ???????????????????? ??????????\n/history - ?????????????? ???????????????? ???? ??????????\n/clear - ???????????????? ?????????????? ??????????????", update.getMessage().getChatId());

                }
                //log.info(comSQL.getRole(id));
            }
            if (message.equals("/help")) {
                //sendMsg("?????? ???????? ?????????? ???????????? ???????????????????? ??????????, ???????????????? /translate, ?????????? ???????????????? ?????????? ???????????? ?????? ????????????????. ?????? ???????????????????? ???????????? ??????????????????????, ?????????????? /exit. ?????????????????? ?????????????????????? TranslatorBot!", update.getMessage().getChatId());
                sendMsgWithReplyButton(Reply.Keyboard4(comSQL.getRole(id)), "?????? ???????? ?????????? ???????????? ???????????????????? ??????????, ???????????????? /translate, ?????????? ???????????????? ?????????? ???????????? ?????? ????????????????. ?????? ???????????????????? ???????????? ??????????????????????, ?????????????? /exit. ?????????????????? ?????????????????????? TranslatorBot!", update.getMessage().getChatId());
            }
            if (message.equals("/translate")) {
                //sendMsg("?????????????? ?????? ??????????, ?? ???????????????? ???????????? ???????????? ???????????????????? ??????????\n/exit - ??????????.\n/change - ?????????????? ???????? ????????????????", update.getMessage().getChatId());
                sendMsgWithReplyButton(Reply.Keyboard7(), "?????????????? ?????? ??????????, ?? ???????????????? ???????????? ???????????? ???????????????????? ??????????\n/exit - ??????????.\n/change - ?????????????? ???????? ????????????????", update.getMessage().getChatId());
                comSQL.setTypeOfMsg(id, "translate_1");
            }
            if (message.equals("/commands")) {
                if (comSQL.getRole(id).equals("Teacher")) {
                    sendMsgWithReplyButton(Reply.Keyboard5(), "/start - ????????????\n/help - ????????????\n/commands - ?????? ??????????????\n/time - ?????????? ?? ????????\n/translate - ???????????? ??????????????\n/exit - ???????????????????? ??????????????\n/history - ?????????????? ?????????????????? ??????????????\n/clear - ???????????????? ?????????????? ??????????????\n/show - ???????????????? ???????????? ??????????????????????????\n/delete - ?????????????? ???????????????????????? ???? ????\n/update - ???????????????? ???????? ????????????????????????", update.getMessage().getChatId());
                } else {
                    sendMsgWithReplyButton(Reply.Keyboard6(),"/start - ????????????\n/help - ????????????\n/commands - ?????? ??????????????\n/time - ?????????? ?? ????????\n/translate - ???????????? ??????????????\n/exit - ???????????????????? ??????????????\n/history - ?????????????? ?????????????????? ??????????????\n/clear - ???????????????? ?????????????? ??????????????", update.getMessage().getChatId());
                }
            }

            if (comSQL.getRole(id).equals("Teacher")) {
                if (message.equals("/update")) {
                    sendMsg("???????????????? id ????????????????????????, ???????????????? ???????????? ????????????????", update.getMessage().getChatId());
                    sendMsgWithReplyButton(Reply.Keyboard2(),"?????? ?????????? ???????? ??????????????????????????:\n" + getInfo(), update.getMessage().getChatId());
                    sendMsg("?? ?????????? ???????????? ???? ???????????? ?????????????????? ?? ?????????????????? ???????????????? ???????????????? /exit", update.getMessage().getChatId());
                    comSQL.setTypeOfMsg(id, "update_1");
                }
                if (message.equals("/show")) {
                    sendMsg("?????? ???????????? ???????? ??????????????????????????:\n" + getInfo(), update.getMessage().getChatId());
                }
                if (message.equals("/delete")) {
                    sendMsg("?????????????? id ????????????????????????, ???????????????? ???????????? ??????????????", update.getMessage().getChatId());
                    sendMsgWithReplyButton(Reply.Keyboard2(),"?????? ?????????? ???????? ??????????????????????????:\n" + getInfo(), update.getMessage().getChatId());
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
                    final_history = "???????? ?????????????? ?????????????? ??????????.";
                }
                sendMsg(final_history + "", update.getMessage().getChatId());
            }
            if (message.equals("/clear")) {
                comSQL.deleteTimeTable(id);
                comSQL.createTimeTable(id);
                sendMsg("?????????????? ?????????????? ??????????????", update.getMessage().getChatId());
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