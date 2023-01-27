import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    long chat_id;
    @Override
    public String getBotUsername() {return "name";}
    @Override
    public String getBotToken() {return "token";}

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals("/start")) {
                chat_id = update.getMessage().getChatId();
                sendMessage("Привіт цей бот показує ціни на різну їжу. " +
                        "Оберіть страву!\uD83C\uDDFA\uD83C\uDDE6\n", chat_id);
            }
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("1")) {
                SendMessage response = new SendMessage();
                response.setChatId(chat_id);
                response.setText("sushi");
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getCallbackQuery().getData().equals("2")) {
                SendMessage response = new SendMessage();
                response.setChatId(chat_id);
                response.setText("burger");
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (update.getCallbackQuery().getData().equals("3")){
                SendMessage response = new SendMessage();
                response.setChatId(chat_id);
                response.setText("pizza");
                try{
                    execute(response);
                } catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessage(String text,long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Суші");
        inlineKeyboardButton1.setCallbackData("1");
        inlineKeyboardButton2.setText("Бургер");
        inlineKeyboardButton2.setCallbackData("2");
        inlineKeyboardButton3.setText("Піца");
        inlineKeyboardButton3.setCallbackData("3");
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(inlineKeyboardButton1);
        keyboardButtonsRow.add(inlineKeyboardButton2);
        keyboardButtonsRow.add(inlineKeyboardButton3);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
