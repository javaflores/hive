package br.com.bbts.hive.utils;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bbts.hive.entidades.ClientesExternos;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public class HiveUtils {

	private final static String TIPO_MASCULINO = "M";
	private final static String TIPO_FEMININO = "F";
	private final static String TIPO_OUTROS = "O";

	public static String recuperarDescricaoSexo(String tipoSexo) {

		// Busca a descrição do sexo. Exemplo sem enum.
		if (StringUtils.equalsIgnoreCase(TIPO_MASCULINO, tipoSexo)) {
			return "Masculino";
		} else if (StringUtils.equalsIgnoreCase(TIPO_FEMININO, tipoSexo)) {
			return "Feminino";
		} else {
			return "Outros";
		}
	}

	public static String recuperarTipoSexo(int codigoSexo) {

		// Busca o tipo do sexo pelo codigo.
		if (codigoSexo == 1) {
			return TIPO_MASCULINO;
		} else if (codigoSexo == 2) {
			return TIPO_FEMININO;
		} else {
			return TIPO_OUTROS;
		}
	}

	public static String recuperarTipoSexo(String codigoSexo) {

		// Busca o tipo do sexo pelo codigo.
		if (StringUtils.equalsIgnoreCase("M", codigoSexo)) {
			return TIPO_MASCULINO;
		} else if (StringUtils.equalsIgnoreCase("F", codigoSexo)) {
			return TIPO_FEMININO;
		} else {
			return TIPO_OUTROS;
		}
	}

	public static LocalDate transformarDataNascimentoShopee(String dataNascimento) {
		// TODO: Falta fazer a conversao da data de nascimento formatada para LocalDate
		return LocalDate.now();
	}

	public static LocalDate formatarData(String data) {
		// Fazendo um parse simplificado.
		return LocalDate.parse(data);
	}
	
	public static void printarDadosClientesComoTabela(List<ClientesExternos> listaDadosClientes) {
		
		// Printa no console para melhor visualização dos dados padronizados.
		System.out.println("\r");
		System.out.println("|================ SOMENTE PARA VISUALIZAÇÃO DO MOTOR EM FUNCIONAMENTO ================|");
		System.out.printf("|%-3s %-24s %-20s %-10s %-10s %-10s%n", "ID", "Empresa", "Nome", "Sexo", "Documento", "Nr. Documento|");
		System.out.println("|=====================================================================================|");
		
		// Percorre a lista dos dados do hive.
		for (ClientesExternos dado : listaDadosClientes) {
		    System.out.printf("|%-3d %-24s %-20s %-10s %-10s %-10s%n", 
		    		dado.getId(), 
		    		dado.getNomeEmpresaExterno(), 
		    		StringUtils.abbreviate(dado.getNomeCliente(), 19),
		    		dado.getNomeTipoSexo(),
		    		dado.getNomeTipoDocumento(),
		    		dado.getNumeroDocumento()
		    );
		}
		
		System.out.println("|======================================== FIM ========================================|\n");
		
		/*
	    "id": 16,
	    "numeroSequencialExterno": 20261092133076924,
	    "codigoEmpresaExterno": 1,
	    "nomeEmpresaExterno": "Mercado Livre do Brasil",
	    "nomeCliente": "Lou Spinka",
	    "codigoTipoDocumento": 1,
	    "numeroDocumento": "341813374",
	    "dataNascimento": "1972-03-01",
	    "nomePai": "Ms. Malik Kshlerin",
	    "nomeMae": "Teressa Bernhard",
	    "tipoSexo": "M",
	    "timestampUltimaAtualizacao": "2026-04-19T21:33:07.692455",
	    "nomeTipoDocumento": "CPF",
	    "nomeTipoSexo": "Masculino"
	    */
	}
}
