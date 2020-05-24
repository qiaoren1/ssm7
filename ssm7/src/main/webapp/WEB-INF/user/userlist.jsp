<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/common.jspf"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="ff" method="post">
    <div>
        <label for="account">账号:</label>
        <input class="easyui-validatebox" type="text" name="account"   />
    </div>
    <div>
        <label for="userName">名称:</label>
        <input class="easyui-validatebox" type="text" name="userName"   />
    </div>
    <input type="button" onclick="queryUser()" value="查询">
</form>

<table id="dg"></table>

<script type="text/javascript">

    function queryUser() {

        if($("[name=account]").val()!="")
        {
            $("#dg").datagrid("load",{
                account:$("[name=account]").val(),

            });
        }

        if($("[name=userName]").val()!="")
        {
            $("#dg").datagrid("load",{
                userName:$("[name=userName]").val(),
            });
        }
    }

        $('#dg').datagrid({
            url:'/sysUser/selectPage.mvc',
            columns:[[
                {checkbox:true},
                {field:'account',title:'账号',width:100},
                {field:'userName',title:'名称',width:100}
            ]],
            fitColumns:true,
            toolbar: [{
                iconCls: 'icon-add',
                text:'新增',
                handler: function(){alert('新增按钮')}
            },'-',{
                iconCls: 'icon-edit',
                text:'编辑',
                handler: function(){alert('编辑按钮')}
            },'-',{
                iconCls: 'icon-remove',
                text:'删除',
                handler: function(){alert('删除按钮')}
            }],
            striped:true,
            pagination:true,
            pageList:[5,10,15,20],
            pageSize:5,
        });

</script>
</body>
</html>
