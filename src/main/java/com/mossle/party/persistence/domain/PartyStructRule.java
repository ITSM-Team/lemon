package com.mossle.party.persistence.domain;

// Generated by Hibernate Tools
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PartyStructRule 组织关系规则.
 * 
 * @author Lingo
 */
@Entity
@Table(name = "PARTY_STRUCT_RULE")
public class PartyStructRule implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** 主键. */
    private Long id;

    /** 外键，上级类型. */
    private PartyType parentType;

    /** 外键，下级类型. */
    private PartyType childType;

    /** 外键，组织关系类型. */
    private PartyStructType partyStructType;

    /** 租户. */
    private String tenantId;

    public PartyStructRule() {
    }

    public PartyStructRule(Long id, PartyType childType,
            PartyStructType partyStructType) {
        this.id = id;
        this.childType = childType;
        this.partyStructType = partyStructType;
    }

    public PartyStructRule(Long id, PartyType parentType, PartyType childType,
            PartyStructType partyStructType, String tenantId) {
        this.id = id;
        this.parentType = parentType;
        this.childType = childType;
        this.partyStructType = partyStructType;
        this.tenantId = tenantId;
    }

    /** @return 主键. */
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            主键.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return 外键，上级类型. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_TYPE_ID")
    public PartyType getParentType() {
        return this.parentType;
    }

    /**
     * @param parentType
     *            外键，上级类型.
     */
    public void setParentType(PartyType parentType) {
        this.parentType = parentType;
    }

    /** @return 外键，下级类型. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHILD_TYPE_ID", nullable = false)
    public PartyType getChildType() {
        return this.childType;
    }

    /**
     * @param childType
     *            外键，下级类型.
     */
    public void setChildType(PartyType childType) {
        this.childType = childType;
    }

    /** @return 外键，组织关系类型. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STRUCT_TYPE_ID", nullable = false)
    public PartyStructType getPartyStructType() {
        return this.partyStructType;
    }

    /**
     * @param partyStructType
     *            外键，组织关系类型.
     */
    public void setPartyStructType(PartyStructType partyStructType) {
        this.partyStructType = partyStructType;
    }

    /** @return 租户. */
    @Column(name = "TENANT_ID", length = 50)
    public String getTenantId() {
        return this.tenantId;
    }

    /**
     * @param tenantId
     *            租户.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
