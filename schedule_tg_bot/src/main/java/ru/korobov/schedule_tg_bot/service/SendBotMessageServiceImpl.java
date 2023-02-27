package ru.korobov.schedule_tg_bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.korobov.schedule_tg_bot.bot.Bot;

//почему без аннотации service все работает???

public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final Bot bot;

    public SendBotMessageServiceImpl(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);// - зачем это???
        sendMessage.setText(message);

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}