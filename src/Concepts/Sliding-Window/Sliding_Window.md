# Sliding Window Problems in Java: Beginner to Advanced

Sliding window is one of the most important patterns for array and string problems.

It is usually used when the problem talks about:

```text
subarray
substring
contiguous part
window
longest
smallest / minimum
maximum sum of k elements
at most k
exactly k
contains all characters
no repeating characters
```

The most important keyword is:

```text
contiguous
```

Sliding window works on continuous parts of an array or string.

---

## 1. What is Sliding Window?

Suppose we have:

```text
nums = [1, 2, 3, 4, 5]
```

A window means a continuous part:

```text
[1, 2]
[2, 3]
[3, 4]
[4, 5]
```

Example:

```text
Find sum of every subarray of size 3.
```

```text
nums = [1, 2, 3, 4, 5]
k = 3
```

Windows:

```text
[1, 2, 3] = 6
[2, 3, 4] = 9
[3, 4, 5] = 12
```

Brute force recalculates every window:

```text
Time = O(n * k)
```

Sliding window reuses previous result:

```text
Remove left element
Add right element
Time = O(n)
```

---

## 2. Types of Sliding Window

There are mainly two types:

```text
1. Fixed-size sliding window
2. Variable-size sliding window
```

---

# Part 1: Fixed-Size Sliding Window

Use fixed-size window when the window size is already given.

Common keywords:

```text
size k
length k
subarray of k elements
substring of length k
maximum sum of k elements
average of k elements
```

---

## 3. Fixed Window Template

```java
int left = 0;
int sum = 0;
int maxSum = Integer.MIN_VALUE;

for (int right = 0; right < nums.length; right++) {
    sum += nums[right];

    if (right - left + 1 == k) {
        maxSum = Math.max(maxSum, sum);

        sum -= nums[left];
        left++;
    }
}
```

Meaning:

```text
right expands the window
when size becomes k, process answer
then remove left and slide
```

---

## 4. Example: Maximum Sum Subarray of Size K

```java
public int maxSumSubarray(int[] nums, int k) {
    int left = 0;
    int sum = 0;
    int maxSum = Integer.MIN_VALUE;

    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];

        if (right - left + 1 == k) {
            maxSum = Math.max(maxSum, sum);

            sum -= nums[left];
            left++;
        }
    }

    return maxSum;
}
```

Example:

```text
nums = [2, 1, 5, 1, 3, 2]
k = 3
```

Dry run:

```text
[2, 1, 5] = 8
[1, 5, 1] = 7
[5, 1, 3] = 9
[1, 3, 2] = 6
```

Answer:

```text
9
```

---

# Part 2: Variable-Size Sliding Window

Use variable-size sliding window when the window size is not fixed.

Common keywords:

```text
longest substring
smallest subarray
minimum window
at most k
no repeating
sum >= target
contains all characters
```

---

## 5. General Variable Window Template

```java
int left = 0;

for (int right = 0; right < n; right++) {
    // 1. Add nums[right] or s.charAt(right)

    while (window is invalid) {
        // 2. Remove nums[left] or s.charAt(left)
        left++;
    }

    // 3. Update answer
}
```

---

# Part 3: Longest Window Pattern

Use this when the problem asks:

```text
longest substring
longest subarray
maximum length
at most k
no repeating
```

Rule:

```text
Expand right.
If invalid, shrink using while.
After window becomes valid, update answer.
```

---

## 6. Longest Window Template

```java
int left = 0;
int maxLen = 0;

for (int right = 0; right < n; right++) {
    // add right element

    while (window is invalid) {
        // remove left element
        left++;
    }

    maxLen = Math.max(maxLen, right - left + 1);
}
```

Why update after `while`?

```text
Because for longest window, first make the window valid,
then store the maximum valid length.
```

---

## 7. Example: Longest Substring Without Repeating Characters

Problem:

```text
s = "abcabcbb"
```

Answer:

```text
3
```

Because longest substring without repeating characters is:

```text
"abc"
```

Java code:

