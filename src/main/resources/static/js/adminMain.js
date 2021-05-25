let Id = null;
let Name = null;
let Page = "adminMain";
$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "getLoginStatus",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: Page,
        async: false,
        success: function (msg) {
            // code=0代表已登录，code=1代表未登录
            if (msg != null && msg["code"] === 0) {
                Id = msg["Id"];
                Name = msg["Name"];
                $('#adminName')[0].innerText = Name;
            }
        }
    });
});

function logout() {
    $.ajax({
        type: "POST",
        url: "logout",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: Page,
        async: false,
        success: function (msg) {
            if (msg === 0) {
                window.location.href = "login.html";
            } else {
                alert("退出时发生了错误");
            }
        }
    });
}

function rmCls(){
    $("#dd_rmCls").removeClass("layui-this");
}