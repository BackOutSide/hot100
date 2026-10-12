# Hot 100 Java 快速通关

这个项目用于快速过一遍力扣 [LeetCode 热题 100（学习计划版）](https://leetcode.cn/studyplan/top-100-liked/) 中简单题和中等题的解题思路。

- 范围：20 道简单题 + 68 道中等题，共 88 道
- 暂不处理：12 道困难题
- 环境：Java 17、Maven、JUnit 5
- 当前模式：思路速览，不要求每题都完整编码
- 目标：看到题目能想起核心套路、关键 Java 数据结构和复杂度

日常使用以 [CHECKIN.md](CHECKIN.md) 为主；需要更完整说明时阅读 [QUICK_REVIEW.md](QUICK_REVIEW.md)，原始清单见 [PROGRESS.md](PROGRESS.md)，常用 Java 模板见 [notes/patterns.md](notes/patterns.md)。

## 立即开始

1. 打开 `CHECKIN.md`，按 17 个专题顺序往下看。
2. 每题先遮住破折号后面的内容，自己用一句话说思路。
3. 能复述就在 `PROGRESS.md` 勾选；不能复述就追加 `REVIEW`。

## 某题需要动手时

项目自带“1. 两数之和”的可运行 Java 示例。只有遇到不熟或容易混淆的题，再生成源码和测试实际写一遍：

新增一道题：

```powershell
.\scripts\New-Problem.ps1 `
  -Number 49 `
  -Name GroupAnagrams `
  -Title "字母异位词分组" `
  -Difficulty Medium `
  -Topic "哈希"
```

脚本会同时生成：

- `src/main/java/com/hot100/solution/P049GroupAnagrams.java`
- `src/test/java/com/hot100/solution/P049GroupAnagramsTest.java`
- `notes/problems/P049GroupAnagrams.md`

新测试默认带有 `@Disabled`。实现题目后删除该注解，补上官方示例和至少一个边界用例。

运行单题测试：

```powershell
mvn -q -Dtest=P049GroupAnagramsTest test
```

运行单个测试方法：

```powershell
mvn -q "-Dtest=P049GroupAnagramsTest#examplesAndBoundaryCases" test
```

## 思路速览节奏

1. 看题名，先用 30 秒判断属于哪种题型。
2. 阅读速查表中的一句话思路，脑内走一遍指针或状态变化。
3. 说出关键 Java 数据结构，例如 `HashMap`、`Deque`、`PriorityQueue`。
4. 说出时间、空间复杂度；能复述就勾选，不能复述就标记 `REVIEW`。
5. 只有特别陌生或容易混淆的题，才进入源码和测试实际写一遍。

每天速览 12～15 道，约一周完成首轮；第二轮只看标记为 `REVIEW` 的题。

## 本轮勾选标准

满足以下条件即可在 `PROGRESS.md` 中勾选：

- 能用一句话复述核心思路；
- 知道主要 Java 数据结构；
- 知道大致时间、空间复杂度。

不要求当前就能默写完整代码。后续准备面试或专项补弱时，再把重点题升级为独立编码。

## 目录

```text
hot100
├─ pom.xml
├─ CHECKIN.md
├─ PROGRESS.md
├─ notes
│  ├─ patterns.md
│  └─ problems
├─ scripts
│  └─ New-Problem.ps1
├─ templates
└─ src
   ├─ main\java\com\hot100
   │  ├─ common       # ListNode、TreeNode
   │  └─ solution     # P题号英文名，一题一类
   └─ test\java\com\hot100
      ├─ solution
      └─ support      # 测试数据构造工具
```

## 代码约定

- 普通题：类名统一为 `P001TwoSum`，方法签名尽量与力扣一致。
- 不写 `main` 方法；JUnit 测试就是运行入口。
- `ListNode` 和 `TreeNode` 共用 `common` 中的定义，不覆盖 `equals/hashCode`。
- LRU、MinStack、Trie 等设计题可使用题号外壳 + 静态内部类；提交时复制内部类。
- 不强制“一题一种写法”。首轮保留最容易复现的正确解法，复习时再补最优解。

