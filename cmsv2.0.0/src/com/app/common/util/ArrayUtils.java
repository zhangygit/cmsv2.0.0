package com.app.common.util;
/**
 * @author Tom
 */
public class ArrayUtils {
	 public static final int INDEX_NOT_FOUND = -1;
	public static Integer[] convertStrArrayToInt(String[]strArray){
		if(strArray!=null&&strArray.length>0){
			Integer array[]=new Integer[strArray.length];
			for(int i=0;i<strArray.length;i++){  
			    array[i]=Integer.parseInt(strArray[i]);
			}
			return array;
		}else{
			return null;
		}
	}
    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind) != INDEX_NOT_FOUND;
    }
    
    public static int indexOf(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }
    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }
}
