package com.wolox.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wolox.challenge.jpa.privilege.Privilege;
import com.wolox.challenge.jpa.privilege.PrivilegeId;

public interface IPrivilegeRepository extends JpaRepository<Privilege, PrivilegeId> {

}
