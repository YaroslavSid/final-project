package com.alevel.telegram.bot.config;

import com.alevel.telegram.bot.TelegramBotAPI;
import com.alevel.telegram.bot.processor.Processor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class BotConfig {
    @Value("${telegram.bot.webHookPath}")
    private String webHookPath;
    @Value("${telegram.bot.userName}")
    private String botUserName;
    @Value("${telegram.bot.botToken}")
    private String botToken;


    @Bean
    public TelegramBotAPI MySuperTelegramBot(Processor processor) {

        TelegramBotAPI mySuperTelegramBot = new TelegramBotAPI(processor);
        mySuperTelegramBot.setBotUserName(botUserName);
        mySuperTelegramBot.setBotToken(botToken);
        mySuperTelegramBot.setWebHookPath(webHookPath);

        return mySuperTelegramBot;
    }
}
