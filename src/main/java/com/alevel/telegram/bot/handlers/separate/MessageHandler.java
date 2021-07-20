package com.alevel.telegram.bot.handlers.separate;


import com.alevel.telegram.bot.cache.model.FindDrugFlowData;
import com.alevel.telegram.bot.cache.model.FindDrugInTimeRangeData;
import com.alevel.telegram.bot.cache.FindDrugInTimeRangeStatus;
import com.alevel.telegram.bot.cache.state.find.impl.FindDrugFlowState;
import com.alevel.telegram.bot.cache.state.find.impl.FindDrugInTimeRangeFlowState;
import com.alevel.telegram.bot.handlers.Handler;
import com.alevel.telegram.bot.services.BotService;
import com.alevel.telegram.bot.services.ButtonsForBot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageHandler implements Handler<Message> {

    @Value("${answer.menuFirst}")
    String menuFirst;
    @Value("${answer.menuSecond}")
    String menuSecond;

    ButtonsForBot buttonsForBot;
    FindDrugFlowState findDrugFlowState;
    FindDrugInTimeRangeFlowState findDrugInTimeRangeFlowState;
    BotService botService;

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (message.hasText()) {

            Optional<FindDrugFlowData> drugFlowData = findDrugFlowState.findBy(message.getChatId());
            if (drugFlowData.isPresent()) {

                findDrugFlowState.remove(drugFlowData.get());

                return SendMessage.builder()
                        .text(String.valueOf(botService.drug(message.getText())))
                        .chatId(String.valueOf(message.getChatId()))
                        .build();
            }


//---------------------------------------------------------

            Optional<FindDrugInTimeRangeData> data = findDrugInTimeRangeFlowState.findBy(message.getChatId());
            if (data.isPresent()) {
                FindDrugInTimeRangeData findDrugInTimeRangeData = data.get();

                if (findDrugInTimeRangeData.getStatus() == FindDrugInTimeRangeStatus.INPUT_FIRST_DATE) {
                    Date fromDate;
                    try {
                        fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(message.getText());
                    } catch (ParseException e) {
                        log.warn("Illegal date format.");
                        return SendMessage.builder()
                                .chatId(String.valueOf(message.getChatId()))
                                .text("Illegal date format. Try again please.")
                                .build();
                    }
                    findDrugInTimeRangeData.setFrom(fromDate);
                    findDrugInTimeRangeData.setStatus(FindDrugInTimeRangeStatus.INPUT_SECOND_DATE);

                    return SendMessage.builder()
                            .chatId(String.valueOf(message.getChatId()))
                            .text("Input 'to' date (yyyy-mm-dd)")
                            .build();
                } else if (findDrugInTimeRangeData.getStatus() == FindDrugInTimeRangeStatus.INPUT_SECOND_DATE) {
                    Date toDate;
                    try {
                        toDate = new SimpleDateFormat("yyyy-MM-dd").parse(message.getText());
                    } catch (ParseException e) {
                        log.warn("Illegal date format.");
                        return SendMessage.builder()
                                .chatId(String.valueOf(message.getChatId()))
                                .text("Illegal date format. Try again please.")
                                .build();
                    }
                    findDrugInTimeRangeData.setTo(toDate);

                    Date from = findDrugInTimeRangeData.getFrom();
                    Date to = findDrugInTimeRangeData.getTo();
                    if (from.after(to)) {
                        Date op = to;
                        to = from;
                        from = op;
                    }

                    findDrugInTimeRangeFlowState.remove(findDrugInTimeRangeData);
                    return SendMessage.builder()
                            .chatId(String.valueOf(message.getChatId()))
                            .text(String.valueOf(botService.listDrugsByDateOfApproval(from, to)))
                            .build();
                }
            }
//---------------------------------------------------------


            if (message.getText().equals("/start")) {
                return menu(menuFirst, message);
            } else if (message.getText().equals("/menu")) {
                return menu(menuSecond, message);
            } else {
                log.debug("Unsupported text command retrieved.");
                return SendMessage.builder()
                        .chatId(String.valueOf(message.getChatId()))
                        .text("Select item from menu")
                        .build();
            }
        }
        log.debug("Unsupported message arrived.");
        return new SendMessage();
    }


    private SendMessage menu(String menu, Message message) {
        return SendMessage.builder()
                .text(menu)
                .parseMode("HTML")
                .chatId(String.valueOf(message.getChatId()))
                .replyMarkup(buttonsForBot.keyBoard())
                .build();
    }

}
