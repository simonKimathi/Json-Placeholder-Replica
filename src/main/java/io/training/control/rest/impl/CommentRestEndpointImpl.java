package io.training.control.rest.impl;

import io.training.boundary.CommentService;
import io.training.boundary.PostService;
import io.training.control.rest.CommentRestEndpoint;
import io.training.entity.Comment;
import io.training.entity.Post;
import io.training.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class CommentRestEndpointImpl implements CommentRestEndpoint {
    @EJB
    private CommentService CommentService;

    /*@Override
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
    }*/

    @Override
    public Response createComment(Comment comment) {
        Comment createdComment = CommentService.create(comment);
        if(createdComment==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(createdComment).build();
    }

    @Override
    public Response getCommentById(int id) {
        Comment comment = CommentService.find(id);
        if(comment==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(comment).build();
    }

    @Override
    public Response getAllComments() {
        List<Comment> commentList = CommentService.findAll();
        if(commentList.size()>0){
            return Response.ok().entity(commentList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getCommentByNameOrEmail(String nameOrEmail) {
        List<Comment> commentList = CommentService.getCommentByNameOrEmail(nameOrEmail);
        if(commentList.size()>0){
            return Response.ok().entity(commentList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getCommentByPostId(int id) {
        List<Comment> commentList = CommentService.getCommentByPostId(id);
        if(commentList.size()>0){
            return Response.ok().entity(commentList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response editComment(int id , Comment comment) {
        if(id == comment.getId()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Comment FindComment= CommentService.find(id);
        if(FindComment == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Comment editedComment = CommentService.edit(comment);
        return Response.ok().entity(editedComment).build();
    }

    @Override
    public Response deleteComment(int id) {
        Comment deletedComment = CommentService.find(id);
        if(deletedComment==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(CommentService.remove(deletedComment)).build();
    }

}
