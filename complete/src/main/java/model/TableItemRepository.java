package model;

import org.springframework.data.repository.CrudRepository;

// This will be auto implemented by Spring into a Bean called userRepository

public interface TableItemRepository extends CrudRepository<TableItem, Long> {

}
