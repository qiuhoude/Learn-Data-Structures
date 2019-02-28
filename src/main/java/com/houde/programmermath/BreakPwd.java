package com.houde.programmermath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 暴力破解密码, 假设有一个4位字母密码，每位密码是a～e之间的小写字母?
 *
 * @author qiukun
 * @create 2019-02-01 13:23
 */
public class BreakPwd {
    private static final Logger logger = LoggerFactory.getLogger(CombinationCoin.class);


    private static final String PWD = "beac";

    /**
     * 密码是否正确
     */
    public static boolean isCorrect(char[] arr) {
        String s = new String(arr);
        return PWD.equals(s);
    }


    /**
     * 全排列
     *
     * @param array
     * @param tmpArr
     * @param n
     */
    public static void permutation(char[] array, char[] tmpArr, int n) {
        if (n == 1) { // 密码最后一位的时候
            for (int i = 0; i < array.length; i++) {
                tmpArr[tmpArr.length - n] = array[i];
                if (isCorrect(tmpArr)) {
                    System.out.println(new String(tmpArr));
                    System.out.println("破解成功");
                    return;
                }
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                tmpArr[tmpArr.length - n] = array[i];
                permutation(array, tmpArr, n - 1);
            }
        }
    }

    public static void breakPwd(char[] arr, int m) {
        char[] tmpArr = new char[m];
        permutation(arr, tmpArr, m);
    }

    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};
        breakPwd(arr, 4);
    }
}

