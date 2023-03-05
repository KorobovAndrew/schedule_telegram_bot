package ru.korobov.schedule_tg_bot.services;

import org.springframework.transaction.annotation.Transactional;
import ru.korobov.schedule_tg_bot.repositories.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface TelegramUserService {
    @Transactional
    void save(TelegramUser telegramUser);

    Optional<TelegramUser> findUserByChatId(String chatId);

    List<TelegramUser> findAll();
}
