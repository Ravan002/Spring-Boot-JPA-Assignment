/**
 * Interface defining basic CRUD operations for service classes.
 * @param <T> The type of entity being managed.
 */
package az.edu.ada.wm2.assignment1.service;

import java.util.List;

public interface BaseService<T> {
    /**
     * Retrieves a list of all entities.
     * @return A list of all entities.
     */
    List<T> list();

    /**
     * Deletes an entity by its ID.
     * @param id The ID of the entity to be deleted.
     */
    void deleteById(Long id);

    /**
     * Retrieves an entity by its ID.
     * @param id The ID of the entity to be retrieved.
     * @return The entity with the specified ID.
     */
    T getById(Long id);

    /**
     * Saves or updates an entity.
     * @param entity The entity object to be saved or updated.
     */
    void save(T entity);
}