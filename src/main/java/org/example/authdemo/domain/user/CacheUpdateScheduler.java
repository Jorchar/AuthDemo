package org.example.authdemo.domain.user;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.authdemo.domain.user.service.RoleService;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class CacheUpdateScheduler {
    private final RoleService roleService;
    private final RedisCacheManager cacheManager;

    //@Scheduled(cron = "50 58 13 * * ?")
    @Scheduled(fixedRate = 10000)
    public void refreshRoles() {
        var roles = roleService.getRoleActions();
    }

    @Scheduled(fixedRate = 15000)
    public void checkCache() {
        Cache cache = cacheManager.getCache("actions");
        if (cache != null) {
            log.info("Data currently in cache: {}", cache.get(SimpleKey.EMPTY, Object.class));
        }
    }
}
