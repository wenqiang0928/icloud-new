package com.honvay.hdms.dms.event;

import com.honvay.hdms.dms.document.entity.Document;
import org.springframework.context.ApplicationEvent;

/**
 * @author wenxinqiang
 * @date 2019/4/16 9:39
 */
public class UpdatePolicingNoEvent extends ApplicationEvent {
    private Document document;

    private String originalPolicingNo;
    private Integer userId;

    public UpdatePolicingNoEvent( Document document, String originalPolicingNo, Integer userId) {
        super(document);
        this.document = document;
        this.originalPolicingNo = originalPolicingNo;
        this.userId = userId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getOriginalPolicingNo() {
        return originalPolicingNo;
    }

    public void setOriginalPolicingNo(String originalPolicingNo) {
        this.originalPolicingNo = originalPolicingNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
