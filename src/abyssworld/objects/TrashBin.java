/**
 * 
 */
package abyssworld.objects;

import abyssworld.abstracts.GameEntity;
import abyssworld.enums.TrashBinType;

/**
 * @author montimage
 *
 */
public class TrashBin extends GameEntity{
	
	private TrashBinType type;
	private String imageSrc;
	public TrashBin(TrashBinType type, int x, int y) {
		this.render(x, y);
		this.type = type;
		switch(type) {
			case TB_PLASTIC:
				this.imageSrc = "images/tb_plastic.png";
				break;
			case TB_ORGANIC:
				this.imageSrc = "images/tb_organic.png";
				break;
			case TB_PAPER:
				this.imageSrc = "images/tb_paper.png";
				break;
		}
	}
	
	public TrashBinType getType() {
		return this.type;
	}
	
	public String getImageSrc() {
		return this.imageSrc;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
}
