package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * Obtenemos el carrito guardado en sesi�n.Si no hay carrito, se trata de un nuevo usuario
	 * entonces instanciamos un nuevo carrito y lo insertamos en sesi�n.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		HashMap<String,Integer> carrito =
		 (HashMap<String,Integer>) request.getSession().getAttribute("carrito");
		// No hay carrito, creamos uno y lo insertamos en sesi�n
		if (carrito == null) {
		carrito = new HashMap<String,Integer>();
		 request.getSession().setAttribute("carrito", carrito);
		}
		String producto = request.getParameter("producto");
		if ( producto != null){
		insertarEnCarrito(carrito, producto);
		}
		// Retornar la vista con par�metro "carrito"
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request,
		response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Insertamos nombre del producto(clave) y n�mero de unidades(valor) en el carrito
	 * 
	 * @param carrito
	 * @param claveProducto
	 */
	private void insertarEnCarrito(Map<String,Integer> carrito, String claveProducto) {
		
		if(carrito.get(claveProducto) == null)
			carrito.put(claveProducto,new Integer(1));
		else {
			int numeroArticulos = carrito.get(claveProducto).intValue();
			carrito.put(claveProducto, new Integer (numeroArticulos+1));
		}
	       
	}
	/**
	 * Devuelve una cadena con todos los elementos del carrito
	 * 
	 * @param carrito
	 * @return
	 */
	private String carritoEnHTML (Map<String,Integer> carrito) {
		
		String carritoEnHTML="";
		
		for(String key:carrito.keySet())
			carritoEnHTML += "<p>[" +key+"]" + carrito.get(key) + " unidades</p>";
		return carritoEnHTML;
		
		
	}
	

	/**
	 * Insertamos nombre del producto(clave) y n�mero de unidades(valor) en el carrito
	 * 
	 * @param carrito
	 * @param claveProducto
	 */
	
}
