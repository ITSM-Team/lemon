package com.mossle.vote.persistence.domain;

// Generated by Hibernate Tools
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

/**
 * VoteItem .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "VOTE_ITEM")
public class VoteItem implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private VoteInfo voteInfo;

    /** null. */
    private String name;

    /** null. */
    private String style;

    /** null. */
    private Integer priority;

    /** null. */
    private String tenantId;

    /** null. */
    private Integer headCount;

    /** . */
    private Set<VoteUser> voteUsers = new HashSet<VoteUser>(0);

    public VoteItem() {
    }

    public VoteItem(Long id) {
        this.id = id;
    }

    public VoteItem(Long id, VoteInfo voteInfo, String name, String style,
            Integer priority, String tenantId, Integer headCount,
            Set<VoteUser> voteUsers) {
        this.id = id;
        this.voteInfo = voteInfo;
        this.name = name;
        this.style = style;
        this.priority = priority;
        this.tenantId = tenantId;
        this.headCount = headCount;
        this.voteUsers = voteUsers;
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
    @JoinColumn(name = "INFO_ID")
    public VoteInfo getVoteInfo() {
        return this.voteInfo;
    }

    /**
     * @param voteInfo
     *            null.
     */
    public void setVoteInfo(VoteInfo voteInfo) {
        this.voteInfo = voteInfo;
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
    @Column(name = "STYLE", length = 50)
    public String getStyle() {
        return this.style;
    }

    /**
     * @param style
     *            null.
     */
    public void setStyle(String style) {
        this.style = style;
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

    /** @return null. */
    @Column(name = "HEAD_COUNT")
    public Integer getHeadCount() {
        return this.headCount;
    }

    /**
     * @param headCount
     *            null.
     */
    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "voteItem")
    public Set<VoteUser> getVoteUsers() {
        return this.voteUsers;
    }

    /**
     * @param voteUsers
     *            .
     */
    public void setVoteUsers(Set<VoteUser> voteUsers) {
        this.voteUsers = voteUsers;
    }
}
