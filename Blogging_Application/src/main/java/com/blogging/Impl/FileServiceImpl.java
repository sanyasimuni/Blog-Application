package com.blogging.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogging.Service.fileService;

@Service
public class FileServiceImpl  implements fileService{
	
	
	

	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {
	
		//fileName
		String name=file.getOriginalFilename();
		
		//random name generated file
		
		String randomID=UUID.randomUUID().toString();
		String filename1=randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//filepath get
		
		String filepath=path+File.separator+filename1;
		
		//create folder if not created
		File f=new File(path);
		if (!f.exists()) {
			f.mkdir();
			
		}
		
		//copy File
		Files.copy(file.getInputStream(),Paths.get(filepath));
		
		
		
		return randomID;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String fullPath=path+File.separator+fileName;
		InputStream is=new FileInputStream(fullPath);
		
		//do logic return input
		
		
		return is;
	}

}
