package Game;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CustomShape implements Shape {
  GeneralPath path;

  public CustomShape(float x, float y, float w, float h) {
	    path = new GeneralPath();
	    
	    float x0 = x + 0.17f*w;
	    float y0 = y + 0.24f*h;
	    float x1 = x + 0.33f*w;
	    float y1 = y + 0.24f*h;
	    float x2 = x + 0.53f*w;
	    float y2 = y + 0.6f*h;  
	    float x3 = x + 0.17f*w;
	    float y3 = y + 0.4f*h;
	    
	    float x4 = x + 0.53f*w;
	    float y4 = y + 0.6f * h;
	    float x5 = x + 0.73f * w;
	    float y5 = y + 0.24f * h;
	    float x6 = x + 0.89f * w;
	    float y6 = y + 0.24f * h;
	    float x7 = x + 0.89f * w;
	    float y7 = y + 0.4f * h;
	    float x8 = x + 0.53f * w;
	    float y8 = y + 0.6f * h;
	    
	    float x9 = x + 0.53f*w;
	    float y9 = y + 0.6f * h;
	    float x10 = x + 0.17f*w;
	    float y10 = y + 0.8f * h;
	    float x11 = x + 0.17f*w;
	    float y11 = y + 0.96f * h;
	    float x12 = x + 0.33f*w;
	    float y12 = y + 0.96f * h;
	    float x13 = x + 0.53f*w;
	    float y13 = y + 0.6f * h;
	    
	    float x14 = x + 0.53f*w;
	    float y14 = y + 0.6f * h;
	    float x15 = x + 0.73f*w;
	    float y15 = y + 0.96f * h;
	    float x16 = x + 0.89f*w;
	    float y16 = y + 0.96f * h;
	    float x17 = x + 0.89f*w;
	    float y17 = y + 0.8f * h;
	    float x18 = x + 0.53f*w;
	    float y18 = y + 0.6f * h;
	    
	    path.moveTo(x0, y0);
	    path.lineTo(x1, y1);
	    path.lineTo(x2, y2);
	    path.lineTo(x3, y3);
	    
	    path.moveTo(x4, y4);
	    path.lineTo(x5,y5);
	    path.lineTo(x6,y6);
	    path.lineTo(x7,y7);
	    path.lineTo(x8,y8);
	    
	    path.moveTo(x9, y9);
	    path.lineTo(x10,y10);
	    path.lineTo(x11,y11);
	    path.lineTo(x12,y12);
	    path.lineTo(x13,y13);
	    
	    path.moveTo(x14, y14);
	    path.lineTo(x15,y15);
	    path.lineTo(x16,y16);
	    path.lineTo(x17,y17);
	    path.lineTo(x18,y18);
	    
	    path.closePath();

  }

  public boolean contains(Rectangle2D rect) {
    return path.contains(rect);
  }

  public boolean contains(Point2D point) {
    return path.contains(point);
  }

  public boolean contains(double x, double y) {
    return path.contains(x, y);
  }

  public boolean contains(double x, double y, double w, double h) {
    return path.contains(x, y, w, h);
  }

  public Rectangle getBounds() {
    return path.getBounds();
  }

  public Rectangle2D getBounds2D() {
    return path.getBounds2D();
  }

  public PathIterator getPathIterator(AffineTransform at) {
    return path.getPathIterator(at);
  }

  public PathIterator getPathIterator(AffineTransform at, double flatness) {
    return path.getPathIterator(at, flatness);
  }

  public boolean intersects(Rectangle2D rect) {
    return path.intersects(rect);
  }

  public boolean intersects(double x, double y, double w, double h) {
    return path.intersects(x, y, w, h);
  }
}
