package service;

import model.Material;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Revisar classe!

public class RankingDataEntrada implements IRankingMaterialStrategy {

	public class MaterialDataEntrada implements Comparator<Material>{
		public int compare( Material material_1, Material material_2 ){
			return material_1.getDataEntrada().compareTo(material_2.getDataEntrada());
		}
	}

	/// MÉTODOS

	/**
	 * @see service.IRankingMaterialStrategy#ranquear()
	 */
	public List<Material> ranquear(List<Material> materiais) {
		Collections.sort( materiais, new MaterialDataEntrada() );
		return materiais;
	}

}
