package es.youtubeapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestHandler {
	String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
