package ru.korobov.schedule_tg_bot.commands;

import org.springframework.stereotype.Component;
import ru.korobov.schedule_tg_bot.service.SendBotMessageService;

import java.util.HashMap;
import java.util.Map;

import static ru.korobov.schedule_tg_bot.commands.CommandName.*;

@Component
public class CommandContainer {

    private final Map<String, Command> commandMap = new HashMap<>();
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService){
        commandMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandMap.put(NOCOMMAND.getCommandName(), new NoCommand(sendBotMessageService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier){
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
