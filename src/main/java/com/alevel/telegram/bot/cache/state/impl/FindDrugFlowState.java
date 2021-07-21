package com.alevel.telegram.bot.cache.state.impl;

import com.alevel.telegram.bot.cache.model.FindDrugFlowData;
import com.alevel.telegram.bot.cache.state.FlowState;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FindDrugFlowState implements FlowState<FindDrugFlowData> {

    Map<Long, FindDrugFlowData> data = new ConcurrentHashMap<>();

    @Override
    public void add(FindDrugFlowData findDrugFlowData) {
        data.put(findDrugFlowData.getChatId(), findDrugFlowData);
    }

    @Override
    public void remove(FindDrugFlowData findDrugFlowData) {
        data.remove(findDrugFlowData.getChatId());
    }

    @Override
    public Optional<FindDrugFlowData> findBy(Long id) {
        return Optional.ofNullable(data.get(id));
    }

}
