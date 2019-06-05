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

    public Todo get(int id) {
        return todoMapper.get(id);
    }

    public List<Todo> getAll() {
        return todoMapper.getAll();
    }

    public int insert(Todo todo) {
        return todoMapper.insert(todo);
    }

    public int update(Todo todo) {
        return todoMapper.update(todo);
    }

    public int delete(int id) {
        return todoMapper.delete(id);
    }
}
