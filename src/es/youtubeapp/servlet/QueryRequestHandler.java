package es.youtubeapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.youtubeapp.conex.SolrQueries;

public class QueryRequestHandler implements RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String consulta = (String) request.getParameter("Busqueda");
		String pagina = request.getParameter("Page");
		
		SolrQueries query = new SolrQueries();
		ArrayList<Map<String, String>> resultado;

		if(pagina == null){
			resultado = query.DoSimpleQuery(consulta, 10, 0);
			pagina = "1";
		}else{
			resultado = query.DoSimpleQuery(consulta, 10, (Integer.parseInt(pagina)-1)*10);
		}
		int paginas = (int) Math.ceil(query.getNumeroDocumentos()/10.0);
		
		request.setAttribute("resultado", resultado);
		request.setAttribute("MaxPages", paginas);
		request.setAttribute("ActivePage", Integer.parseInt(pagina));
		request.setAttribute("Busqueda", consulta);

		return "Inicio.jsp";
	}

}
