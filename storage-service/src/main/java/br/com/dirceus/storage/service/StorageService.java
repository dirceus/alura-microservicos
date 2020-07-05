package br.com.dirceus.storage.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void store(MultipartFile file);

	Resource loadAsResource(String docId);
	
}
