package com.codeup.drinkhustle.Repos;

import com.codeup.drinkhustle.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long> {
    User findByUsername(String username);
    boolean findEmail(String email);
}
