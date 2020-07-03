package br.com.dirceus.storage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.dirceus.storage.model.Documento;
import br.com.dirceus.storage.service.StorageService;

@RestController
@RequestMapping("upload")
public class FileUploadController {

	private static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);
	
	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public Documento upload(@RequestParam("arquivo") MultipartFile file,
			@RequestParam("metadados") String metadados) {

		LOG.info("Recebida solicitação para armazenar o arquivo");
		LOG.info("Nome: "+file.getOriginalFilename() +" - Tamanho: "+file.getSize());
		Documento documento = new Documento();
		documento.setId(1L);
		documento.setAplicacao("Edoc");
		documento.setReferencia("idDoEdoc");
		documento.setMetadados(metadados);
		
		storageService.store(file);
		
		return documento;
	}
	
	
}
