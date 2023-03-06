package ru.korobov.schedule_tg_bot.buttons;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.korobov.schedule_tg_bot.services.SendBotMessageService;

import java.util.ArrayList;
import java.util.List;

public class ChoosingFirstStudioHallButtons implements Button {

    public static final String BUTTON_FIRST_STUDIO_FIRST_HALL_NAME = "Первый зал";
    public static final String BUTTON_FIRST_STUDIO_SECOND_HALL_NAME = "Второй зал";

    private final SendBotMessageService sendBotMessageService;

    public ChoosingFirstStudioHallButtons(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, String text) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        editMessageText.setText(text);
        editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());

        sendBotMessageService.editMessage(editMessageText);
    }

    @Override
    public InlineKeyboardMarkup getButtons() {
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var buttonFirstStudio = new InlineKeyboardButton();
        buttonFirstStudio.setText(BUTTON_FIRST_STUDIO_FIRST_HALL_NAME);
        buttonFirstStudio.setCallbackData(BUTTON_FIRST_STUDIO_FIRST_HALL_NAME);

        var buttonNewHalls = new InlineKeyboardButton();
        buttonNewHalls.setText(BUTTON_FIRST_STUDIO_SECOND_HALL_NAME);
        buttonNewHalls.setCallbackData(BUTTON_FIRST_STUDIO_SECOND_HALL_NAME);

        rowInLine.add(buttonFirstStudio);
        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();
        rowInLine.add(buttonNewHalls);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }
}