```java
import java.util.*;

public int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();

    int left = 0;
    int maxLen = 0;

    for (int right = 0; right < s.length(); right++) {
        char curr = s.charAt(right);

        while (set.contains(curr)) {
            set.remove(s.charAt(left));
            left++;
        }

        set.add(curr);

        maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
}
```

Important:

```text
Use while, not if.
```

Because one removal may not be enough to make the window valid.

---

# Part 4: Smallest / Minimum Window Pattern

Use this when the problem asks:

```text
minimum length
smallest subarray
minimum window substring
shortest window
```

Rule:

```text
Expand right until window becomes valid.
Then shrink from left as much as possible.
Update answer inside while.
```

---

## 8. Smallest Window Template

```java
int left = 0;
int minLen = Integer.MAX_VALUE;

for (int right = 0; right < n; right++) {
    // add right element

    while (window is valid) {
        minLen = Math.min(minLen, right - left + 1);

        // remove left element
        left++;
    }
}
```

Why update inside `while`?

```text
Because once the window becomes valid,
we keep shrinking to find the smallest valid window.
```

---

## 9. Example: Minimum Size Subarray Sum

Problem:

```text
nums = [2, 3, 1, 2, 4, 3]
target = 7
```

Answer:

```text
2
```

Because:

```text
[4, 3] = 7
```

Java code:

```java
public int minSubArrayLen(int target, int[] nums) {
    int left = 0;
    int sum = 0;
    int minLen = Integer.MAX_VALUE;

    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];

        while (sum >= target) {
            minLen = Math.min(minLen, right - left + 1);

            sum -= nums[left];
            left++;
        }
    }

    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
```

---

# Part 5: Most Important Rule

```text
If asked for longest window:
    update answer after while

If asked for smallest window:
    update answer inside while
```

Reason:

```text
Longest window:
    first make it valid, then store maximum size

Smallest window:
    once valid, keep shrinking and store minimum size
```

---

# Part 6: while vs if in Sliding Window

Usually use `while` when shrinking.

Bad:

```java
if (set.contains(curr)) {
    set.remove(s.charAt(left));
    left++;
}
```

Good:

```java
while (set.contains(curr)) {
    set.remove(s.charAt(left));
    left++;
}
```

Reason:

```text
if removes only one element.
while removes until the window becomes valid.
```

Example:

```text
s = "abcdbef"
```

When duplicate appears, one removal may not be enough.

---

# Part 7: Frequency Map Sliding Window

Use frequency map when problem talks about:

```text
anagram
permutation
contains characters
minimum window substring
character count
at most k distinct characters
```

---

## 10. Frequency Map Base Example: Valid Anagram

```java
import java.util.*;

public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;

    Map<Character, Integer> map = new HashMap<>();

    for (char ch : s.toCharArray()) {
        map.put(ch, map.getOrDefault(ch, 0) + 1);
    }

    for (char ch : t.toCharArray()) {
        if (!map.containsKey(ch)) return false;

        map.put(ch, map.get(ch) - 1);

        if (map.get(ch) == 0) {
            map.remove(ch);
        }
    }

    return map.isEmpty();
}
```

---

# Part 8: Fixed Window + Frequency Map

## 11. Example: Find All Anagrams in a String

Problem:

```text
s = "cbaebabacd"
p = "abc"
```

Answer:

```text
[0, 6]
```

Because:

```text
s[0..2] = "cba"
s[6..8] = "bac"
```

Both are anagrams of `"abc"`.

Java code:

```java
import java.util.*;

public List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();

    if (p.length() > s.length()) return result;

    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();

    for (char ch : p.toCharArray()) {
        need.put(ch, need.getOrDefault(ch, 0) + 1);
    }

    int left = 0;
    int k = p.length();

    for (int right = 0; right < s.length(); right++) {
        char curr = s.charAt(right);
        window.put(curr, window.getOrDefault(curr, 0) + 1);

        if (right - left + 1 == k) {
            if (window.equals(need)) {
                result.add(left);
            }

            char leftChar = s.charAt(left);
            window.put(leftChar, window.get(leftChar) - 1);

            if (window.get(leftChar) == 0) {
                window.remove(leftChar);
            }

            left++;
        }
    }

    return result;
}
```

