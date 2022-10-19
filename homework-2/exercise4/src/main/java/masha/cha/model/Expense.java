package masha.cha.model;

import java.math.BigDecimal;
import java.sql.Date;


public class Expense {

    private int num;
    private Date paydate;
    private int receiver;
    private BigDecimal value;

    public Expense(int num, Date paydate, int receiver,
                   BigDecimal value) {
        this.num = num;
        this.paydate = paydate;
        this.receiver = receiver;
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String toString() {
        return "Expense: " +
                " num = " + getNum() +
                ", date = " + getPaydate() +
                ", receiver = " + getReceiver() +
                ", value = " + getValue();
    }


}
