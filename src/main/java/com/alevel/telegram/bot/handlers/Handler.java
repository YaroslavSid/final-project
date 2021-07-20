package com.alevel.telegram.bot.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface Handler <T>{
    BotApiMethod<?> handle(T t);
}
