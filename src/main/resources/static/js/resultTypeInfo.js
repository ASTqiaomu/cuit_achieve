let url;
let type = null;

function searchResultType() {
    $('#dg').datagrid('load', {
        typeName: $('#s_typeName').val()
    });
}

$(document).ready(function () {
    Page = parent.$('body')[0].id;
    // 非管理员访问时，隐藏部分页面元素
    if (Page === "userMain") {
        $("#addIcon")[0].remove();
        $("#editIcon")[0].remove();
        $("#delIcon")[0].remove();
    }
});

function openResultTypeAddDialog() {
    $("#fm").form("reset");
    $("#typeName")[0].disabled = false;
    $("#dlg").dialog("open").dialog("setTitle", "添加成果类型");
    url = "addUserResultType";
    type = "add";
}

function saveResultType() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($(this).form("validate") === false) {
                return false;
            }
            if (type === "add") {
                let exist = true;
                let typeName = $('#typeName')[0].value;
                let data = {typeName: typeName};
                // ajax去服务器端校验，判断用户名是否存在
                $.ajax({
                    type: "POST",
                    url: "haveResultType",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    async: false,
                    success: function (msg) {
                        if (msg === true) {
                            $.messager.alert("系统提示", "成果名已存在");
                            exist = true;
                        } else if (msg === false) {
                            exist = false;
                        }
                    }
                });
                return !exist;
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
}

function closeResultTypeDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

function openResultTypeModifyDialog() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length !== 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    let row = selectedRows[0];
    $("#typeName")[0].disabled = true;
    $("#dlg").dialog("open").dialog("setTitle", "编辑成果类型");
    $("#fm").form("load", row);
    url = "updateResultType?typeId=" + row.typeId;
    type = "update";
}

function deleteResultType() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length === 0) {
        $.messager.alert("系统提示", "请选择要删除的用户！");
    } else {
        let strIds = [];
        for (let i = 0; i < selectedRows.length; i++) {
            strIds.push(selectedRows[i].typeId);
        }
        let ids = strIds.join(",");
        $.messager.confirm("系统提示", "您确认要删除这<span style='color:red;'>" + selectedRows.length + "</span>条成果类型吗？", function (r) {
            if (r) {
                $.post("deleteResultType", {ids: ids}, function (result) {
                    if (eval(result.success)) {
                        $.messager.alert("系统提示", "您已成功删除<span style='color:red;'>" + result.num + "</span>条成果类型！");
                    } else {
                        $.messager.alert("系统提示", "删除失败，成果类型<span style='color:red;'>" + result.delTypeName + "</span>已有教师申请的成果");
                    }
                    $("#dg").datagrid("reload");
                    $("#dg").datagrid("clearChecked");
                }, "json");
            }
        });
    }
}
