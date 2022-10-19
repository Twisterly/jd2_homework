package masha.cha.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Expense {

    private int num;
    private Date paydate;
    private int receiver;
    private BigDecimal value;
    private BigDecimal totalValue;

    public Expense(int num, Date paydate, int receiver, BigDecimal value) {
        this.num = num;
        this.paydate = paydate;
        this.receiver = receiver;
        this.value = value;
    }

    public Expense(int receiver, BigDecimal totalValue) {
        this.receiver = receiver;
        this.totalValue = totalValue;
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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String toString() {
        return "Expenses " +
                " num = " + getNum() +
                ", date = " + getPaydate() +
                ", receiver = " + getReceiver() +
                ", value = " + getValue() +
                ", total valur = " + getTotalValue();
    }


}
