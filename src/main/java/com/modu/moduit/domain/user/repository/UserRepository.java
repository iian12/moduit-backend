package com.modu.moduit.domain.user.repository;

import com.modu.moduit.domain.user.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findByEmail(String email);
}
