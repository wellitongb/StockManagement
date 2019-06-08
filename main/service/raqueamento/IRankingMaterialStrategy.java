package service.raqueamento;

import java.util.List;
import model.Material;

public interface IRankingMaterialStrategy {

	public List<Material> ranquear(List<Material> materiais);

}
