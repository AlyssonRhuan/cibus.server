package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.repository.ProductRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.apache.commons.io.FileUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Locale;
import java.util.Optional;

@Service
public class ImageService {

	public String save(String data, String name) throws Exception {
		String imageBase64 = data.split(",")[1];
		byte[] imageByes = DatatypeConverter.parseBase64Binary(imageBase64);

		String rootPath = java.nio.file.Paths.get(System.getProperty("user.dir"),"assets",  name).toString();

		try (OutputStream stream = new FileOutputStream(rootPath)){
			stream.write(imageByes);
			stream.flush();
		}

		return rootPath;
	}

	public String get(String imagePath) {
		String imageImage64 = "data:image/png;base64,";

		try
		{
			File image = new File(imagePath);
			byte[] bytes = FileUtils.readFileToByteArray(image);
			imageImage64 = imageImage64 + DatatypeConverter.printBase64Binary(bytes);
		}
		catch (Exception e)
		{
			imageImage64 = "";
		}

		return imageImage64;
	}
}
