package ru.korobov.schedule_tg_bot.services;

import org.springframework.stereotype.Service;
import ru.korobov.schedule_tg_bot.repositories.TelegramUserRepository;
import ru.korobov.schedule_tg_bot.repositories.entity.TelegramUser;

import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public Optional<TelegramUser> findUserByChatId(String chatId) {
        return telegramUserRepository.findById(chatId);
    }
}
