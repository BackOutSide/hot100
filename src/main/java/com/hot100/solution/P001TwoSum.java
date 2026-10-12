package com.hot100.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和（简单）
 *
 * <p>一次遍历。对当前数字 nums[i]，先检查 target - nums[i] 是否出现过，
 * 再把当前数字写入哈希表，避免同一个元素被使用两次。</p>
 *
 * <p>时间复杂度 O(n)，空间复杂度 O(n)。</p>
 */
public final class P001TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer otherIndex = indexByValue.get(complement);
            if (otherIndex != null) {
                return new int[]{otherIndex, i};
            }
            indexByValue.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two-sum solution");
    }
}

