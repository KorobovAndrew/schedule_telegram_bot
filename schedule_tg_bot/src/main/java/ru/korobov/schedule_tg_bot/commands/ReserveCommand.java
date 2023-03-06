package ru.korobov.schedule_tg_bot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.korobov.schedule_tg_bot.buttons.ChoosingStudioButtons;
import ru.korobov.schedule_tg_bot.services.SendBotMessageService;

import java.util.ArrayList;
import java.util.List;

public class ReserveCommand implements Command{

    private final SendBotMessageService sendBotMessageService;

    public ReserveCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Выберите студию");

        var choosingStudioButtons = new ChoosingStudioButtons(sendBotMessageService);
        sendMessage.setReplyMarkup(choosingStudioButtons.getButtons());
        sendBotMessageService.sendMessage(sendMessage);
    }
}
