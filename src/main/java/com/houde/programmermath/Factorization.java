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
     * 因数分解，打印每一种情况
     *
     * @param total
     * @param result
     */
    private static void factorization(int total, ArrayList<Integer> result) {
        if (total == 1) {
            if (!result.contains(1)) result.add(1);
            logger.debug(result.toString());
            return;
        } else {
            for (int i = 1; i <= total; i++) {
                if (i == 1 && result.contains(1)) continue;
                ArrayList<Integer> newResult = (ArrayList<Integer>) result.clone();
                newResult.add(i);
                if (total % i != 0) {
                    continue;
                } else {
                    factorization(total / i, newResult);
                }
            }
        }
    }

    public static void main(String[] args) {
        factorization(8, new ArrayList<>());
    }

}
