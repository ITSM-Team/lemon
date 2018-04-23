package com.mossle.auth.persistence.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 权限角色
 * @author Administrator
 *
 */
@Entity
@Table(name = "AUTH_USER_ROLE")
public class AuthUserRole implements java.io.Serializable {
	 private static final long serialVersionUID = 0L;
	 
	 
	 public AuthUserRole() {
		super();
	}

	/**
	  * 用户id
	  */
	 private Long  userStatusId;
	 
	 /**
	  * 权限id
	  */
	 private Long roleId;

	@Id
    @Column(name = "USER_STATUS_ID", unique = true, nullable= false)
	public Long getUserStatusId() {
		return userStatusId;
	}

	public void setUserStatusId(Long userStatusId) {
		this.userStatusId = userStatusId;
	}

	@Id
    @Column(name = "ROLE_ID", unique = true, nullable= false)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	 
	 
	 
	 
	 
}
