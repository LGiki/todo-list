package cc.lgiki.todolist.entity;

public class Todo {
    private Integer id;
    private String content;
    private Boolean isComplete;

    public Todo() {

    }

    public Todo(Integer id, String content, Boolean isComplete) {
        this.id = id;
        this.content = content;
        this.isComplete = isComplete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
