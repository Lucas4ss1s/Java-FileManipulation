package br.com.senior.fis.edi.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.senior.fis.edi.repository.custom.ProcessaEdiRepository;

@Repository
public class ProcessaEdiRepositoryImpl implements ProcessaEdiRepository {	
		
	@Autowired
	EntityManager em;	
	
	@Override
	public void callNotfis() throws Exception{
		
		org.hibernate.Session session = em.unwrap(org.hibernate.Session.class);
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection conn) {
				java.sql.CallableStatement cs = null;
				
				try{
					cs = conn.prepareCall("{call K_FIS_EDI.PR_INTERF_NOTFIS_SAIDA}");
					cs.execute();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	@Override
	public void callPrefat() throws Exception{
		
		Session session = em.unwrap(Session.class);
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				CallableStatement cs = null;
				
				try {
					cs = conn.prepareCall("{call K_FIS_EDI.PR_INTERF_PREFAT_SAIDA}");
					cs.execute();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

	@Override
	public void gravaLogConemb(Long Empresa, Long Transportadora, String Log) throws Exception {
		
		Session session = em.unwrap(Session.class);
		session.doWork(new Work(){
			
			@Override
			public void execute(Connection conn) throws SQLException{
				CallableStatement cs = null;
				
				try{
					cs = conn.prepareCall("{call K_FIS_CONEMB.PR_GRAVA_LOG(?,?,?,?,?,?,?)}");
					cs.setLong(1, Empresa);
					cs.setLong(2, Transportadora);
					cs.setNull(3, Types.VARCHAR);
					cs.setString(4, "K_FIS_EDI.PR_INTERF_CONEMB_ENTRADA");
					cs.setString(5, "B");
					cs.setNull(6, Types.VARCHAR);
					cs.setString(7, Log);
					cs.execute();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		});
			
		
	}

	@Override
	public void gravaLogOcorren(Long Empresa, Long Transportadora, String Log) throws Exception {
		
		Session session = em.unwrap(Session.class);
		session.doWork(new Work(){
			
			@Override
			public void execute(Connection conn) throws SQLException{
				CallableStatement cs = null;
				
				try{
					cs = conn.prepareCall("{call K_FIS_EDI_OCORREN.PR_GRAVA_LOG(?,?,?,?,?,?,?,?,?,?,?,?)}");
					cs.setLong(1, Empresa);
					cs.setLong(2, Transportadora);
					cs.setNull(3, Types.VARCHAR);
					cs.setNull(4, Types.VARCHAR);
					cs.setNull(5, Types.VARCHAR);
					cs.setNull(6, Types.VARCHAR);
					cs.setString(7, "K_FIS_EDI.PR_INTERF_OCORREN_ENTRADA");
					cs.setString(8, "B");
					cs.setNull(9, Types.VARCHAR);
					cs.setString(10, Log);
					cs.setNull(12, Types.DATE);
					cs.setNull(12, Types.DATE);
					cs.execute();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		});
			
		
	}
}
