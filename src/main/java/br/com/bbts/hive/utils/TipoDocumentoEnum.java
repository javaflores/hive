package br.com.bbts.hive.utils;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public enum TipoDocumentoEnum {
	
    CPF("CPF", 1),
    CNPJ("CNPJ", 2),
    IDENTIDADE("Identidade", 3),
    PASSAPORTE("Passaporte", 4),
    NONE("Não parametrizado", 99);

	private Integer codigo;
	private String descricao;

	private TipoDocumentoEnum(String descricao, int codigo) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static String getDescricaoPorCodigo(int codigo) {
		for (TipoDocumentoEnum tipo : TipoDocumentoEnum.values()) {
			if(tipo.getCodigo() == codigo) {
				return tipo.getDescricao();
			}
		}
		return TipoDocumentoEnum.NONE.getDescricao();
	}

}
