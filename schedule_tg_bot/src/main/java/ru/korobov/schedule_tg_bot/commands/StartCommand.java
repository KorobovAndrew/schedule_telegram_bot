package ru.korobov.schedule_tg_bot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.korobov.schedule_tg_bot.service.SendBotMessageService;

public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private static final String START_MESSAGE =
            "Привет. Я бот, который пока еще ничего не умеет, но обязательно чему-то научится.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }

}

