package br.com.bbts.hive.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bbts.hive.entidades.ClientesExternos;
import br.com.bbts.hive.enums.TipoEmpresaEnum;
import br.com.bbts.hive.tasks.dto.DadosClienteMercadoLivreDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@ApplicationScoped
public class HiveService {

	private final static int TIPO_CPF = 1;
	private final static int TIPO_CNPJ = 2;
	private final static int TIPO_PASSAPORTE = 3;

	public List<ClientesExternos> listarDadosClientesNoHive() throws Exception {
		return ClientesExternos.listAll();
	}

	@Transactional
	public void salvarDadosClientesExternosDoMercadoLivre(List<DadosClienteMercadoLivreDTO> listaDadosRetorno) {

		List<ClientesExternos> listaClientesExternos = new ArrayList<ClientesExternos>();

		// Percorre a lista dos dados dos clientes do mercado livre para gravar no hive.
		listaDadosRetorno.forEach(dadoCliente -> {

			var cliente = new ClientesExternos();

			// Preenchendo o tipo de empresa que no caso é o Mercado Livre.
			cliente.setCodigoEmpresaExterno(TipoEmpresaEnum.MERCADO_LIVRE.getCodigo());
			cliente.setNomeEmpresaExterno(TipoEmpresaEnum.getDescricaoPorCodigo(cliente.getCodigoEmpresaExterno()));

			// Dados pessoais
			cliente.setNomeCliente(dadoCliente.getName().concat(" ").concat(dadoCliente.getLastName()));
			cliente.setNomeMae(dadoCliente.getMotherFullName());
			cliente.setNomePai(dadoCliente.getFatherFullName());

			// remover o mock abaixo, feito somente para testar.
			cliente.setNumeroDocumento("123");
			cliente.setCodigoTipoDocumento(recuperarTipoDocumento(cliente.getNumeroDocumento()));
			cliente.setDataNascimento(LocalDate.now());
			cliente.setNumeroSequencialExterno(new BigDecimal(1));
			cliente.setTipoSexo("X");
			cliente.setTimestampUltimaAtualizacao(LocalDateTime.now());

			listaClientesExternos.add(cliente);
		});

		// Inclui todos os clientes da lista.
		ClientesExternos.incluir(listaClientesExternos);
	}

	private Integer recuperarTipoDocumento(String numeroDocumento) {

		// Busca pelo tamanho do numero do documento o seu tipo.
		if (StringUtils.length(numeroDocumento) < 5) {
			return TIPO_PASSAPORTE;
		} else if (StringUtils.length(numeroDocumento) < 12) {
			return TIPO_CPF;
		} else {
			return TIPO_CNPJ;
		}
	}

}
