package com.alevel.telegram.bot.handlers.separate;

import com.alevel.telegram.bot.cache.model.FindDrugFlowData;
import com.alevel.telegram.bot.cache.model.FindDrugInTimeRangeData;
import com.alevel.telegram.bot.cache.FindDrugInTimeRangeStatus;
import com.alevel.telegram.bot.cache.state.find.impl.FindDrugFlowState;
import com.alevel.telegram.bot.cache.state.find.impl.FindDrugInTimeRangeFlowState;
import com.alevel.telegram.bot.handlers.Handler;
import com.alevel.telegram.bot.services.BotService;
import com.alevel.telegram.bot.services.button.ChoseButton;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;


@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallBackQueryHandler implements Handler<CallbackQuery> {

    FindDrugFlowState findDrugFlowState;
    FindDrugInTimeRangeFlowState findDrugInTimeRangeFlowState;
    BotService botService;

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {

        switch (callbackQuery.getData()) {
            case ChoseButton.BUTTON_ONE:
                return SendMessage.builder()
                        .text(String.valueOf(botService.listDrugs()))
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
            case ChoseButton.BUTTON_TWO:
                return SendMessage.builder()
                        .text(String.valueOf(botService.listManufacturers()))
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
            case ChoseButton.BUTTON_THREE:
                findDrugFlowState.add(FindDrugFlowData.builder()
                        .chatId(callbackQuery.getMessage().getChatId())
                        .build());

                return SendMessage.builder()
                        .text("Input name a drug")
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
            case ChoseButton.BUTTON_FOUR:
                findDrugInTimeRangeFlowState.add(FindDrugInTimeRangeData.builder()
                        .chatId(callbackQuery.getMessage().getChatId())
                        .status(FindDrugInTimeRangeStatus.INPUT_FIRST_DATE)
                        .build());

                return SendMessage.builder()
                        .text("Input 'from' date (yyyy-mm-dd)")
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
        }

        log.warn("Unknown data in CallbackQuery received: " + callbackQuery.getData());
        throw new RuntimeException("Illegal callback data: " + callbackQuery.getData());
    }


}
