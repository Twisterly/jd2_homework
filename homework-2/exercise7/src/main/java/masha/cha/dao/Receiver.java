package masha.cha.dao;


public class Receiver {

    private int num;
    private String name;

    public Receiver() {
    }

    public Receiver(int num) {
        this.num = num;
    }

    public Receiver(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
