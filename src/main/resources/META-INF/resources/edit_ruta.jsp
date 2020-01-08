<%@include file = "init.jsp" %>

<%
        long rutaId = ParamUtil.getLong(request, "rutaId");

        Ruta ruta = null;

        if (rutaId > 0) {
                ruta = RutaLocalServiceUtil.getRuta(rutaId);
        }
%>

<portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name='<%= ruta == null ? "addRuta" : "updateRuta" %>' var="editRutaURL" />

<aui:form action="<%= editRutaURL %>" name="fm">

        <aui:model-context bean="<%= ruta %>" model="<%= Ruta.class %>" />

        <aui:input type="hidden" name="rutaId"
            value='<%= ruta == null ? "" : ruta.getRutaId() %>' />

        <aui:fieldset>
             <aui:input name="nombreRuta" />
        </aui:fieldset>

		<aui:fieldset>
             <aui:input name="capacidad" />
        </aui:fieldset>

		<aui:fieldset>
             <aui:input name="disponibilidad" />
        </aui:fieldset>

        <aui:button-row>
             <aui:button type="submit" />
             <aui:button onClick="<%= viewURL %>" type="cancel"  />
        </aui:button-row>
</aui:form>