package cc.lgiki.todolist.controller;

import cc.lgiki.todolist.entity.Todo;
import cc.lgiki.todolist.service.TodoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @RequestMapping("/")
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"getAll/{page}", "getAll"}, method = RequestMethod.GET)
    public String getTodo(@PathVariable(required = false) Integer page) {
        if(page == null) {
            page = 1;
        }
        List<Todo> result = todoService.getAll(page);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            String json = "error";
            try {
                json = mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return json;
        } else {
            return "error";
        }
    }
}
