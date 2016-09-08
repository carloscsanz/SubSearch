package es.youtubeapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.youtubeapp.conex.SolrQueries;

public class AdvancedSearchRequestHandler implements RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = request.getParameter("Page");
		String allWords = request.getParameter("AllWords");
		String anyWord = request.getParameter("AnyWord");
		String notAnyWord = request.getParameter("NotAnyWord");
		String category = request.getParameter("Category");
		String author = request.getParameter("Author");
		String dateSince = request.getParameter("DateSince");
		String dateUntil = request.getParameter("DateUntil");
		String timeMark = request.getParameter("Time");
		
		/*	CAMPO TODAS ESTAS PALABRAS*/
		if(allWords != null){
			if(allWords.trim() == ""){
				allWords = null;
			}else{
				request.setAttribute("AllWords", allWords);
			}
		}
		
		
		/*	CAMPO CUALQUIERA DE ESTAS PALABRAS*/
		if(anyWord != null){
			if(anyWord.trim() == ""){
				anyWord = null;
			}else{
				if(!anyWord.startsWith("|")){
					String anyWords = "";
					StringTokenizer st = new StringTokenizer(anyWord, " ");
					while(st.hasMoreTokens()){
						anyWords = anyWords + "|" + st.nextToken();
					}
					anyWord = anyWords;
				}
				request.setAttribute("AnyWord", anyWord);
			}
		}
		
		
		/*	CAMPO NINGUNA DE ESTAS PALABRAS	*/
		if(notAnyWord != null){
			if(notAnyWord.trim() == ""){
				notAnyWord = null;
			}else{
				if(!notAnyWord.startsWith("-")){
					String notWords = "";
					StringTokenizer st = new StringTokenizer(notAnyWord, " ");
					while(st.hasMoreTokens()){
						notWords = notWords + "-" + st.nextToken();
					}
					notAnyWord = notWords;
				}
				request.setAttribute("NotAnyWord", notAnyWord);
			}
		}
		
		/*	CAMPO MARCAS DE TIEMPO	*/
		boolean time = true;
		if(timeMark != null){
			if(!timeMark.equals("true")){
				time = false;
				request.setAttribute("Time", "false");
			}else{
				request.setAttribute("Time", "true");
			}
		}
		
		
		/*	CAMPO CATEGORIA	*/
		if(category != null){
			if(!category.equals("*")){
				request.setAttribute("Category", category);
			}else{
				category = null;
			}
		}

		
		/*	CAMPO AUTOR	*/
		if(author != null){
			if(author.trim() == ""){
				author = null;
			}else{
				request.setAttribute("Author", author);
			}
		}
		
		
		/*	CAMPO DATE	*/
		if(dateSince != null){
			if(dateSince == ""){
				dateSince = null;
			}else{
				request.setAttribute("DateSince", dateSince);
				dateSince = dateSince + "T00:00:00Z";
			}
		}
		
		if(dateUntil != null){
			if(dateUntil == ""){
				dateUntil = null;
			}else{
				request.setAttribute("DateUntil", dateUntil);
				dateUntil = dateUntil + "T00:00:00Z";
			}
		}
		
//		System.out.println("_________________________________");
//		System.out.println("allWords: " + allWords);
//		System.out.println("anyWord: " + anyWord);
//		System.out.println("notAnyWord: " + notAnyWord);
//		System.out.println("categoria: " + category);
//		System.out.println("author: " + author);
//		System.out.println("dateSince: " + dateSince);
//		System.out.println("dateUntil: " + dateUntil);
//		System.out.println("_________________________________");
		
		SolrQueries query = new SolrQueries();
		ArrayList<Map<String, String>> resultado;

		if(pagina == null){
			resultado = query.DoAdvancedQuery(allWords, anyWord, notAnyWord, category, author, dateSince, dateUntil, time, 10, 0);
			pagina = "1";
		}else{
			resultado = query.DoAdvancedQuery(allWords, anyWord, notAnyWord, category, author, dateSince, dateUntil, time, 10, (Integer.parseInt(pagina)-1)*10);
		}
		int paginas = (int) Math.ceil(query.getNumeroDocumentos()/10.0);

		request.setAttribute("resultado", resultado);
		request.setAttribute("MaxPages", paginas);
		request.setAttribute("ActivePage", Integer.parseInt(pagina));

		return "Inicio.jsp";
	}

}
