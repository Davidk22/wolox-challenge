package com.wolox.challenge.jpa.privilege;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrivilegeId implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private String userId;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private String albumId;

}
