package com.codeup.drinkhustle.Repos;

import com.codeup.drinkhustle.Models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends CrudRepository <Event, Long> {
    Event findByTitle(String title);
    @Query("from Event where title like %:term%")
    List<Event> searchByTitleLike(@Param("term") String term);

    Iterable<Event> findAll();

    Event findOne(long id);

    void delete(long id);

    Iterable<Event> findByUserId(long user_id);
}
