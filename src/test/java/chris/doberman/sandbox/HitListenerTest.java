package chris.doberman.sandbox;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HitListenerTest {

    HitListener hitListener = new HitListener();

    @Test
    public void testHandle() {
        Hit hit = new Hit(1L);
        hitListener.handle(hit);
        Integer hitCount = hitListener.getCurrentHitCounts().get(1L);
        assertEquals(Integer.valueOf(1), hitCount);
        hitListener.handle(hit);
        assertEquals(Integer.valueOf(2), hitListener.getCurrentHitCounts().get(1L));

        hitListener.updateHitTrend();
        List<Integer> hitsForLastHour = hitListener.getHitsForLastHour(1L);
        assertTrue(hitsForLastHour.size() == 2);
    }
}