---

# Part 9: Advanced Frequency Window with have and need

This is used in:

```text
Minimum Window Substring
Permutation in String
Find All Anagrams
```

Instead of comparing two maps every time, we maintain:

```text
needCount = number of unique characters needed
have = number of unique characters currently satisfied
```

Example:

```text
t = "AABC"
```

Need map:

```text
A -> 2
B -> 1
C -> 1
```

So:

```text
needCount = 3
```

When window has:

```text
A -> 2
B -> 1
C -> 1
```

Then:

```text
have = 3
```

Valid window means:

```text
have == needCount
```

---

## 12. Example: Minimum Window Substring

Problem:

```text
s = "ADOBECODEBANC"
t = "ABC"
```

Answer:

```text
"BANC"
```

Java code:

```java
import java.util.*;

public String minWindow(String s, String t) {
    if (t.length() == 0 || t.length() > s.length()) {
        return "";
    }

    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();

    for (char ch : t.toCharArray()) {
        need.put(ch, need.getOrDefault(ch, 0) + 1);
    }

    int left = 0;
    int have = 0;
    int needCount = need.size();

    int minLen = Integer.MAX_VALUE;
    int startIndex = 0;

    for (int right = 0; right < s.length(); right++) {
        char curr = s.charAt(right);

        window.put(curr, window.getOrDefault(curr, 0) + 1);

        if (need.containsKey(curr) &&
            window.get(curr).intValue() == need.get(curr).intValue()) {
            have++;
        }

        while (have == needCount) {
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                startIndex = left;
            }

            char leftChar = s.charAt(left);
            window.put(leftChar, window.get(leftChar) - 1);

            if (need.containsKey(leftChar) &&
                window.get(leftChar) < need.get(leftChar)) {
                have--;
            }

            left++;
        }
    }

    return minLen == Integer.MAX_VALUE
            ? ""
            : s.substring(startIndex, startIndex + minLen);
}
```

Why update inside `while`?

```text
Because this is a smallest window problem.
As long as the window is valid, keep shrinking it.
```

---

# Part 10: At Most K Pattern

Use this for:

```text
Longest substring with at most K distinct characters
Fruit Into Baskets
Max Consecutive Ones III
Longest subarray with at most K zeros
```

---

## 13. At Most K Template

```java
int left = 0;
int maxLen = 0;

for (int right = 0; right < n; right++) {
    // add right

    while (condition is invalid) {
        // remove left
        left++;
    }

    maxLen = Math.max(maxLen, right - left + 1);
}
```

---

## 14. Example: Longest Substring With At Most K Distinct Characters

```java
import java.util.*;

public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if (k == 0) return 0;

    Map<Character, Integer> freq = new HashMap<>();

    int left = 0;
    int maxLen = 0;

    for (int right = 0; right < s.length(); right++) {
        char curr = s.charAt(right);
        freq.put(curr, freq.getOrDefault(curr, 0) + 1);

        while (freq.size() > k) {
            char leftChar = s.charAt(left);

            freq.put(leftChar, freq.get(leftChar) - 1);

            if (freq.get(leftChar) == 0) {
                freq.remove(leftChar);
            }

            left++;
        }

        maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
}
```

Example:

```text
s = "eceba"
k = 2
```

Answer:

```text
3
```

Because:

```text
"ece"
```

---

# Part 11: Exactly K Pattern

This is an advanced and very important pattern.

Many `exactly K` problems are solved using:

```text
exactlyK = atMost(K) - atMost(K - 1)
```

Used in:

```text
Subarrays with exactly K distinct integers
Binary subarrays with sum
Number of nice subarrays
```

---

## 15. Why exactlyK = atMost(K) - atMost(K - 1)?

Number of subarrays with exactly `K` distinct:

```text
= subarrays with at most K distinct
- subarrays with at most K - 1 distinct
```

---

## 16. Example: Subarrays With Exactly K Distinct Integers

