/*
 * Project setup note:
 * Based on Exercise 07-08, Repository interface pattern. Generic placeholder until final entities are defined.
 */

package hr.algebra.dal;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository contract. Concrete entity-specific repositories will extend this later.
 */
public interface Repository<T, K> {

    K create(T entity) throws Exception;

    void update(K id, T entity) throws Exception;

    void delete(K id) throws Exception;

    Optional<T> select(K id) throws Exception;

    List<T> selectAll() throws Exception;
}
