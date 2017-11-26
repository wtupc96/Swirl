package edu.xjtu.se;

import java.util.Date;

/**
 * @author wtupc96
 */
public class BarItem extends RawItem {
    public BarItem(Date date, double open, double high, double low, double close, double volume, double open_interest) {
        super(date, open, high, low, close, volume, open_interest);
    }
}
