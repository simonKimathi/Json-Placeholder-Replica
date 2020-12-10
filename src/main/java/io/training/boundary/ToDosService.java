package io.training.boundary;

import io.training.entity.ToDo;

import java.util.List;

public interface ToDosService extends CrudAbstractBean<ToDo,Long> {

    List<ToDo> getToDosByUserId(long id);
    List<ToDo> getToDosByTitle(String title);
}
