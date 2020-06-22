package br.com.dirceus.microservice.loja.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.dirceus.microservice.loja.dto.InfoFornecedorDTO;
import br.com.dirceus.microservice.loja.dto.InfoPedidoDTO;
import br.com.dirceus.microservice.loja.dto.ItemDaCompraDTO;


@FeignClient("fornecedor")
public interface FornecedorClient {

	@RequestMapping("/info/{estado}")
	public InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);
	
	@RequestMapping(method = RequestMethod.POST ,value = "/pedido")
	public InfoPedidoDTO realizaPedido(List <ItemDaCompraDTO> itens);
	
}