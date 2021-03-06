package com.mossle.internal.whitelist.persistence.domain;

// Generated by Hibernate Tools
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WhitelistService .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "WHITELIST_SERVICE")
public class WhitelistService implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private WhitelistPackage whitelistPackage;

    /** null. */
    private String name;

    /** null. */
    private String description;

    /** null. */
    private String userId;

    /** null. */
    private Date createTime;

    /** null. */
    private Date updateTime;

    /** null. */
    private String status;

    /** null. */
    private String priority;

    /** . */
    private Set<WhitelistInfo> whitelistInfos = new HashSet<WhitelistInfo>(0);

    public WhitelistService() {
    }

    public WhitelistService(Long id) {
        this.id = id;
    }

    public WhitelistService(Long id, WhitelistPackage whitelistPackage,
            String name, String description, String userId, Date createTime,
            Date updateTime, String status, String priority,
            Set<WhitelistInfo> whitelistInfos) {
        this.id = id;
        this.whitelistPackage = whitelistPackage;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.priority = priority;
        this.whitelistInfos = whitelistInfos;
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
    @JoinColumn(name = "PACKAGE_ID")
    public WhitelistPackage getWhitelistPackage() {
        return this.whitelistPackage;
    }

    /**
     * @param whitelistPackage
     *            null.
     */
    public void setWhitelistPackage(WhitelistPackage whitelistPackage) {
        this.whitelistPackage = whitelistPackage;
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
    @Column(name = "DESCRIPTION", length = 200)
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            null.
     */
    public void setDescription(String description) {
        this.description = description;
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME", length = 26)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * @param updateTime
     *            null.
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /** @return null. */
    @Column(name = "STATUS", length = 50)
    public String getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            null.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** @return null. */
    @Column(name = "PRIORITY", length = 50)
    public String getPriority() {
        return this.priority;
    }

    /**
     * @param priority
     *            null.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "whitelistService")
    public Set<WhitelistInfo> getWhitelistInfos() {
        return this.whitelistInfos;
    }

    /**
     * @param whitelistInfos
     *            .
     */
    public void setWhitelistInfos(Set<WhitelistInfo> whitelistInfos) {
        this.whitelistInfos = whitelistInfos;
    }
}
