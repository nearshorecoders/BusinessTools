package com.org.pos.utils;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

public class ImagePrint {
 public static void main(String[] args) throws Exception {
   PrintService service = PrintServiceLookup.lookupDefaultPrintService();
   DocPrintJob job = service.createPrintJob();
   DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
   SimpleDoc doc = new SimpleDoc(new MyPrintable(), flavor, null);
   
   PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
   aset.add(OrientationRequested.PORTRAIT);
   aset.add(MediaSizeName.INVOICE);
   job.print(doc,aset);
   
 }
}
