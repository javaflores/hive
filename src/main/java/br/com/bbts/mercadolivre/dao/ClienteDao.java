package br.com.bbts.mercadolivre.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.bbts.mercadolivre.entidades.ClienteMercadoLivre;
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

	public int incluirDadosClienteMercadoLivre(ClienteMercadoLivre dadosClienteMercadoLivre) throws Exception {
		Query query = em.createNamedQuery(ClienteMercadoLivre.INCLUIR_DADOS_USUARIOS);
		
		query.setParameter("numeroSolicitacao", dadosClienteMercadoLivre.getNumeroSolicitacao());
		query.setParameter("nome", dadosClienteMercadoLivre.getNome());
		query.setParameter("codigoTipoDocumento", dadosClienteMercadoLivre.getCodigoTipoDocumento());
		query.setParameter("numeroDocumento", dadosClienteMercadoLivre.getNumeroDocumento());
		query.setParameter("timestampAtualizacao", dadosClienteMercadoLivre.getTimestampAtualizacao());
		query.setParameter("dataNascimento", dadosClienteMercadoLivre.getDataNascimento());
		query.setParameter("nomePai", dadosClienteMercadoLivre.getNomePai());
		query.setParameter("nomeMae", dadosClienteMercadoLivre.getNomeMae());
		query.setParameter("sexo", dadosClienteMercadoLivre.getSexo());
		
		try {
			return query.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<ClienteMercadoLivre> filtrarDadosClienteMercadoLivre(BigDecimal numeroSolicitacaoSequencial) throws Exception {
		TypedQuery<ClienteMercadoLivre> query = em.createNamedQuery(ClienteMercadoLivre.FILTRAR_DADOS_USUARIOS, ClienteMercadoLivre.class);

		query.setParameter("numeroSolicitacao", numeroSolicitacaoSequencial);

		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}


}
