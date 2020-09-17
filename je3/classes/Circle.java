package je3.classes;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class Circle {

    int centerX, centerY, radius;

    public Circle(int cx, int cy, int rad){
        centerX = cx;
        centerY = cy;
        radius = rad;
    }

    public void move(int dx, int dy) {
        centerX += dx;
        centerY += dy;
    }

    public boolean isInside(int x, int y) {
        int dx = (x > centerX) ? x-centerX : centerX - x;
        int dy = (y > centerY) ? y-centerY : centerY - y;
        double hyp = Math.sqrt( dx * dx + dy * dy);
        return (hyp < radius);
    }

    public Rectangle boundingBox(){
        return new Rectangle(centerX - radius,centerY - radius, radius *2, radius*2);
    }

}
