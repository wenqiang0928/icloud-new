/***/
package com.honvay.hdms.dms.event;

import com.honvay.hdms.dms.document.entity.Document;
import org.springframework.context.ApplicationEvent;

/**
 * @author wxq
 */
public class LockEvent extends ApplicationEvent {

	private Document document;

	private Integer userId;

	public LockEvent(Document document, Integer userId) {
		super(document);
		this.document = document;
		this.userId = userId;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
