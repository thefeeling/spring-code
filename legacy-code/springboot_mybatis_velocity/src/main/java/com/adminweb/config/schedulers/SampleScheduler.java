package com.adminweb.config.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.adminweb.admin.AdminController;


/**
 * @Title   Sample Scheduler
 * @Comment
 * -----------------------------------------------------------------------------------------------------------
 * [Date]				[Modifier]				[Comment]
 * 2016.05.24			kschoi					샘플 스캐쥴러 작성							
 * -----------------------------------------------------------------------------------------------------------
 * @Author kschoi
 * @AnotherComment
 * 
 *
 */
@Component
public class SampleScheduler {
	private final Logger log = LoggerFactory.getLogger(AdminController.class);
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    public void reportCurrentTime() {
        log.info("The time is now " + dateFormat.format(new Date()));
    }
}
