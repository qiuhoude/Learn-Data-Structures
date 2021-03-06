package com.houde.programmermath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author qiukun
 * @create 2019-02-01 12:24
 */
public class MergeSort {

    private static final Logger logger = LoggerFactory.getLogger(Factorization.class);

    public static int[] msort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        //先进行拆分
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        left = msort(left);
        right = msort(right);

        //再合并
        return mergeArr(left, right);
    }

    private static int[] mergeArr(int[] left, int[] right) {
        int sumCnt = left.length + right.length;
        int[] newArr = new int[sumCnt];
        int leftIndex = 0;
        int rightIndex = 0;
        int mi = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            int tmp;
            if (left[leftIndex] <= right[rightIndex]) {
                tmp = left[leftIndex];
                leftIndex++;
            } else {
                tmp = right[rightIndex];
                rightIndex++;
            }
            newArr[mi] = tmp;
            mi++;
        }
        if (leftIndex < left.length) {
            for (int i = leftIndex; i < left.length; i++) {
                newArr[mi] = left[i];
                mi++;
            }

        } else {
            for (int i = rightIndex; i < right.length; i++) {
                newArr[mi] = right[i];
                mi++;
            }
        }
        return newArr;
    }


    public static void main(String[] args) {
        int[] narr = {7, 6, 4, 1, 9, 3, 8, 0, 5};
        logger.debug("排序前：{}", Arrays.toString(narr));

        logger.debug("----------------");

        int[] marr = msort(narr);
        logger.debug("排序后：{}", Arrays.toString(marr));
    }
}
