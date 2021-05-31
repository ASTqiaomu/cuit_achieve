let url;
let type = null;

function openCollegeAddDialog() {
    $("#fm").form("reset");
    $("#dlg").dialog("open").dialog("setTitle", "添加学院信息");
    url = "addCollege";
    type = "add";
}

function saveCollege() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($(this).form("validate") === false) {
                return false;
            }
            if (type === "add") {
                let collegeName = $('#collegeName')[0].value;
                let data = {collegeName: collegeName};
                let exist = true;
                // ajax去服务器端校验，判断用户名是否存在
                $.ajax({
                    type: "POST",
                    url: "haveCollege",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    async: false,
                    success: function (msg) {
                        if (msg === true) {
                            $.messager.alert("系统提示", "学院名已存在");
                            exist = true;
                        } else if (msg === false) {
                            exist = false;
                        }
                    }
                });
                return !exist;
            } else if (type === "update") {
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

function closeCollegeDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

function openCollegeModifyDialog() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length !== 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    let row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "编辑学院信息");
    $("#fm").form("load", row);
    url = "updateCollegeInfo?collegeId=" + row.collegeId;
    type = "update";
}