package com.jdc.onestop.weekend.products.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageFileWriter {
	
	@Value("${app.image.folder}")
	private String imageDirectory;
	private Logger logger = LoggerFactory.getLogger(ImageFileWriter.class);
	
	private static final String FILE_FORMAT = "p_%06d_%s_%03d.%s";
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	public List<String> save(int producdtId, MultipartFile ... files) {
		
		List<String> list = new ArrayList<String>();
		
		for(var i = 0; i < files.length; i ++) {
			try {
				var file = files[i];
				var fileName = getFileName(file, producdtId, i);
				var destination = Path.of(imageDirectory, fileName);
				
				Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
				
				list.add(fileName);
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return list;
	}

	// p_000000_yyyyMMddHHmmss_000.extension
	private String getFileName(MultipartFile file, int productId, int index) {
		var array = file.getOriginalFilename().split("\\.");
		var extension = array[array.length - 1];
		return FILE_FORMAT.formatted(productId, LocalDateTime.now().format(DF), index + 1, extension);
	}
	
	
}
