package com.alevel.telegram.bot.cache.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FindDrugFlowData {

    Long chatId;

}
