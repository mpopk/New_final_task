package com.kodilla.Task_final.repository;


import com.kodilla.Task_final.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    @Override
    Optional<Task> findById(Long id);

    @Override
    boolean existsById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();

    @Override
    long count();
}
