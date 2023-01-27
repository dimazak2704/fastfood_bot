
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;
public class Bot2 extends TelegramLongPollingBot {
    StringBuilder sb = new StringBuilder();
    long chat_id;
    int message_id, num;
    boolean start_message=true, last_message=false;
    //List<String> pizza_list = new ArrayList<String>();
    //List<String> sushi_list = new ArrayList<String>();
    //List<String> burger_list = new ArrayList<String>();
    List<String> final_list;


    @Override
    public String getBotUsername() {
        return "name";
    }
    @Override
    public String getBotToken() {
        return "token";
    }
    @Override
    public void onUpdateReceived(Update update) {
        /*for(int i=1; i<=100; i++){
            pizza_list.add("pizza_" + i);
            sushi_list.add("sushi_" + i);
            burger_list.add("burger_" + i);
        }*/

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            start_message=true;
            last_message=false;
            chat_id = update.getMessage().getChatId();
            message_id = update.getMessage().getMessageId();
            System.out.println(message_id);
            sendMessage("Привіт цей бот показує ціни на різну їжу. Оберіть страву!\uD83C\uDDFA\uD83C\uDDE6", chat_id);
            start_message = false;
        }
        if (update.getCallbackQuery().getData().equals("Cbd_pizza") && last_message==false) {
            SendMessage send_pizza_list = new SendMessage();
            send_pizza_list.setChatId(chat_id);

            num = 10;
            //final_list = pizza_list;
            final_list = Main.pizzaList;
            if(final_list.size()%10!=0){
                for(int i=0; i<final_list.size()%10; i++){
                    final_list.add("-");
                }
            }

            sb = new StringBuilder("");
            for (int i = num - 10; i < num; i++) {
                sb.append(final_list.get(i) + "\n");
            }
            send_pizza_list.setText(String.valueOf(sb));

            try {execute(send_pizza_list);last_message=true;}
            catch (TelegramApiException e) {System.out.println("error in send_pizza_list");}
            sendMessage("Змінити сторінку: ", chat_id);
        }

        if (update.getCallbackQuery().getData().equals("Cbd_sushi") && last_message==false) {
            SendMessage send_sushi_list = new SendMessage();
            send_sushi_list.setChatId(chat_id);

            num = 10;
            //final_list = sushi_list;
            final_list = Main.sushiList;
            if(final_list.size()%10!=0){
                for(int i=0; i<final_list.size()%10; i++){
                    final_list.add("-");
                }
            }

            sb = new StringBuilder("");
            for (int i = num - 10; i < num; i++) {
                sb.append(final_list.get(i) + "\n");
            }
            send_sushi_list.setText(String.valueOf(sb));

            try {execute(send_sushi_list);last_message=true;}
            catch (TelegramApiException e) {System.out.println("error in send_sushi_list");}
            sendMessage("Змінити сторінку: ", chat_id);
        }

        if (update.getCallbackQuery().getData().equals("Cbd_burger") && last_message==false) {
            SendMessage send_burger_list = new SendMessage();
            send_burger_list.setChatId(chat_id);

            num = 10;
            //final_list = burger_list;
            final_list = Main.burgerList;
            if(final_list.size()%10!=0){
                for(int i=0; i<final_list.size()%10; i++){
                    final_list.add("-");
                }
            }

            sb = new StringBuilder("");
            for (int i = num - 10; i < num; i++) {
                sb.append(final_list.get(i) + "\n");
            }
            send_burger_list.setText(String.valueOf(sb));

            try {execute(send_burger_list);last_message=true;}
            catch (TelegramApiException e) {System.out.println("error in send_burger_list");}
            sendMessage("Змінити сторінку: ", chat_id);
        }


        if (update.getCallbackQuery().getData().equals("Cbd_forward") && num<final_list.size() - final_list.size()%10) {
            EditMessageText new_message = new EditMessageText();
            new_message.setChatId(chat_id);
            new_message.setMessageId(message_id+2);

            num+=10;
            sb = new StringBuilder("");
            for(int i=num-10; i<num; i++){
                sb.append(final_list.get(i) + "\n");
            }
            new_message.setText(String.valueOf(sb));

            try {execute(new_message);}
            catch (TelegramApiException e) {System.out.println("forward is");}
        }

        if (update.getCallbackQuery().getData().equals("Cbd_back") && num>10) {
            EditMessageText new_message = new EditMessageText();
            new_message.setChatId(chat_id);
            new_message.setMessageId(message_id+2);

            num-=10;
            sb = new StringBuilder("");
            for(int i=num-10; i<num; i++){
                sb.append(final_list.get(i) + "\n");
            }
            new_message.setText(String.valueOf(sb));
            //System.out.println(sb);

            try {execute(new_message);}
            catch (TelegramApiException e) {System.out.println("back is");}
        }
    }

    public void sendMessage(String text,long chatId/*, int messageId*/){
        if(start_message==true) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(text);

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
            List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();

            InlineKeyboardButton pizza_button = new InlineKeyboardButton();
            pizza_button.setText("Піца \uD83C\uDF55");
            pizza_button.setCallbackData("Cbd_pizza");
            rowInline.add(pizza_button);

            InlineKeyboardButton sushi_button = new InlineKeyboardButton();
            sushi_button.setText("Суші \uD83C\uDF63");
            sushi_button.setCallbackData("Cbd_sushi");
            rowInline.add(sushi_button);

            InlineKeyboardButton burger_button = new InlineKeyboardButton();
            burger_button.setText("Бургери \uD83C\uDF54");
            burger_button.setCallbackData("Cbd_burger");
            rowInline.add(burger_button);

            rowsInline.add(rowInline);
            inlineKeyboardMarkup.setKeyboard(rowsInline);
            message.setReplyMarkup(inlineKeyboardMarkup);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                System.out.println("void SendMessage поломався");
            }
        }
        else{
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(text);

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
            List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();

            InlineKeyboardButton back_button = new InlineKeyboardButton();
            back_button.setText("<-");
            back_button.setCallbackData("Cbd_back");
            rowInline.add(back_button);

            InlineKeyboardButton forward_button = new InlineKeyboardButton();
            forward_button.setText("->");
            forward_button.setCallbackData("Cbd_forward");
            rowInline.add(forward_button);

            rowsInline.add(rowInline);
            inlineKeyboardMarkup.setKeyboard(rowsInline);
            message.setReplyMarkup(inlineKeyboardMarkup);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                System.out.println("void SendMessage(else) поломався");
            }
        }
    }
}
