package br.com.bbts.hive.utils;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;

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
}
