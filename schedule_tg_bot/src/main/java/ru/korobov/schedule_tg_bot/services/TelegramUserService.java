package ru.korobov.schedule_tg_bot.services;

import ru.korobov.schedule_tg_bot.repositories.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {
    void save(TelegramUser telegramUser);

    Optional<TelegramUser> findUserByChatId(String chatId);
}