<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
<script type="text/javascript">
    function show(t,url){
        if($("#tt").tabs('exists',t))
        {
            $("#tt").tabs('select',t)

        }else{
            $("#tt").tabs('add',{
                title:t,
                href:url,
                closable:true,
                selected:true
            })
        }
    }


</script>
</head>
<body>
<div id="cc" class="easyui-layout" data-options="fit:true" style="width:600px;height:400px;">
    <div data-options="region:'north',title:'用户管理界面',split:true,content:'欢迎您 ${sessionScope.ok.account} 进入主界面!'" style="height:100px;"></div>
    <div data-options="region:'south',title:'版权声明',split:true" style="height:100px;"></div>
    <div data-options="region:'east',iconCls:'icon-reload',title:'新闻公告',split:true" style="width:180px;"></div>
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:180px;">
        <div id="aa" class="easyui-accordion" data-options="fit:true" style="width:300px;height:200px;">
            <div title="系统管理" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
                <a id="btn1" onclick="show('用户管理','/base/goURL/user/userlist.mvc')" class="easyui-linkbutton" data-options="iconCls:'icon-search'">用户管理</a>
                <a id="btn2" onclick="show('角色管理','/base/goURL/user/rolelist.mvc')" class="easyui-linkbutton" data-options="iconCls:'icon-search'">角色管理</a>
            </div>
            <div title="商品管理" data-options="iconCls:'icon-reload'" style="padding:10px;">
                content2
            </div>
            <div title="其他需求" data-options="iconCls:'icon-reload'">
                content3
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'主要内容'" style="padding:5px;">
        <div id="tt" class="easyui-tabs" data-options="fit:true" style="width:500px;height:250px;">
            <div title="介绍" style="padding:20px;">
                说明:
            </div>
        </div>
    </div>
</div>
</body>
</html>
