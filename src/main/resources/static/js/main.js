var app = new Vue({
    el: '#container',
    data: {
        todo: {
            'pageNum': 1,
        },
    },
    methods: {
        removeTodo: function (index) {
            let todo = this.todo.list[index];
            axios.delete('todo/' + todo.id)
                .then((response) => {
                    let removeResult = response.data;
                    if (removeResult.code === 200) {
                        this.todo.list.splice(index, 1);
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
            let todo = this.todo.list[todoIndex];
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
                        this.todo.list[todoIndex] = updateResult.todo;
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
            $('#todo-content-edit').val(this.todo.list[index].content);
            $('#myModalLabel').text('Edit todo');
            $('#todo-index').val(index);
            $('#myModal').modal('toggle');
        },
        completeTodo: function (index) {
            let todo = this.todo.list[index];
            todo.complete = true;
            axios.put('todo/' + todo.id, todo)
                .then((response) => {
                    let completeTodoResult = response.data;
                    if (completeTodoResult.code === 200) {
                        this.todo.list[todoIndex] = completeTodoResult.todo;
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
                        this.todo.list.unshift(addResult.todo);
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
                    this.todo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    },
    mounted() {
        this.switchPage(this.todo.pageNum);
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