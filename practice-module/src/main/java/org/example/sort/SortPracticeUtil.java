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

    /**
     * 希尔排序
     * @return
     */
    public static int[] shellSort(int[] array){
//        if(array==null||array.length<2) return array;
        int gap = array.length/2;
        while (gap > 0){
            for (int i = 0; i < array.length-gap; i++) {
                if(array[i] > array[i+gap]){
                    int tmp = array[i];
                    array[i] = array[i+gap];
                    array[i+gap] = tmp;
                }
            }
            gap--;
        }
        return array;
    }

    /**
     * 推排序（利用二叉树性质排序）
     * @param arr
     * @return
     */
    public static int[] heapSort(int[] arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1; i>=0; i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            maximumHeap(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1; j>0; j--){
            swap(arr,0, j);//将堆顶元素与末尾元素进行交换
            maximumHeap(arr,0, j);//重新对堆进行调整
        }
        return arr;
    }
    public static void maximumHeap(int[] arr, int i, int len){
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<len;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<len && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }
    public static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
