package ru.korobov.schedule_tg_bot.buttons;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface Button {
    void execute(Update update, String text);
    InlineKeyboardMarkup getButtons();
}
