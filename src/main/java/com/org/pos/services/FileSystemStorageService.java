package com.org.pos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.org.pos.config.StorageProperties;
import com.org.pos.exception.StorageException;
import com.org.pos.exception.StorageFileNotFoundException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
    	ClassLoader classLoader = getClass().getClassLoader();
    	//File resourcesDirectory = new File("src/main/resources/static/products");
    	//this.rootLocation = Paths.get(resourcesDirectory.getAbsolutePath());
    	File file = new File(classLoader.getResource("static/products").getFile());
    	this.rootLocation = Paths.get(file.getPath());
        //this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String store(MultipartFile file,String fileName) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            
            String nombreArchivo=file.getOriginalFilename();
            String[] extensionArray=nombreArchivo.split("\\.");
            String extension=extensionArray[1];
            String[] partesNombre=fileName.split("_");
            String pathMarcaString=this.rootLocation+"/"+partesNombre[0];
            pathMarcaString=pathMarcaString.replaceAll("\\s+","_");
            pathMarcaString=pathMarcaString.toLowerCase();
            String directorioMarca=partesNombre[0];
            directorioMarca=directorioMarca.replaceAll("\\s+","_");
            directorioMarca=directorioMarca.toLowerCase();
            
            Path pathMarca=Paths.get(pathMarcaString);
            if(!Files.exists(pathMarca)){
            	Files.createDirectories(pathMarca);
            }
            
            String pathModeloString=pathMarca+"/"+partesNombre[1];
            pathModeloString=pathModeloString.toLowerCase();
            String directorioModelo=partesNombre[1];
            directorioModelo=directorioModelo.toLowerCase();
            
            Path pathModelo=Paths.get(pathModeloString);
            if(!Files.exists(pathModelo)){
            	Files.createDirectories(pathModelo);
            }
            
            String nombreArchivoFinal=partesNombre[2]+"."+extension;
            nombreArchivoFinal=nombreArchivoFinal.toLowerCase();
            
            String pathArchivoString=pathModelo+"/"+partesNombre[2]+"."+extension;
            pathArchivoString=pathArchivoString.toLowerCase();
            Path pathArchivo=Paths.get(pathArchivoString);
            if(Files.exists(pathArchivo)){
            	Files.delete(pathArchivo);
            }
            
            Files.copy(file.getInputStream(), pathModelo.resolve(partesNombre[2]+"."+extension));
           
            String directorioCreado="products/"+directorioMarca+"/"+directorioModelo+"/"+nombreArchivoFinal;
            
            return directorioCreado;
            
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            
        	Boolean existeDirectorio=Files.exists(rootLocation);
        	if(!existeDirectorio) {
        		Files.createDirectory(rootLocation);	
        	}
        	
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}