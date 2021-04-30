let count = 1;

function submitUsers()
{
    let users = [];

    for(let i = 1; i <= count; i++)
    {
        users.push(
            {
                firstName: document.getElementById("fname" + i).value,
                lastName: document.getElementById("lname" + i).value,
                email: document.getElementById("email" + i).value,
                password: document.getElementById("pass" + i).value,
                role_id: document.getElementById("role" + i).value
            }
        )
    }

    let request = new XMLHttpRequest();
    request.open("POST", "/users/submit");
    request.onreadystatechange = function()
    {
        if(request.readyState == 4 && request.status >= 200 && request.status < 400)
        {
            window.location ="/users/add/success";
        }
    }
    request.setRequestHeader("Content-Type", "application/json;charset=UTF8");
    request.send(JSON.stringify(users));
}

function addRow()
{
    count++;
    let addButton = document.getElementById("addButton").remove();

    let html = '<tr><td><input type="text"  id="fname' + count + '" ></td>' +
             '<td><input type="text"  id="lname' + count + '" ></td>' +
             '<td><input type="text"  id="email' + count + '" ></td>' +
             '<td><input type="text"  id="pass' + count + '" ></td>' +
             '<td>' +
                 '<select id="role' + count + '" value="1">' +
                     '<option value="1">Student</option>' +
                     '<option value="2">Teacher</option>' +
                 '</select>' +
             '</td>' +
             '<td><button id="addButton" class="clickable" onclick="addRow()">+</button></td></tr>';

    document.getElementById("userList").insertAdjacentHTML("beforeend", html);
}