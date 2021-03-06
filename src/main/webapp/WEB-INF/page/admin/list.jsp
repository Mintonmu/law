<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../include/publicMeta.jsp"%>
<%@include file="../../include/publicHeader.jsp"%>
<%@include file="../../include/publicMenu.jsp"%>

<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span>
        管理员列表
         <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                 href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <div class="cl pd-5 bg-1 bk-gray mt-20">
                <span class="l"> <a href="javascript:;" onclick="go('添加','${pageContext.request.contextPath}/admin/toadd','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a> </span>
                <span class="r">共有数据：<strong>${pageInfo.totalElements}</strong> 条</span>
            </div>
            <table class="table table-border table-bordered table-bg" id="mytable">
                <tr>
                    <td>管理员编号</td>
                    <td>管理员昵称</td>
                    <td>管理员姓名</td>
                    <td>管理员密码</td>
                    <td>编辑操作</td>
                    <td>删除操作</td>
                </tr>
                <c:forEach items="${pageInfo.content}" var="item">
                    <tr>
                        <td>${item.adminId}</td>
                        <td>${item.adminNickname}</td>
                        <td>${item.adminRealname}</td>
                        <td>${item.adminPassword}</td>
                        <td><a href="javascript:;" onclick="go('编辑','${pageContext.request.contextPath}/admin/toedit?id=${item.adminId}','800','500')">编辑</a></td>
                        <td><a href="${pageContext.request.contextPath}/admin/delete?id=${item.adminId}">删除</a></td>
                    </tr>
                </c:forEach>
            </table>

        </article>
        <article class="cl pd-20">
            <%@include file="../adminPage.jsp"%>
        </article>
    </div>
</section>

<%@include file="../../include/publicFooter.jsp"%>

<script type="text/javascript">
    /*
        参数解释：
        title	标题
        url		请求的url
        id		需要操作的数据id
        w		弹出层宽度（缺省调默认值）
        h		弹出层高度（缺省调默认值）
    */
    function go(title,url,w,h){
        layer_show(title,url,w,h);
    }





</script>

</body>
</html>
