package daos;

import models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository <Event, Long> {

}
