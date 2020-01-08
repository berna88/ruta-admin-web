<%@include file="init.jsp"%>

<%
    String mvcPath = ParamUtil.getString(request, "mvcPath");

    ResultRow row = (ResultRow) request
                    .getAttribute("SEARCH_CONTAINER_RESULT_ROW");

    Ruta ruta = (Ruta) row.getObject();
%>

<liferay-ui:icon-menu>

    <portlet:renderURL var="editURL">
        <portlet:param name="rutaId"
            value="<%=String.valueOf(ruta.getRutaId()) %>" />
        <portlet:param name="mvcPath"
            value="/edit_ruta.jsp" />
    </portlet:renderURL>

    <liferay-ui:icon image="edit" message="Edit"
            url="<%=editURL.toString() %>" />

    <portlet:actionURL name="deleteRuta" var="deleteURL">
            <portlet:param name="rutaId"
                value="<%= String.valueOf(ruta.getRutaId()) %>" />
    </portlet:actionURL>

    <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

</liferay-ui:icon-menu>