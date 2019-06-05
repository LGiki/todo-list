package cc.lgiki.todolist.mapper;

import cc.lgiki.todolist.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoMapper {
    Todo get(int id);

    List<Todo> getAll();

    int insert(Todo todo);

    int update(Todo todo);

    int delete(int id);
}
