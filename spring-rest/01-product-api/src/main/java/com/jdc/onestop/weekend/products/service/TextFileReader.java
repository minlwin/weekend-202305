package com.jdc.onestop.weekend.products.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.weekend.products.utils.exceptions.ApiBusinessException;

@Service
public class TextFileReader {

	public List<String> read(MultipartFile file) {
		
		var list = new ArrayList<String>();
		
		try(var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line = null;
			
			while(null != (line = reader.readLine())) {
				list.add(line);
			}
			
		} catch (IOException e) {
			throw new ApiBusinessException("Invalid file format!");
		}
		
		return list;
	}
}
