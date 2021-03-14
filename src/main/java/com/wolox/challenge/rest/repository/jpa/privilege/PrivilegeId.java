package com.wolox.challenge.rest.repository.jpa.privilege;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
@AllArgsConstructor
public class PrivilegeId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String albumId;

}
