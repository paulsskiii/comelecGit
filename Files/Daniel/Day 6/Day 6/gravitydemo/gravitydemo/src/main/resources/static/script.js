const API_URL = '/todos';

async function fetchTodos() {
    const response = await fetch(API_URL);
    const todos = await response.json();
    renderTodos(todos);
}

function renderTodos(todos) {
    const list = document.getElementById('todo-list');
    list.innerHTML = '';
    todos.forEach(todo => {
        const li = document.createElement('li');
        li.className = todo.completed ? 'completed' : '';
        
        const textSpan = document.createElement('span');
        textSpan.className = 'todo-text';
        textSpan.textContent = todo.title;
        textSpan.onclick = () => toggleTodo(todo);

        const deleteBtn = document.createElement('button');
        deleteBtn.className = 'delete-btn';
        deleteBtn.innerHTML = '&times;';
        deleteBtn.onclick = (e) => {
            e.stopPropagation();
            deleteTodo(todo.id);
        };

        li.appendChild(textSpan);
        li.appendChild(deleteBtn);
        list.appendChild(li);
    });
}

async function addTodo() {
    const input = document.getElementById('todo-input');
    const title = input.value.trim();
    if (!title) return;

    await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, completed: false })
    });
    
    input.value = '';
    fetchTodos();
}

async function toggleTodo(todo) {
    await fetch(`${API_URL}/${todo.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ ...todo, completed: !todo.completed })
    });
    fetchTodos();
}

async function deleteTodo(id) {
    await fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    });
    fetchTodos();
}

// Allow Enter key to add todo
document.getElementById('todo-input').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        addTodo();
    }
});

// Initial load
fetchTodos();
