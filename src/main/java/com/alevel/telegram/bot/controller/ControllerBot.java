package com.alevel.telegram.bot.controller;

import com.alevel.telegram.bot.TelegramBotAPI;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class ControllerBot {
    private final TelegramBotAPI telegramBot;

    public ControllerBot(TelegramBotAPI telegramBot) {
        this.telegramBot = telegramBot;
    }

    @RequestMapping(value = "/bot", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
}
