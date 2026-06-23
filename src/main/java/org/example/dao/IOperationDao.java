package org.example.dao;

import org.example.entities.Operation;

import java.util.List;

public interface IOperationDao {

    void save(Operation operation);

    Operation findById(Long id);

    List<Operation> findAll();

    void update(Operation operation);

    void delete(Long id);
}
