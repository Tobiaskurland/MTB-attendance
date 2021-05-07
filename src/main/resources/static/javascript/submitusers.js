let count = 1;

function getField(row, index)
{
    let field = row.children[index].children[0];
    return field;
}

function deleteRow(event)
{
    let button = event.target;
    button.parentElement.parentElement.remove();
}

function submitUsers()
{
    let users = [];

    let rows = document.getElementById("userList").children;

    for(let i = 0; i < rows.length; i++)
    {
        console.log(rows);
        let fname = getField(rows[i], 0).value;
        let lname = getField(rows[i], 1).value;
        let email = getField(rows[i], 2).value;
        let pass = getField(rows[i], 3).value;
        let roleId = getField(rows[i], 4).value;

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
             '<td><button class="clickable" onclick="deleteRow(event)">X</button></td></tr>';

    document.getElementById("userList").insertAdjacentHTML("beforeend", html);
}