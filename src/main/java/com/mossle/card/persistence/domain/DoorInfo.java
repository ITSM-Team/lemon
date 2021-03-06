package com.mossle.card.persistence.domain;

// Generated by Hibernate Tools
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DoorInfo .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "DOOR_INFO")
public class DoorInfo implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private String name;

    /** null. */
    private String type;

    /** null. */
    private String building;

    /** null. */
    private String floor;

    /** null. */
    private Integer priority;

    /** null. */
    private String description;

    public DoorInfo() {
    }

    public DoorInfo(Long id) {
        this.id = id;
    }

    public DoorInfo(Long id, String name, String type, String building,
            String floor, Integer priority, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.building = building;
        this.floor = floor;
        this.priority = priority;
        this.description = description;
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
    @Column(name = "BUILDING", length = 100)
    public String getBuilding() {
        return this.building;
    }

    /**
     * @param building
     *            null.
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /** @return null. */
    @Column(name = "FLOOR", length = 100)
    public String getFloor() {
        return this.floor;
    }

    /**
     * @param floor
     *            null.
     */
    public void setFloor(String floor) {
        this.floor = floor;
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
}
