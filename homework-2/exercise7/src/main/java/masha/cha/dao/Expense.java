package masha.cha.dao;

import java.math.BigDecimal;
import java.sql.Date;

public class Expense<T> {

    private int num;
    private Date date;
    private Receiver receiver;
    private BigDecimal value;

    public Expense() {
    }

    public Expense(int num, Date date, Receiver receiver, BigDecimal value) {
        this.num = num;
        this.date = date;
        this.receiver = receiver;
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return "Expense: " +
                " num = " + getNum() +
                ", date = " + getDate() +
                ", receiver = " + getReceiver().getNum() +
                ", value = " + getValue();
    }
}
