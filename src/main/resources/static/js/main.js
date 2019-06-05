var app = new Vue({
    el: '#container',
    data: {
        todos: [],
        pages: {
            'pageNum': 1,
        },
    },
    methods: {
        removeTodo: function (index) {
            let todo = this.todos[index];
            axios.delete('todo/' + todo.id)
                .then((response) => {
                    let removeResult = response.data;
                    if (removeResult.code === 200) {
                        this.todos.splice(index, 1);
                    } else {
                        alert(removeResult.message);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        updateTodo: function () {
            let todoIndex = $('#todo-index').val();
            let todo = this.todos[todoIndex];
            let newTodoContent = $('#todo-content-edit').val();
            if (newTodoContent == "") {
                alert("Todo content can not be empty!");
                return;
            }
            todo.content = newTodoContent;
            axios.put('todo/' + todo.id, todo)
                .then((response) => {
                    let updateResult = response.data;
                    if (updateResult.code === 200) {
                        this.todos[todoIndex] = updateResult.todo;
                    } else {
                        alert(updateResult.message);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
            $('#myModal').modal('toggle');
        },
        openTodoEditModal: function (index) {
            $('#todo-content-edit').val(this.todos[index].content);
            $('#myModalLabel').text('Edit todo');
            $('#todo-index').val(index);
            $('#myModal').modal('toggle');
        },
        completeTodo: function (index) {
            let todo = this.todos[index];
            todo.complete = true;
            axios.put('todo/' + todo.id, todo)
                .then((response) => {
                    let completeTodoResult = response.data;
                    if (completeTodoResult.code === 200) {
                        this.todos[todoIndex] = completeTodoResult.todo;
                    } else {
                        alert(completeTodoResult.message);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        addTodo: function () {
            let todoContent = $('#todo-content-input').val();
            if (todoContent === "") {
                alert("Please enter todo content!");
                return;
            }
            let todo = {
                'id': 0,
                'content': todoContent,
                'complete': false
            };
            axios.post('todo/', todo)
                .then((response) => {
                    let addResult = response.data;
                    if (addResult.code === 200) {
                        this.todos.unshift(addResult.todo);
                    } else {
                        alert(addResult.message);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        switchPage: function (page) {
            axios.get('todo/' + page)
                .then((response) => {
                    let responseData = response.data;
                    this.todos = responseData.list;
                    delete responseData.list;
                    this.pages = responseData;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    },
    mounted() {
        this.switchPage(this.pages.pageNum);
    }
});
$(function () {
    'use strict';
    axios.get('https://v1.hitokoto.cn')
        .then((response) => $('#hitokoto').text(response.data.hitokoto))
        .catch(function (error) {
            console.log(error);
        });
});