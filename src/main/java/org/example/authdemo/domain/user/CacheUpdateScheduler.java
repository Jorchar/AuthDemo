package org.example.authdemo.domain.user;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.service.RoleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CacheUpdateScheduler {
    private final RoleService roleService;

    @Scheduled(cron = "50 58 13 * * ?")
    public void refreshRoles() {
        var roles = roleService.getRoleActions();
    }
}
