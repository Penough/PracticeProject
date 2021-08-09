package org.example.sort;

/**
 * 排序练习工具类
 */
public class SortPracticeUtil {

    /**
     * 快排
     * @param array
     * @return
     */
    public static int[] quickSort(int[] array){
        int i=0, j=array.length-1;
        return quickSort(array, i , j);
    }
    public static int[] quickSort(int[] array, int i, int j){
        if (i<j){
            int pos = quickSortSubArray(array, i , j);
            quickSort(array, i, pos-1);
            quickSort(array, pos+1, j);
        }
        return array;
    }
    public static int quickSortSubArray(int[] array, int i, int j){
        int head = array[i];
        while (i<j) {
            while(array[j]>=head && i < j) j--;
            array[i] = array[j];
            while(array[i]<head && i < j) i++;
            array[j] = array[i];
        }
        array[i] = head;
        return i;
    }
}
