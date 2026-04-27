package dev.shermende.lib.dal.service;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @param <E> entity
 * @param <I> primary key
 * @param <Q> query-dsl
 */
public abstract class AbstractCrudService<E, I, Q extends EntityPath<?>> implements CrudService<E, I> {

    private final QueryDslRepository<E, I, Q> queryDslRepository;

    public AbstractCrudService(
            QueryDslRepository<E, I, Q> queryDslRepository
    ) {
        this.queryDslRepository = queryDslRepository;
    }


    @Override
    public E save(E entity) {
        return queryDslRepository.save(entity);
    }

    @Override
    public boolean exists(Predicate predicate) {
        return queryDslRepository.exists(predicate);
    }

    @Override
    public boolean existsById(I p) {
        return queryDslRepository.existsById(p);
    }

    @Override
    public boolean notExistsById(I p) {
        return !existsById(p);
    }

    @Override
    public Optional<E> findOne(Predicate predicate) {
        return queryDslRepository.findOne(predicate);
    }

    @Override
    public Optional<E> findById(I id) {
        return queryDslRepository.findById(id);
    }

    @Override
    public E getById(I id) {
        return queryDslRepository.getOne(id);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return queryDslRepository.findAll(pageable);
    }

    @Override
    public Page<E> findAll(Predicate predicate, Pageable pageable) {
        return queryDslRepository.findAll(Optional.ofNullable(predicate)
                .orElseGet(() -> Expressions.asBoolean(true).isTrue()), pageable);
    }

    @Override
    public List<E> findAll(Predicate predicate) {
        return StreamSupport.stream(queryDslRepository.findAll(predicate).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(E entity) {
        queryDslRepository.delete(entity);
    }

    @Override
    public long count(Predicate predicate) {
        return queryDslRepository.count(predicate);
    }
}
