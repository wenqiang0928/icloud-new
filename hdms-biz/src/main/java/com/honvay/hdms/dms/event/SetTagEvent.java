/***/
package com.honvay.hdms.dms.event;

import com.honvay.hdms.dms.document.entity.Document;
import org.springframework.context.ApplicationEvent;

/**
 * @author wxq
 * created on 2019/3/9
 **/
public class SetTagEvent extends ApplicationEvent {

	private Document document;

	private String originalTags;
	private Integer userId;

	public SetTagEvent(Document document, String originalTags, Integer userId) {
		super(document);
		this.document = document;
		this.originalTags = originalTags;
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

	public String getOriginalTags() {
		return originalTags;
	}

	public void setOriginalTags(String originalTags) {
		this.originalTags = originalTags;
	}
}
