package br.com.bbts.hive.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import br.com.bbts.hive.entidades.ClientesExternos;
import br.com.bbts.hive.tasks.dto.DadosClienteShopeeDTO;
import br.com.bbts.hive.tasks.dto.DadosRetornoMercadoLivreDTO;
import br.com.bbts.hive.utils.HiveUtils;
import br.com.bbts.hive.utils.TipoDocumentoEnum;
import br.com.bbts.hive.utils.TipoEmpresaEnum;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@ApplicationScoped
public class HiveService {

	public BigDecimal recuperarUltimaSolicitacaoDadosClienteMercadoLivre() throws Exception {

        // Monta os parametros do filtro.
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codigoEmpresaExterno", TipoEmpresaEnum.MERCADO_LIVRE.getCodigo());

        // Monta a query dos atributos a serem atualizados.
        String query = "SELECT MAX(numeroSequencialExterno) FROM ClientesExternos " +
                "where codigoEmpresaExterno = :codigoEmpresaExterno ";

        // Executa o select para buscar o ultimo numero de solicitacao para utilizar na rechamada.
        BigDecimal ultimoNumeroSequencial = ClientesExternos.find(query, parametros).project(BigDecimal.class).firstResult();
        
        if (ultimoNumeroSequencial == null) {
        	return new BigDecimal(1);
        }

		return ultimoNumeroSequencial;
	}
	
	public List<ClientesExternos> listarDadosClientesNoHive() throws Exception {

		List<ClientesExternos> listaDadosClientes = ClientesExternos.listAll();

		// Formatando os dados a serem retornados.
		listaDadosClientes.forEach(dado -> {
			dado.setNomeTipoDocumento(TipoDocumentoEnum.getDescricaoPorCodigo(dado.getCodigoTipoDocumento()));
			dado.setNomeTipoSexo(HiveUtils.recuperarDescricaoSexo(dado.getTipoSexo()));
		});

		return listaDadosClientes;
	}

	public void salvarDadosClientesExternosDaShopee(List<DadosClienteShopeeDTO> listaDadosRetorno) {

		List<ClientesExternos> listaClientesExternos = new ArrayList<ClientesExternos>();

		// Percorre a lista dos dados dos clientes do mercado livre para gravar no hive.
		listaDadosRetorno.forEach(dadoCliente -> {

			var cliente = new ClientesExternos();
			cliente.setNumeroSequencialExterno(new BigDecimal(0));
			cliente.setTimestampUltimaAtualizacao(LocalDateTime.now());

			// Preenchendo o tipo de empresa que no caso é o Mercado Livre.
			cliente.setCodigoEmpresaExterno(TipoEmpresaEnum.SHOPEE.getCodigo());
			cliente.setNomeEmpresaExterno(TipoEmpresaEnum.getDescricaoPorCodigo(cliente.getCodigoEmpresaExterno()));

			// Dados pessoais
			cliente.setNomeCliente(dadoCliente.getName().concat(" ").concat(dadoCliente.getLastName()));
			cliente.setNomeMae(dadoCliente.getMotherFullName());
			cliente.setNomePai(dadoCliente.getFatherFullName());
			cliente.setTipoSexo(HiveUtils.recuperarTipoSexo(dadoCliente.getGenderType()));
			
			// Regra de negócio: Se menor, usar o passaporte.
			if (dadoCliente.getAge() < 18) {
				cliente.setNumeroDocumento(dadoCliente.getPassportNumber());
				cliente.setCodigoTipoDocumento(TipoDocumentoEnum.PASSAPORTE.getCodigo());
			} else {
				cliente.setNumeroDocumento(StringUtils.leftPad(String.valueOf(dadoCliente.getSocialSecurityNumber()), 3));
				cliente.setCodigoTipoDocumento(TipoDocumentoEnum.IDENTIDADE.getCodigo());
			}

			// Formata da data de nascimento que vem da shopee.
			cliente.setDataNascimento(HiveUtils.transformarDataNascimentoShopee(dadoCliente.getBirthDate()));

			listaClientesExternos.add(cliente);
		});

		// Inclui todos os clientes da lista.
		incluirListaClientesExternos(listaClientesExternos);
	}
	
	public void salvarDadosClientesExternosDoMercadoLivre(DadosRetornoMercadoLivreDTO dadosMercadoLivreDto) {

		List<ClientesExternos> listaClientesExternos = new ArrayList<ClientesExternos>();

		// Percorre a lista dos dados dos clientes do mercado livre para gravar no hive.
		dadosMercadoLivreDto.getListaClientesMercadoLivre().forEach(dadoCliente -> {

			var cliente = new ClientesExternos();
			cliente.setNumeroSequencialExterno(dadoCliente.getNumeroSolicitacao());
			cliente.setTimestampUltimaAtualizacao(LocalDateTime.parse(dadoCliente.getTimestampAtualizacao()));

			// Preenchendo o tipo de empresa que no caso é o Mercado Livre.
			cliente.setCodigoEmpresaExterno(TipoEmpresaEnum.MERCADO_LIVRE.getCodigo());
			cliente.setNomeEmpresaExterno(TipoEmpresaEnum.getDescricaoPorCodigo(cliente.getCodigoEmpresaExterno()));

			// Dados pessoais
			cliente.setNomeCliente(dadoCliente.getNome());
			cliente.setNomeMae(dadoCliente.getNomeMae());
			cliente.setNomePai(dadoCliente.getNomePai());
			cliente.setTipoSexo(HiveUtils.recuperarTipoSexo(dadoCliente.getSexo()));
			
			// Regra de negócio: Todos do mercado livre serão CPF.
			cliente.setNumeroDocumento(dadoCliente.getNumeroDocumento());
			cliente.setCodigoTipoDocumento(TipoDocumentoEnum.CPF.getCodigo());

			// Formata da data de nascimento que vem do mercado livre.
			cliente.setDataNascimento(HiveUtils.formatarData(dadoCliente.getDataNascimento()));

			listaClientesExternos.add(cliente);
		});

		// Inclui todos os clientes da lista.
		incluirListaClientesExternos(listaClientesExternos);
	}
	
	@Transactional 
	public void incluirListaClientesExternos(List<ClientesExternos> listaClientesExternos) {
		Uni.combine().all().unis(ClientesExternos.incluir(listaClientesExternos)).discardItems().await().indefinitely();
	}

}
