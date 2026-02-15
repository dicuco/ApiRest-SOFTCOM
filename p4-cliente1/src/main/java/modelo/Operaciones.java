package modelo;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Operaciones {
	 private List<String> operaciones;

	    public Operaciones() {}

	    public Operaciones(List<String> operaciones) {
	        this.operaciones = operaciones;
	    }

	    @XmlElementWrapper(name = "operaciones")
	    @XmlElement(name = "operacion")
	    public List<String> getOperaciones() {
	        return operaciones;
	    }

	    public void setOperaciones(List<String> operaciones) {
	        this.operaciones = operaciones;
	    }
}
