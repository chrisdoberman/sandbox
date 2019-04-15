package chris.doberman.sandbox;

import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HitListener {

    private Map<Long, Integer> currentHitCounts = new ConcurrentHashMap<>();
    private Map<Long, FixedTrendQueue> queryRequestHitTrend = new HashMap<>();

    public void handle(Hit hit) {
        currentHitCounts.merge(hit.getQueryId(), Integer.valueOf(1), Integer::sum);
    }


    @Scheduled(fixedRate = 60000)
    public void updateHitTrend() {
        for (Map.Entry<Long, Integer> entry : currentHitCounts.entrySet()) {
            FixedTrendQueue trendQueue = queryRequestHitTrend.get(entry.getKey());
            if (trendQueue != null) {
                trendQueue.add(entry.getValue());
                // this isn't thread safe, use AtomicInteger getAndSet
                entry.setValue(0);
            } else {
                queryRequestHitTrend.put(entry.getKey(), new FixedTrendQueue(entry.getValue()));
            }
        }
    }

    public List<Integer> getHitsForLastHour(Long queryId) {
        FixedTrendQueue queue = queryRequestHitTrend.get(queryId);
        if (queue != null) {
            return queue.covertToList();
        }
        return new ArrayList<>();
    }

    public Map<Long, Integer> getCurrentHitCounts() {
        return Collections.unmodifiableMap(currentHitCounts);
    }

}
