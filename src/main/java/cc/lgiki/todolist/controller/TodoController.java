package cc.lgiki.todolist.controller;

import cc.lgiki.todolist.entity.Todo;
import cc.lgiki.todolist.service.TodoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class TodoController {
    @Autowired
    private TodoService todoService;
    final private int pageNum = 5;

    @Autowired
    @RequestMapping("/")
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/todo"}, method = RequestMethod.GET)
    public List<Todo> getTodo() {
        PageHelper.startPage(1, pageNum);
        return todoService.getAll();
    }

    @RequestMapping(value = {"/todo/{page}"}, method = RequestMethod.GET)
    public List<Todo> getTodo(@PathVariable(value = "page") Integer page) {
        PageHelper.startPage(page, pageNum);
        return todoService.getAll();
    }

    @RequestMapping(value = {"/todo"}, method = RequestMethod.POST, produces = "application/json")
    public int addTodo(@RequestBody Todo todo) {
        return todoService.insert(todo);
    }

    @RequestMapping(value = {"/todo/{id}"}, method = RequestMethod.DELETE)
    public int deleteTodo(@PathVariable int id) {
        return todoService.delete(id);
    }

    @RequestMapping(value = {"/todo/{id}"}, method = RequestMethod.PUT, produces = "application/json")
    public int updateTodo(@PathVariable int id, @RequestBody Todo todo) {
        todo.setId(id);
        return todoService.update(todo);
    }

}
