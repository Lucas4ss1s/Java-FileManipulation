package br.com.senior.fis.edi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "VB_INTERF_ENTRADA_TRANSP")
public class BuscaArquivoEntradaTransp implements Serializable{

	private static final long serialVersionUID = 2003120890800547870L;
	
	@Id
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CD_EMPRESA")
	public Long cdEmpresa;
	
	@Column(name = "CD_TRANSPORTADORA")
	public Long cdTransportadora;
	
	@Column(name = "DS_INTERF_DIR_REC")
	public String dsInterfDirRec;
	
	@Column(name = "DS_INTERF_DIR_REC_BAD")
	public String dsInterfDirRecBad;
	
	@Column(name = "DS_INTERF_DIR_REC_OK")
	public String dsInterfDirRecOk;	
	
	@Column(name = "DS_INTERF_DIR_REC_PRC")
	public String dirInterfDirRecPrc;
	
	@Column(name = "DS_INTERF_CONEMB_REC")
	public String dsInterfConembRec;
	
	@Column(name = "DS_INTERF_CONEMB_OK")
	public String dsInterfConembOk;
	
	@Column(name = "DS_INTERF_CONEMB_BAD")
	public String dsInterfConembBad;
	
	@Column(name = "DS_INTERF_CONEMB_PRC")
	public String dsInterfConembPrc;	
	
}
