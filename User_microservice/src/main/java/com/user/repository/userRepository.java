package com.user.repository;

import com.user.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findAll();
    Optional<UserEntity> findByUsername(String username);
    void deleteById(Long id);
}
