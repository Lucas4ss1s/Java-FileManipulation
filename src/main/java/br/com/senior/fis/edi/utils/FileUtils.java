package br.com.senior.fis.edi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FileUtils {

	public static void gravaArquivo(String caminho, String conteudo)throws Exception{
		
		try{
			String content = conteudo;
			File file = new File(caminho);
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(content);
			bw.close();
			
		}catch (Exception e) {
			throw e;
		}
	}
	
	public static void leArquivo(String caminho){
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			while(br.ready()){
				br.readLine();
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean validaDiretorio(String diretorio){
		
		try {
			File file = new File(diretorio);
			if (file.exists()){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public static List<File> carregaArquivo(String diretorio)throws IOException{
		
		List<File> listaRetorno = new ArrayList<File>();
		
		File fileDiretorio = new File(diretorio);
		File listFile[] = fileDiretorio.listFiles();
		int x = 0;
		for (int y = listFile.length; x < y; x++ ){
			File arquivo = listFile[x];
			if (arquivo.isFile()){
				listaRetorno.add(arquivo);
			}
		}
		
		return listaRetorno;
	}
	
	public static void ConectaFTP(String Conection, String User, String Pass) throws Exception{
		
		FTPClient ftp = new FTPClient();
		
		try {
			//Valida Conexão
			ftp.connect(Conection);
			if(FTPReply.isPositiveCompletion(ftp.getReplyCode())){
				ftp.login(User, Pass);
			}else{
				//Erro na tentativa de conectar
				throw new Exception("Conexão Recusada");
			}
			
		} catch (Exception e) {	
			throw e;
		} finally {
			if(ftp.isConnected())ftp.disconnect();
		}
		
		
	}
	
}
