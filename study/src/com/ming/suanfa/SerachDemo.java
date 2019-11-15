package com.ming.suanfa;

import java.util.Arrays;

/**
 * 算法demo
 */
public class SerachDemo {
    public static void main(String[] args){
        int[] sourceArry = {1,2,3,4,5,6,8,9,10,100};
        System.out.println("biSearch:"+biSearch(sourceArry,3));
        int[] array = {4,3,2,1};
        System.out.println("bubbleSort:"+Arrays.toString(bubbleSort(array))); //3421,3241,3214。 2314,2134。 1234。
        System.out.println("insertSort:"+Arrays.toString(bubbleSort(array))); //3421。  3241,2341。  2314,2134,1234。

    }

    /**
     *
     * 二分查找：要求待查序列有序
     * 假设 sourceArray 为升序
     * @param sourceArray
     * @param target
     * @return
     */
    public static int biSearch(int[] sourceArray,int target){
        int start = 0;
        int last = sourceArray.length - 1;
        int mid;
        while (start <= last){
            mid = (start + last) / 2;
            if(sourceArray[mid] == target){
                //等于 target 返回
                return mid;
            }else if(sourceArray[mid] < target){
                //小于target 则查找右边
                start = mid + 1;
            }else{
                //中间元素大于 target，则查找左边元素。
                last = mid -1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序：排为升序,时间复杂度为：n的平方
     * 每次冒泡排序操作都会将相邻的两个元素进行比较，看是否满足大小关系要求，如果不满足，就交换这两个相邻元素的次序，
     * 一次冒泡至少让一个元素移动到它应该排列的位置，重复N次，就完成了冒泡排序
     * @param sourceArray
     * @return
     */
    public static int[] bubbleSort(int[] sourceArray){
        for(int i = 0; i < sourceArray.length-1;i++){
            for(int j = 1; j < sourceArray.length;j++){
                if(sourceArray[j - 1] > sourceArray[j]){
                    int temp = sourceArray[j-1];
                    sourceArray[j-1] = sourceArray[j];
                    sourceArray[j] = temp;
                }
            }
        }
        return sourceArray;
    }


    /**
     * 插入排序 排为升序 时间复杂度为n的平方
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * 插入排序在实现上，在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     * @param sourceArray
     * @return
     */
    public static int[] insertSort(int[] sourceArray){
        for(int i = 1;i<sourceArray.length;i++){
            int insertVal = sourceArray[i];
            int index = i-1;
            while (index >= 0 && insertVal < sourceArray[index]){
                sourceArray[index + 1] = sourceArray[index];
                index -- ;
            }
            sourceArray[index+1] = insertVal;
        }
        return sourceArray;
    }

}
