package com.codeup.drinkhustle.Repos;

import com.codeup.drinkhustle.Models.Event;
import com.codeup.drinkhustle.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends CrudRepository <Event, Long> {

    Event findByTitleLike(String title);

    @Query("from Event where title like %:term%")
    List<Event> searchByTitleLike(@Param("term") String term);

    @Query("from Event where description like %:term%")
    List<Event> searchByDescriptionLike(@Param("term") String term);

    @Query("from Event where address like %:term%")
    List<Event> searchByAddressLike(@Param("term") String term);

    Iterable<Event> findAll();

    Event findOne(long id);

    void delete(long id);

    Iterable<Event> findByOwner(User owner);




}
