package Service;

import Model.Material;
import java.util.Collections;

public class RankingDataEntrada implements IRankingMaterialStrategy {

	public class MaterialDataEntrada implements Comparator<Material>{
		public int compare( Material material_1, Material material_2 ){
			return material_1.getDataEntrada().compareTo(material_2);
		}
	}

	/// MÉTODOS

	/**
	 * @see Service.IRankingMaterialStrategy#ranquear()
	 */
	public List<Material> ranquear(List<Material> materiais) {
		return Collections.( materiais, new MaterialDataEntrada() );
	}

}
