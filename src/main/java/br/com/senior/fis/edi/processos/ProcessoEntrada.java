package br.com.senior.fis.edi.processos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.senior.fis.edi.entities.FilaArquivoConemb;
import br.com.senior.fis.edi.entities.FilaArquivoOcorren;
import br.com.senior.fis.edi.repository.BuscaArquivoEntradaTranspRepository;
import br.com.senior.fis.edi.repository.FilaArquivoConembRepository;
import br.com.senior.fis.edi.repository.FilaArquivoOcorrenRepository;
import br.com.senior.fis.edi.repository.custom.ProcessaEdiRepository;
import br.com.senior.fis.edi.utils.FileUtils;

/* 	Autor: Lucas S. de Assis 				
 *  Data:  13.10.2017			
 * 	Objetivo: Ler os arquivos de entrada Ocorren e Conemb, disponibilizado pelas transportadoas,  
 * 			  inserir as informações no banco de dados.
 * */

@Component
public class ProcessoEntrada {

	@Autowired
	private BuscaArquivoEntradaTranspRepository buscaArquivoEntradaTranspRepository;
	
	@Autowired
	FilaArquivoOcorrenRepository filaArquivoOcorrenRepository;
	
	@Autowired
	FilaArquivoConembRepository filaArquivoConembRepository;
	
	@Autowired
	ProcessaEdiRepository processaEdiRepository;
	
	@Scheduled(cron="*/20 * * * * *")
	public void processaOcorren (){
		
		try {
			//Busca o diretório das transportadoras
			buscaArquivoEntradaTranspRepository.findAll().forEach(diretorio -> {				
				try {
					//Valida se o Diretório Existe
					if (FileUtils.validaDiretorio(diretorio.dsInterfDirRec)) {	
						
						//Guarda todos os arquivos do diretório em uma Lista
						List<File> listaArquivos = FileUtils.carregaArquivo(diretorio.dsInterfDirRec);				
						listaArquivos.forEach(dados -> {
							
							try {								
								BufferedReader br = new BufferedReader(new FileReader( new File(diretorio.dsInterfDirRec+dados.getName())));
								StringBuilder sb = new StringBuilder();
								
								while(br.ready()){ 	
									//Armazena o conteúdo do arquivo
									String linha = br.readLine(); 
									sb.append(linha);
									} 
								
								br.close();
								//Atribui os valores armazenados na entidade e salva no BD.
								FilaArquivoOcorren registros = new FilaArquivoOcorren(diretorio.cdEmpresa, diretorio.cdTransportadora,dados.getName(), sb.toString(),diretorio.dsInterfDirRec);
								filaArquivoOcorrenRepository.save(registros);
								
								//Move o arquivo para a pasta de processamento, que será verificado pelo BD.
								Files.move(dados.toPath(), Paths.get(diretorio.dirInterfDirRecPrc, dados.getName()));
								
							} catch (Exception e) {
								e.printStackTrace();
							}												
						});			
						
					}else{
						processaEdiRepository.gravaLogOcorren(diretorio.cdEmpresa, diretorio.cdTransportadora, "DIRETORIO "+diretorio.dsInterfDirRec +" CONEMB NAO ENCONTRADO PARA A TRANSPORTADORA" + diretorio.cdTransportadora);
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//Busca os Registros processados pelo banco e move para as pastas OK/BAD
				try {
					filaArquivoOcorrenRepository.buscaStatus().forEach(registro -> {
						try {
							File file = new File(diretorio.dirInterfDirRecPrc+registro.nomeArq);
							
							if(registro.status.equals("O")){
								//Transfere para a pasta OK						
							    Files.move(file.toPath(), Paths.get(diretorio.dsInterfDirRecOk, registro.nomeArq));
							}else if(registro.status.equals("B")){
								//Transfere para a pasta BAD
								Files.move(file.toPath(), Paths.get(diretorio.dsInterfDirRecBad, registro.nomeArq));
							}
							
							registro.idMove = "S";
							filaArquivoOcorrenRepository.save(registro);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Scheduled(cron="*/30 * * * * *")
	public void processaConemb (){
		
		try {
			//Busca o diretório das transportadoras
			buscaArquivoEntradaTranspRepository.findAll().forEach(diretorio -> {				
				try {
					//Valida se o Diretório Existe
					if (FileUtils.validaDiretorio(diretorio.dsInterfConembRec)) {
						
						//Guarda todos os arquivos do diretório em uma Lista
						List<File> listaArquivos = FileUtils.carregaArquivo(diretorio.dsInterfConembRec);				
						listaArquivos.forEach(dados -> {
							
							try {								
								BufferedReader br = new BufferedReader(new FileReader( new File(diretorio.dsInterfConembRec+dados.getName())));
								StringBuilder sb = new StringBuilder();
								
								while(br.ready()){ 	
									//Armazena o conteúdo do arquivo
									String linha = br.readLine(); 
									sb.append(linha);
									} 
								
								br.close();
								//Atribui os valores armazenados na entidade e salva no BD.
								FilaArquivoConemb registros = new FilaArquivoConemb(diretorio.cdEmpresa, diretorio.cdTransportadora,dados.getName(), sb.toString(),diretorio.dsInterfConembRec);
								filaArquivoConembRepository.save(registros);
								
								//Move o arquivo para a pasta de processamento, que será verificado pelo BD.
								Files.move(dados.toPath(), Paths.get(diretorio.dsInterfConembPrc, dados.getName()));
								
							} catch (Exception e) {
								e.printStackTrace();
							}												
						});			
						
					}else{
						
						processaEdiRepository.gravaLogConemb(diretorio.cdEmpresa, diretorio.cdTransportadora, "DIRETORIO "+diretorio.dsInterfConembRec +" CONEMB NAO ENCONTRADO PARA A TRANSPORTADORA" + diretorio.cdTransportadora);
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//Busca os Registros processados pelo banco e move para as pastas OK/BAD
				try {
					filaArquivoConembRepository.buscaStatus().forEach(registro -> {
						try {
							File file = new File(diretorio.dsInterfConembPrc+registro.nomeArq);
							
							if(registro.status.equals("O")){
								//Transfere para a pasta OK						
							    Files.move(file.toPath(), Paths.get(diretorio.dsInterfConembOk, registro.nomeArq));
							}else if(registro.status.equals("B")){
								//Transfere para a pasta BAD
								Files.move(file.toPath(), Paths.get(diretorio.dsInterfConembBad, registro.nomeArq));
							}
							
							registro.idMove = "S";
							filaArquivoConembRepository.save(registro);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
