<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jspf" %>
<html>
<head>
    <title>s</title>

    <script type="text/javascript">
        $(function(){
                $("#bt1").click(
                    function(){
                         var accountVal = $("#account1").val();
                         var userNameVal = $("#userName1").val();
                         var passwordVal = $("#password1").val();
                         var sexVa1 = $("[name=sex]").val();
                         alert(sexVa1);
                        $.post(
                            "sysUser/insert2.mvc",
                            {"account":accountVal,"userName":userNameVal,"password":passwordVal,"sex":sexVa1},
                            function(json){
                                alert(json.msg);
                            },
                            "json"
                        );
                    }
                );
            }
        );
    </script>
        </head>

        <body>
        <h2>Hello World!</h2>
        请输入需要添加的用户信息
        <hr>
        <form action="sysUser/insert.mvc" method="post">
            账号：<input type="text"  name="account"><br>
            名称：<input type="text" name="userName"><br>
            密码：<input type="text" name="password"><br>
            <input type="submit" value="注册">
            </form>

            <br>
            请输入需要添加的用户信息2(返回本页面)
            <hr>
            <form action="/sysUser/insert2.mvc" id="form2" method="post">
            账号：<input type="text"  id="account1" name="account"><br>
            名称：<input type="text" id="userName1" name="userName"><br>
            密码：<input type="text" id="password1" name="password"><br>
            <%--性别: <input type="text" id="sex1" name="sex"><br>--%>
                  <label for="sex">性别:</label>
                <select id="cc" class="easyui-combobox" name="sex" style="width:200px;">
                    <c:forEach items="${applicationScope.sysParam.sex}" var="s">
                        <option value="${s.key}">
                            ${s.value}
                        </option>
                    </c:forEach>
                </select>
                <input type="button" id="bt1" value="注册">
            </form>

            <br>
            请输入需要查询的用户编号(返回本页面)
            <hr>
            <form action="/sysUser/selectByKey.mvc"  method="post">
            编号：<input type="text"      name="id"><br>
            <input type="submit"  value="搜索">
            </form>
            </body>
            </html>
