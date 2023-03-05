package ru.korobov.schedule_tg_bot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
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

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        button1.setText("Студия 1");
        button1.setCallbackData("STUDIO_1_BUTTON");

        var button2 = new InlineKeyboardButton();
        button2.setText("Студия 2");
        button2.setCallbackData("STUDIO_2_BUTTON");

        rowInLine.add(button1);
        rowInLine.add(button2);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markupInLine);

        sendBotMessageService.sendMessage(sendMessage);
    }
}
