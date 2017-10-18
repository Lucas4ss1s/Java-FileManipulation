package br.com.senior.fis.edi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.senior.fis.edi.entities.FilaArquivoPrefat;

public interface FilaArquivoPrefatRepository extends CrudRepository<FilaArquivoPrefat, Long>{

	@Query(" select l"
		 + "   from FilaArquivoPrefat l"
		 + "  where l.idProcessado = 'N'"
		 + "    and l.dtProcessado is null")
	public List<FilaArquivoPrefat> buscaArquivosNaoProcessados();
}
