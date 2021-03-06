package br.com.dirceus.microservice.loja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dirceus.microservice.loja.client.FornecedorClient;
import br.com.dirceus.microservice.loja.dto.CompraDTO;
import br.com.dirceus.microservice.loja.dto.InfoFornecedorDTO;
import br.com.dirceus.microservice.loja.dto.InfoPedidoDTO;
import br.com.dirceus.microservice.loja.model.Compra;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(CompraDTO compra) {
		
		final String estado = compra.getEndereco().getEstado();
		
		LOG.info("Buscando informações dos fornecedores do estado: "+estado);	
		InfoFornecedorDTO infoFornecedor =  fornecedorClient.getInfoPorEstado(estado);
		
		LOG.info("Realizando um pedido: "+compra.toString());
		InfoPedidoDTO infoPedido = fornecedorClient.realizaPedido(compra.getItens());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(infoPedido.getId());
		compraSalva.setTempoPreparo(infoPedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;
		
	}

}
