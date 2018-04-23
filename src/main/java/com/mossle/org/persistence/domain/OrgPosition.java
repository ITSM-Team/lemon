package com.mossle.org.persistence.domain;

// Generated by Hibernate Tools
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrgPosition .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "ORG_POSITION")
public class OrgPosition implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private OrgPositionType orgPositionType;

    /** null. */
    private String name;

    /** null. */
    private Integer priority;

    /** null. */
    private String scopeId;

    public OrgPosition() {
    }

    public OrgPosition(OrgPositionType orgPositionType, String name,
            Integer priority, String scopeId) {
        this.orgPositionType = orgPositionType;
        this.name = name;
        this.priority = priority;
        this.scopeId = scopeId;
    }

    /** @return null. */
    @Id
    @GeneratedValue
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
    @JoinColumn(name = "TYPE_ID")
    public OrgPositionType getOrgPositionType() {
        return this.orgPositionType;
    }

    /**
     * @param orgPositionType
     *            null.
     */
    public void setOrgPositionType(OrgPositionType orgPositionType) {
        this.orgPositionType = orgPositionType;
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
    @Column(name = "SCOPE_ID", length = 50)
    public String getScopeId() {
        return this.scopeId;
    }

    /**
     * @param scopeId
     *            null.
     */
    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }
}
