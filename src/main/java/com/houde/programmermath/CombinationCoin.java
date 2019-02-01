package com.houde.programmermath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 金币组合, 有金币种类 {1元,5元,10元} ,给你n元(n>0)，一共有多少种分发
 *
 * @author qiukun
 * @create 2019-02-01 12:33
 */
public class CombinationCoin {

    private static final Logger logger = LoggerFactory.getLogger(CombinationCoin.class);
    /**
     * 金币种类
     */
    private static final int[] COIN_TYPE = {1, 5, 10};

    /**
     * 金币组合构成
     *
     * @param totalMoney 金币数
     * @param result
     */
    private static void coinCombination(int totalMoney, ArrayList<Integer> result, List<List<Integer>> allResult) {
        if (totalMoney == 0) { // totalMoney满足条件打印
            logger.debug(result.toString());
            allResult.add(result);
        } else if (totalMoney < 0) {// totalMoney不满足条跳出
            return;
        } else {
            for (int i = 0; i < COIN_TYPE.length; i++) {
                ArrayList<Integer> nResult = (ArrayList<Integer>) result.clone();
                int money = COIN_TYPE[i];
                nResult.add(money);
                coinCombination(totalMoney - money, nResult, allResult);
            }
        }
    }

    public static void main(String[] args) {
        int totalMoney = 13;
        List<List<Integer>> allResult = new ArrayList<>();
        coinCombination(totalMoney, new ArrayList<>(), allResult);
        logger.debug("一共有 {} 种可能性", allResult.size());
    }
}
