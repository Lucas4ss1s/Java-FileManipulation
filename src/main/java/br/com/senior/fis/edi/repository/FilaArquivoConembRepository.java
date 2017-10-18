package br.com.senior.fis.edi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.senior.fis.edi.entities.FilaArquivoConemb;

public interface FilaArquivoConembRepository extends CrudRepository<FilaArquivoConemb, Long>{
	
	@Query(" select l"
		+  "   from FilaArquivoConemb l"
		+  "  where l.status != 'P'"
		+  "    and l.idMove = 'N'")
	public List<FilaArquivoConemb> buscaStatus();

}
