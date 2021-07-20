package com.alevel.telegram.bot.processors.impl;

import com.alevel.telegram.bot.handlers.separate.CallBackQueryHandler;
import com.alevel.telegram.bot.handlers.separate.MessageHandler;
import com.alevel.telegram.bot.processors.Processor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ProcessorImpl extends Processor {

    private final CallBackQueryHandler callBackQueryHandler;
    private final MessageHandler messageHandler;

    public ProcessorImpl(CallBackQueryHandler callBackQueryHandler, MessageHandler messageHandler) {
        this.callBackQueryHandler = callBackQueryHandler;
        this.messageHandler = messageHandler;
    }

    @Override
    protected BotApiMethod<?> executeMessage(Message message) {
        return messageHandler.handle(message);
    }

    @Override
    protected BotApiMethod<?> executeCallBackQuery(CallbackQuery callbackQuery) {
        return callBackQueryHandler.handle(callbackQuery);
    }
}
