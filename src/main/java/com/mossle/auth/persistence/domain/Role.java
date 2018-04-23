package com.mossle.auth.persistence.domain;

// Generated by Hibernate Tools
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Role 角色.
 * 
 * @author Lingo
 */
@Entity
@Table(name = "AUTH_ROLE")
public class Role implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** 主键. */
    private Long id;

    /** 外键，角色定义. */
    private RoleDef roleDef;

    /** 名称. */
    private String name;

    /** 备注. */
    private String descn;

    /** 租户. */
    private String tenantId;

    /** . */
    private Set<UserStatus> userStatuses = new HashSet<UserStatus>(0);

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, RoleDef roleDef, String name, String descn,
            String tenantId, Set<UserStatus> userStatuses) {
        this.id = id;
        this.roleDef = roleDef;
        this.name = name;
        this.descn = descn;
        this.tenantId = tenantId;
        this.userStatuses = userStatuses;
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

    /** @return 外键，角色定义. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_DEF_ID")
    public RoleDef getRoleDef() {
        return this.roleDef;
    }

    /**
     * @param roleDef
     *            外键，角色定义.
     */
    public void setRoleDef(RoleDef roleDef) {
        this.roleDef = roleDef;
    }

    /** @return 名称. */
    @Column(name = "NAME", length = 50)
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            名称.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** @return 备注. */
    @Column(name = "DESCN", length = 200)
    public String getDescn() {
        return this.descn;
    }

    /**
     * @param descn
     *            备注.
     */
    public void setDescn(String descn) {
        this.descn = descn;
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

    /** @return . */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AUTH_USER_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USER_STATUS_ID", nullable = false, updatable = false) })
    public Set<UserStatus> getUserStatuses() {
        return this.userStatuses;
    }

    /**
     * @param userStatuses
     *            .
     */
    public void setUserStatuses(Set<UserStatus> userStatuses) {
        this.userStatuses = userStatuses;
    }
}
