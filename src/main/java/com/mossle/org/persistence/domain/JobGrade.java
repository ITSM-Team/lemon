package com.mossle.org.persistence.domain;

// Generated by Hibernate Tools
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobGrade .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "JOB_GRADE")
public class JobGrade implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private String name;

    /** null. */
    private String tenantId;

    /** null. */
    private Integer priority;

    /** . */
    private Set<JobLevel> jobLevels = new HashSet<JobLevel>(0);

    public JobGrade() {
    }

    public JobGrade(Long id) {
        this.id = id;
    }

    public JobGrade(Long id, String name, String tenantId, Integer priority,
            Set<JobLevel> jobLevels) {
        this.id = id;
        this.name = name;
        this.tenantId = tenantId;
        this.priority = priority;
        this.jobLevels = jobLevels;
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

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobGrade")
    public Set<JobLevel> getJobLevels() {
        return this.jobLevels;
    }

    /**
     * @param jobLevels
     *            .
     */
    public void setJobLevels(Set<JobLevel> jobLevels) {
        this.jobLevels = jobLevels;
    }
}
