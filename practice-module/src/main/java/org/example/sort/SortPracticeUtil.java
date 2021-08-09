package org.example.sort;

import java.util.Arrays;

/**
 * 排序练习工具类
 */
public class SortPracticeUtil {

    /**
     * 快排
     * 时间复杂度 nlogn
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

    /**
     * 冒泡排序
     * 时间复杂度 O(n²)
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        if(array==null) return array;
        int count = array.length-1;
        while (count > 0){
            for (int i = 1; i < array.length; i++) {
                if(array[i] < array[i-1]){
                    int tmp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = tmp;
                }
            }
            count--;
        }
        return array;
    }

    /**
     * 选择排序
     * 最坏时间复杂度O(n²)
     * @param array
     * @return
     */
    public static int[] selectSort(int[] array){
        if(array==null||array.length<2) return array;
        for (int i = 0; i < array.length; i++) {
            int val = array[i], cur=i;
            for (int j = i+1; j < array.length; j++) {
                if(val > array[j]){
                    val = array[j];
                    cur = j;
                }
            }
            int tmp = array[i];
            array[i] = val;
            array[cur] = tmp;
        }
        return array;
    }

    /**
     * 归并排序
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array){
        if(array==null||array.length<2) return array;
        if(array.length==2 && array[0]>array[1]){
            int tmp = array[0];
            array[0] = array[1];
            array[1] = tmp;
            return array;
        }
        int mid = array.length/2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        left = mergeSort(left);
        right = mergeSort(right);
        if(left.length+right.length == 13) {
            return merge(left, right);
        }
        return merge(left, right);
    }
    public static int[] merge(int[] left, int[] right){
        int[] result = new int[left.length + right.length];
        int i = 0, lc=0, rc=0;
        while (lc < left.length && rc < right.length) {
            if (left[lc] <= right[rc]) {
                result[i++] = left[lc++];
            } else {
                result[i++] = right[rc++];
            }
        }
        while (lc < left.length) {
            result[i++] = left[lc++];
        }
        while (rc < right.length) {
            result[i++] = right[rc++];
        }
        return result;
    }
}
