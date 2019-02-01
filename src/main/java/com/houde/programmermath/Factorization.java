package com.houde.programmermath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * 因数分解 例如：将 8 分解成 1x8； 1x2x4；4x1x2；....
 *
 * @author qiukun
 * @create 2019-02-01 12:23
 */
public class Factorization {
    private static final Logger logger = LoggerFactory.getLogger(Factorization.class);

    /**
     * 因数分解
     *
     * @param total
     * @param result
     */
    private static void factorization(int total, ArrayList<Integer> result) {
        if (total == 1) { //条件满足
            if (!result.contains(1)) { //不包含1需要添加进去
                result.add(1);
            }
            logger.debug(result.toString());
        } else {
            for (int i = 1; i <= total; i++) {
                if (i == 1 && result.contains(1)) continue;
                if (total % i != 0) { //排除掉不能被整除的数,比如 total是8 那么3,5,6,7就被排除了
                    continue;
                } else {
                    ArrayList<Integer> newResult = (ArrayList<Integer>) result.clone();
                    newResult.add(i);
                    factorization(total / i, newResult);
                }
            }
        }
    }

    public static void main(String[] args) {
        factorization(8, new ArrayList<>());
    }

}
