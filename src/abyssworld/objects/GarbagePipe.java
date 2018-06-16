/**
 * 
 */
package abyssworld.objects;

import java.util.Random;

import abyssworld.enums.GarbageType;

/**
 * @author montimage
 *
 */
public class GarbagePipe {

	final int GARBAGE_GENERATED_TIME = 3; // 3s
	static long lastGarbageTime = 0;
	public static Garbage generateAGarbage(int xMin, int yMin, int xMax, int yMax) {
		Random random = new Random();
		int x = random.nextInt(xMax) + xMin;
		int y = random.nextInt(yMax) + yMin;
		GarbageType gbType = randomEnum(GarbageType.class);
		Garbage gb = new Garbage(gbType, x, y);
		return gb;
	}
	
	public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
		Random random = new Random();
		int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
