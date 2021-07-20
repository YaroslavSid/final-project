package com.alevel.telegram.bot.processors;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public abstract class Processor {
    protected abstract BotApiMethod<?> executeMessage(Message message);

    protected abstract BotApiMethod<?> executeCallBackQuery(CallbackQuery callbackQuery);

    public BotApiMethod<?> process(Update update){
        BotApiMethod<?> separate = new SendMessage();

        if (update.hasMessage()){
            log.debug("Message arrived");
            separate = executeMessage(update.getMessage());
        } else if (update.hasCallbackQuery()){
            log.debug("Callback arrived");
            separate =executeCallBackQuery(update.getCallbackQuery());
        } else {
            log.debug("Unknown update arrived");
        }

        return separate;
    }
}
