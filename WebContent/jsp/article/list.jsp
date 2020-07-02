<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<% 
	int count = (int) request.getAttribute("count");
%>
<!-- 하이라이트 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<!-- 하이라이트 라이브러리, 언어 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/css.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/javascript.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/xml.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php-template.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/sql.min.js"></script>

<!-- 코드 미러 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />

<!-- 토스트 UI 에디터, 자바스크립트 코어 -->
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.js"></script>

<!-- 토스트 UI 에디터, 신택스 하이라이트 플러그인 추가 -->
<script
	src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

<!-- 토스트 UI 에디터, CSS 코어 -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
	
	<style type="text/css"> .numButton:hover{color: blue;} </style>

<img class="bg-java"
	style="border-radius: 50px; width: 100%; opacity: 0.35; z-index: -1; position: absolute;"
	src="${pageContext.request.contextPath}/resource/img/java.jpg" alt="" />
	
	<input type="hidden" name="page" value="1"/>
	<input type="hidden" name="page" value="1"/>

<div class="button con" style="text-align:center; position:relative; margin-top:50px;">
	<form action="/blog/s/article/list" method="get">
		<div class="cate" style="position:absolute; top:0; left:50%; transform:translateX(-50%);">		
			<ul class="row">
				<li class="cell"><button type="submit" name="cateItemId" value="1"><% %>JAVA</button></li>
				<li class="cell"><button type="submit" name="cateItemId" value="2">JavaScript</button></li>
				<li class="cell"><button type="submit" name="cateItemId" value="3">DB</button></li>
				<li class="cell"><button type="submit" name="cateItemId" value="4">Spring</button></li>
				<li class="cell"><button type="submit" name="cateItemId" value="5">Algorithm</button></li>
				<input type="hidden" name="page" value="1"/>
			</ul>
		</div>
	</form>

<%
int cateItemId = 0;
if(request.getParameter("cateItemId") != null){
	cateItemId = Integer.parseInt(request.getParameter("cateItemId"));
}
%>
<%
int page_ = 0;
int pageNow = 0;
if(request.getParameter("page") != null){
	page_ = Integer.parseInt(request.getParameter("page"));
	
	pageNow = page_;
	if(page_%5 == 0){
		page_ = page_-4;
	}else if (page_%5 != 0){
		page_ = page_ - (page_%5) + 1;
	}
}
int minPage = page_-1;
int pluPage = page_+5;
if(page_-1 == 0){
	minPage = 1;
}
%>
	
	<form action="/blog/s/article/list" method="get">
		<div class="prevNext" style="position:absolute; left:50%; transform:translateX(-50%) translateY(100%);">
			<input type="hidden" name="cateItemId" value="<%=cateItemId%>"/>
			<button type="submit" name="page" value="<%=minPage%>">prev</button>
			
			<% if(page_ != 0){%>
				<%for(int i = page_; i < page_+5; i++){%>
					<div style="display:inline-block; margin-top:7px;">
						<a hover="" href="${pageContext.request.contextPath}/s/article/list?cateItemId=<%=cateItemId%>&page=<%=i%>" class="numButton" style="padding: 5px; font-weight:bold;"><%=i%></a>
					</div>	
					<%if(i > count/5){
						pluPage = i;
						break;
					}%>
				<%}
				}
			%>
			
			<button type="submit" name="page" value="<%=pluPage%>">next</button>
		</div>
	</form>
</div>


<!-- <input type="hidden" name="JAVA" value="1"/>
<input type="hidden" name="JavaScript" value="2"/>
<input type="hidden" name="DB" value="3"/>
<input type="hidden" name="Spring" value="4"/>
<input type="hidden" name="Algorithm" value="5"/> -->

<%
String cateItem = "";
switch(cateItemId){
	case 1:
		cateItem = "JAVA";
		break;
	case 2:
		cateItem = "자바스크립트";
		break;
	case 3:
		cateItem = "DB";
		break;
	case 4:
		cateItem = "스프링";
		break;
	case 5:
		cateItem = "알고리즘";
		break;
}
%>

<h1 style="text-align: center; margin-top: 140px;"><%=cateItem%>*게시물 리스트</h1>
<h2 style="text-align: center;">== <%=pageNow%>페이지 ==</h2>
<h3 style="text-align: center;">현재 게시판 게시물 수 : <%=count%></h3>

<div class="con menu1">
	<ul class="row menu-list-1">
		<li class="cell">ID</li>
		<li class="cell">등록날짜</li>
		<li class="cell">갱신날짜</li>
		<li class="cell">제목</li>
	</ul>
</div>

<div class="con menu2" style="">
	<%
		for (Article article : articles) {
	%>
	<ul class="row menu-list-2">
		<li class="cell"><%=article.getId()%></li>
		<li class="cell"><%=article.getRegDate()%></li>
		<li class="cell"><%=article.getUpdateDate()%></li>
		<li class="cell title"><a href="./detail?id=<%=article.getId()%>"><%=article.getTitle()%></a></li>
	</ul>
	<%
		}
	%>
</div>




<%@ include file="/jsp/part/foot.jspf"%>