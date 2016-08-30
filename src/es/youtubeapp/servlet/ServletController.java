package es.youtubeapp.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletController")
public class ServletController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Hash table of RequestHandler instances, keyed by request URL
	private Map<String, RequestHandler> handlerHash = new HashMap<String, RequestHandler>();

	// Initialize mappings: not implemented here
	public void init() throws ServletException {

		// This will read mapping definitions and populate handlerHash
		handlerHash.put("/Inicio.input", new es.youtubeapp.servlet.RandomDataRequestHandler());
		handlerHash.put("/Query.input", new es.youtubeapp.servlet.QueryRequestHandler());
		handlerHash.put("/Categoria.input", new es.youtubeapp.servlet.CategoryRequestHandler());
		handlerHash.put("/AdvancedSearch.input", new es.youtubeapp.servlet.AdvancedSearchRequestHandler());


		handlerHash.put("/addDocument.input", new es.youtubeapp.servlet.AddDocumentRequestHandler());
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve from the HashMap the instance of the class which implements the logic of the requested url
		RequestHandler rh = (RequestHandler) handlerHash.get(request.getServletPath());

		// If no instance is retrieved redirects to error
		if (rh == null) {
			
			response.sendError(HttpServletResponse.SC_NOT_FOUND);

		}else{

			// Call the method handleRequsest of the instance in order to obtain the url
			String viewURL = rh.handleRequest(request, response);

			// Dispatch the request to the url obtained
			if (viewURL != null) {
				
				request.getRequestDispatcher(viewURL).forward(request, response);
				
			}
		}	
	}

}
