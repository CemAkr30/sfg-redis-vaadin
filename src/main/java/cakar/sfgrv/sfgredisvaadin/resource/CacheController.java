package cakar.sfgrv.sfgredisvaadin.resource;


import cakar.sfgrv.sfgredisvaadin.service.redisService.RedisCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CacheController.BASE_URL)
public class CacheController {

    public static final String BASE_URL = "cache";
    private  final RedisCacheService redisCacheService;

    public CacheController(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }


    private int sayac = 0;

    @GetMapping
    public String getCache(){
        if(sayac == 5){
            redisCacheService.clearCache();
            sayac = 0;
        }
        sayac++;
        return redisCacheService.longRunningProcess();
    }
}
