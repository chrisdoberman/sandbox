package chris.doberman.sandbox;

import com.google.common.collect.EvictingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FixedTrendQueue {

    private static final int QUEUE_SIZE = 60;
    private Queue fixedQueue;

    public FixedTrendQueue(Integer initialHits) {
        fixedQueue = EvictingQueue.create(QUEUE_SIZE);
        fixedQueue.add(initialHits);
    }

    public boolean add(Integer hits) {
        return fixedQueue.add(hits);
    }

    public List<Integer> covertToList() {
        return new ArrayList<>(fixedQueue);
    }

}
