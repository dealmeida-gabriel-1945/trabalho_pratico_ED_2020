package main.datashape;

import java.time.LocalDateTime;
import java.util.Random;

public class Container {

    public String serial;


    public void prepare() {
        Random rand = new Random();
        this.serial = LocalDateTime.now().toString() +  "_containers_" + rand.nextInt() + "-" + rand.toString();
    }
}
