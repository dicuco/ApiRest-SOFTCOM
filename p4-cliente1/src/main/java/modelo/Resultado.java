package modelo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resultado {
    private double valor;
    
    public Resultado() {}
    
    public Resultado(double valor) {
        this.valor = valor;
    }

    @XmlElement
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}