package calculadora;

/** Esta clase contiene la implementación final de cada operación disponible en la calculadora.
 * Debe contener un método público de instancia por cada método de la interfaz CalculadoraGUI.ICalculadora.
 * Para evitar confusiones, se recomienda que cada uno de los métodos citados anteponga 'implementacion' al
 * nombre del método de CalculadoraGUI.ICalculadora. Por ejemplo: si se desea crear el método que implementa
 * la suma, su nombre en esta clase será 'implementacionSumar'.
 */
public class OperacionesCalculadora {
	// Implementar aquí lo que se necesite.
	static double ultimoValor;
	static double memoriaAcumulada;
	public static final double TOLERANCE = 1e-6;
	
    public OperacionesCalculadora() {
        ultimoValor = 0;
        memoriaAcumulada = 0;
    }
	
	public double implementacionSumar(double operando1, double operando2) {
		ultimoValor=(operando1+operando2);
		return ultimoValor;
	}
		
	public double implementacionRestar(double operando1, double operando2) {
		ultimoValor=(operando1-operando2);
		return ultimoValor;
	}
		
	public double implementacionMultiplicar(double operando1, double operando2) {
		ultimoValor=(operando1*operando2);
		return ultimoValor;
	}
		
	public double implementacionDividir(double operando1, double operando2) throws Exception {
	    if (operando1 == 0 && operando2 == 0) {
	        throw new Exception("Indeterminación: 0 dividido entre 0");
	    }
	    
		if(operando2==0) {	
			throw new Exception("No se puede dividir entre 0");
		}

		ultimoValor=(operando1/operando2);
		return ultimoValor;
	}
	
	public double implementacionRaizCuadrada(double operando) throws Exception{
		if(operando<0) {
			throw new Exception("No se puede hacer la raiz de un número negativo");
		}
		ultimoValor=Math.sqrt(operando);
		return ultimoValor;
	}
	
	public double implementacionObtenerUR() {
		return ultimoValor;
	}
	
	public void implementacionMA() {
		memoriaAcumulada+=ultimoValor;
	}

	public void implementacionML() {
		memoriaAcumulada=0;
	}
	
	public double implementacionMO() {
		return memoriaAcumulada;
	}
	
	public double implementacionCuadrado(double x) {
		ultimoValor=(x*x);
		return ultimoValor;
	}
	
	public double implementacionTg(double x) throws Exception{
		if(esCercanoPiMedios(x)) {
			throw new Exception("Tangente infinita");
		}
		ultimoValor=Math.tan(x);
		return ultimoValor;
	}
	
	private double normalizarRadianes(double angulo) {
		double dosPi=2*Math.PI;
		angulo=angulo%dosPi;
		if(angulo<0) {
			angulo+=dosPi;
		}
		return angulo;
	}
	
	private boolean esCercanoPiMedios(double x) {
		double piMedios=Math.PI/2;
		double tresPiMedios=3*Math.PI/2;
		double angulo=normalizarRadianes(x);
				
		return Math.abs(angulo-piMedios) < TOLERANCE || Math.abs(angulo-tresPiMedios)<TOLERANCE;
	}
	
	public double implementacionLg(double x) throws Exception {
		if(x<0) {
			throw new Exception("Valor menor a 0");
		}
		ultimoValor=Math.log(x);
		return ultimoValor;
	}
	
}
