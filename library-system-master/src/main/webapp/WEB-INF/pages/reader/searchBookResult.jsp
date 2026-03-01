<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>图书搜索结果</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
  <script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="layuimini-container">
  <div class="layuimini-main">
    <h1>图书搜索结果</h1>
    <table class="layui-table">
      <thead>
      <tr>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>出版日期</th>
        <th>类别</th>
        <th>库存</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="book" items="${books}">
        <tr>
          <td>${book.name}</td>
          <td>${book.author}</td>
          <td>${book.publisher}</td>
          <td>${book.publishDate}</td>
          <td>${book.category}</td>
          <td>${book.counts}</td>
          <td>
            <button class="layui-btn layui-btn-normal reserve-btn" data-book-id="${book.id}">预约</button>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<script>
  layui.use(['jquery'], function(){
    var $ = layui.jquery;

    $('.reserve-btn').on('click', function() {
      var bookId = $(this).data('book-id');
      var userId = '${sessionScope.user.id}'; // 假设用户ID保存在会话中

      $.post('${pageContext.request.contextPath}/reserveBook', {
        userId: userId,
        bookId: bookId
      }, function(response) {
        if (response.code == 0) {
          alert('预约成功');
        } else {
          alert('预约失败: ' + response.msg);
        }
      });
    });
  });
</script>
</body>
</html>
