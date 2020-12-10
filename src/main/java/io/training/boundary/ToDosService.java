package io.training.boundary;

import io.training.entity.Todo;

import java.util.List;

public interface ToDosService extends CrudAbstractBean<Todo,Long> {
    public List<Todo> findAllByUserId(long userId);
}
