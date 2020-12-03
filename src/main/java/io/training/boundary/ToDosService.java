package io.training.boundary;

import io.training.entity.ToDos;

import java.util.List;

public interface ToDosService extends AbstractBean<ToDos,Long> {

    List<ToDos> getToDosByUserId(long id);
    List<ToDos> getToDosByTitle(String title);
}
