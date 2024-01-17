package org.duffy.leet_code;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizedSet {
    private final ArrayList<Integer> nums;
    private final Map<Integer, Integer> indexMap;
    private final ThreadLocalRandom random;

    public RandomizedSet() {
        this.nums = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.random = ThreadLocalRandom.current();
    }

    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }
        indexMap.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }

        int idx = indexMap.get(val);
        int lastIdx = nums.size() - 1;
        if (idx != lastIdx) {
            int lastVal = nums.get(nums.size() - 1);
            nums.set(idx, lastVal);
            indexMap.put(lastVal, idx);
        }
        nums.remove(lastIdx);
        indexMap.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}
