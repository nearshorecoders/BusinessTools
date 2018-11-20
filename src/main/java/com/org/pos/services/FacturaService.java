package com.org.pos.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.springframework.stereotype.Service;

import mx.bigdata.sat.cfdi.CFDv33;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;

@Service
public class FacturaService {

	public void createFactura() throws Exception {
        CFDv33 cfd = new CFDv33(CFDv33FactoryService.createComprobante(), "mx.bigdata.sat.cfdi.examples");
        cfd.addNamespace("http://www.bigdata.mx/cfdi/example", "example");

        PrivateKey key = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
                new FileInputStream("resources/certs/CSD_Prueba_CFDI_LAN8507268IA.key"),
                "12345678a"
        ).getKey();

        X509Certificate cert = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
                new FileInputStream("resources/certs/CSD_Prueba_CFDI_LAN8507268IA.cer")
        ).getKey();

        Comprobante sellado = cfd.sellarComprobante(key, cert);
        System.err.println(sellado.getSello());
        cfd.validar();
        cfd.verificar();
        ByteArrayOutputStream by = new ByteArrayOutputStream();
        cfd.guardar(by);
        cfd.guardar(System.out);
		
	}
	
}
