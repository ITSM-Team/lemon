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
 * CmsAttachment .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "CMS_ATTACHMENT")
public class CmsAttachment implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private CmsArticle cmsArticle;

    /** null. */
    private String type;

    /** null. */
    private String name;

    /** null. */
    private String path;

    /** null. */
    private Integer size;

    /** null. */
    private Integer height;

    /** null. */
    private Integer width;

    /** null. */
    private Date createTime;

    /** null. */
    private String userId;

    public CmsAttachment() {
    }

    public CmsAttachment(Long id) {
        this.id = id;
    }

    public CmsAttachment(Long id, CmsArticle cmsArticle, String type,
            String name, String path, Integer size, Integer height,
            Integer width, Date createTime, String userId) {
        this.id = id;
        this.cmsArticle = cmsArticle;
        this.type = type;
        this.name = name;
        this.path = path;
        this.size = size;
        this.height = height;
        this.width = width;
        this.createTime = createTime;
        this.userId = userId;
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
    @JoinColumn(name = "ARTICLE_ID")
    public CmsArticle getCmsArticle() {
        return this.cmsArticle;
    }

    /**
     * @param cmsArticle
     *            null.
     */
    public void setCmsArticle(CmsArticle cmsArticle) {
        this.cmsArticle = cmsArticle;
    }

    /** @return null. */
    @Column(name = "TYPE", length = 200)
    public String getType() {
        return this.type;
    }

    /**
     * @param type
     *            null.
     */
    public void setType(String type) {
        this.type = type;
    }

    /** @return null. */
    @Column(name = "NAME", length = 200)
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
    @Column(name = "PATH", length = 200)
    public String getPath() {
        return this.path;
    }

    /**
     * @param path
     *            null.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /** @return null. */
    @Column(name = "SIZE")
    public Integer getSize() {
        return this.size;
    }

    /**
     * @param size
     *            null.
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /** @return null. */
    @Column(name = "HEIGHT")
    public Integer getHeight() {
        return this.height;
    }

    /**
     * @param height
     *            null.
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /** @return null. */
    @Column(name = "WIDTH")
    public Integer getWidth() {
        return this.width;
    }

    /**
     * @param width
     *            null.
     */
    public void setWidth(Integer width) {
        this.width = width;
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
    @Column(name = "USER_ID", length = 200)
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
}
