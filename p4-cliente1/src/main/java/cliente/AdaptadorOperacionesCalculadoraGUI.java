package cliente;


import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import calculadoraGUI.ICalculadoraFunciones;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Resultado;
import modelo.Operaciones;


/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se puede utilizar un adaptador de clase o de objeto.
 */
class AdaptadorOperacionesCalculadoraGUI implements  ICalculadoraFunciones {

	private Client client;
    private WebTarget target;

    public AdaptadorOperacionesCalculadoraGUI() {
        client = ClientBuilder.newClient().register(JacksonJsonProvider.class);

        target = client.target("http://localhost:8080/calculadora");
    }
    
	@Override
	public double sumar(double operando1, double operando2) {

	    WebTarget t = target.path("sumar").queryParam("operando1", operando1)
	            .queryParam("operando2", operando2);

	    Response r = t.request(MediaType.APPLICATION_JSON).get();
	    Resultado res = r.readEntity(Resultado.class);
	    	
	    return res.getValor();
	}
	
	@Override
	public double restar(double operando1, double operando2) {
	    WebTarget t = target.path("restar").queryParam("operando1", operando1)
	            .queryParam("operando2", operando2);

	    Response r = t.request(MediaType.APPLICATION_JSON).get();
	    Resultado res = r.readEntity(Resultado.class);
	    	
	    return res.getValor();
	}
	
	@Override
	public String[] getOperaciones(int numeroBotonesDisponibles) {

	    WebTarget t = target
	            .path("operaciones")
	            .queryParam("numeroOperaciones", numeroBotonesDisponibles);

	    Operaciones lista = t
	            .request(MediaType.APPLICATION_JSON)
	            .get(Operaciones.class);

	    return lista.getOperaciones().toArray(new String[0]);
	}
	
	@Override
	public double dividir(double dividendo, double divisor) throws Exception {
	    WebTarget t = target.path("dividir").queryParam("dividendo", dividendo).queryParam("divisor", divisor);

	    Response r = t.request(MediaType.APPLICATION_JSON).get();
	    if (r.getStatus() == 200) {
	        Resultado res = r.readEntity(Resultado.class);
	        return res.getValor();
	    } else {
	        throw new Exception(r.readEntity(String.class));
	    }	    	
	}
	
	@Override
	public double operar(int numeroDeOperacion, double operando) throws Exception {
	    WebTarget t = target.path("operar").queryParam("operacion", numeroDeOperacion).queryParam("operando", operando);

	    Response r = t.request(MediaType.APPLICATION_JSON).get();
	    if (r.getStatus() == 200) {
	        Resultado res = r.readEntity(Resultado.class);
	        return res.getValor();
	    } else {
	        throw new Exception(r.readEntity(String.class));
	    }	    
	}
	
	@Override
	public double multiplicar(double operando1, double operando2) {
	    WebTarget t = target.path("multiplicar").queryParam("operando1", operando1)
	            .queryParam("operando2", operando2);

	    Response r = t.request(MediaType.APPLICATION_JSON).get();
	    Resultado res = r.readEntity(Resultado.class);
	    	
	    return res.getValor();
	}
	
	@Override
	public double memoriaObtener() {
		 WebTarget t = target.path("memoria");
		 Response r= t.request(MediaType.APPLICATION_JSON).get();
		 Resultado res = r.readEntity(Resultado.class);
		 return res.getValor();
	}
	
	@Override
	public double obtenerUltimoResultado() {
		 WebTarget t = target.path("ur");
		 Response r= t.request(MediaType.APPLICATION_JSON).get();
		 Resultado res = r.readEntity(Resultado.class);
		 return res.getValor();
	}
	
	@Override
	public void memoriaAniadir() {
		WebTarget t = target.path("memoria");
        t.request().head();
	}
	
	@Override
	public void memoriaLimpiar() {
		WebTarget t = target.path("memoria");
        t.request().delete();
	}
	
	
}