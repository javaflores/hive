package br.com.bbts.hive.utils;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public enum TipoEmpresaEnum {
	
    MERCADO_LIVRE("Mercado Livre do Brasil", 1),
    SHOPEE("Shopee LTDA", 2),
    NONE("Não parametrizado", 99);

	private Integer codigo;
	private String descricao;

	private TipoEmpresaEnum(String descricao, int codigo) {
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
		for (TipoEmpresaEnum tipo : TipoEmpresaEnum.values()) {
			if(tipo.getCodigo() == codigo) {
				return tipo.getDescricao();
			}
		}
		return TipoEmpresaEnum.NONE.getDescricao();
	}

}
