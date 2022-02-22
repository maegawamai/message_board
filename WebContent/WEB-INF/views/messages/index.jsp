<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--このjspはビューとしての役割としてIndexServletで呼び出される --%>
<%--JSTL式（c:を使う） --%>
<%--c:importとc:paramタグを使用することでテンプレートファイルの読み取り可能--%>
<c:import url="../layout/app.jsp">

    <%-- paramタグの中がapp.jspの$param.contentのところにあてはまる--%>
    <c:param name="content">

        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>メッセージ一覧</h2>
        <ul>
            <%--forEachは繰り返し送られてきたmessageを1件ずつ取り出して処理 --%>
            <c:forEach var="message" items="${messages}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${message.id}">
                        <c:out value="${message.id}" />
                    </a>
                    ：<c:out value="${message.title}"></c:out> &gt; <c:out value="${message.content}" />
                </li>
            </c:forEach>
        </ul>
         <%--message_boardのコンテキストの文字列が自動で挿入 --%>
        <p><a href="${pageContext.request.contextPath}/new">新規メッセージの投稿</a></p>

    </c:param>
</c:import>

