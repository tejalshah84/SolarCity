/*
 * This servlet supports a POST call to /solarprospect. The post call would be made ideally through a form available
 * in the front-end that accepts prospect name, age, address and other details. The servlet accepts the prospect info
 * in JSON format, saves all the prospect details in the DB and sends back a confirmation to the user.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import database.DBConnectMySQL;
import model.Prospect;

@WebServlet("/solarprospect")
public class SolarSalesServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = request.getRequestDispatcher("solar.html");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Read JSON string from request
			int length = request.getContentLength();
			byte[] input = new byte[length];
			ServletInputStream sin = request.getInputStream();
			int c, count = 0 ;
			String jsonString = "";
			while ((c = sin.read(input, count, input.length-count)) != -1) {
				count +=c;
			}
			sin.close();
			jsonString = new String(input);

			//Map JSON string to prospect object
			ObjectMapper mapper = new ObjectMapper();
			Prospect p = mapper.readValue(jsonString, Prospect.class);

			//Save prospect in DB
			boolean done = DBConnectMySQL.saveProspect(p);

			//Write JSON response indicating success or failure of prospect inquiry registration
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();

			ObjectNode objectNode = mapper.createObjectNode();

			if (done){
				objectNode.put("respMsg", p.printSuccessMessage());
			}else{
				objectNode.put("respMsg", p.printErrorMessage());
			}
			out.println(objectNode.toString());
			out.flush();
			out.close();

		}catch(IOException e) {
			try{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().print(e.getMessage());
				response.getWriter().close();
			} catch (IOException ioe) {

			}
		}
	}
}
