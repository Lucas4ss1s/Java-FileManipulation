package br.com.senior.fis.edi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.senior.fis.edi.entities.FilaArquivoOcorren;

public interface FilaArquivoOcorrenRepository extends CrudRepository<FilaArquivoOcorren, Long>{
	
	@Query(" select l"
		+  "   from FilaArquivoOcorren l"
		+  "  where l.status != 'P'"
		+  "    and l.idMove = 'N'")
	public List<FilaArquivoOcorren> buscaStatus();

}
