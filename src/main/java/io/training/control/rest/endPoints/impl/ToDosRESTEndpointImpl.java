package io.training.control.rest.endPoints.impl;

import io.training.boundary.ToDosService;
import io.training.control.rest.endPoints.ToDosRESTEndpoint;
import io.training.entity.Todo;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import java.util.List;

public class ToDosRESTEndpointImpl implements ToDosRESTEndpoint {
    @EJB
    private ToDosService toDosService;

    @Override
    public Response retrieveToDos(long id) {
        Todo toDo = toDosService.find(id);
        if(toDo ==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(toDo).build();
    }

    @Override
    public Response getAllToDoss(int start, int limit) {

        List<Todo> commentList = toDosService.findRange(new int[]{start,limit});;
        if(commentList.size()>0){
            return Response.ok().entity(commentList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @Override
    public Response createToDos(Todo toDo) {
        Todo createdTodos = toDosService.save(toDo);
        if(createdTodos==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(createdTodos).build();
    }

    @Override
    public Response editToDos(long id, Todo toDo) {
        if(id != toDo.getId()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Todo findTodos = toDosService.find(id);
        if(findTodos == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Todo editedTodos = toDosService.update(toDo);
        return Response.ok().entity(toDosService).build();
    }

    @Override
    public Response deleteToDos(long id) {
        Todo deletedTodos = toDosService.find(id);
        if(deletedTodos==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(toDosService.delete(deletedTodos)).build();
    }
}

