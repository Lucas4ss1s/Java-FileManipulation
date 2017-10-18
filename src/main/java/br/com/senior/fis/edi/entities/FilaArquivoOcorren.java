package br.com.senior.fis.edi.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "T_FILA_ARQUIVO_OCORREN")
public class FilaArquivoOcorren implements Serializable {
	
	private static final long serialVersionUID = -758738652276527893L;
	
	public FilaArquivoOcorren (){};
	
	public FilaArquivoOcorren(Long cdEmpresa, Long cdTransportadora, String nomeArq, String dadosArq, String dirEntrada){
		this.cdEmpresa = cdEmpresa;
		this.cdTransportadora = cdTransportadora;
		this.nomeArq = nomeArq;
		this.dadosArq = dadosArq;
		this.dirEntrada = dirEntrada;
		this.idMove = "N";
		this.status = "P";
	};
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_FILAARQUIVOOCORREN")
    @SequenceGenerator(name="SEQ_FILAARQUIVOOCORREN", sequenceName = "SEQ_FILAARQUIVOOCORREN", allocationSize = 1) 
    @Column(name = "ID")
    public Long id;
	
	@Column(name = "CD_EMPRESA")
	public Long cdEmpresa;
	
	@Column(name = "CD_TRANSPORTADORA")
	public Long cdTransportadora;
	
	@Column(name = "NOME_ARQ")
	public String nomeArq;
	
	@Column(name = "DADOS_ARQ")
	public String dadosArq;
	
	@Column(name = "DIR_ENTRADA")
	public String dirEntrada;

	@Column(name = "STATUS")
	public String status;
	
	@Column(name = "DT_STATUS")
	public Date dtStatus;
	
	@Column(name = "ID_MOVE")
	public String idMove;
	
}
