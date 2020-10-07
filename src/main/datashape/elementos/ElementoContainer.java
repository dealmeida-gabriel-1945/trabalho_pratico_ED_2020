package main.datashape.elementos;

import main.datashape.Container;

public class ElementoContainer {
    public Container container;
    public  ElementoContainer proximo;

    public ElementoContainer(Container container){
        this.container = container;
    }
}