```java
import java.util.*;

public int subarraysWithKDistinct(int[] nums, int k) {
    return atMost(nums, k) - atMost(nums, k - 1);
}

private int atMost(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();

    int left = 0;
    int count = 0;

    for (int right = 0; right < nums.length; right++) {
        freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

        while (freq.size() > k) {
            freq.put(nums[left], freq.get(nums[left]) - 1);

            if (freq.get(nums[left]) == 0) {
                freq.remove(nums[left]);
            }

            left++;
        }

        count += right - left + 1;
    }

    return count;
}
```

Important line:

```java
count += right - left + 1;
```

Why?

For every `right`, all subarrays ending at `right` and starting from `left` to `right` are valid.

```text
[left ... right]
[left + 1 ... right]
[left + 2 ... right]
...
[right ... right]
```

Total:

```text
right - left + 1
```

---

# Part 12: Counting Sliding Window Pattern

When problem asks:

```text
number of subarrays
count substrings
how many windows
```

Answer often uses:

```java
count += right - left + 1;
```

But only when:

```text
If current window is valid,
all smaller windows ending at right are also valid.
```

Example conditions:

```text
At most K distinct
Sum less than K with positive numbers
Product less than K
```

---

## 17. Example: Subarray Product Less Than K

```java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (k <= 1) return 0;

    int left = 0;
    int product = 1;
    int count = 0;

    for (int right = 0; right < nums.length; right++) {
        product *= nums[right];

        while (product >= k) {
            product /= nums[left];
            left++;
        }

        count += right - left + 1;
    }

    return count;
}
```

---

# Part 13: Monotonic Deque Sliding Window

This is an advanced sliding window pattern.

Used when the problem asks:

```text
maximum in every window of size k
minimum in every window of size k
```

Example:

```text
Sliding Window Maximum
```

---

## 18. Why Use Deque?

Brute force:

```text
For every window, find max.
Time = O(n * k)
```

Using monotonic deque:

```text
Time = O(n)
```

---

## 19. Deque Idea for Maximum

Deque stores indexes in decreasing order of values.

```text
front of deque = index of maximum element in current window
```

Rules:

```text
1. Remove indexes from front if they are outside the window.
2. Remove indexes from back while nums[back] <= nums[right].
3. Add right index.
4. Once window size reaches k, answer is nums[deque.front].
```

---

## 20. Example: Sliding Window Maximum

```java
import java.util.*;

public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] result = new int[n - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();

    int index = 0;

    for (int right = 0; right < n; right++) {

        // Remove indexes outside current window
        while (!deque.isEmpty() && deque.peekFirst() <= right - k) {
            deque.pollFirst();
        }

        // Maintain decreasing order
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {
            deque.pollLast();
        }

        deque.offerLast(right);

        // Add answer when first window is complete
        if (right >= k - 1) {
            result[index] = nums[deque.peekFirst()];
            index++;
        }
    }

    return result;
}
```

---

## 21. Why outside-window removal can use while

```java
while (!deque.isEmpty() && deque.peekFirst() <= right - k) {
    deque.pollFirst();
}
```

Usually one index expires at a time, so `if` also works in many standard cases.

But `while` is safer and more general:

```text
Remove all expired indexes.
```

---

# Part 14: Prefix Sum vs Sliding Window

Sliding window works best when:

```text
numbers are positive
condition changes predictably when left/right moves
```

Example:

```text
sum >= target with positive numbers
```

Because:

```text
When right moves, sum increases.
When left moves, sum decreases.
```

But if array has negative numbers:

```text
nums = [2, -1, 2]
```

Sum is not predictable.

Then normal sliding window may fail.

Use other techniques:

```text
prefix sum
hash map
deque
dynamic programming
```

Example:

```text
Subarray Sum Equals K
```

This should be solved using prefix sum + hash map, not normal sliding window.

---

# Part 15: How to Identify Sliding Window

Ask these questions:

---

## Question 1: Does it ask about a contiguous part?

Look for:

```text
subarray
substring
continuous segment
window
```

If yes, sliding window may apply.

---

## Question 2: Is the window size fixed?

Example:

```text
maximum sum of subarray of size k
```

Use fixed-size window.

---

## Question 3: Is it asking longest or smallest?

Example:

```text
longest substring without repeating characters
minimum window substring
```

Use variable-size window.

