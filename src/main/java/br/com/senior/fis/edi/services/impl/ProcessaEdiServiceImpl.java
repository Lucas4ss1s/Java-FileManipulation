package br.com.senior.fis.edi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.senior.fis.edi.repository.custom.ProcessaEdiRepository;
import br.com.senior.fis.edi.service.ProcessaEdiService;

@Service
public class ProcessaEdiServiceImpl implements ProcessaEdiService {
	
	@Autowired
	ProcessaEdiRepository processaEdiRepository;
	
	@Override
	@Transactional (rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void callNotfis() throws Exception{
		processaEdiRepository.callNotfis();
	};
	
	@Override
	@Transactional (rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void callPrefat() throws Exception{
		processaEdiRepository.callPrefat();
	};
	
	@Override
	@Transactional (rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void gravaLogConemb(Long Empresa, Long Transportadora, String Log) throws Exception{
		processaEdiRepository.gravaLogConemb(Empresa, Transportadora, Log);
	};
	
	@Override
	@Transactional (rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void gravaLogOcorren(Long Empresa, Long Transportadora, String Log) throws Exception{
		processaEdiRepository.gravaLogOcorren(Empresa, Transportadora, Log);
	};

}
