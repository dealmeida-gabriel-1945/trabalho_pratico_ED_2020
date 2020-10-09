package main.datashape;

public class Container {

    public String id;

    public Container(){}
    public Container(String id){this.id = id;}

    public void show(){
        System.out.println("\t\t\t\t\tID: " + this.id);
    }
}
