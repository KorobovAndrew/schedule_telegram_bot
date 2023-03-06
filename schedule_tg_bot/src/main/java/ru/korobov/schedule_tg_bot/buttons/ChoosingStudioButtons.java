package ru.korobov.schedule_tg_bot.buttons;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.korobov.schedule_tg_bot.services.SendBotMessageService;

import java.util.ArrayList;
import java.util.List;

public class ChoosingStudioButtons implements Button {

    public static final String BUTTON_FIRST_STUDIO_NAME = "Первая студия";
    public static final String BUTTON_NEW_HALLS_NAME = "Новые залы";

    private final SendBotMessageService sendBotMessageService;

    public ChoosingStudioButtons(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, String text) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        editMessageText.setText(text);
        editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());

        var choosingFirstStudioHallButtons = new ChoosingFirstStudioHallButtons(sendBotMessageService);
        editMessageText.setReplyMarkup(choosingFirstStudioHallButtons.getButtons());

        sendBotMessageService.editMessage(editMessageText);
    }

    @Override
    public InlineKeyboardMarkup getButtons() {
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var buttonFirstStudio = new InlineKeyboardButton();
        buttonFirstStudio.setText(BUTTON_FIRST_STUDIO_NAME);
        buttonFirstStudio.setCallbackData(BUTTON_FIRST_STUDIO_NAME);

        var buttonNewHalls = new InlineKeyboardButton();
        buttonNewHalls.setText(BUTTON_NEW_HALLS_NAME);
        buttonNewHalls.setCallbackData(BUTTON_NEW_HALLS_NAME);

        rowInLine.add(buttonFirstStudio);
        rowInLine.add(buttonNewHalls);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }
}
