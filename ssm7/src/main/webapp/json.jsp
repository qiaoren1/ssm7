<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jspf" %>
<html>
<head>

    <title>My JSP 'json.jsp' starting page</title>
    <script type="text/javascript">
        $(function(){
                $("#bt1").click(
                    function(){
                        $.post(
                            "dept/doAjax.mvc",
                            {deptId:1,deptName:"服务"},
                            function(json){
                                alert(json.deptId+"||"+json.deptName);
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
<button id="bt1" >testJson</button>
</body>
</html>