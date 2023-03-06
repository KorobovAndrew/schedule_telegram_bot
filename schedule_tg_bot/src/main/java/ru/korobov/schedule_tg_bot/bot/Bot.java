package ru.korobov.schedule_tg_bot.bot;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.korobov.schedule_tg_bot.buttons.ChoosingStudioButtons;
import ru.korobov.schedule_tg_bot.commands.CommandContainer;
import ru.korobov.schedule_tg_bot.services.SendBotMessageServiceImpl;
import ru.korobov.schedule_tg_bot.services.TelegramUserService;

import static ru.korobov.schedule_tg_bot.commands.CommandName.NOCOMMAND;
import static ru.korobov.schedule_tg_bot.commands.CommandName.RESERVE;

@Component
public class Bot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    private final ChoosingStudioButtons choosingStudioButtons;


    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;


    public Bot(TelegramUserService telegramUserService) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this),
                telegramUserService);
        this.choosingStudioButtons = new ChoosingStudioButtons(new SendBotMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();

            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NOCOMMAND.getCommandName()).execute(update);
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            if (callbackData.equals(ChoosingStudioButtons.BUTTON_FIRST_STUDIO_NAME)) {
                String text = "Вы выбрали первую студию";
                choosingStudioButtons.execute(update, text);
            } else if (callbackData.equals(ChoosingStudioButtons.BUTTON_NEW_HALLS_NAME)) {
                String text = "Вы выбрали новые залы";
                choosingStudioButtons.execute(update, text);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
