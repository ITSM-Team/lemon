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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RoleDef 角色定义.
 * 
 * @author Lingo
 */
@Entity
@Table(name = "AUTH_ROLE_DEF")
public class RoleDef implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** 主键. */
    private Long id;

    /** 名称. */
    private String name;

    /** 备注. */
    private String descn;

    /** 租户. */
    private String tenantId;

    /** . */
    private Set<Role> roles = new HashSet<Role>(0);

    /** . */
    private Set<Perm> perms = new HashSet<Perm>(0);

    public RoleDef() {
    }

    public RoleDef(Long id) {
        this.id = id;
    }

    public RoleDef(Long id, String name, String descn, String tenantId,
            Set<Role> roles, Set<Perm> perms) {
        this.id = id;
        this.name = name;
        this.descn = descn;
        this.tenantId = tenantId;
        this.roles = roles;
        this.perms = perms;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roleDef")
    public Set<Role> getRoles() {
        return this.roles;
    }

    /**
     * @param roles
     *            .
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /** @return . */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AUTH_PERM_ROLE_DEF", joinColumns = { @JoinColumn(name = "ROLE_DEF_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "PERM_ID", nullable = false, updatable = false) })
    public Set<Perm> getPerms() {
        return this.perms;
    }

    /**
     * @param perms
     *            .
     */
    public void setPerms(Set<Perm> perms) {
        this.perms = perms;
    }
}
