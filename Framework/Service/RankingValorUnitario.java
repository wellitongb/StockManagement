package Service;

import Model.Material;
import java.util.Collections;

public class RankingValorUnitario implements IRankingMaterialStrategy {

	public class MaterialValorUnitario implements Comparator<Material>{
		public int compare( Material material_1, Material material_2 ){
			if(  material_1.getValorUnitario() < material_2.getValorUnitario() ){
				return -1;
			}	
			else if(  material_1.getValorUnitario() > material_2.getValorUnitario() ){
				return 1;
			}
			else{
				return 0;
			}	
		}
	}

	/**
	 * @see Service.IRankingMaterialStrategy#ranquear()
	 */
	public List<Material> ranquear(List<Material> materiais) {
		return Collections.( materiais, new MaterialValorUnitario() );
	}

}
