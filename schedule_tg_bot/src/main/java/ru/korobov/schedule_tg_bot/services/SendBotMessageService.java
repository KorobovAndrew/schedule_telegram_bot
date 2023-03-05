package ru.korobov.schedule_tg_bot.services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
    void sendMessage(SendMessage sendMessage);

}
