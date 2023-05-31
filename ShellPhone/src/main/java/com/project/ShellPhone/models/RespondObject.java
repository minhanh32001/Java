package com.project.ShellPhone.models;

import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public class RespondObject {
    private String status;
    private String message;
    private Object data;
    private List<Comment> comments;
    private List<Type> types;

    public RespondObject() {
    }

    public RespondObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RespondObject(String status, String message, Object data, List<Comment> comments) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.comments = comments;
    }

    public List<Comment> getList() {
        return comments;
    }

    public void setList(List<Comment> comments) {
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

    public List<Type> getTypes() {
        return types;
    }

    public void setType(List<Type> types) {
        this.types = types;
    }
}
