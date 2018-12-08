<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        function req() {

            $.ajax({
                url: "testResolver",
                data: {i:1},
                type: "POST",
                success: function(data){
//                    debugger;
                    $("#resp").append("<h3>" + data + "</h3>");
                },
                error:function (a,b,c) {
                    debugger;
                    alert(a.responseText)
                }
            });
        }

        function req1() {

            $.ajax({
                url: "testAjaxException",
                type: "POST",
                success: function(data){
                   debugger;
                    $("#resp").append("<h3>" + JSON.stringify(data) + "</h3>");
                },
                error:function (a,b,c) {
                    debugger;
                    alert(a.responseText)
                }
            });
        }


    </script>
    <title>Insert title here</title>
</head>
<body>
<input type="button" onclick="req();" value="请求"/>
<input type="button" onclick="req1();" value="testAjaxException"/>
<div id = "resp">
</div>

</body>
</html>