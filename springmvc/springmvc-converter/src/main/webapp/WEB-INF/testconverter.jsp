<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        function req() {
           var user = {name:'wutao',gender:'job'};

            $.ajax({
                url: "custom",
                data: user,
                type: "GET",
                success: function(data){
                    debugger;
                    var json = JSON.parse(data);
                    $("#resp").append("<h3>" + json.name + "</h3>");
                }
            });
        }
    </script>
    <title>Insert title here</title>
</head>
<body>
<input type="button" onclick="req();" value="请求"/>
<div id = "resp">
</div>

</body>
</html>