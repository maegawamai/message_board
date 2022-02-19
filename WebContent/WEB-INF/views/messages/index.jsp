<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
<%--c:importとc:paramタグを使用することでテンプレートファイルの読み取り可能--%>
    <c:param name="content">
   <%-- paramタグの中がapp.jspの$param.contentのところにあてはまる--%>
        <h2>メッセージ一覧</h2>
        <ul>
            <c:forEach var="message" items="${messages}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${message.id}">
                        <c:out value="${message.id}" />
                    </a>
                    ：<c:out value="${message.title}"></c:out> &gt; <c:out value="${message.content}" />
                </li>
            </c:forEach>
        </ul>

        <p><a href="${pageContext.request.contextPath}/new">新規メッセージの投稿</a></p>
    <%--message_boardのコンテキストの文字列が自動で挿入 --%>
    </c:param>
</c:import>
<%--このjspはビューとしての役割としてIndexServletで呼び出される --%>
