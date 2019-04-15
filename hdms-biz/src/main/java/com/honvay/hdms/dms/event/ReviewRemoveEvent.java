/***/
package com.honvay.hdms.dms.event;

import com.honvay.hdms.dms.document.entity.Document;
import org.springframework.context.ApplicationEvent;

/**
 * @author wxq
 * created on 2019/3/9
 **/
public class ReviewRemoveEvent extends ApplicationEvent {

	private Document document;

	private String content;
	private Integer userId;

	public ReviewRemoveEvent(Document document, String content, Integer userId) {
		super(document);
		this.document = document;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
