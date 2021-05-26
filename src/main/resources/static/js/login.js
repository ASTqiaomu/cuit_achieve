let userName = null, password = null, data = null, loginType = null;
let element_userName = null, element_password = null, element_loginType = null;

window.onload = function () {
    element_userName = $("#userName");
    element_password = $("#password");
    element_loginType = document.getElementsByName("loginType");

    /*光标处于输入密码框，按回车登录 begin*/
    element_password.bind("keyup", function (e) {
        //event.preventDefault() 方法阻止元素发生默认的行为。
        if (e.key === "Enter") {
            e.preventDefault();
            //按下回车后需要执行的函数
            login();
        }
    });
    /*光标处于输入密码框，按回车登录 end*/
}

function check() {
    let val = element_userName[0].value;
    userName = val;
    if (val === "" || val === undefined || val == null) {
        alert("用户名不能为空");
        element_userName[0].focus();
        return false;
    }
    val = element_password[0].value;
    password = val;
    if (val === "" || val === undefined || val == null) {
        alert("密码不能为空");
        element_password[0].focus();
        return false;
    }
    for (let i = 0; i < element_loginType.length; i++) {
        if (element_loginType[i].checked) {
            loginType = element_loginType[i].id;
            break;
        }
    }
    return true;
}

function login() {
    if (check()) {
        //ajax去服务器端校验
        data = {userName: userName, password: password, loginType: loginType};
        $.ajax({
            type: "POST",
            url: "login",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: false,
            success: function (msg) {
                if (msg === true) {
                    //账号密码正确
                    if (loginType === "admin") {
                        window.location.href = "adminMain.html";

                    } else if (loginType === "user") {
                        window.location.href = "userMain.html";
                    }
                } else {
                    alert("用户名或密码错误！");
                }
            }
        });
    }
}