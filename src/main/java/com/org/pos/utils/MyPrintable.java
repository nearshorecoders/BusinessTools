package com.org.pos.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

import javax.swing.ImageIcon;

public class MyPrintable implements Printable {
    
	  
	  ImageIcon printImage = new javax.swing.ImageIcon("C:\\printImage.png");

	  @Override
	  public int print(Graphics g, PageFormat pf, int pageIndex) {
	    Graphics2D g2d = (Graphics2D) g;
	    g.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));
	    if (pageIndex == 0) {
	      double pageWidth = pf.getImageableWidth();
	      double pageHeight = pf.getImageableHeight();
	      double imageWidth = printImage.getIconWidth();
	      double imageHeight = printImage.getIconHeight();
	      double scaleX = pageWidth / imageWidth;
	      double scaleY = pageHeight / imageHeight;
	      double scaleFactor = Math.min(scaleX, scaleY);
	      g2d.scale(scaleFactor, scaleFactor);
	      g.drawImage(printImage.getImage(), 0, 0, null);
	      return Printable.PAGE_EXISTS;
	    }
	    return Printable.NO_SUCH_PAGE;
	  }
	  
	}
