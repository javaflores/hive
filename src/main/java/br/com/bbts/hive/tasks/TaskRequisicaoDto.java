package br.com.bbts.hive.tasks;

import java.io.Serial;
import java.io.Serializable;

public class TaskRequisicaoDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Integer codigoCliente;
	private String numeroSolicitacao;
	private Integer codigoEstadoSolicitacao;
	private String dataInicio;

}
