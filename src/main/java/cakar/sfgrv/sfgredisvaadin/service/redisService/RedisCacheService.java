package cakar.sfgrv.sfgredisvaadin.service.redisService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisCacheService {

    @Cacheable(cacheNames = "longRunningProcess") // cache tutuyoruz
    public String longRunningProcess(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "long running process";
    }

    @CacheEvict(cacheNames = "longRunningProcess") // cache temizliyoruz
    public void clearCache(){
       log.info("Cache cleared");
    }
}
