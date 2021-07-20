package  com.alevel.telegram.bot.cache.state;

import java.util.Optional;

public interface FlowState<T> {

    void add(T t);

    void remove(T t);

    Optional<T> findBy(Long id);

}