---

## Question 4: Does the condition involve frequency or count?

Example:

```text
anagram
permutation
distinct characters
contains all characters
```

Use HashMap or array frequency.

---

## Question 5: Does it ask maximum/minimum in every window?

Use monotonic deque.

---

# Part 16: Common Templates

---

## Template 1: Fixed Size Window

```java
int left = 0;
int sum = 0;

for (int right = 0; right < n; right++) {
    sum += nums[right];

    if (right - left + 1 == k) {
        // process answer

        sum -= nums[left];
        left++;
    }
}
```

---

## Template 2: Longest Valid Window

```java
int left = 0;
int maxLen = 0;

for (int right = 0; right < n; right++) {
    // add right

    while (window is invalid) {
        // remove left
        left++;
    }

    maxLen = Math.max(maxLen, right - left + 1);
}
```

---

## Template 3: Smallest Valid Window

```java
int left = 0;
int minLen = Integer.MAX_VALUE;

for (int right = 0; right < n; right++) {
    // add right

    while (window is valid) {
        minLen = Math.min(minLen, right - left + 1);

        // remove left
        left++;
    }
}
```

---

## Template 4: Count At Most K

```java
int left = 0;
int count = 0;

for (int right = 0; right < n; right++) {
    // add right

    while (window is invalid) {
        // remove left
        left++;
    }

    count += right - left + 1;
}
```

---

## Template 5: Exactly K

```java
exactlyK = atMost(K) - atMost(K - 1);
```

---

## Template 6: Monotonic Deque Maximum

```java
Deque<Integer> dq = new ArrayDeque<>();

for (int right = 0; right < n; right++) {

    while (!dq.isEmpty() && dq.peekFirst() <= right - k) {
        dq.pollFirst();
    }

    while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[right]) {
        dq.pollLast();
    }

    dq.offerLast(right);

    if (right >= k - 1) {
        ans.add(nums[dq.peekFirst()]);
    }
}
```

---

# Part 17: Beginner to Advanced Problem List

## Beginner

```text
1. Maximum Sum Subarray of Size K
2. Average of Subarray of Size K
3. First Negative Number in Every Window of Size K
4. Count Occurrences of Anagrams
```

---

## Intermediate

```text
1. Longest Substring Without Repeating Characters
2. Longest Substring With At Most K Distinct Characters
3. Fruit Into Baskets
4. Max Consecutive Ones III
5. Minimum Size Subarray Sum
```

---

## Advanced

```text
1. Minimum Window Substring
2. Sliding Window Maximum
3. Subarrays With K Different Integers
4. Number of Subarrays With Product Less Than K
5. Binary Subarrays With Sum
6. Count Number of Nice Subarrays
```

---

# Part 18: Interview Explanation

You can say:

```text
Sliding window is used for contiguous subarray or substring problems.
It maintains a window using two pointers, usually left and right.
The right pointer expands the window, and the left pointer shrinks it
when the condition is violated or when we want to minimize the window.
```

For longest window:

```text
I expand the window with right, shrink while invalid,
and update the maximum length after the window becomes valid.
```

For smallest window:

```text
I expand until the window becomes valid,
then shrink from the left while it remains valid,
updating the minimum length during shrinking.
```

For fixed window:

```text
Since the window size is fixed, I add the right element,
and once the window reaches size k,
I process the answer and remove the left element.
```

For monotonic deque:

```text
For sliding window maximum, I use a deque that stores indexes
in decreasing order of values.
The front always gives the maximum of the current window.
```

---

# Part 19: Final Mental Model

Think like this:

```text
right pointer = include new element
left pointer = remove old element
window = current valid/invalid section
answer = updated depending on longest/smallest/count/fixed-size requirement
```

Most sliding window problems become easy once you identify:

```text
1. What makes the window valid?
2. When should I shrink?
3. When should I update answer?
4. Am I finding longest, smallest, count, or fixed-size result?
```

The biggest rule:

```text
Longest  -> update after while
Smallest -> update inside while
Fixed    -> update when size == k
Count    -> often use count += right - left + 1
Maximum/minimum in each window -> use monotonic deque
```
