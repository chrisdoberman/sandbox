package chris.doberman.sandbox.service;

import chris.doberman.sandbox.HitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HitServiceImpl implements HitService {

    private final HitListener hitListener;

    public HitServiceImpl(HitListener hitListener) {
        this.hitListener = hitListener;
    }

    @Override
    public List<Integer> getHitsForLastHour(Long queryId) {
        return hitListener.getHitsForLastHour(queryId);
    }
}
