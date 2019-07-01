$(function(){
    //使用datagrid绑定数据展示
    $('#dg').datagrid({
        url:'/admin/getType',
        fitColumns: true,  //自动扩展或收缩列的大小以适应网格宽度和防止水平滚动条
        pagination: true,   //表示在datagrid设置分页
        pageList: [5, 10, 15, 20],
        toolbar:"#ToolBar",
        // singleSelect:true,  //设置只能选择一个
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '类型', width: 50, align: "center" },
            { field: 'opt', title: '操作', width: 50, align: "center",
                formatter: function(value,row,index){
                    //同步跳转
                    //return "<a href='delDistrict?id="+row.id+"'>删除</a>"
                    return "<a href='javascript:delSingle("+row.id+")'>删除</a> | <a href='javascript:openStreetDialog("+row.id+")'>查看房屋</a> ";
                }
            }
        ]]
    });
});

//打开添加窗口
function Add() {
    // alert("打开窗口");
    $("#AddDialog").dialog("open").dialog('setTitle',"添加类型");
}

//保存
function SaveDialog() {
    //跳转到后台实现保存
    //传统：取值，通过$.ajax方法发送异步请求
    $("#saveDialogForm").form("submit",{
        url:"/admin/addDistrict",   //提交的服务器地址
        success:function (data) {
            //data接收服务器返回值json字符串，不是json对象
            var obj=$.parseJSON(data);//将json字符串转换为json对象
            if (obj.result>0){
                $("#dg").datagrid("reload");//刷新
                $("#AddDialog").dialog("close");//关闭
                // alert("添加成功!");
                $.messager.alert("提示框","恭喜添加成功！");
            } else {
                $.messager.alert("提示框","系统正在维护");
                // alert("添加失败!");
            }
        }
    });
}

//弹出修改对话框
function ModifyBySelect() {
    //判断是否勾选要修改的行
    //获取所有选中行,如果没有选择返回空
    var SelectRows=$("#dg").datagrid("getSelections");
    if (SelectRows.length!=1){
        $.messager.alert("提示框","您还没有选中行,或者选择了多行");
        return;
    }
    //选择了一行
    //还原数据
    var SelectRow=SelectRows[0];//获取当前行
    //打开编辑对话框
    $("#upDialog").dialog("open").dialog("setTitle","修改区域");
    //获得行对象的数据加载到表单中显示
    //SelectRow表示的就是当前行的json
    //表单对象名称与json对象的键相同即可还原
    $("#upDialogForm").form("load",SelectRow);
}

//实现修改。修改后提交
function upDialog() {
    //跳转到后台实现保存
    //传统：取值，通过$.ajax方法发送异步请求
    $("#upDialogForm").form("submit",{
        url:"/admin/upDistrict",   //提交的服务器地址
        success:function (data) {
            //data接收服务器返回值json字符串，不是json对象
            var obj=$.parseJSON(data);//将json字符串转换为json对象
            if (obj.result>0){
                $("#dg").datagrid("reload");
                $("#upDialog").dialog("close");//关闭
                // alert("添加成功!");
                $.messager.alert("提示框","恭喜修改成功！");
            } else {
                $.messager.alert("提示框","系统正在维护");
                // alert("添加失败!");
            }
        }
    });
}

//删除一行
function delSingle(id) {
    // alert(id)
    $.messager.confirm("确认提示框","确认要删除吗？",function (result) {
        if (result){
            //实现删除,发送异步请求
            // $.post("服务器地址",给服务器传参,回调函数,"json");
            $.post("delDistrict",{"id":id},function (data) {
                if (data.result>0){
                    $("#dg").datagrid("reload"); //刷新
                    // $.messager.alert("提示框","删除成功！");
                } else {
                    $.messager.alert("提示框","系统正在维护");
                    // alert("添加失败!");
                }
            },"json");
        }
    });
}

//删除多行
function DeleteMore(){
    //判断有无选中行
    var SelectRows=$("#dg").datagrid("getSelections");
    if (SelectRows.length==0){
        $.messager.alert("提示框","您还没有选中行。");
        return;
    }
    var SelectRow=SelectRows[0];//获取选中行
    var delValue="";
    for (var i=0;i<SelectRows.length;i++){
        delValue=delValue+SelectRows[i].id+",";
    }
    delValue=delValue.substring(0,delValue.length-1);
    // alert(delValue);
    //发送异步请求到服务器
    $.post("delMoreDistrict",{"ids":delValue},function (data) {
        if (data.result>0){
            $("#dg").datagrid("reload"); //刷新
            $.messager.alert("提示框","恭喜成功删除"+data.result+"行！");
        } else {
            $.messager.alert("提示框","系统正在维护");
            // alert("添加失败!");
        }
    },"json");

}
//打开显示街道的对话框
function openStreetDialog(obj) { //obj接收传过来的区域编号
    //显示对话框
    $("#streetDialog").dialog("open").dialog('setTitle',"街道信息");
    //加载对话框中展示的街道,发请求，显示数据
    // alert(obj); //测试是否获得id值
    $('#streetDg').datagrid({
        title:">>>街道信息",
        url:'/admin/getStreetByDid?did='+obj,   //将选择的区域id放入请求一起到控制器
        // queryParams:{"did":obj},//发送参数
        fitColumns: true,  //自动扩展或收缩列的大小以适应网格宽度和防止水平滚动条
        pagination: true,   //表示在datagrid设置分页
        pageList: [5, 10, 15, 20],
        toolbar:"#ToolBar",
        // singleSelect:true,  //设置只能选择一个
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '名称', width: 50, align: "center" },
            { field: 'opt', title: '操作', width: 50, align: "center",
                formatter: function(value,row,index){
                    //同步跳转
                    //     return "<a href='delDistrict?id="+row.id+"'>删除</a>"
                    return "<a href='javascript:delSingle("+row.id+")'>删除</a>";
                }
            }
        ]]
    });

}

//    关闭窗口
function CloseDialog(obj) {
    $("#"+obj).dialog("close");
}
