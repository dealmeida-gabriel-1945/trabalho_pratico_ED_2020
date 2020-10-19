package main.datashape.elementos;

import main.datashape.Navio;

import java.util.Objects;

public class ElementoNavio {
    public Navio navio;
    public ElementoNavio proximo = null;

    public ElementoNavio(Navio navio){
        this.navio = navio;
    }
    public ElementoNavio(){}

    //deep copy da classe
    public static ElementoNavio clone(ElementoNavio elementoOriginal) {
        if(Objects.isNull(elementoOriginal)) return null;
        ElementoNavio clone = new ElementoNavio();
        clone.navio = Navio.clone(elementoOriginal.navio);
        clone.proximo = ElementoNavio.clone(elementoOriginal.proximo);
        return clone;
    }
}
