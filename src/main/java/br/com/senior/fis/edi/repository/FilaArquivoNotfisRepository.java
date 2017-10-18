package br.com.senior.fis.edi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.senior.fis.edi.entities.FilaArquivoNotfis;

public interface FilaArquivoNotfisRepository extends CrudRepository<FilaArquivoNotfis, Long>{
	
	@Query(" select l"
	     +"   from FilaArquivoNotfis l"
		 +"  where l.idProcessado = 'N'"
	     +"    and l.dtProcessado is null")
	public  List<FilaArquivoNotfis> buscaArquivosNaoProcessados();

}
