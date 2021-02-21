package utils;

import javax.swing.*;
import java.awt.*;

public class Input extends JTextField {
    private String placeHolder;

    public Input(){}

    public Input(String s){
        placeHolder=s;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(placeHolder == null || placeHolder.length() == 0 || getText().length() > 0){
            return;
        }

        final Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(getDisabledTextColor());
        g2.setColor(new Color(211,211,211));
        g2.setFont(new Font("宋体",Font.BOLD,14));
        g2.drawString(placeHolder,getInsets().left,g.getFontMetrics().getMaxAscent()+getInsets().top);
    }

}
