package com.enya.trainig.ejb.services;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.lang.management.ManagementFactory;

@Singleton
@Startup
public class EhCacheMBean {

    @PostConstruct
    public void init() {
        for (CacheManager manager : CacheManager.ALL_CACHE_MANAGERS) {
            ManagementService.registerMBeans(manager, ManagementFactory.getPlatformMBeanServer(), true, true, true, true, true);
        }
    }
}
