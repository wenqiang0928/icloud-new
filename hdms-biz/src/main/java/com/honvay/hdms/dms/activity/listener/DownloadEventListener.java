/***/
package com.honvay.hdms.dms.activity.listener;

import com.honvay.hdms.dms.activity.entity.Activity;
import com.honvay.hdms.dms.activity.enums.ActivityScope;
import com.honvay.hdms.dms.activity.enums.OperationType;
import com.honvay.hdms.dms.activity.service.ActivityService;
import com.honvay.hdms.dms.document.entity.Document;
import com.honvay.hdms.dms.event.DownloadEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author wxq
 * created on 2019/3/9
 **/
@Slf4j
@Component
public class DownloadEventListener implements ApplicationListener<DownloadEvent> {

	@Autowired
	private ActivityService activityService;

	@Override
	public void onApplicationEvent(DownloadEvent downloadEvent) {

		Document document = downloadEvent.getDocument();

		if (log.isDebugEnabled()) {
			log.debug("Download file:{} ", document.getId());
		}

		Activity activity = Activity.builder()
				.documentId(document.getId())
				.documentName(document.getName())
				.documentType(document.getType())
				.path(document.getPath())
				.scope(ActivityScope.BOTH)
				.operator(downloadEvent.getUserId())
				.operation(OperationType.DOWNLOAD)
				.build();
		activityService.save(activity);


	}
}
