package cc.lgiki.todolist.controller;

import cc.lgiki.todolist.entity.Result;
import cc.lgiki.todolist.entity.Todo;
import cc.lgiki.todolist.service.TodoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class TodoController {
    @Autowired
    private TodoService todoService;
    final private int pageNum = 6;

    @Autowired
    @RequestMapping("/")
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/todo"}, method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Todo> getTodo() {
        PageHelper.startPage(1, pageNum);
        return new PageInfo<>(todoService.getAll());
    }

    @RequestMapping(value = {"/todo/{page}"}, method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Todo> getTodo(@PathVariable(value = "page") Integer page) {
        PageHelper.startPage(page, pageNum);
        return new PageInfo<>(todoService.getAll());
    }

    @RequestMapping(value = {"/todo"}, method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addTodo(@RequestBody Todo todo) {
        int insertResult = todoService.insert(todo);
        Result result;
        if (insertResult == 1) {
            result = new Result(200, "OK", todo);
        } else {
            result = new Result(400, "Insert error!");
        }
        return result;
    }

    @RequestMapping(value = {"/todo/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteTodo(@PathVariable int id) {
        int deleteResult = todoService.delete(id);
        Result result;
        if (deleteResult == 1) {
            result = new Result(200, "OK");
        } else {
            result = new Result(400, "Delete error!");
        }
        return result;
    }

    @RequestMapping(value = {"/todo/{id}"}, method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Result updateTodo(@PathVariable int id, @RequestBody Todo todo) {
        todo.setId(id);
        int updateResult = todoService.update(todo);
        Result result;
        if (updateResult == 1) {
            result = new Result(200, "OK", todo);
        } else {
            result = new Result(400, "Update error!");
        }
        return result;
    }

}
