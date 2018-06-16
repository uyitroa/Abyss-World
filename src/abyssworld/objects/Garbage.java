/**
 * 
 */
package abyssworld.objects;

import abyssworld.abstracts.GameEntity;
import abyssworld.enums.GarbageType;
import abyssworld.enums.TrashBinType;

/**
 * @author montimage
 *
 */
public class Garbage extends GameEntity{

	private GarbageType type;
	private String imageSrc;
	private TrashBinType tbType;
	
	public Garbage(GarbageType type, int x, int y) {
		this.render(x, y);
		this.type = type;
		switch(type) {
		case GB_PLASTIC_1:
			this.imageSrc = "images/gb_plastic_1.png";
		case GB_PLASTIC_2:
			this.imageSrc = "images/gb_plastic_2.png";
		case GB_PLASTIC_3:
			this.imageSrc = "images/gb_plastic_3.png";
			this.tbType = TrashBinType.TB_PLASTIC;
			break;
		case GB_ORGANIC_1:
			this.imageSrc = "images/gb_organic_1.png";
		case GB_ORGANIC_2:
			this.imageSrc = "images/gb_organic_2.png";
		case GB_ORGANIC_3:
			this.imageSrc = "images/gb_organic_3.png";
			this.tbType = TrashBinType.TB_ORGANIC;
			break;
		case GB_PAPER_1:
			this.imageSrc = "images/gb_paper_1.png";
		case GB_PAPER_2:
			this.imageSrc = "images/gb_paper_2.png";
		case GB_PAPER_3:
			this.imageSrc = "images/gb_paper_3.png";
			this.tbType = TrashBinType.TB_PAPER;
			break;
		}
	}
	
	public TrashBinType getTrashBinType() {
		return this.tbType;
	}

	/**
	 * @return the type
	 */
	public GarbageType getType() {
		return type;
	}

	/**
	 * @return the imageSrc
	 */
	public String getImageSrc() {
		return imageSrc;
	}

	public void show() {
		System.out.println("Gabarge: ");
		System.out.println("x: " + this.getX());
		System.out.println("y: " + this.getY());
		System.out.println("type: " + this.getType());
		System.out.println("TrashBin Type: " + this.getTrashBinType());
		System.out.println("Image src: " + this.getImageSrc());		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
