package com.houde.programmermath;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 排列的例子
 * <p>
 * n个元素里取出m个进行全排列 = n! / ( n-m)!
 * n个元素的全排列 = n!
 *
 * @author qiukun
 * @create 2019-02-28 14:37
 */
public class PermutationDemo {
    public static void permutation(ArrayList<String> teams, ArrayList<String> result, int m) {
        // 取完m个元素
        if (result.size() == m) {
            System.out.println(result);
            return;
        }
        for (int i = 0; i < teams.size(); i++) {
            ArrayList<String> newResult = (ArrayList<String>) (result.clone());
            String element = teams.get(i);
            if (newResult.contains(element)) { // 去掉重复的 , 之前密码破解就不能
                continue;
            } else {
                newResult.add(element);
            }
            permutation(teams, newResult, m);
        }
    }

    /**
     * 测试 permutation 方法
     */
    public static void testPermutation() {
//        ArrayList<String> teams = new ArrayList<>(Arrays.asList("中国队", "巴西队", "哥斯达黎加队", "土耳其队"));
//        permutation(teams, new ArrayList<>(), 2);

        ArrayList<String> teams = new ArrayList<>(Arrays.asList("上等马", "中等马", "下等马"));
        permutation(teams, new ArrayList<>(), 3);
    }

    public static void main(String[] args) {
        testPermutation();
    }
}
