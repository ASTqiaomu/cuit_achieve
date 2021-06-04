function deleteLogLogin() {
    let selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length === 0) {
        $.messager.alert("系统提示", "请选择要删除的数据！");
        return;
    }
    let strIds = [];
    for (let i = 0; i < selectedRows.length; i++) {
        strIds.push(selectedRows[i].logId);
    }
    let ids = strIds.join(",");
    //输出选择的行
    $.messager.confirm("系统提示", "您确认要删掉这<span style='color:red'>" + selectedRows.length + "</span>条数据吗？", function (r) {
        if (r) {
            $.post("deleteLogLogin", {delIds: ids}, function (result) {
                if (result.success) {
                    $.messager.alert("系统提示", "您已成功删除<span style='color:red'>" + result.delNums + "</span>条数据！");
                    $("#dg").datagrid("reload");
                } else {
                    $.messager.alert('系统提示', '删除失败');
                }
            }, "json");
        }
    });
}