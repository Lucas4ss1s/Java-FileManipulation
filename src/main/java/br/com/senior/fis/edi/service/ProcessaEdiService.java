package br.com.senior.fis.edi.service;

public interface ProcessaEdiService {

	void callNotfis() throws Exception;
	void callPrefat() throws Exception;	
	void gravaLogConemb(Long Empresa, Long Transportadora, String Log ) throws Exception;
	void gravaLogOcorren(Long Empresa, Long Transportadora, String Log) throws Exception;
}
