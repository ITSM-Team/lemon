package com.mossle.ticket.persistence.domain;

// Generated by Hibernate Tools
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TicketMember .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "TICKET_MEMBER")
public class TicketMember implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private TicketGroup ticketGroup;

    /** null. */
    private Long user;

    public TicketMember() {
    }

    public TicketMember(Long id) {
        this.id = id;
    }

    public TicketMember(Long id, TicketGroup ticketGroup, Long user) {
        this.id = id;
        this.ticketGroup = ticketGroup;
        this.user = user;
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
    @JoinColumn(name = "GROUP_ID")
    public TicketGroup getTicketGroup() {
        return this.ticketGroup;
    }

    /**
     * @param ticketGroup
     *            null.
     */
    public void setTicketGroup(TicketGroup ticketGroup) {
        this.ticketGroup = ticketGroup;
    }

    /** @return null. */
    @Column(name = "USER")
    public Long getUser() {
        return this.user;
    }

    /**
     * @param user
     *            null.
     */
    public void setUser(Long user) {
        this.user = user;
    }
}
