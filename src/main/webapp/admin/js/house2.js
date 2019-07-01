$(function(){
    //使用datagrid绑定数据展示
    $('#dg').datagrid({
        title:">>>已审核出租房管理",
        url:'/admin/getHouseByYesPass',
        fitColumns: true,  //自动扩展或收缩列的大小以适应网格宽度和防止水平滚动条
        pagination: true,   //表示在datagrid设置分页
        pageList: [5, 10, 15, 20],
        toolbar:"#ToolBar",
        // singleSelect:true,  //设置只能选择一个
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'title', title: '标题', width: 50, align: "center" },
            { field: 'price', title: '价格', width: 50, align: "center" },
            { field: 'floorage', title: '面积', width: 50, align: "center" },
            { field: 'dname', title: '区域', width: 50, align: "center" },
            { field: 'sname', title: '街道', width: 50, align: "center" },
            { field: 'tname', title: '类型', width: 50, align: "center" },
            { field: 'ispass', title: '状态', width: 50, align: "center" ,
                formatter:function (value,row,index) {
                    return "已审核";
                }
              },
            { field: 'opt', title: '操作', width: 50, align: "center",
                formatter: function(value,row,index){
                    //同步跳转
                    //     return "<a href='delDistrict?id="+row.id+"'>删除</a>"
                    return "<a href='javascript:delSingle("+row.id+")'>审核</a>";
                }
            }
        ]]
    });
});



//实现条件搜索
function searchUser() {
    var $telephone=$("#telephone").val();
    var $name=$("#name").val();
    $("#dg").datagrid("load",{"name":$name,"telephone":$telephone});
}
//    关闭窗口
function CloseDialog(obj) {
    $("#"+obj).dialog("close");
}
