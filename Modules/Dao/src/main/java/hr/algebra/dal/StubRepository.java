/*
 * Project setup note:
 * Placeholder for final project. The final implementation will be completed after choosing the RSS source, domain entities, and database schema.
 */

package hr.algebra.dal;

import java.util.List;
import java.util.Optional;

/**
 * Setup-phase placeholder. Final {@code SqlRepository} (Exercise 07-08) will use
 * {@code CallableStatement} and {@code { CALL procedureName (?) }} only — no ad-hoc SQL in Java.
 */
public final class StubRepository implements Repository<Object, Integer> {

    private static final String NOT_READY = "Repository not configured yet. Define DB schema and SqlRepository implementation.";

    @Override
    public Integer create(Object entity) throws Exception {
        throw new UnsupportedOperationException(NOT_READY);
    }

    @Override
    public void update(Integer id, Object entity) throws Exception {
        throw new UnsupportedOperationException(NOT_READY);
    }

    @Override
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException(NOT_READY);
    }

    @Override
    public Optional<Object> select(Integer id) throws Exception {
        throw new UnsupportedOperationException(NOT_READY);
    }

    @Override
    public List<Object> selectAll() throws Exception {
        throw new UnsupportedOperationException(NOT_READY);
    }
}
