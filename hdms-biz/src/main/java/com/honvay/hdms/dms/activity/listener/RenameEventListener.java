/***/
package com.honvay.hdms.dms.activity.listener;

import com.honvay.hdms.dms.activity.entity.Activity;
import com.honvay.hdms.dms.activity.enums.ActivityScope;
import com.honvay.hdms.dms.activity.enums.OperationType;
import com.honvay.hdms.dms.activity.service.ActivityService;
import com.honvay.hdms.dms.document.entity.Document;
import com.honvay.hdms.dms.event.RenameEvent;
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
public class RenameEventListener implements ApplicationListener<RenameEvent> {

	@Autowired
	private ActivityService activityService;

	@Override
	public void onApplicationEvent(RenameEvent event) {

		Document document = event.getDocument();

		if (log.isDebugEnabled()) {
			log.debug("Rename file:{} ", document.getId());
		}

		Activity activity = Activity.builder()
				.documentId(document.getId())
				.documentName(document.getName())
				.documentType(document.getType())
				.path(document.getPath())
				.scope(ActivityScope.BOTH)
				.originalName(event.getOriginalName())
				.currentName(document.getName())
				.operator(event.getUserId())
				.operation(OperationType.RENAME)
				.build();
		activityService.save(activity);

	}
}
