package ru.korobov.schedule_tg_bot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.korobov.schedule_tg_bot.services.SendBotMessageService;
import ru.korobov.schedule_tg_bot.services.TelegramUserService;

public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Деактивировал все ваши подписки \uD83D\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        telegramUserService.findUserByChatId(update.getMessage().getChatId().toString())
                .ifPresent(user -> {
                  user.setActive(false);
                  telegramUserService.save(user);
                });

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }

    @Override
    public void executeEditMessage(Update update, String text) {

    }
}
