package com.hvdomingues.simpleCrud.dao.jpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.hvdomingues.simpleCrud.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	Page<User> findByIsDeleted(Boolean isDeleted, Pageable pageable);
	
	Page<User> findBy(User user, Pageable pageable);
	
	Page<User> findAll(Example<User> user, Pageable pageable);
	
	User findByLogin(String login);

}
