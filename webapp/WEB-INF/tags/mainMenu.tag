<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@tag import="java.util.List"%>
<%@tag import="org.springframework.context.ApplicationContext"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="com.mossle.api.menu.MenuConnector"%>
<%@tag import="com.mossle.api.menu.MenuDTO"%>
<%@tag import="com.mossle.core.auth.CurrentUserHolder"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	
	    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	    MenuConnector menuConnector = ctx.getBean(MenuConnector.class);
	    CurrentUserHolder currentUserHolder = ctx.getBean(CurrentUserHolder.class);
	    try {
	        String userId = currentUserHolder.getUserId();
	        List<MenuDTO> menuDtos = menuConnector.findMainMenu(userId);
	        //System.out.println("menuDtos : " + menuDtos.size());
	        for (MenuDTO menuDto : menuDtos) {
	         // System.out.println("title : " + menuDto.getChildren().get(0).getTitle());
	          jspContext.setAttribute("menu", menuDto);
%>
<div class="panel panel-default">
	<div class="panel-heading" role="tab" id="collapse-header-${menu.code }" data-toggle="collapse" data-parent="#accordion" href="#collapse-body-${menu.code }" aria-expanded="true" aria-controls="collapse-body-${menu.code }">
		<h4 class="panel-title">
			<i class="glyphicon glyphicon-list"></i> ${menu.title}
		</h4>
	</div>
	<div id="collapse-body-${menu.code }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapse-header-${menu.code }">
		<div class="panel-body">
			<ul class="nav nav-list">
				<c:forEach items="${menu.children}" var="child">
					<li><a href="${tenantPrefix}/${child.url}"><i class="glyphicon glyphicon-list"></i> ${child.title}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>

<%
			      }
	       } catch(Exception ex) {
	         System.out.println(ex);
	       }
%>
