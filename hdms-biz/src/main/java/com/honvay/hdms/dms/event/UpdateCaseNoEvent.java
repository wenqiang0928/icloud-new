package com.honvay.hdms.dms.event;

import com.honvay.hdms.dms.document.entity.Document;
import org.springframework.context.ApplicationEvent;

public class UpdateCaseNoEvent extends ApplicationEvent {
    private Document document;

    private String originalCaseNo;
    private Integer userId;

    public UpdateCaseNoEvent( Document document, String originalCaseNo, Integer userId) {
        super(document);
        this.document = document;
        this.originalCaseNo = originalCaseNo;
        this.userId = userId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getOriginalCaseNo() {
        return originalCaseNo;
    }

    public void setOriginalCaseNo(String originalCaseNo) {
        this.originalCaseNo = originalCaseNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
