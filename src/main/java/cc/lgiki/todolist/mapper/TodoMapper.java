package cc.lgiki.todolist.mapper;

import cc.lgiki.todolist.entity.Todo;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoMapper {
    List<Todo> getAll();

    int insert(Todo todo);

    int update(Todo todo);

    int delete(int id);
}
