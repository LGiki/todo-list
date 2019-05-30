package cc.lgiki.todolist.service;

import cc.lgiki.todolist.entity.Todo;
import cc.lgiki.todolist.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoMapper todoMapper;
    public List<Todo> getAll(int page) {
        return todoMapper.getAll(page);
    }
}
