package com.mossle.internal.sequence.persistence.domain;

// Generated by Hibernate Tools
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SequenceInfo .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "SEQUENCE_INFO")
public class SequenceInfo implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private String name;

    /** null. */
    private String code;

    /** null. */
    private String content;

    /** null. */
    private Integer value;

    /** null. */
    private Date updateDate;

    /** null. */
    private Date createTime;

    /** null. */
    private String userId;

    /** null. */
    private String tenantId;

    public SequenceInfo() {
    }

    public SequenceInfo(Long id) {
        this.id = id;
    }

    public SequenceInfo(Long id, String name, String code, String content,
            Integer value, Date updateDate, Date createTime, String userId,
            String tenantId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.content = content;
        this.value = value;
        this.updateDate = updateDate;
        this.createTime = createTime;
        this.userId = userId;
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
    @Column(name = "NAME", length = 100)
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
    @Column(name = "CODE", length = 50)
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
    @Column(name = "CONTENT", length = 100)
    public String getContent() {
        return this.content;
    }

    /**
     * @param content
     *            null.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /** @return null. */
    @Column(name = "VALUE")
    public Integer getValue() {
        return this.value;
    }

    /**
     * @param value
     *            null.
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /** @return null. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE", length = 26)
    public Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * @param updateDate
     *            null.
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
    @Column(name = "TENANT_ID", length = 50)
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
