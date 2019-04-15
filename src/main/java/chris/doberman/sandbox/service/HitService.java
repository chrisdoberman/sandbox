package chris.doberman.sandbox.service;

import java.util.List;

public interface HitService {

    List<Integer> getHitsForLastHour(Long queryId);
}
