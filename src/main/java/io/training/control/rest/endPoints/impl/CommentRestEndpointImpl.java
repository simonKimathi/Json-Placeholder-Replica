package io.training.control.rest.endPoints.impl;

import io.training.boundary.CommentService;
import io.training.control.rest.endPoints.CommentRestEndpoint;
import io.training.entity.Comment;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Response createComment(Comment comment, UriInfo uriInfo) {
        if (CommentService.existsById(comment.getId())) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        Comment savedComment = CommentService.save(comment);
        URI location = uriInfo.getBaseUriBuilder()
                .path(CommentService.class)
                .path(String.valueOf(savedComment.getId()))
                .build();
        return Response.created(location).entity(savedComment).build();
    }

    @Override
    public Response getCommentById(long id) {
        Optional<Comment> optionalComment = CommentService.findById(id);
        return optionalComment.isPresent() ?
                Response.ok().entity(optionalComment.get()).build()
                :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getAllComments(long postId, String nameOrEmail) {
        List<Comment> commentList = CommentService.findAll();
        if(nameOrEmail != null){
            return Response
                    .ok()
                    .entity(CommentService.getCommentByNameOrEmail(nameOrEmail))
                    .build();
        }if(postId != 0.0f){
            return Response
                    .ok()
                    .entity(commentList
                    .stream()
                    .filter((comment) -> comment.getPost().getId().equals(postId))
                            .collect(Collectors.toList()))
                    .build();
        }
        if(commentList.size()>0){
            return Response.ok().entity(commentList).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Override
    public Response editComment(long id, @Valid Comment comment, UriInfo uriInfo) {
        if (!CommentService.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Comment updateComment = CommentService.update(comment);
        URI location = uriInfo.getBaseUriBuilder()
                .path(CommentService.class)
                .path(String.valueOf(updateComment.getId()))
                .build();
        return Response.created(location).entity(updateComment).build();
    }

    @Override
    public Response deleteComment(long id) {
        if (!CommentService.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        CommentService.deleteById(id);
        return Response.ok().build();
    }

}
