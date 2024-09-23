package com.Delivion.telegram;


import com.Delivion.matchInfo.Client;
import com.Delivion.matchInfo.FlagsUnicodes;
import com.Delivion.matchInfo.Match;
import com.Delivion.matchInfo.MatchInfo;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.*;


public class Bot extends TelegramLongPollingBot {

    private final Map<String, String> flagsUnicodes  = FlagsUnicodes.flagsUnicodes;

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi tbapi = new TelegramBotsApi(DefaultBotSession.class);
        tbapi.registerBot(new Bot());
    }

    @Override
    public String getBotUsername() {
        return "FootballClubsScoreBot";
    }

    @Override
    public String getBotToken() {
        return "7959452639:AAFaJJf6rdkCl7o1P4n4Pdhfe29wFKZAyBQ";
    }

    @Override
    public void onUpdateReceived(Update update) {

        Long chatId = getChatId(update);
        System.out.println(update.getMessage().getChat().getUserName());

        if(update.hasMessage()&&update.getMessage().getText().equals("/start")){
            SendMessage message = createMessage("Наразі відбуваються матчі в таких країнах :");
            Map<String, String> countries;
            try {
                countries = getMapOfCountries();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            attachButtons(message,countries);

            message.setChatId(chatId);
            sendApiMethodAsync(message);
        }
    }

    private SendMessage createMessage(String text){
        SendMessage message = new SendMessage();
        message.setText(text);
        return message;
    }

    private Map<String, String> getMapOfCountries() throws IOException {

        Map<String,String> mapOfCountries = new HashMap<>();
        List<Match> matches = getMatchInfo();

        for (Match match : matches) {
            mapOfCountries.put(match.getCountry().getName().toUpperCase(),match.getCountry().getName()+"_btn");

//            System.out.println(match.getHome().getName()+" - "+match.getAway().getName()+"| Country - "
//            +match.getCountry().getName()
//            +" "+match.getScores().getScore());

        }


        return mapOfCountries;
    }

    public Long getChatId(Update update){
        if(update.hasMessage()){
            return update.getMessage().getChatId();
        }
        if(update.hasCallbackQuery()){
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }

    private List<Match> getMatchInfo () throws IOException {
        MatchInfo matchInfo = Client.sendGet();
//        StringBuilder sb = new StringBuilder();
//        for (Match match : matches) {
////            sb.append(match.getHome().getName()).append(" - ").append(match.getAway().getName()).append(" | ")
////                    .append(match.getScores().getScore()).append(" | ").append(match.getCountry().getName()).append("\n");
//
//        }
        return matchInfo.getData().getMatch();
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {

            String buttonValue = buttons.get(buttonName);
            String buttonText;
            if(flagsUnicodes.containsKey(buttonName)){
                buttonText = buttonName+flagsUnicodes.get(buttonName);
            }
            else{
                buttonText=buttonName;
            }

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonText);
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }
}
