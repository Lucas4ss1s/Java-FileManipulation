package br.com.senior.fis.edi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.senior.fis.edi.entities.BuscaArquivoEntradaTransp;

public interface BuscaArquivoEntradaTranspRepository extends CrudRepository<BuscaArquivoEntradaTransp, Long> {

	@Query(" select l"
		 + " from BuscaArquivoEntradaTransp l")
	public List<BuscaArquivoEntradaTransp> buscaArquivoEntradaTransp();
}
