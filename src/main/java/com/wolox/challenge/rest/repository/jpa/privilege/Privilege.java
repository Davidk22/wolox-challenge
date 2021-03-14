package com.wolox.challenge.rest.repository.jpa.privilege;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "privileges", schema = "woloxtest")
@Setter
@Getter
@NoArgsConstructor
public class Privilege {

	@EmbeddedId
	private PrivilegeId id;

	@Column(nullable = false, length = 1)
	@Check(constraints = "privileges IN('0','1','2')")
	private String privileges;
}
