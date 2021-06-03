let url = null;
let Id = null;
let Page = null;
let title = null;
let type = null;

$(document).ready(function () {
    Page = parent.$('body')[0].id;
    // 非管理员访问时，隐藏部分页面元素
    if (Page === "userMain") {
        $("#adminTr")[0].remove();
        $("#addIcon")[0].remove();
        $("#delIcon")[0].remove();
        $("#resetIcon")[0].remove();
        // 获取父页存储的用户ID，随后更改数据的请求地址
        Id = parent.$('#userId')[0].innerText;
        title = "个人信息";
    } else if (Page === "adminMain") {
        Id = parent.$('#adminId')[0].innerText;
        title = "老师信息";
    }
    document.getElementById("dg").title = getTitle();
    $("#dg").datagrid("options").url = getUrl();
});

function getTitle() {
    return title;
}

function getUrl() {
    if (Page === "userMain") {
        url = "getUsers?type=" + Page + "&userId=" + Id;
    } else if (Page === "adminMain") {
        url = "getUsers?type=" + Page;
    }
    return url;
}

function saveUser() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($(this).form("validate")===false){
                return false;
            }
            if ($('#userSex').combobox('getValue') === "") {
                $.messager.alert("系统提示", "请选择性别");
                return false;
            }
            if (Page === "adminMain") {
                if ($('#collegeId').combobox('getValue') === "") {
                    $.messager.alert("系统提示", "请选择学院");
                    return false;
                }
            }
            if (type==="add"){
                let userName = $('#userName')[0].value;
                if (userName === "") {
                    $.messager.alert("系统提示", "用户名不能为空");
                    return false;
                } else {
                    let data = {userName: userName};
                    // ajax去服务器端校验，判断用户名是否存在
                    $.ajax({
                        type: "POST",
                        url: "haveUser",
                        contentType: "application/json; charset=utf-8",
                        dataType: 'json',
                        data: JSON.stringify(data),
                        async: false,
                        success: function (msg) {
                            if (msg === true) {
                                $.messager.alert("系统提示", "用户名已存在");
                                return false;
                            }
                        }
                    });
                }
            }
            return true;
        },
        success: function (res) {
            // res只会返回字符串的true或false，不是布尔类型的true和false
            // 这里的eval函数，是把字符串"true"和"false"解析成布尔类型的true和false
            let result = null;
            if (res === "true" || res === "false") {
                result = eval(res);
            }
            if (result === true) {
                // 由用户发起的修改姓名成功后，更新父页右上角的用户真实姓名
                if (Page === "userMain") {
                    parent.$('#userTrueName')[0].innerText = $('#userTrueName')[0].value;
                }
                $.messager.alert("系统提示", "保存成功");
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
                $("#dg").datagrid("clearChecked");
            } else {
                $.messager.alert("系统提示", "保存失败");
            }
        }
    });
}

function resetValue() {
    $("#fm").form("reset");
    $('#collegeId').combobox('select', "");
}

function closeUserDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

function openUserModifyDialog() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length !== 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    let row = selectedRows[0];
    $("#userName")[0].disabled = true;
    $("#dlg").dialog("open").dialog("setTitle", "编辑" + getTitle());
    $("#fm").form("load", row);
    url = "updateUserInfo?type=" + Page + "&userId=" + row.userId;
    type = "update";
}

function openUserAddDialog() {
    $("#fm").form("reset");
    $("#userName")[0].disabled = false;
    $("#dlg").dialog("open").dialog("setTitle", "添加" + getTitle());
    url = "addUser";
    type = "add";
}

function resetPassword() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length === 0) {
        $.messager.alert("系统提示", "请选择要重置密码的用户！");
    } else {
        let strIds = [];
        for (let i = 0; i < selectedRows.length; i++) {
            strIds.push(selectedRows[i].userId);
        }
        let ids = strIds.join(",");
        $.messager.confirm("系统提示", "您确认要重置这<span style='color:red;'>" + selectedRows.length + "</span>个用户的密码吗？", function (r) {
            if (r) {
                $.post("resetUserPassword", {ids: ids}, function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "您已成功重置<span style='color:red;'>" + result.num + "</font>个用户的密码！");
                        $("#dg").datagrid("reload");
                        $("#dg").datagrid("clearChecked");
                    }
                }, "json");
            }
        });
    }
}

function deleteUser() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length === 0) {
        $.messager.alert("系统提示", "请选择要删除的用户！");
    } else {
        let strIds = [];
        for (let i = 0; i < selectedRows.length; i++) {
            strIds.push(selectedRows[i].userId);
        }
        let ids = strIds.join(",");
        $.messager.confirm("系统提示", "您确认要删除这<span style='color:red;'>" + selectedRows.length + "</span>个用户吗？", function (r) {
            if (r) {
                $.post("deleteUser", {ids: ids}, function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "您已成功删除<span style='color:red;'>" + result.num + "</font>个用户！");
                        $("#dg").datagrid("reload");
                        $("#dg").datagrid("clearChecked");
                    }
                }, "json");
            }
        });
    }
}
