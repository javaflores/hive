package br.com.bbts.shopee.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.bbts.shopee.entidades.ClienteShopee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@ApplicationScoped
public class ClienteDao {

	@PersistenceContext
	EntityManager em;

	public int incluirDadosClienteShopee(ClienteShopee dadosClienteShopee) throws Exception {
		Query query = em.createNamedQuery(ClienteShopee.INCLUIR_DADOS_USUARIOS);
		
		query.setParameter("numeroSolicitacao", dadosClienteShopee.getNumeroSolicitacao());
		query.setParameter("nome", dadosClienteShopee.getNome());
		query.setParameter("codigoTipoDocumento", dadosClienteShopee.getCodigoTipoDocumento());
		query.setParameter("numeroDocumento", dadosClienteShopee.getNumeroDocumento());
		query.setParameter("timestampAtualizacao", dadosClienteShopee.getTimestampAtualizacao());
		query.setParameter("dataNascimento", dadosClienteShopee.getDataNascimento());
		query.setParameter("nomePai", dadosClienteShopee.getNomePai());
		query.setParameter("nomeMae", dadosClienteShopee.getNomeMae());
		query.setParameter("sexo", dadosClienteShopee.getSexo());
		
		try {
			return query.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<ClienteShopee> filtrarDadosClienteShopee(BigDecimal numeroSolicitacaoSequencial) throws Exception {
		TypedQuery<ClienteShopee> query = em.createNamedQuery(ClienteShopee.FILTRAR_DADOS_USUARIOS, ClienteShopee.class);

		query.setParameter("numeroSolicitacao", numeroSolicitacaoSequencial);

		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}


}
