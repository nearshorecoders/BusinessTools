package com.org.pos.utils;

import java.awt.print.Printable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;


public class Ticket {

    private String contentTicket = "\n{{nameLocal}}\n"
            + "{{expedition}}\nTels: 484-43-29,462-26-86\n"
            + "----------------\n"
            + "Ticket #{{ticket}} "+ "Fecha:{{dateTime}}\n"
            + "Le atendio: {{cajero}}\nCliente:{{clienteCompro}}\nDireccion entrega:{{clienteDir}}\n"
            + "Cant. Descripcion        T Costo\n"
            + "----------------\n"
            + "{{items}}\n"
            + "----------------\n"
             + "{{refrescoE}}\n"
            //+ "SUBTOTAL: {{subTotal}}\n"
            + "EFECTIVO: {{recibo}}\n"
            + "TOTAL: {{total}} | CAMBIO: {{change}}\n"
            + "----------------\n"
            + "GRACIAS POR SU COMPRA...\n"
            + "******::::::::*******"
            + "\nEste ticket no es un comprobante fiscal"
            + "\n----------------"
            + "\nSistema desarrollado por Nearshore"
            + "\nCoders www.nearshorecoders.com"
            + "\nCel. 248-156-156-7"    
            + "\n           "
            + "\n           "
            + "\n           .\n";

    String impresionAbrirCaja= ".";
    public Ticket(){
        impresionAbrirCaja=".";
    }
    //El constructor que setea los valores a la instancia
    public Ticket(String nameLocal, String expedition, String ticket, String caissier, String dateTime, String items, String subTotal, String tax, String total, String recibo, String change,String cliente,String dirEntrega,String refresco) {
        this.contentTicket = this.contentTicket.replace("{{nameLocal}}", nameLocal);
        this.contentTicket = this.contentTicket.replace("{{expedition}}", expedition);
        this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
        this.contentTicket = this.contentTicket.replace("{{cajero}}", caissier);
        this.contentTicket = this.contentTicket.replace("{{dateTime}}", dateTime);
        this.contentTicket = this.contentTicket.replace("{{items}}", items);
        this.contentTicket = this.contentTicket.replace("{{subTotal}}", subTotal);
        this.contentTicket = this.contentTicket.replace("{{tax}}", tax);
        this.contentTicket = this.contentTicket.replace("{{total}}", total);
        this.contentTicket = this.contentTicket.replace("{{recibo}}", recibo);
        this.contentTicket = this.contentTicket.replace("{{change}}", change);
        this.contentTicket= this.contentTicket.replace("{{clienteCompro}}", cliente);
        this.contentTicket= this.contentTicket.replace("{{clienteDir}}", dirEntrega);
        this.contentTicket= this.contentTicket.replace("ñ", "n");
        this.contentTicket= this.contentTicket.replace("{{refrescoE}}",refresco);
        this.contentTicket=this.contentTicket.trim();
        System.out.println(contentTicket);
    }

    public void Imprimir() {
        //Especificamos el tipo de dato a imprimir
        //Tipo: bytes; Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        //Aca obtenemos el servicio de impresion por defatul
       
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        
        //Creamos un arreglo de tipo byte
        byte[] bytes;
        bytes = this.contentTicket.getBytes();

        Doc doc = new SimpleDoc(bytes, flavor, null);
//        //Creamos un trabajo de impresiÃ³n
        DocPrintJob job = service.createPrintJob();
        //Imprimimos dentro de un try de a huevo
        try {
            //El metodo print imprime
            job.print(doc, null);
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error al imprimir: " + er.getMessage());
        }
    }
    
    public void AbrirCaja() {
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob job = service.createPrintJob();
        //DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        byte[] bytes;
        bytes = this.contentTicket.getBytes();
        SimpleDoc doc = new SimpleDoc(new AbrirCaja(), flavor, null);

        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(OrientationRequested.PORTRAIT);
        aset.add(MediaSizeName.INVOICE);
        try {
            job.print(doc,aset);
        } catch (PrintException ex) {
            //Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


