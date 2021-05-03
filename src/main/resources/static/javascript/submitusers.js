let count = 1;

function getField(row, name)
{
    let field = row.getElementsByClassName(name)[0];

    return field;
}

function submitUsers()
{
    let users = [];

    let rows = document.getElementById("userList").childNodes;

    for(let i = 0; i < rows.length; i++)
    {
        let fname = getField(rows[i], "fname").value;
        let lname = getField(rows[i], "lname").value;
        let email = getField(rows[i], "email").value;
        let pass = getField(rows[i], "pass").value;
        let roleId = getField(rows[i], "role").value;

        if(fname == "" || lname == "" || email == "" | pass == "" | roleId == "")
        {
            console.log("missing values in row" + i);
            return;
        }

        users.push(
            {
                firstName: fname,
                lastName: lname,
                email: email,
                password: pass,
                role_id: roleId
            }
        );
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
    document.getElementById("addButton").remove();

    let html = '<tr><td><input type="text"  id="fname" ></td>' +
             '<td><input type="text"  class="lname" ></td>' +
             '<td><input type="text"  class="email" ></td>' +
             '<td><input type="text"  class="pass" ></td>' +
             '<td>' +
                 '<select class="role" value="1">' +
                     '<option value="1">Student</option>' +
                     '<option value="2">Teacher</option>' +
                 '</select>' +
             '</td>' +
             '<td><button class="clickable" onclick="addRow()">+</button></td></tr>' +
             '<td><button id="addButton" class="clickable" onclick="addRow()">+</button></td></tr>';

    document.getElementById("userList").insertAdjacentHTML("beforeend", html);
}