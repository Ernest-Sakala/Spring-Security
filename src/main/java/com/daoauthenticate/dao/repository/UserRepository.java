package com.daoauthenticate.dao.repository;

import com.daoauthenticate.dao.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String username);
}
