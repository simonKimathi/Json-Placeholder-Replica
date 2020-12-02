package io.training.control.rest.impl;

import io.training.boundary.CommentService;
import io.training.boundary.PostService;
import io.training.control.rest.CommentRestEndpoint;
import io.training.entity.Comment;
import io.training.entity.Post;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

@Stateless
public class CommentRestEndpointImpl implements CommentRestEndpoint {
    @EJB
    private CommentService CommentService;

    @Override
    public Response retrieveComment(int id) {
        Comment comment = CommentService.find(id);
        if(comment==null){
            Error error = new Error();
            ErrorInfo errorInfo =  new ErrorInfo();
            errorInfo.setBriefSummary("User not found");
            errorInfo.setStatusCode(400);
            errorInfo.setDescriptionURL("http://localhost:8080/TrainingApp/swagger-ui/#/Comment/retrieveComment");
            error.setErrorInfo(errorInfo);
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
        return Response.ok().entity(comment).build();
    }

    @Override
    public Response createComment(Comment comment) {
        Comment createdComment = CommentService.create(comment);
        if (createdComment==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(createdComment).build();
    }
}
