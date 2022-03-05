package br.com.lucashsouza.cursomc.services;

import br.com.lucashsouza.cursomc.domain.ItemPedido;
import br.com.lucashsouza.cursomc.domain.PagamentoComBoleto;
import br.com.lucashsouza.cursomc.domain.Pedido;
import br.com.lucashsouza.cursomc.domain.enums.EstadoPagamento;
import br.com.lucashsouza.cursomc.repositories.ItemPedidoRepository;
import br.com.lucashsouza.cursomc.repositories.PagamentoRepository;
import br.com.lucashsouza.cursomc.repositories.PedidoRepository;
import br.com.lucashsouza.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {

		Optional<Pedido> categoria = pedidoRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
			      "ID: " + id + " " +
			      "Tipo: " + Pedido.class.getName()));
	}

	@Transactional
    public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());

		for (ItemPedido itemPedido : obj.getItens()) {
			itemPedido.setDesconto(0.0);
			itemPedido.setPreco(produtoService.find(itemPedido.getProduto().getId()).getPreco());
			itemPedido.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
    }
}
