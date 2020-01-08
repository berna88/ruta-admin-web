<%@ include file="init.jsp" %>

<liferay-ui:search-container
    total="<%= RutaLocalServiceUtil.getRutasCount(scopeGroupId) %>">
    <liferay-ui:search-container-results
        results="<%= RutaLocalServiceUtil.getRutas(scopeGroupId, 
            searchContainer.getStart(), searchContainer.getEnd()) %>" />

    <liferay-ui:search-container-row
        className="mx.com.cuervo.rutas.transporte.model.Ruta" modelVar="Ruta">

        <liferay-ui:search-container-column-text property="nombreRuta" />
        <liferay-ui:search-container-column-text property="capacidad" />
        <liferay-ui:search-container-column-text property="disponibilidad" />

        <liferay-ui:search-container-column-jsp
            align="right" 
            path="/ruta_actions.jsp" />

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:button-row cssClass="ruta-admin-buttons">
    <portlet:renderURL var="addRutaURL">
        <portlet:param name="mvcPath"
            value="/edit_ruta.jsp" />
        <portlet:param name="redirect" value="<%= "currentURL" %>" />
    </portlet:renderURL>

    <aui:button onClick="<%= addRutaURL.toString() %>"
        value="Agregar Ruta" />
</aui:button-row>

