package com.lrsilva.projetospring.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lrsilva.projetospring.services.exceptions.FileException;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream iis = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(iis, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("IO Error");
		}
	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Initialize Upload");
			s3Client.putObject(new PutObjectRequest(bucketName, fileName, is, meta));
			LOG.info("Upload SuccessFull");

			return s3Client.getUrl(bucketName, fileName).toURI();

		} catch (URISyntaxException e) {
			throw new FileException("Error to convert URL to URI");
		}

	}
}
