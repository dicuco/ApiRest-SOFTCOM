package cliente;


import calculadoraGUI.ICalculadoraFunciones;
import restclient.ApiClient;
import restclient.ApiException;
import restclient.Configuration;
import restclient.api.CalculadoraApi;
import restclient.model.Operaciones;
import restclient.model.Resultado;
import restclient.ApiClient;


/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se puede utilizar un adaptador de clase o de objeto.
 */
class AdaptadorOperacionesCalculadoraGUI implements ICalculadoraFunciones{
	CalculadoraApi apiInstance;
	ApiClient defaultClient;
	
	public AdaptadorOperacionesCalculadoraGUI() {
		defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");
        this.apiInstance= new CalculadoraApi(defaultClient);
	}
	
	@Override
	public double sumar(double operando1, double operando2) {
		try {
			Resultado result= apiInstance.sumar(operando1, operando2);
			return result.getValor();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al sumar", e);
		}
	}

	@Override
	public double dividir(double dividendo, double divisor) throws Exception {
		try {
			Resultado result= apiInstance.dividir(dividendo, divisor);
			return result.getValor();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al dividir", e);
		}
	}

	@Override
	public String[] getOperaciones(int numeroBotonesDisponibles) {
		try {
			Operaciones operaciones= apiInstance.getOperaciones(numeroBotonesDisponibles);
			String[] ops=operaciones.getOperaciones().toArray(new String[0]);
			return ops;
			
		} catch (ApiException e) {
		    throw new RuntimeException("Error al obtener lista operaciones", e);
		}
	
	}

	@Override
	public void memoriaAniadir() {
		try {
			apiInstance.addMemoria();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al aniadir memoria", e);
		}
	}

	@Override
	public void memoriaLimpiar() {
		try {
			apiInstance.clearMemoria();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al borrar memoria", e);
		}
	}

	@Override
	public double memoriaObtener() {
		try {
			Resultado result= apiInstance.getMemoria();
			return result.getValor();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al obtener memoria", e);
		}
	}

	@Override
	public double multiplicar(double operando1, double operando2) {
		try {
			Resultado result= apiInstance.multiplicar(operando1, operando2);
			return result.getValor();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al multiplicar", e);
		}
	}

	@Override
	public double obtenerUltimoResultado() {
		try {
			Resultado result= apiInstance.getUR();
			return result.getValor();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al obtener el ultimo resultado", e);
		}
	}

	@Override
	public double operar(int numeroDeOperacion, double operando) throws Exception {
		try {
			Resultado result= apiInstance.operar(numeroDeOperacion, operando);
			return result.getValor();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al operar", e);
		}
	}

	@Override
	public double restar(double operando1, double operando2) {
		try {
			Resultado result= apiInstance.restar(operando1, operando2);
			return result.getValor();
		} catch (ApiException e) {
		    throw new RuntimeException("Error al restar", e);
		}
	}
	
	
	
	
}	