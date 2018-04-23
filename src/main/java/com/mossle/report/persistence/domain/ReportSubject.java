package com.mossle.report.persistence.domain;

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
 * ReportSubject .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "REPORT_SUBJECT")
public class ReportSubject implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private String name;

    /** null. */
    private String description;

    /** null. */
    private Integer priority;

    /** . */
    private Set<ReportQuery> reportQueries = new HashSet<ReportQuery>(0);

    public ReportSubject() {
    }

    public ReportSubject(Long id) {
        this.id = id;
    }

    public ReportSubject(Long id, String name, String description,
            Integer priority, Set<ReportQuery> reportQueries) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.reportQueries = reportQueries;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reportSubject")
    public Set<ReportQuery> getReportQueries() {
        return this.reportQueries;
    }

    /**
     * @param reportQueries
     *            .
     */
    public void setReportQueries(Set<ReportQuery> reportQueries) {
        this.reportQueries = reportQueries;
    }
}
