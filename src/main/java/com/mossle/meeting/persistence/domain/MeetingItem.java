package com.mossle.meeting.persistence.domain;

// Generated by Hibernate Tools
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MeetingItem .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "MEETING_ITEM")
public class MeetingItem implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private MeetingInfo meetingInfo;

    /** null. */
    private String name;

    /** null. */
    private String tenantId;

    public MeetingItem() {
    }

    public MeetingItem(Long id) {
        this.id = id;
    }

    public MeetingItem(Long id, MeetingInfo meetingInfo, String name,
            String tenantId) {
        this.id = id;
        this.meetingInfo = meetingInfo;
        this.name = name;
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
    @JoinColumn(name = "INFO_ID")
    public MeetingInfo getMeetingInfo() {
        return this.meetingInfo;
    }

    /**
     * @param meetingInfo
     *            null.
     */
    public void setMeetingInfo(MeetingInfo meetingInfo) {
        this.meetingInfo = meetingInfo;
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
