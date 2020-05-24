<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
       $(function(){
           $('#d1').window({
               title:"登录窗口",
               width:230,
               height:150,
               modal:true,
               minimizable:false,
               maximizable:false,
               closable:false,
               draggable:false,
               resizable:false,
           });
       })

        function login() {
           if($('#ff').form('validate'))
           {
                $("#ff").submit();
           }else {
               return false;
           }
        }

    </script>
</head>
<body>
<div id="d1">
<form id="ff" action="${proPath}/sysUser/login.mvc" method="post">
        <label for="account">账号:</label>
        <input class="easyui-validatebox" type="text" name="account" data-options="required:true,validType:'length[3,18]'"/><br/>
        <label for="password">密码:</label>
        <input class="easyui-validatebox" type="text" name="password" data-options="required:true,validType:'length[3,18]'"/><br/>
        <input type="button" onclick="login()"  value="登录"><input type="reset" value="重置">
</form>
    <p style="color: #CC2222">${requestScope.err}</p>
</div>
</body>
</html>
