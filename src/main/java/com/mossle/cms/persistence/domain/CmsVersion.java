package com.mossle.cms.persistence.domain;

// Generated by Hibernate Tools
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsVersion .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "CMS_VERSION")
public class CmsVersion implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private CmsContent cmsContent;

    /** null. */
    private String code;

    /** null. */
    private String name;

    /** null. */
    private String userId;

    /** null. */
    private Date createTime;

    /** null. */
    private Integer priority;

    /** null. */
    private String tenantId;

    public CmsVersion() {
    }

    public CmsVersion(Long id) {
        this.id = id;
    }

    public CmsVersion(Long id, CmsContent cmsContent, String code, String name,
            String userId, Date createTime, Integer priority, String tenantId) {
        this.id = id;
        this.cmsContent = cmsContent;
        this.code = code;
        this.name = name;
        this.userId = userId;
        this.createTime = createTime;
        this.priority = priority;
        this.tenantId = tenantId;
    }

    /** @return null. */
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            null.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return null. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTENT_ID")
    public CmsContent getCmsContent() {
        return this.cmsContent;
    }

    /**
     * @param cmsContent
     *            null.
     */
    public void setCmsContent(CmsContent cmsContent) {
        this.cmsContent = cmsContent;
    }

    /** @return null. */
    @Column(name = "CODE", length = 64)
    public String getCode() {
        return this.code;
    }

    /**
     * @param code
     *            null.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /** @return null. */
    @Column(name = "NAME", length = 50)
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            null.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** @return null. */
    @Column(name = "USER_ID", length = 64)
    public String getUserId() {
        return this.userId;
    }

    /**
     * @param userId
     *            null.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** @return null. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", length = 26)
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * @param createTime
     *            null.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** @return null. */
    @Column(name = "PRIORITY")
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * @param priority
     *            null.
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /** @return null. */
    @Column(name = "TENANT_ID", length = 64)
    public String getTenantId() {
        return this.tenantId;
    }

    /**
     * @param tenantId
     *            null.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
