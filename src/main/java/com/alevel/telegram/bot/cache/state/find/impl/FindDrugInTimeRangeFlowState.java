package com.alevel.telegram.bot.cache.state.find.impl;

import com.alevel.telegram.bot.cache.model.FindDrugInTimeRangeData;
import com.alevel.telegram.bot.cache.state.FlowState;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FindDrugInTimeRangeFlowState implements FlowState<FindDrugInTimeRangeData> {

    Map<Long, FindDrugInTimeRangeData> data = new ConcurrentHashMap<>();

    @Override
    public void add(FindDrugInTimeRangeData findDrugInTimeRangeData) {
        data.put(findDrugInTimeRangeData.getChatId(), findDrugInTimeRangeData);
    }

    @Override
    public void remove(FindDrugInTimeRangeData findDrugInTimeRangeData) {
        data.remove(findDrugInTimeRangeData.getChatId());
    }

    @Override
    public Optional<FindDrugInTimeRangeData> findBy(Long id) {
        return Optional.ofNullable(data.get(id));
    }
}
