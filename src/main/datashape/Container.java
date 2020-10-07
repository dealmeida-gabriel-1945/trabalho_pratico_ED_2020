package main.datashape;

public class Container {

    public String id;

    public Container(){}
    public Container(String id){this.id = id;}

    public static void show(Container container){
        System.out.println("\t\t\t\t\tID: " + container.id);
    }
}
