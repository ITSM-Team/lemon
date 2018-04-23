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
 * MeetingAttendee .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "MEETING_ATTENDEE")
public class MeetingAttendee implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private MeetingInfo meetingInfo;

    /** null. */
    private String userId;

    /** null. */
    private Integer priority;

    /** null. */
    private String type;

    /** null. */
    private String tenantId;

    public MeetingAttendee() {
    }

    public MeetingAttendee(Long id) {
        this.id = id;
    }

    public MeetingAttendee(Long id, MeetingInfo meetingInfo, String userId,
            Integer priority, String type, String tenantId) {
        this.id = id;
        this.meetingInfo = meetingInfo;
        this.userId = userId;
        this.priority = priority;
        this.type = type;
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
    @Column(name = "TYPE", length = 50)
    public String getType() {
        return this.type;
    }

    /**
     * @param type
     *            null.
     */
    public void setType(String type) {
        this.type = type;
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
