package com.hvdomingues.simpleCrud.dao.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hvdomingues.simpleCrud.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	

}
