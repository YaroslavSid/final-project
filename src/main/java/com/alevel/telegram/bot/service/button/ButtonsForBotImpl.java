package com.alevel.telegram.bot.service.button;

import com.alevel.telegram.bot.service.ButtonsForBot;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class ButtonsForBotImpl implements ButtonsForBot {

    @Override
    public InlineKeyboardMarkup keyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = InlineKeyboardButton.builder()
                .text("List of innovative drugs")
                .callbackData(ChoseButton.BUTTON_ONE)
                .build();
        InlineKeyboardButton button2 = InlineKeyboardButton.builder()
                .text("List all companies")
                .callbackData(ChoseButton.BUTTON_TWO)
                .build();
        InlineKeyboardButton button3 = InlineKeyboardButton.builder()
                .text("Find a drug")
                .callbackData(ChoseButton.BUTTON_THREE)
                .build();
        InlineKeyboardButton button4 = InlineKeyboardButton.builder()
                .text("Find approved medications in a specific time frame")
                .callbackData(ChoseButton.BUTTON_FOUR)
                .build();

        List<InlineKeyboardButton> list1 = new ArrayList<>();
        list1.add(button1);

        List<InlineKeyboardButton> list2 = new ArrayList<>();
        list2.add(button2);

        List<InlineKeyboardButton> list3 = new ArrayList<>();
        list3.add(button3);

        List<InlineKeyboardButton> list4 = new ArrayList<>();
        list4.add(button4);


        List<List<InlineKeyboardButton>> keyBoards = new ArrayList<>();
        keyBoards.add(list1);
        keyBoards.add(list2);
        keyBoards.add(list3);
        keyBoards.add(list4);

        inlineKeyboardMarkup.setKeyboard(keyBoards);
        return inlineKeyboardMarkup;
    }

}
