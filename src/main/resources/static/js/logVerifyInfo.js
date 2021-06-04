let url = null;
let Page = null;
let Id = null;

$(document).ready(function () {
    Page = parent.$('body')[0].id;
    if (Page === "userMain") {
        // 获取父页存储的用户ID，随后更改数据的请求地址
        Id = parent.$('#userId')[0].innerText;
    } else if (Page === "adminMain") {
        // 管理员访问时，显示部分页面元素
        Id = parent.$('#adminId')[0].innerText;
        $('#dg').datagrid('showColumn', 'adminName');
    }
    $("#dg").datagrid("options").url = getUrl();
});

function getUrl() {
    if (Page === "userMain") {
        url = "getLogVerify?type=" + Page + "&userId=" + Id;
    } else if (Page === "adminMain") {
        url = "getLogVerify?type=" + Page;
    }
    return url;
}

function searchLogVerify() {
    $('#dg').datagrid('load', {
        typeId: $('#s_typeId').combobox("getValue")
    });
}

function formatResStatus(resStatus) {
    if (resStatus === 0) {
        return "申请";
    } else if (resStatus === 1) {
        return "通过";
    } else if (resStatus === 2) {
        return "拒绝";
    }
}