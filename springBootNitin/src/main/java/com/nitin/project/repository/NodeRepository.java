package com.nitin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitin.project.model.*;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
	

}
