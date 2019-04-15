package chris.doberman.sandbox.controller;

import chris.doberman.sandbox.service.HitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HitController {

    private final HitService hitService;

    public HitController(HitService hitService) {
        this.hitService = hitService;
    }

    @GetMapping("/hit/{queryId}/")
    public List<Integer> getHitTrend(@PathVariable Long queryId) {
       return hitService.getHitsForLastHour(queryId);
    }

}
