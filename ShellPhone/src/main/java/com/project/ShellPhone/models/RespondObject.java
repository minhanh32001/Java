package com.project.ShellPhone.models;

import com.project.ShellPhone.models.DTO.CommentDTO;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public class RespondObject {
    private String status;
    private String message;
    private Object data;
    private List<CommentDTO> comments;

    public RespondObject() {
    }

    public RespondObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RespondObject(String status, String message, Object data, List<CommentDTO> comments) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.comments = comments;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
