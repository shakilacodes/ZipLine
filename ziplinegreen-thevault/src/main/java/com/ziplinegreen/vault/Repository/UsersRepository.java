package com.ziplinegreen.vault.Repository;

import com.ziplinegreen.vault.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);
}
