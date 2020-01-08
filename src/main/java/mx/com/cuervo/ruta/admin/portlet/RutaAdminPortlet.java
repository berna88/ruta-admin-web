package mx.com.cuervo.ruta.admin.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mx.com.cuervo.ruta.admin.constants.RutaAdminPortletKeys;
import mx.com.cuervo.rutas.transporte.model.Ruta;
import mx.com.cuervo.rutas.transporte.service.RutaLocalService;


@Component(
	    immediate = true,
	    property = {
	            "com.liferay.portlet.display-category=category.hidden",
	            "com.liferay.portlet.scopeable=true",
	            "javax.portlet.display-name=Rutas",
	            "javax.portlet.expiration-cache=0",
	            "javax.portlet.init-param.portlet-title-based-navigation=true",
	            "javax.portlet.init-param.template-path=/",
	            "javax.portlet.init-param.view-template=/view.jsp",
	            "javax.portlet.name=" + RutaAdminPortletKeys.RUTA_ADMIN,
	            "javax.portlet.resource-bundle=content.Language",
	            "javax.portlet.security-role-ref=administrator",
	            "javax.portlet.supports.mime-type=text/html",
	            "com.liferay.portlet.add-default-resource=true"
	    },
	    service = Portlet.class
	)
public class RutaAdminPortlet extends MVCPortlet {
	
	@SuppressWarnings("deprecation")
	public void addRuta(ActionRequest request, ActionResponse response)
		    throws PortalException {

		    ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Ruta.class.getName(), request);

		    String nombreRuta = ParamUtil.getString(request, "nombreRuta");
		    long capacidad = ParamUtil.getLong(request, "capacidad");
		    long disponibilidad = ParamUtil.getLong(request, "disponibilidad");
	
		    try {
		        _rutaLocalService.addRuta(
		            serviceContext.getUserId(), nombreRuta, capacidad, disponibilidad, serviceContext);
		    }
		    catch (PortalException pe) {

		        Logger.getLogger(RutaAdminPortlet.class.getName()).log(
		            Level.SEVERE, null, pe);

		        response.setRenderParameter(
		            "mvcPath", "/edit_ruta.jsp");
		    }
		}
	
		@SuppressWarnings("deprecation")
		public void updateRuta(ActionRequest request, ActionResponse response)
			    throws PortalException {

			System.out.println("UPDATE RUTA");
			    ServiceContext serviceContext = ServiceContextFactory.getInstance(
			        Ruta.class.getName(), request);

			    String nombreRuta = ParamUtil.getString(request, "nombreRuta");
			    long capacidad = ParamUtil.getLong(request, "capacidad");
			    long disponibilidad = ParamUtil.getLong(request, "disponibilidad");
			    long rutaId = ParamUtil.getLong(request, "rutaId");

			    System.out.println("nombreRuta::" + nombreRuta);
			    System.out.println("capacidad::" + capacidad);
			    System.out.println("disponibilidad::" + disponibilidad);
			    System.out.println("rutaId::" + rutaId);
				   
			    try {
			        Ruta ru = _rutaLocalService.updateRuta(
			            serviceContext.getUserId(), rutaId, nombreRuta, capacidad, disponibilidad, serviceContext);
			        System.out.println(ru);
			//        response.setRenderParameter(
			        //	            "mvcPath", "/view.jsp");
			    } catch (PortalException pe) {

			        Logger.getLogger(RutaAdminPortlet.class.getName()).log(
			            Level.SEVERE, null, pe);

			        response.setRenderParameter(
			            "mvcPath", "/edit_ruta.jsp");
			    }
			}
		
		public void deleteRuta(ActionRequest request, ActionResponse response)
			    throws PortalException {

			    ServiceContext serviceContext = ServiceContextFactory.getInstance(
			        Ruta.class.getName(), request);

			    long rutaId = ParamUtil.getLong(request, "rutaId");

			    try {
			        _rutaLocalService.deleteRuta(rutaId, serviceContext);
			    }
			    catch (PortalException pe) {

			        Logger.getLogger(RutaAdminPortlet.class.getName()).log(
			            Level.SEVERE, null, pe);
			    }
			}


		@Reference(unbind = "-")
		private RutaLocalService _rutaLocalService;

}