package com.houde.programmermath;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 组合的例子
 * <p>
 * n个元素里取出m个的组合 = 可能性数量就是n个里取m个的排列数量 / m个全排列的数量
 * n个元素里取出m个的组合 = (n! / ( n-m)!) / m!
 * <p>
 * 例如世界杯小组赛的组合, 32支队伍, 分8组, 每组4支球队, 每2支队伍进行比赛不分主客场（也就是不考虑顺序）；4个元素中取出2个的组合=
 * (4! / (4-2)!) / 2! = (4 * 3) / 2 = 6,每组比赛6场， 6 * 8 = 48场比赛
 * <p>
 * 全组合的一般写法 （效率低下）
 * 1. 列出所有排列组合 例如{t1, t2}, {t2, t1}
 * 2. 针对每种排列，对其中的元素按照一定的规则排序。得出 是{t1, t2},{t1, t2}
 * 3. 去重
 *
 * 优化写法
 * 1. 每次传入嵌套函数的剩余元素，不再是所有的未选择元素，而是出现在当前被选元素
 *
 * @author qiukun
 * @create 2019-02-28 14:05
 */
public class CombinationDemo {

    /**
     * 使用函数的递归（嵌套）调用，找出所有可能的队伍组合
     *
     * @param teams  目前还剩多少队伍没有参与组合
     * @param result 保存当前已经组合的队伍
     * @param m      个次组合取出m个队伍
     */
    public static void combine(ArrayList<String> teams, ArrayList<String> result, int m) {
        // 取完m个元素
        if (result.size() == m) {
            System.out.println(result);
            return;
        }
        for (int i = 0; i < teams.size(); i++) {
            // 从剩下的队伍中，选择一队，加入结果
            ArrayList<String> newResult = (ArrayList<String>) (result.clone());
            newResult.add(teams.get(i));
            // 只考虑当前选择之后的所有队伍
            ArrayList<String> resTeams = new ArrayList<>(teams.subList(i + 1, teams.size()));
            // 递归调用，对于剩余的队伍继续生成组合
            combine(resTeams, newResult, m);
        }
    }

    /**
     * 测试 combine 方法
     */
    public static void testCombine() {
        ArrayList<String> teams = new ArrayList<>(Arrays.asList("中国队", "巴西队", "哥斯达黎加队", "土耳其队"));
        combine(teams, new ArrayList<>(), 2);
    }

    public static void main(String[] args) {
        testCombine();
    }
}
