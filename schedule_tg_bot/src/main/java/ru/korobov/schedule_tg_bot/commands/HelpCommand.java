package ru.korobov.schedule_tg_bot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.korobov.schedule_tg_bot.services.SendBotMessageService;

import static ru.korobov.schedule_tg_bot.commands.CommandName.*;

public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("<b>Доступные команды</b>\n\n"
                    + "%s - начать работу со мной\n"
                    + "%s - забронировать световое оборудование\n"
                    + "%s - приостановить работу со мной\n"
                    + "%s - получить помощь в работе со мной\n",
            START.getCommandName(), RESERVE.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }

    @Override
    public void executeEditMessage(Update update, String text) {

    }
}
