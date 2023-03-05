package ru.korobov.schedule_tg_bot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.korobov.schedule_tg_bot.bot.Bot;
import ru.korobov.schedule_tg_bot.repositories.entity.TelegramUser;
import ru.korobov.schedule_tg_bot.services.SendBotMessageService;
import ru.korobov.schedule_tg_bot.services.TelegramUserService;

public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;


    private static final String START_MESSAGE =
            "Привет. Я бот, который пока еще ничего не умеет, но обязательно чему-то научится.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        Long userId = update.getMessage().getFrom().getId();

        telegramUserService.findUserByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    telegramUserService.save(TelegramUser.builder()
                            .chatId(chatId)
                            .userId(userId)
                            .active(true)
                            .build());
                }
        );

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);



//        код для добавления текстовых ссылок
//        var builder = new StringBuilder();
//        var users = telegramUserService.findAll();
//        int counter = 0;
//        for (TelegramUser telegramUser : users) {
//            builder.append("\n<a href='tg://user?id="
//                    + telegramUser.getUserId()
//                    + "'>Пользователь "
//                    + counter
//                    + "</a>");
//            counter++;
//        }
//
//        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), builder.toString());

    }

}



