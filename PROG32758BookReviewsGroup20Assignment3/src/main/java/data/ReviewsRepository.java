package data;

import models.Reviews;
import org.springframework.data.repository.CrudRepository;

public interface ReviewsRepository extends CrudRepository<Reviews, Long> {
}
