package com.codeup.drinkhustle.Repos;

import com.codeup.drinkhustle.Models.Event;
import com.codeup.drinkhustle.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository <User, Long> {
    User findByEmail(String email);


    User findByName(String name);
    @Query("from User where name like %:userterm%")
    List<User> searchByNameLike(@Param("userterm") String term);

}
