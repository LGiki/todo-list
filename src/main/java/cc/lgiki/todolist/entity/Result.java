package cc.lgiki.todolist.entity;

public class Result {
    private Integer code;
    private String message;
    private Todo todo;

    public Result() {

    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Todo todo) {
        this.code = code;
        this.message = message;
        this.todo = todo;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}
