package br.com.senior.fis.edi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_FILA_ARQUIVO_PREFAT")
public class FilaArquivoPrefat implements Serializable {
	
	private static final long serialVersionUID = 1109268124339955432L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_FILAARQUIVOPREFAT")
    @SequenceGenerator(name="SEQ_FILAARQUIVOPREFAT", sequenceName = "SEQ_FILAARQUIVOPREFAT", allocationSize = 1) 
    @Column(name = "ID")
    public Long id;
	
	@Column(name = "NOME_ARQ")
	public String nomeArq;
	
	@Column(name = "DADOS_ARQ")
	public String dadosArq;
	
	@Column(name = "DIR_SAIDA")
	public String dirSaida;
	
	@Column(name = "DT_PROCESSADO")
	public java.util.Date dtProcessado;
	
	@Column(name = "ID_PROCESSADO")
	public String idProcessado;
	
	@Column(name = "DS_LOG")
	public String dsLog;

}
