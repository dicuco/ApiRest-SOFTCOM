package modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Schema(name="Resultado", description="Resultado de una operacion")
@XmlRootElement
public class Resultado {
    private double valor;
    
    public Resultado() {}
    
    public Resultado(double valor) {
        this.valor = valor;
    }

    @Schema(name="valor",requiredMode= Schema.RequiredMode.REQUIRED)
    @XmlElement
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}