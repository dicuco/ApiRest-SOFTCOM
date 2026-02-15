package modelo;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Schema(name="Operaciones", description="Lista Operaciones")
@XmlRootElement 
public class Operaciones {
	 private List<String> operaciones;

	    public Operaciones() {}

	    public Operaciones(List<String> operaciones) {
	        this.operaciones = operaciones;
	    }
	    @Schema(requiredMode= Schema.RequiredMode.REQUIRED)
	    @XmlElement(name = "operacion")
	    public List<String> getOperaciones() {
	        return operaciones;
	    }

	    public void setOperaciones(List<String> operaciones) {
	        this.operaciones = operaciones;
	    }
}
