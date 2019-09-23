package com.codeup.drinkhustle.Repos;

import com.codeup.drinkhustle.Models.Event;
import com.codeup.drinkhustle.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long> {
    User findByEmail(String email);

//    User findById(long id);
}
