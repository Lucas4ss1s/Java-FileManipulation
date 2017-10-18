package br.com.senior.fis.edi.repository.custom;

public interface ProcessaEdiRepository {
	
	void callNotfis() throws Exception;
	void callPrefat() throws Exception;
	void gravaLogConemb(Long Empresa, Long Transportadora, String Log) throws Exception;
	void gravaLogOcorren(Long Empresa, Long Transportadora, String Log) throws Exception;
}
