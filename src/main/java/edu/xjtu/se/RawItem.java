package edu.xjtu.se;

import java.util.Date;

/**
 * @author wtupc96
 *
 */
public class RawItem {
    protected Date date;
    protected double open;
    protected double high;
    protected double low;
    protected double close;
    protected double volume;
    protected double open_interest;

    public RawItem(Date date, double open, double high, double low, double close, double volume, double open_interest) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.open_interest = open_interest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getOpen_interest() {
        return open_interest;
    }

    public void setOpen_interest(double open_interest) {
        this.open_interest = open_interest;
    }
}
