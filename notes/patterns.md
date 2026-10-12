# Java 刷题速查

只记录高频模板。理解不清时回到原理，不要机械背代码。

## 1. 哈希计数 / 查找

```java
Map<Integer, Integer> count = new HashMap<>();
count.merge(x, 1, Integer::sum);
int frequency = count.getOrDefault(x, 0);
```

关键词：去重、配对、计数、是否出现过、前缀和次数。

## 2. 双指针

```java
int left = 0, right = nums.length - 1;
while (left < right) {
    if (/* 当前状态满足条件 */) {
        left++;
    } else {
        right--;
    }
}
```

关键词：有序数组、两端向中间、原地修改、快慢指针。

## 3. 滑动窗口

```java
int left = 0;
for (int right = 0; right < s.length(); right++) {
    // 把 s.charAt(right) 加入窗口
    while (/* 窗口不合法 */) {
        // 移除 s.charAt(left)
        left++;
    }
    // 用 [left, right] 更新答案
}
```

关键问题只有三个：窗口里存什么、何时收缩、何时更新答案。

## 4. 前缀和

```java
int prefix = 0;
Map<Integer, Integer> frequency = new HashMap<>();
frequency.put(0, 1);
for (int x : nums) {
    prefix += x;
    answer += frequency.getOrDefault(prefix - k, 0);
    frequency.merge(prefix, 1, Integer::sum);
}
```

## 5. 二分边界

```java
int left = 0, right = nums.length; // [left, right)
while (left < right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid;
    }
}
// left 是第一个 >= target 的位置
```

## 6. 栈与单调栈

```java
Deque<Integer> stack = new ArrayDeque<>();
while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
    int index = stack.pop();
    // i 是 index 右侧第一个更大的位置
}
stack.push(i);
```

用 `Deque`，不要再用旧的 `Stack` 类。

## 7. BFS

```java
Deque<TreeNode> queue = new ArrayDeque<>();
queue.add(root);
while (!queue.isEmpty()) {
    int levelSize = queue.size();
    for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.remove();
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
}
```

图和网格 BFS 要在“入队时”标记访问，避免重复入队。

## 8. 二叉树 DFS

```java
private int dfs(TreeNode node) {
    if (node == null) return 0;
    int left = dfs(node.left);
    int right = dfs(node.right);
    return 1 + Math.max(left, right);
}
```

先明确递归函数返回什么，再写当前节点如何组合左右子树结果。

## 9. 回溯

```java
private void backtrack(int start, List<Integer> path) {
    if (/* 找到答案 */) {
        answers.add(new ArrayList<>(path));
        return;
    }
    for (int i = start; i < candidates.length; i++) {
        path.add(candidates[i]);
        backtrack(i + 1, path);
        path.remove(path.size() - 1);
    }
}
```

检查：选择列表、结束条件、是否需要排序、同层去重还是全局去重。

## 10. 堆 / Top K

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int x : nums) {
    minHeap.offer(x);
    if (minHeap.size() > k) minHeap.poll();
}
```

求前 K 大通常维护大小为 K 的小顶堆；求前 K 小反之。

## 11. 动态规划

下笔前写清四件事：

1. `dp[i]` 或 `dp[i][j]` 的含义；
2. 状态转移；
3. 初始状态；
4. 遍历顺序。

空间优化放到正确性之后做。

## 12. 常用复杂度

- 哈希查找：平均 `O(1)`
- 排序：`O(n log n)`
- 堆插入 / 删除：`O(log k)`
- 树 DFS / BFS：`O(n)`
- 回溯：通常是指数级，要说明剪枝
- 二维 DP：常见 `O(mn)` 时间和空间

