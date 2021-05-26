let oldPassword = null, newPassword = null, data = null;
let element_oldPassword = null, element_newPassword = null, element_againPassword = null;

window.onload = function () {
    element_oldPassword = $("#oldPassword");
    element_newPassword = $("#newPassword");
    element_againPassword = $("#againPassword");
    element_againPassword.bind("keyup", function (e) {
        //event.preventDefault() 方法阻止元素发生默认的行为。
        if (e.key === "Enter") {
            e.preventDefault();
            //按下回车后需要执行的函数
            changePw();
        }
    });
}

function check() {
    let val = element_oldPassword[0].value;
    oldPassword = val;
    if (val === "" || val === undefined || val == null) {
        alert("请输入原密码");
        element_oldPassword[0].focus();
        return false;
    }
    val = element_newPassword[0].value;
    newPassword = val;
    if (val === "" || val === undefined || val == null) {
        alert("请输入新密码");
        element_newPassword[0].focus();
        return false;
    }
    val = element_againPassword[0].value;
    if (val === "" || val === undefined || val == null) {
        alert("请再次输入新密码");
        element_againPassword[0].focus();
        return false;
    }
    if (val !== newPassword) {
        alert("两次输入的密码不相同");
        element_againPassword[0].focus();
        return false;
    }
    return true;
}

function changePw() {
    if (check()) {
        //ajax去服务器端校验
        data = {oldPassword: oldPassword, newPassword: newPassword};
        $.ajax({
            type: "POST",
            url: "adminChangePw",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: false,
            success: function (msg) {
                if (msg === true) {
                    //账号密码正确
                    alert("修改成功");
                } else {
                    alert("原密码不正确");
                }
            }
        });
    }
}