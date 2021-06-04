let url = null;
let uploadUrl = null;
let Id = null;
let Page = null;
let type = null;

$(document).ready(function () {
    Page = parent.$('body')[0].id;
    if (Page === "userMain") {
        // 普通用户访问时，移除部分页面元素
        $("#verifyIcon")[0].remove();
        $("#label_s_userId")[0].remove();
        $("#s_userId").combobox("destroy");
        // 获取父页存储的用户ID，随后更改数据的请求地址
        Id = parent.$('#userId')[0].innerText;
    } else if (Page === "adminMain") {
        // 管理员访问时，移除部分页面元素
        $("#addIcon")[0].remove();
        $("#editIcon")[0].remove();
        $("#delIcon")[0].remove();
        $("#uploadIcon")[0].remove();
        Id = parent.$('#adminId')[0].innerText;
    }
    $("#dg").datagrid("options").url = getUrl();
});

function getUrl() {
    if (Page === "userMain") {
        url = "getResult?type=" + Page + "&userId=" + Id;
    } else if (Page === "adminMain") {
        url = "getResult?type=" + Page;
    }
    return url;
}

function searchResult() {
    if (Page === "userMain") {
        $('#dg').datagrid('load', {
            resName: $('#s_resName').val(),
            typeId: $('#s_typeId').combobox("getValue"),
            resStatus: $('#s_resStatus').combobox("getValue")
        });
    } else if (Page === "adminMain") {
        $('#dg').datagrid('load', {
            resName: $('#s_resName').val(),
            typeId: $('#s_typeId').combobox("getValue"),
            userId: $('#s_userId').combobox("getValue"),
            resStatus: $('#s_resStatus').combobox("getValue")
        });
    }
}

function openResultAddDialog() {
    $("#fm").form("reset");
    $("#dlg").dialog("open").dialog("setTitle", "添加成果信息");
    type = "add";
    url = "addResult?type=" + type + "&userId=" + Id;
}

function saveResult() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($(this).form("validate") === false) {
                return false;
            }
            if ($('#typeId').combobox('getValue') === "") {
                $.messager.alert("系统提示", "请选择成果类型");
                return false;
            }
            return $(this).form("validate");
        },
        success: function (res) {
            // res只会返回字符串的true或false，不是布尔类型的true和false
            // 这里的eval函数，是把字符串"true"和"false"解析成布尔类型的true和false
            let result = null;
            if (res === "true" || res === "false") {
                result = eval(res);
            }
            if (result === true) {
                $.messager.alert("系统提示", "保存成功");
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
                $("#dg").datagrid("clearChecked");
                resetValue();
            } else {
                $.messager.alert("系统提示", "保存失败");
            }
        }
    });
}

function resetValue() {
    $("#fm").form("reset");
    $('#typeId').combobox('select', "");
}

function closeResultDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

function openResultModifyDialog() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length !== 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    let row = selectedRows[0];
    if (row.resStatus === 1) {
        $.messager.alert("系统提示", "审核已经通过！");
        return;
    }
    $("#dlg").dialog("open").dialog("setTitle", "编辑成果信息");
    $("#fm").form("load", row);
    type = "update";
    url = "addResult?type=" + type + "&userId=" + Id + "&resId=" + row.resId;
}

function deleteResult() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length === 0) {
        $.messager.alert("系统提示", "请选择要删除的数据！");
        return;
    }
    let strIds = [];
    for (let i = 0; i < selectedRows.length; i++) {
        strIds.push(selectedRows[i].resId);
    }
    let ids = strIds.join(",");
    $.messager.confirm("系统提示", "您确认要删除这<span style='color:red;'>" + selectedRows.length + "</span>条数据吗？", function (r) {
        if (r) {
            $.post("deleteResult", {ids: ids}, function (result) {
                if (result.success === "true") {
                    $.messager.alert("系统提示", "您已成功删除<span style='color:red;'>" + resultInfo.num + "</font>条数据！");
                    $("#dg").datagrid("reload");
                    $("#dg").datagrid("clearChecked");
                } else {
                    $.messager.alert("系统提示", "删除失败");
                }
            }, "json");
        }
    });
}

function uploadResult() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length !== 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    let row = selectedRows[0];
    if (row.resStatus === 1) {
        $.messager.alert("系统提示", "审核已经通过！");
        return;
    }
    $("#upload").dialog("open").dialog("setTitle", "上传成果文件");
    $("#uploadFm").form("load", row);
    uploadUrl = "uploadResFile?userId=" + Id + "&resId=" + row.resId;
}

function closeUploadResult() {
    $("#upload").dialog("close");
}

function saveUploadResult() {
    $("#uploadFm").form("submit", {
        url: uploadUrl,
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (res) {
            // res只会返回字符串的true或false，不是布尔类型的true和false
            // 这里的eval函数，是把字符串"true"和"false"解析成布尔类型的true和false
            let result = null;
            if (res === "true" || res === "false") {
                result = eval(res);
            }
            if (result === true) {
                $.messager.alert("系统提示", "保存成功");
                $("#upload").dialog("close");
                $("#dg").datagrid("reload");
                $("#dg").datagrid("clearChecked");
            } else {
                $.messager.alert("系统提示", "保存失败");
            }
        }
    });
}

function openVerifyDialog() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length !== 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    let row = selectedRows[0];
    if (row.resStatus !== 0) {
        $.messager.alert("系统提示", "不是申请状态！");
        return;
    }
    $("#dlgVerify").dialog("open").dialog("setTitle", "审核成果信息");
    $("#resName_verify")[0].value = row.resName;
    $("#resDesc_verify")[0].value = row.resDesc;
    $("#fm").form("load", row);
    type = "verify";
    url = "addResult?type=" + type + "&adminId=" + Id + "&resId=" + row.resId;
}

function closeVerifyDialog() {
    $("#dlgVerify").dialog("close");
}

function saveVerify() {
    $("#verifyFm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($('#resStatus').combobox('getValue') === "") {
                $.messager.alert("系统提示", "请选择审核状态");
                return false;
            } else {
                return true;
            }
        },
        success: function (res) {
            // res只会返回字符串的true或false，不是布尔类型的true和false
            // 这里的eval函数，是把字符串"true"和"false"解析成布尔类型的true和false
            let result = null;
            if (res === "true" || res === "false") {
                result = eval(res);
            }
            if (result === true) {
                $.messager.alert("系统提示", "保存成功");
                $("#dlgVerify").dialog("close");
                $("#dg").datagrid("reload");
                $("#dg").datagrid("clearChecked");
            } else {
                $.messager.alert("系统提示", "保存失败");
            }
        }
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

function formatResFile(resFile, row) {
    if (resFile) {
        return '<a target="_blank" style="color:red;" ' +
            'href="downloadFile/?userId=' + row.resId + '">下载' + '</a>';
    } else {
        return "未上传";
    }
}
