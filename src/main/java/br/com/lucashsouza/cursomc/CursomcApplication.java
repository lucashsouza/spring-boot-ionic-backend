package br.com.lucashsouza.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.lucashsouza.cursomc.domain.Categoria;
import br.com.lucashsouza.cursomc.domain.Cidade;
import br.com.lucashsouza.cursomc.domain.Cliente;
import br.com.lucashsouza.cursomc.domain.Endereco;
import br.com.lucashsouza.cursomc.domain.Estado;
import br.com.lucashsouza.cursomc.domain.ItemPedido;
import br.com.lucashsouza.cursomc.domain.Pagamento;
import br.com.lucashsouza.cursomc.domain.PagamentoComBoleto;
import br.com.lucashsouza.cursomc.domain.PagamentoComCartao;
import br.com.lucashsouza.cursomc.domain.Pedido;
import br.com.lucashsouza.cursomc.domain.Produto;
import br.com.lucashsouza.cursomc.domain.enums.EstadoPagamento;
import br.com.lucashsouza.cursomc.domain.enums.TipoCliente;
import br.com.lucashsouza.cursomc.repositories.CategoriaRepository;
import br.com.lucashsouza.cursomc.repositories.CidadeRepository;
import br.com.lucashsouza.cursomc.repositories.ClienteRepository;
import br.com.lucashsouza.cursomc.repositories.EnderecoRepository;
import br.com.lucashsouza.cursomc.repositories.EstadoRepository;
import br.com.lucashsouza.cursomc.repositories.ItemPedidoRepository;
import br.com.lucashsouza.cursomc.repositories.PagamentoRepository;
import br.com.lucashsouza.cursomc.repositories.PedidoRepository;
import br.com.lucashsouza.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
