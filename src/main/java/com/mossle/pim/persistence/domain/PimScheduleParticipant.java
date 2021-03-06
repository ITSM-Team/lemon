package com.mossle.pim.persistence.domain;

// Generated by Hibernate Tools
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PimScheduleParticipant .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "PIM_SCHEDULE_PARTICIPANT")
public class PimScheduleParticipant implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private PimSchedule pimSchedule;

    /** null. */
    private Integer type;

    /** null. */
    private String userId;

    /** null. */
    private Integer status;

    /** null. */
    private String tenantId;

    public PimScheduleParticipant() {
    }

    public PimScheduleParticipant(Long id) {
        this.id = id;
    }

    public PimScheduleParticipant(Long id, PimSchedule pimSchedule,
            Integer type, String userId, Integer status, String tenantId) {
        this.id = id;
        this.pimSchedule = pimSchedule;
        this.type = type;
        this.userId = userId;
        this.status = status;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    public PimSchedule getPimSchedule() {
        return this.pimSchedule;
    }

    /**
     * @param pimSchedule
     *            null.
     */
    public void setPimSchedule(PimSchedule pimSchedule) {
        this.pimSchedule = pimSchedule;
    }

    /** @return null. */
    @Column(name = "TYPE")
    public Integer getType() {
        return this.type;
    }

    /**
     * @param type
     *            null.
     */
    public void setType(Integer type) {
        this.type = type;
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
    @Column(name = "STATUS")
    public Integer getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            null.
     */
    public void setStatus(Integer status) {
        this.status = status;
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
}
