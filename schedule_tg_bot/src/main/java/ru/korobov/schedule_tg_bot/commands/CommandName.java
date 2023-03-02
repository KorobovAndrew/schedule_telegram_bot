package ru.korobov.schedule_tg_bot.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandName {

    START("/start"),
    HELP("/help"),
    STOP("/stop"),
    NOCOMMAND("nocommand");

    private final String commandName;

}