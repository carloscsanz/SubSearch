package es.youtubeapp.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Esta Clase se encarga de recuperar los valores introducidos en el formulario para añadir nuevos documentos al indice, trata esos valores (limpiando los datos, normalizandolos,...) y añadirlos al indice.
 * Además de crear un documento .xml para poder utilizarlo en futuras versiones sin tener que volver a completar el formulario.
 * @author Carlos Contreras Sanz
 *
 */

public class AddDocumentRequestHandler implements RequestHandler {
	
	public static String [] campos = new String [] {"id", "user", "titulo", "autor", "fecha_publicacion", "categoria", "descripcion", "subtitulos"};

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//	CAMPOS DEL DOCUMENTO A INDEXAR:
		//		- ID (ID del video de YouTube)
		//		- TITULO
		//		- USER	(nick del canal)
		//		- AUTOR (Dueño del canal que ha subido el video)
		//		- FECHA PUBLICACION
		//		- CATEGORIA (Etiquetas de categoria del video encabvezadas por # y separadas por comas)
		//		- DESCRIPCION
		//		- CONTENIDO (Subtitulos sin marcas de tiempo del video)
				
		String id = request.getParameter(campos[0]);
		String user = request.getParameter(campos[1]);
		String titulo = request.getParameter(campos[2]);
		String autor = request.getParameter(campos[3]);
		String fecha = request.getParameter(campos[4]);
		String categoria = request.getParameter(campos[5]);
		String descripcion = request.getParameter(campos[6]);
		String subtitulos = request.getParameter(campos[7]);

		/*	DOCUMENTOS CORE MARCAS DE TIEMPO	*/
		Map<String, String> subtitulosMT = SubtitlesWithTimeMark(subtitulos);
		createDocumentsXMLMT(id, user, titulo, autor, fecha, categoria, descripcion, subtitulosMT);
		
		/*	DOCUMENTOS CORE SIN MARCAS DE TIEMPO	*/
		subtitulos = clearSubtitlesText(subtitulos);
		createDocumentXML(id, user, titulo,  autor,  fecha,  categoria,  descripcion,  subtitulos);
		
		return "FormAddDocument.jsp";
	}
			
			
	/**
	 * Método que se encarga de crear un fichero .xml con los datos del documento a añadir a la coleccion para tenerlos guardados y poder recuperar la coleccion cuando se desee.
	 * @param id
	 * @param user
	 * @param titulo
	 * @param autor
	 * @param fecha
	 * @param categoria
	 * @param descripcion
	 * @param subtitulos
	 * @throws IOException
	 */
	private void createDocumentXML(String id, String user, String titulo, String autor, String fecha, String categoria, String descripcion, String contenido) throws IOException{
		
		File archivo = new File("/Users/Carlos/Desktop/Documentos SubSearch/Documentos/" + id + ".xml");
		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
		
		bw.write("<add>\n\t<doc>\n");
		bw.write("\t\t<field name=\"id_video\">"+ id + "</field>\n");
		bw.write("\t\t<field name=\"user\">"+ user + "</field>\n");
		bw.write("\t\t<field name=\"titulo\">"+ titulo + "</field>\n");
		bw.write("\t\t<field name=\"autor\">"+ autor + "</field>\n");
		bw.write("\t\t<field name=\"fecha_publicacion\">"+ fecha + "</field>\n");
		bw.write("\t\t<field name=\"categoria\">"+ categoria + "</field>\n");
		bw.write("\t\t<field name=\"descripcion\">"+ descripcion + "</field>\n");
		bw.write("\t\t<field name=\"contenido\">"+ contenido + "\n\t\t</field>\n");
		bw.write("\t</doc>\n</add>");

		bw.close();
	}
	
	private void createDocumentXMLMT(String id, String user, String titulo, String autor, String fecha, String categoria, String descripcion, String contenido, String tiempo, int it) throws IOException{
		
		File archivo = new File("/Users/Carlos/Desktop/Documentos SubSearch/Documentos MT/" + id + "/" + id + "_" + it + ".xml");
		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
		
		bw.write("<add>\n\t<doc>\n");
		bw.write("\t\t<field name=\"id_video\">"+ id + "</field>\n");
		bw.write("\t\t<field name=\"user\">"+ user + "</field>\n");
		bw.write("\t\t<field name=\"titulo\">"+ titulo + "</field>\n");
		bw.write("\t\t<field name=\"autor\">"+ autor + "</field>\n");
		bw.write("\t\t<field name=\"fecha_publicacion\">"+ fecha + "</field>\n");
		bw.write("\t\t<field name=\"categoria\">"+ categoria + "</field>\n");
		bw.write("\t\t<field name=\"descripcion\">"+ descripcion + "</field>\n");
		bw.write("\t\t<field name=\"marca_tiempo\">"+ tiempo + "</field>\n");
		bw.write("\t\t<field name=\"contenido\">"+ contenido + "\n\t\t</field>\n");
		bw.write("\t</doc>\n</add>");

		bw.close();
	}
	
	private void createDocumentsXMLMT(String id, String user, String titulo, String autor, String fecha, String categoria, String descripcion, Map<String, String> subtitulos) throws IOException{
		
		int it = 1;
		File directorio = new File("/Users/Carlos/Desktop/Documentos SubSearch/Documentos MT/" + id );
		directorio.mkdir();
		
		for (Entry<String, String> entry : subtitulos.entrySet()){
			String contenido = entry.getValue();
			String tiempo = entry.getKey();
			
			createDocumentXMLMT(id, user, titulo, autor, fecha, categoria, descripcion, contenido, tiempo, it);
			
			it++;
		}
	}
	
	private Map<String, String> SubtitlesWithTimeMark(String subtitulos){
		
		String[] lines = subtitulos.split("\r\n|\r|\n");
		String tiempo = null;
		Map<String, String> marcasTiempo = new HashMap<String, String>();
				
		for(int i=0; i<lines.length; i++){
			
			if(!lines[i].equals("")){
				if(lines[i].contains("-->")){
					String [] temp = lines[i].split(",")[0].split(":");
					tiempo = temp[0] + "h" + temp[1] + "m" + temp[2] + "s";
					marcasTiempo.put(tiempo, "");
				}else if(!stringIsNumber(lines[i])){
					String contenido = marcasTiempo.get(tiempo) + ("\n\t\t\t" + lines[i]);
					marcasTiempo.put(tiempo, contenido);
				}
			}
		}
		
		return marcasTiempo;
	}
	

	/**
	 * Método que se encarga de limpiar el texto de los subtitulos recibido mediante el formulario de las marcas de tiempo que no aportan valor a la hora de indexar el documento
	 * @param subtitulos
	 * @return
	 */
	private String clearSubtitlesText(String subtitulos){
		
		String[] lines = subtitulos.split("\r\n|\r|\n");
		String subs = "";
		
		for(int i=0; i<lines.length; i++){
			if(!stringIsNumber(lines[i]) && !lines[i].contains("-->") && !lines[i].equals("") && !lines[i].contains("[")){
				subs = subs + ("\n\t\t\t" + lines[i]);
			}
		}
		
		return subs;
	}
		
	private boolean stringIsNumber(String cadena){
		try{
			Integer.parseInt(cadena);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
