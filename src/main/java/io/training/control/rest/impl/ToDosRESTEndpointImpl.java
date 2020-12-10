package io.training.control.rest.impl;

import io.training.boundary.ToDosService;
import io.training.control.rest.ToDosRESTEndpoint;
import io.training.entity.ToDo;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import java.util.List;

public class ToDosRESTEndpointImpl implements ToDosRESTEndpoint {
    @EJB
    private ToDosService toDosService;

    @Override
    public Response retrieveToDos(long id) {
        ToDo toDo = toDosService.find(id);
        if(toDo ==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(toDo).build();
    }

    @Override
    public Response getAllToDoss() {

        List<ToDo> commentList = toDosService.findAll();
        if(commentList.size()>0){
            return Response.ok().entity(commentList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getToDosByUserId(long id) {
        List<ToDo> toDoList = toDosService.getToDosByUserId(id);
        if(toDoList.size()>0){
            return Response.ok().entity(toDoList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getToDosByTitle(String title) {
        List<ToDo> toDoList = toDosService.getToDosByTitle(title);
        if(toDoList.size()>0){
            return Response.ok().entity(toDoList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response createToDos(ToDo toDo) {
        ToDo createdTodos = toDosService.save(toDo);
        if(createdTodos==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(createdTodos).build();
    }

    @Override
    public Response editToDos(long id, ToDo toDo) {
        if(id != toDo.getId()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        ToDo findTodos = toDosService.find(id);
        if(findTodos == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ToDo editedTodos = toDosService.update(toDo);
        return Response.ok().entity(toDosService).build();
    }

    @Override
    public Response deleteToDos(long id) {
        ToDo deletedTodos = toDosService.find(id);
        if(deletedTodos==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(toDosService.delete(deletedTodos)).build();
    }
}

