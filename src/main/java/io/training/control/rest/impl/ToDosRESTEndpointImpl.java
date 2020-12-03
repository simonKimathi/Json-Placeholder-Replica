package io.training.control.rest.impl;

import io.training.boundary.ToDosService;
import io.training.control.rest.ToDosRESTEndpoint;
import io.training.entity.ToDos;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import java.util.List;

public class ToDosRESTEndpointImpl implements ToDosRESTEndpoint {
    @EJB
    private ToDosService toDosService;

    @Override
    public Response retrieveToDos(long id) {
        ToDos toDos = toDosService.find(id);
        if(toDos==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(toDos).build();
    }

    @Override
    public Response getAllToDoss() {

        List<ToDos> commentList = toDosService.findAll();
        if(commentList.size()>0){
            return Response.ok().entity(commentList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getToDosByUserId(long id) {
        List<ToDos> toDosList = toDosService.getToDosByUserId(id);
        if(toDosList.size()>0){
            return Response.ok().entity(toDosList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getToDosByTitle(String title) {
        List<ToDos> toDosList = toDosService.getToDosByTitle(title);
        if(toDosList.size()>0){
            return Response.ok().entity(toDosList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response createToDos(ToDos toDos) {
        ToDos createdTodos = toDosService.create(toDos);
        if(createdTodos==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(createdTodos).build();
    }

    @Override
    public Response editToDos(long id, ToDos toDos) {
        if(id != toDos.getId()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        ToDos FindTodos= toDosService.find(id);
        if(FindTodos == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ToDos editedTodos = toDosService.edit(toDos);
        return Response.ok().entity(toDosService).build();
    }

    @Override
    public Response deleteToDos(long id) {
        ToDos deletedTodos = toDosService.find(id);
        if(deletedTodos==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(toDosService.remove(deletedTodos)).build();
    }
}

