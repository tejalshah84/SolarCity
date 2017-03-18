/*
 * This servlet accepts a GET call to /solarcells with the parameter priceLimit, whose value would be an integer.
 * Example: http://localhost:8080/SolarCity/solarcells?priceLimit=10
 * The API returns a list of all the solar cells from the database 
 * whose price per watt is less than or equal to the request parameter (priceLimit) value.
 * 
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import database.DBConnectMySQL;
import model.SolarCell;

@WebServlet("/solarcells")
public class SolarCellsServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Extract Parameter from GET Call
		int priceLimit = Integer.valueOf(request.getParameter("priceLimit"));

		//GET list of solar cells from DB meeting the priceLimit criterion
		ArrayList<SolarCell> listSC = DBConnectMySQL.listSolarCells(priceLimit);
		if(listSC==null){
			listSC = new ArrayList<SolarCell>();
		}

		//Convert list of solar cells to JSON format
		ObjectMapper mapper = new ObjectMapper();
		String jsonoutput = mapper.writeValueAsString(listSC);
		
		//Send JSON string in response object
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonoutput);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}
}
