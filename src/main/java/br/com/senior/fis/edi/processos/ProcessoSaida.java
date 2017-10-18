package br.com.senior.fis.edi.processos;

import java.util.Date;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.senior.fis.edi.repository.FilaArquivoNotfisRepository;
import br.com.senior.fis.edi.repository.FilaArquivoPrefatRepository;
import br.com.senior.fis.edi.service.ProcessaEdiService;
import br.com.senior.fis.edi.utils.FileUtils;

/* 	Autor: Lucas S. de Assis 				
 *  Data:  13.10.2017			
 * 	Objetivo: Gerar os arquivos de saída Notfis e Prefat, de acordo com layout proceda, 
 * 			  extraindo as informações contidas no banco de dados.
 * */

@Component
public class ProcessoSaida {
	
	@Autowired
	private FilaArquivoNotfisRepository filaArquivoNotfisRepository; 
	
	@Autowired
	private FilaArquivoPrefatRepository filaArquivoPrefatRepository;
	
	@Autowired
	ProcessaEdiService processaEdiService;
		
	@Scheduled(cron="${cron.gera.notfis}")	
	public void geraNotfis() {
		try {						
			//Chamada da Package
			processaEdiService.callNotfis();
			//Recupera as informações da tabela(T_FILA_ARQUIVO_NOTFIS)
			filaArquivoNotfisRepository.buscaArquivosNaoProcessados().forEach(retorno -> {
				
				try {
					//Gera o arquivo no diretório
					FileUtils.gravaArquivo(retorno.dirSaida+retorno.nomeArq,retorno.dadosArq) ;
				} catch (Exception e) {
					retorno.dsLog = e.getMessage();
					retorno.idProcessado = "E";
					filaArquivoNotfisRepository.save(retorno);
				}				
				
				if((retorno.dirSaida == null)||(retorno.nomeArq == null)){
					retorno.dsLog = "Diretório/Nome do Arquivo não Cadastrado para Transportadora";
					retorno.idProcessado = "E"; 
					
				}else{
					retorno.idProcessado = "S";
					retorno.dtProcessado = new Date();	
				}
				
				filaArquivoNotfisRepository.save(retorno);
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Scheduled (cron="*/30 * * * * *")
	public void geraPrefat(){
		try {
			//Chamada da Package
			processaEdiService.callPrefat();
			//Recupera as informações da tabela(T_FILA_ARQUIVO_PREFAT)
			filaArquivoPrefatRepository.buscaArquivosNaoProcessados().forEach(retorno -> {
				try {
					FileUtils.gravaArquivo(retorno.dirSaida+retorno.nomeArq,retorno.dadosArq );
				} catch (Exception e) {
					retorno.dsLog = e.getMessage();
					retorno.idProcessado = "E";
					filaArquivoPrefatRepository.save(retorno);
				}			
				
				if((retorno.dirSaida == null)||(retorno.nomeArq == null)){
					retorno.idProcessado = "E";
					retorno.dsLog = "Diretório/Nome do Arquivo não Cadastrado para Transportadora";
				}
				else{
					retorno.idProcessado = "S";
					retorno.dtProcessado = new Date();
				}
				filaArquivoPrefatRepository.save(retorno);
			});
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}


