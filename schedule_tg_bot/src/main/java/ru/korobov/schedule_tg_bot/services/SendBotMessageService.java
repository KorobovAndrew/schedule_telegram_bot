package ru.korobov.schedule_tg_bot.services;

public interface SendBotMessageService {

    public void sendMessage(String chatId, String message);

}
