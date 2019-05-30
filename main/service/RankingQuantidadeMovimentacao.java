package service;

import model.Usuario;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingQuantidadeMovimentacao implements IRankingUsuarioStrategy {

	public class UsuarioQuantidadeMovimentacao implements Comparator<Usuario>{
		
		@Override
		public int compare( Usuario usuario_1, Usuario usuario_2 ){
			 
			if(  usuario_1.getQuantidadeDeMovimentacoes() < usuario_2.getQuantidadeDeMovimentacoes() ){
				return -1;
			}	
			else if(  usuario_1.getQuantidadeDeMovimentacoes() > usuario_2.getQuantidadeDeMovimentacoes() ){
				return 1;
			}
			else{
				return 0;
			}
		}
	}

	/**
	 * @see Service.IRankingUsuarioStrategy#ranquear()
	 */
	public List<Usuario> ranquear(List<Usuario> usuarios) {
		Collections.sort( usuarios, new UsuarioQuantidadeMovimentacao() );
		return usuarios;
	}

}
