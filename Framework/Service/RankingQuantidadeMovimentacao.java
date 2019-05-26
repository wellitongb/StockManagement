package Service;

import Model.Usuario;
import java.util.Collections;

public class RankingQuantidadeMovimentacao implements IRankingUsuarioStrategy {

	public class UsuarioQuantidadeMovimentacao implements Comparator<Usuario>{
		public int compare( Usuario usuario_1, Usuario usuario_2 ){
			 
			if(  usuario_1.getQuantidadeDeMovimentacao() < usuario_2.getQuantidadeDeMovimentacao() ){
				return -1;
			}	
			else if(  usuario_1.getQuantidadeDeMovimentacao() > usuario_2.getQuantidadeDeMovimentacao() ){
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
		return Collections.( usuarios, new UsuarioQuantidadeMovimentacao() );
	}

}
