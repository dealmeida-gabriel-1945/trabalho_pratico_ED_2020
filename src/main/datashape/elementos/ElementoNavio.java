package main.datashape.elementos;

import main.datashape.Navio;

public class ElementoNavio {
    public Navio navio;
    public ElementoNavio proximo = null;

    public ElementoNavio(Navio navio){
        this.navio = navio;
    }
}