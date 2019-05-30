package cc.lgiki.todolist.mapper;

import cc.lgiki.todolist.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoMapper {
    List<Todo> getAll(int page);
}
