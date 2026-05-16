# Java Map — Beginner to Advanced Notes

## 1. What is `Map` in Java?

A `Map` stores data in **key-value** form.

```java
Map<KeyType, ValueType> map = new HashMap<>();
```

Example:

```java
Map<Integer, String> students = new HashMap<>();

students.put(1, "Ayush");
students.put(2, "Rahul");
students.put(3, "Amit");
```

Conceptually:

```text
1 -> Ayush
2 -> Rahul
3 -> Amit
```

Important points:

| Property | Meaning |
|---|---|
| Key-value pair | Data is stored as `key -> value` |
| Unique keys | Duplicate keys are not allowed |
| Duplicate values | Values can be duplicate |
| One key maps to one value | A key can point to only one value |
| Part of Java Collections Framework | But `Map` does not extend `Collection` |

---

## 2. Basic Example

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();

        map.put(1, "Ayush");
        map.put(2, "Rahul");
        map.put(3, "Amit");

        System.out.println(map);
    }
}
```

Output may be:

```text
{1=Ayush, 2=Rahul, 3=Amit}
```

In `HashMap`, order is **not guaranteed**.

---

## 3. Common `Map` Implementations

### 3.1 `HashMap`

Most commonly used implementation.

```java
Map<Integer, String> map = new HashMap<>();
```

Features:

| Feature | `HashMap` |
|---|---|
| Order | No guaranteed order |
| Null key | One null key allowed |
| Null values | Multiple null values allowed |
| Performance | Average `O(1)` for `put`, `get`, `remove` |
| Thread-safe | No |

Use when you need **fast lookup** and do not care about order.

---

### 3.2 `LinkedHashMap`

Maintains insertion order.

```java
Map<Integer, String> map = new LinkedHashMap<>();

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");

System.out.println(map);
```

Output:

```text
{3=C, 1=A, 2=B}
```

Use when you want:

```text
fast lookup + insertion order
```

---

### 3.3 `TreeMap`

Stores keys in sorted order.

```java
Map<Integer, String> map = new TreeMap<>();

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");

System.out.println(map);
```

Output:

```text
{1=A, 2=B, 3=C}
```

Use when you need **sorted keys**.

---

### 3.4 `ConcurrentHashMap`

Used in multithreading.

```java
import java.util.concurrent.ConcurrentHashMap;

Map<Integer, String> map = new ConcurrentHashMap<>();
```

Use when multiple threads access and modify the map.

---

### 3.5 `Hashtable`

Old synchronized map.

```java
Map<Integer, String> map = new Hashtable<>();
```

Usually avoid it in modern Java. Prefer:

```text
ConcurrentHashMap
```

---

### 3.6 `WeakHashMap`

Keys are weakly referenced. If a key is no longer used elsewhere, it can be garbage collected and removed from the map.

```java
Map<Object, String> map = new WeakHashMap<>();
```

Used in cache-like scenarios.

---

### 3.7 `IdentityHashMap`

Compares keys using `==`, not `.equals()`.

```java
Map<String, Integer> map = new IdentityHashMap<>();
```

Normal `HashMap`:

```java
key1.equals(key2)
```

`IdentityHashMap`:

```java
key1 == key2
```

Rarely used.

---

## 4. Core `Map` Methods

### 4.1 `put()`

Adds or updates a key-value pair.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Ayush");
map.put(2, "Rahul");
```

If key already exists, value is replaced.

```java
map.put(1, "Amit");

System.out.println(map);
```

Output:

```text
{1=Amit, 2=Rahul}
```

Because key `1` already existed.

---

### 4.2 `get()`

Returns value by key.

```java
String name = map.get(1);
System.out.println(name);
```

If key does not exist:

```java
System.out.println(map.get(100));
```

Output:

```text
null
```

---

### 4.3 `containsKey()`

Checks whether key exists.

```java
if (map.containsKey(1)) {
    System.out.println("Key exists");
}
```

Very common in DSA.

---

### 4.4 `containsValue()`

Checks whether value exists.

```java
if (map.containsValue("Ayush")) {
    System.out.println("Value exists");
}
```

Important:

```text
containsKey() is usually fast
containsValue() is usually slower
```

Because to check a value, Java may need to scan all values.

---

### 4.5 `remove()`

Removes key-value pair by key.

```java
map.remove(1);
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");
map.put(2, "B");

map.remove(1);

System.out.println(map);
```

Output:

```text
{2=B}
```

---

### 4.6 `remove(key, value)`

Removes only if both key and value match.

```java
map.remove(2, "B");
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");

map.remove(1, "B"); // will not remove

System.out.println(map);
```

Output:

```text
{1=A}
```

---

### 4.7 `size()`

Returns number of key-value pairs.

```java
System.out.println(map.size());
```

---

### 4.8 `isEmpty()`

Checks whether map is empty.

```java
if (map.isEmpty()) {
    System.out.println("Map is empty");
}
```

---

### 4.9 `clear()`

Removes all entries.

```java
map.clear();
```

---

## 5. `getOrDefault()`

Very useful in DSA.

```java
map.getOrDefault(key, defaultValue)
```

Example:

```java
Map<String, Integer> freq = new HashMap<>();

String word = "apple";

int count = freq.getOrDefault(word, 0);
freq.put(word, count + 1);

System.out.println(freq);
```

Output:

```text
{apple=1}
```

Common frequency counting pattern:

```java
String s = "aabbbc";

Map<Character, Integer> freq = new HashMap<>();

for (char ch : s.toCharArray()) {
    freq.put(ch, freq.getOrDefault(ch, 0) + 1);
}

System.out.println(freq);
```

Output:

```text
{a=2, b=3, c=1}
```

---

## 6. `putIfAbsent()`

Adds value only if key does not already exist.

```java
map.putIfAbsent(1, "Ayush");
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");

map.putIfAbsent(1, "B");
map.putIfAbsent(2, "C");

System.out.println(map);
```

Output:

```text
{1=A, 2=C}
```

Key `1` already had value `A`, so it was not replaced.

---

## 7. `putIfAbsent()` vs `computeIfAbsent()`

This is an important interview-level difference.

### Main difference

```text
putIfAbsent(key, value)
    -> adds value only if key is absent
    -> value is created eagerly, meaning value is always created before the method call

computeIfAbsent(key, function)
    -> adds value only if key is absent
    -> value is created lazily, meaning function runs only when value is actually needed
```

In simple words:

```text
putIfAbsent -> it creates the value even if key exists, but does not insert it
computeIfAbsent -> it creates the value only if key does not exist
```

---

### 7.1 `putIfAbsent()` creates value eagerly

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("name", "Alice");

        map.putIfAbsent("name", new String("Bob"));

        System.out.println(map);
    }
}
```

What happens internally:

```text
"name" already exists -> "Alice"
"Bob" is still created in memory
But map does NOT change
```

Final map:

```text
{name=Alice}
```

Why?

Because Java evaluates method arguments before calling the method.

So this part executes first:

```java
new String("Bob")
```

Then Java calls:

```java
map.putIfAbsent("name", createdBobString);
```

Since key `"name"` already exists, the map keeps old value `"Alice"`.

---

### 7.2 `computeIfAbsent()` creates value lazily

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("name", "Alice");

        map.computeIfAbsent("name", k -> {
            return "Bob";
        });

        System.out.println(map);
    }
}
```

What happens internally:

```text
"name" exists -> function is NOT executed
"Bob" is never created by the lambda
```

Final map:

```text
{name=Alice}
```

Why?

Because `computeIfAbsent()` receives a function:

```java
k -> {
    return "Bob";
}
```

This function is executed only if key is missing.

---

### 7.3 Best example: expensive object creation

Suppose creating a value is expensive.

```java
class HeavyObject {
    HeavyObject() {
        System.out.println("Heavy object created");
    }
}
```

Using `putIfAbsent()`:

```java
Map<String, HeavyObject> map = new HashMap<>();
map.put("config", new HeavyObject());

map.putIfAbsent("config", new HeavyObject());
```

Output:

```text
Heavy object created
Heavy object created
```

Even though `"config"` already exists, second `HeavyObject` is still created.

Using `computeIfAbsent()`:

```java
Map<String, HeavyObject> map = new HashMap<>();
map.put("config", new HeavyObject());

map.computeIfAbsent("config", k -> new HeavyObject());
```

Output:

```text
Heavy object created
```

Second object is not created because key already exists.

---

### 7.4 When to use which?

| Situation | Prefer |
|---|---|
| Value is already available | `putIfAbsent()` |
| Value is cheap/simple | `putIfAbsent()` is fine |
| Value creation is expensive | `computeIfAbsent()` |
| You need to create list/set/map only when key is missing | `computeIfAbsent()` |

Common DSA/graph pattern:

```java
Map<Integer, List<Integer>> graph = new HashMap<>();

// Create list only if node is absent
graph.computeIfAbsent(1, k -> new ArrayList<>()).add(2);
graph.computeIfAbsent(1, k -> new ArrayList<>()).add(3);
```

Final map:

```text
{1=[2, 3]}
```

---

## 8. Iterating Over a Map

A `Map` provides three main views:

```text
keySet()
values()
entrySet()
```

---

### 8.1 Iterate over keys

```java
for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

---

### 8.2 Iterate over values

```java
for (String value : map.values()) {
    System.out.println(value);
}
```

---

### 8.3 Iterate over entries

Best way when you need both key and value.

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

Full example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");
map.put(2, "B");
map.put(3, "C");

for (Map.Entry<Integer, String> entry : map.entrySet()) {
    int key = entry.getKey();
    String value = entry.getValue();

    System.out.println(key + " -> " + value);
}
```

---

### 8.4 Using `forEach()`

```java
map.forEach((key, value) -> {
    System.out.println(key + " -> " + value);
});
```

---

## 9. Updating Values

### 9.1 Normal update

```java
map.put(1, "Updated Value");
```

---

### 9.2 Update using old value

```java
map.put(1, map.get(1) + " Updated");
```

Example:

```java
Map<String, Integer> score = new HashMap<>();

score.put("Ayush", 10);
score.put("Ayush", score.get("Ayush") + 5);

System.out.println(score);
```

Output:

```text
{Ayush=15}
```

---

## 10. `replace()`

### 10.1 Replace value by key

```java
map.replace(1, "New Value");
```

It replaces only if key exists.

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");

map.replace(1, "B");
map.replace(2, "C");

System.out.println(map);
```

Output:

```text
{1=B}
```

Key `2` does not exist, so nothing happens.

---

### 10.2 Replace only if old value matches

```java
map.replace(1, "Old", "New");
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");

map.replace(1, "B", "C"); // false
map.replace(1, "A", "C"); // true

System.out.println(map);
```

Output:

```text
{1=C}
```

---

## 11. `compute()`, `computeIfAbsent()`, `computeIfPresent()`

These are advanced but very useful.

---

### 11.1 `compute()`

Computes new value for a key.

```java
map.compute(key, (k, oldValue) -> newValue);
```

Example:

```java
Map<String, Integer> map = new HashMap<>();

map.put("apple", 2);

map.compute("apple", (key, value) -> value + 1);

System.out.println(map);
```

Output:

```text
{apple=3}
```

Careful: value can be `null`.

```java
map.compute("banana", (key, value) -> value == null ? 1 : value + 1);
```

---

### 11.2 `computeIfAbsent()`

Runs only if key is absent or mapped to `null`.

```java
map.computeIfAbsent(key, k -> value);
```

Very useful for grouping.

Example:

```java
Map<String, List<String>> map = new HashMap<>();

map.computeIfAbsent("fruits", k -> new ArrayList<>()).add("Apple");
map.computeIfAbsent("fruits", k -> new ArrayList<>()).add("Banana");

System.out.println(map);
```

Output:

```text
{fruits=[Apple, Banana]}
```

Without `computeIfAbsent`, you would write:

```java
if (!map.containsKey("fruits")) {
    map.put("fruits", new ArrayList<>());
}
map.get("fruits").add("Apple");
```

So this:

```java
map.computeIfAbsent("fruits", k -> new ArrayList<>()).add("Apple");
```

means:

```text
If key "fruits" does not exist,
create new ArrayList.
Then return the list.
Then add "Apple" to it.
```

---

### 11.3 `computeIfPresent()`

Runs only if key exists.

```java
map.computeIfPresent(key, (k, oldValue) -> newValue);
```

Example:

```java
Map<String, Integer> map = new HashMap<>();

map.put("apple", 2);

map.computeIfPresent("apple", (key, value) -> value + 10);
map.computeIfPresent("banana", (key, value) -> value + 10);

System.out.println(map);
```

Output:

```text
{apple=12}
```

`banana` was absent, so nothing happened.

---

## 12. `merge()`

Very useful for frequency counting.

```java
map.merge(key, value, remappingFunction);
```

Example:

```java
Map<String, Integer> freq = new HashMap<>();

freq.merge("apple", 1, Integer::sum);
freq.merge("apple", 1, Integer::sum);
freq.merge("banana", 1, Integer::sum);

System.out.println(freq);
```

Output:

```text
{banana=1, apple=2}
```

Meaning:

```text
If key does not exist, put 1.
If key exists, add old value + new value.
```

Equivalent to:

```java
freq.put("apple", freq.getOrDefault("apple", 0) + 1);
```

For DSA, both are useful:

```java
freq.put(ch, freq.getOrDefault(ch, 0) + 1);
```

or

```java
freq.merge(ch, 1, Integer::sum);
```

---

## 13. Sorting a Map

### 13.1 Sort by key using `TreeMap`

```java
Map<Integer, String> map = new TreeMap<>();

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");

System.out.println(map);
```

Output:

```text
{1=A, 2=B, 3=C}
```

---

### 13.2 Sort by key using custom comparator

Descending order:

```java
Map<Integer, String> map = new TreeMap<>((a, b) -> Integer.compare(b, a));

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");

System.out.println(map);
```

Output:

```text
{3=C, 2=B, 1=A}
```

---

### 13.3 Sort by value

```java
Map<String, Integer> map = new HashMap<>();

map.put("A", 30);
map.put("B", 10);
map.put("C", 20);

List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

list.sort((e1, e2) -> e1.getValue() - e2.getValue());

for (Map.Entry<String, Integer> entry : list) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

Output:

```text
B -> 10
C -> 20
A -> 30
```

If you want sorted result back in a map:

```java
Map<String, Integer> sortedMap = new LinkedHashMap<>();

for (Map.Entry<String, Integer> entry : list) {
    sortedMap.put(entry.getKey(), entry.getValue());
}

System.out.println(sortedMap);
```

---

## 14. Nested Map

A map can store another map.

```java
Map<String, Map<String, Integer>> marks = new HashMap<>();
```

Example:

```java
Map<String, Map<String, Integer>> students = new HashMap<>();

students.put("Ayush", new HashMap<>());
students.get("Ayush").put("Math", 90);
students.get("Ayush").put("Science", 85);

System.out.println(students);
```

Output:

```text
{Ayush={Science=85, Math=90}}
```

Better with `computeIfAbsent()`:

```java
Map<String, Map<String, Integer>> students = new HashMap<>();

students.computeIfAbsent("Ayush", k -> new HashMap<>()).put("Math", 90);
students.computeIfAbsent("Ayush", k -> new HashMap<>()).put("Science", 85);

System.out.println(students);
```

---

## 15. Map With List as Value

Very common in graph problems.

```java
Map<Integer, List<Integer>> graph = new HashMap<>();
```

Example:

```java
Map<Integer, List<Integer>> graph = new HashMap<>();

graph.computeIfAbsent(1, k -> new ArrayList<>()).add(2);
graph.computeIfAbsent(1, k -> new ArrayList<>()).add(3);
graph.computeIfAbsent(2, k -> new ArrayList<>()).add(4);

System.out.println(graph);
```

Output:

```text
{1=[2, 3], 2=[4]}
```

This is useful for adjacency list:

```text
1 -> 2, 3
2 -> 4
```

---

## 16. Map With Set as Value

Used when duplicates are not allowed.

```java
Map<String, Set<String>> map = new HashMap<>();
```

Example:

```java
Map<String, Set<String>> map = new HashMap<>();

map.computeIfAbsent("fruits", k -> new HashSet<>()).add("Apple");
map.computeIfAbsent("fruits", k -> new HashSet<>()).add("Apple");
map.computeIfAbsent("fruits", k -> new HashSet<>()).add("Banana");

System.out.println(map);
```

Output:

```text
{fruits=[Apple, Banana]}
```

`Apple` appears once because `Set` removes duplicates.

---

## 17. Immutable Map

### 17.1 `Map.of()`

```java
Map<Integer, String> map = Map.of(
    1, "A",
    2, "B",
    3, "C"
);
```

This map cannot be changed.

```java
map.put(4, "D"); // Runtime exception
```

Throws:

```text
UnsupportedOperationException
```

---

### 17.2 `Map.ofEntries()`

Useful for many entries.

```java
Map<Integer, String> map = Map.ofEntries(
    Map.entry(1, "A"),
    Map.entry(2, "B"),
    Map.entry(3, "C")
);
```

---

### 17.3 `Collections.unmodifiableMap()`

```java
Map<Integer, String> original = new HashMap<>();
original.put(1, "A");

Map<Integer, String> readOnly = Collections.unmodifiableMap(original);
```

Important:

```java
readOnly.put(2, "B"); // Not allowed
```

But if original changes:

```java
original.put(2, "B");
System.out.println(readOnly);
```

Then `readOnly` also reflects the change because it is a view over original map.

---

## 18. Null Handling

| Implementation | Null Key | Null Values |
|---|---:|---:|
| `HashMap` | Yes, one | Yes |
| `LinkedHashMap` | Yes, one | Yes |
| `TreeMap` | Usually no null key with natural ordering | Yes |
| `Hashtable` | No | No |
| `ConcurrentHashMap` | No | No |

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(null, "A");
map.put(1, null);

System.out.println(map);
```

Valid for `HashMap`.

But this is not allowed:

```java
Map<Integer, String> map = new ConcurrentHashMap<>();
map.put(null, "A"); // NullPointerException
```

---

## 19. Difference Between `HashMap`, `LinkedHashMap`, and `TreeMap`

| Feature | `HashMap` | `LinkedHashMap` | `TreeMap` |
|---|---|---|---|
| Order | No order | Insertion order | Sorted key order |
| Data structure | Hash table | Hash table + linked list | Red-Black tree |
| `put/get/remove` | Average `O(1)` | Average `O(1)` | `O(log n)` |
| Null key | One allowed | One allowed | Usually not allowed |
| Use case | Fast lookup | Fast lookup + order | Sorted keys |

---

## 20. Internal Working of `HashMap`

`HashMap` internally uses an array of buckets.

Simple mental model:

```text
Key -> hashCode() -> index -> bucket
```

Example:

```java
map.put("Ayush", 25);
```

Internally:

```text
"Ayush".hashCode() is calculated
index is found
entry is stored in that bucket
```

Each entry contains:

```text
key
value
hash
next
```

Because multiple keys may go to the same bucket.

That is called **collision**.

---

### 20.1 Collision Example

Suppose two keys produce same bucket index:

```text
Key A -> bucket 5
Key B -> bucket 5
```

Then both entries are stored in same bucket.

In modern Java, when too many entries go into same bucket, Java can convert that bucket structure into a tree for better performance.

---

### 20.2 Load Factor

Default load factor of `HashMap` is usually:

```text
0.75
```

Meaning:

```text
When map becomes 75% full, capacity increases.
```

Example:

```java
Map<Integer, String> map = new HashMap<>(16, 0.75f);
```

Here:

```text
Initial capacity = 16
Load factor = 0.75
Resize threshold = 16 * 0.75 = 12
```

After more than 12 entries, resizing may happen.

---

## 21. Why `hashCode()` and `equals()` Are Important

For custom objects as keys, override both.

Bad version:

```java
class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

If you use this directly as key:

```java
Map<Student, Integer> map = new HashMap<>();

Student s1 = new Student(1, "Ayush");
Student s2 = new Student(1, "Ayush");

map.put(s1, 100);

System.out.println(map.get(s2));
```

Output:

```text
null
```

Because `s1` and `s2` are different objects.

Correct version:

```java
import java.util.*;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Student)) return false;

        Student other = (Student) obj;

        return this.id == other.id &&
               Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class Main {
    public static void main(String[] args) {
        Map<Student, Integer> map = new HashMap<>();

        Student s1 = new Student(1, "Ayush");
        Student s2 = new Student(1, "Ayush");

        map.put(s1, 100);

        System.out.println(map.get(s2));
    }
}
```

Output:

```text
100
```

Rule:

```text
If two objects are equal using equals(),
they must return the same hashCode().
```

---

## 22. Dangerous Mistake: Mutable Key

Do not use mutable fields in keys.

Bad example:

```java
class Student {
    int id;

    Student(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) return false;
        return this.id == ((Student) obj).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```

Problem:

```java
Map<Student, String> map = new HashMap<>();

Student s = new Student(1);

map.put(s, "Ayush");

s.id = 2;

System.out.println(map.get(s));
```

This may return:

```text
null
```

Why?

Because key was stored using hash of `id = 1`.

After changing `id = 2`, hash changed. Now Java searches in another bucket.

Best practice:

```text
Use immutable keys.
```

Example:

```java
final class Student {
    private final int id;
    private final String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

---

## 23. `Map.Entry`

Each key-value pair is represented by `Map.Entry<K, V>`.

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getKey());
    System.out.println(entry.getValue());
}
```

You can also update value through entry:

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    if (entry.getKey() == 1) {
        entry.setValue("Updated");
    }
}
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");
map.put(2, "B");

for (Map.Entry<Integer, String> entry : map.entrySet()) {
    if (entry.getKey() == 1) {
        entry.setValue("Updated A");
    }
}

System.out.println(map);
```

Output:

```text
{1=Updated A, 2=B}
```

---

## 24. Removing While Iterating

Bad:

```java
for (Integer key : map.keySet()) {
    if (key == 1) {
        map.remove(key); // may throw ConcurrentModificationException
    }
}
```

Good:

```java
Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();

while (iterator.hasNext()) {
    Map.Entry<Integer, String> entry = iterator.next();

    if (entry.getKey() == 1) {
        iterator.remove();
    }
}
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");
map.put(2, "B");
map.put(3, "C");

Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();

while (it.hasNext()) {
    Map.Entry<Integer, String> entry = it.next();

    if (entry.getKey() == 2) {
        it.remove();
    }
}

System.out.println(map);
```

Output:

```text
{1=A, 3=C}
```

Also possible:

```java
map.entrySet().removeIf(entry -> entry.getKey() == 2);
```

---

## 25. `NavigableMap` and `SortedMap`

`TreeMap` implements `NavigableMap`.

Useful methods:

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");
```

Methods:

```java
map.firstKey();       // 10
map.lastKey();        // 40

map.lowerKey(30);     // 20, strictly less than 30
map.floorKey(30);     // 30, less than or equal to 30

map.higherKey(30);    // 40, strictly greater than 30
map.ceilingKey(30);   // 30, greater than or equal to 30
```

Example:

```java
System.out.println(map.lowerKey(30));
System.out.println(map.floorKey(30));
System.out.println(map.higherKey(30));
System.out.println(map.ceilingKey(30));
```

Output:

```text
20
30
40
30
```

Very useful in DSA when you need nearest smaller/larger key.

---

## 26. Java 21 `SequencedMap`

Java 21 added `SequencedMap`, which represents maps with a defined encounter order.

Useful methods:

```java
firstEntry()
lastEntry()
pollFirstEntry()
pollLastEntry()
reversed()
```

Example with `LinkedHashMap` in Java 21+:

```java
SequencedMap<Integer, String> map = new LinkedHashMap<>();

map.put(1, "A");
map.put(2, "B");
map.put(3, "C");

System.out.println(map.firstEntry());
System.out.println(map.lastEntry());
System.out.println(map.reversed());
```

Conceptually:

```text
firstEntry() -> 1=A
lastEntry()  -> 3=C
reversed()   -> reverse order view
```

---

## 27. Time Complexity

For `HashMap`:

| Operation | Average Case | Worst Case |
|---|---:|---:|
| `put()` | `O(1)` | `O(n)` |
| `get()` | `O(1)` | `O(n)` |
| `remove()` | `O(1)` | `O(n)` |
| `containsKey()` | `O(1)` | `O(n)` |
| `containsValue()` | `O(n)` | `O(n)` |

For `TreeMap`:

| Operation | Time |
|---|---:|
| `put()` | `O(log n)` |
| `get()` | `O(log n)` |
| `remove()` | `O(log n)` |
| `containsKey()` | `O(log n)` |
| sorted traversal | `O(n)` |

---

## 28. Common DSA Patterns Using Map

### 28.1 Frequency Map

```java
String s = "aabbccc";

Map<Character, Integer> freq = new HashMap<>();

for (char ch : s.toCharArray()) {
    freq.put(ch, freq.getOrDefault(ch, 0) + 1);
}

System.out.println(freq);
```

Output:

```text
{a=2, b=2, c=3}
```

---

### 28.2 First Non-Repeating Character

```java
String s = "aabbcdde";

Map<Character, Integer> freq = new LinkedHashMap<>();

for (char ch : s.toCharArray()) {
    freq.put(ch, freq.getOrDefault(ch, 0) + 1);
}

for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
    if (entry.getValue() == 1) {
        System.out.println(entry.getKey());
        break;
    }
}
```

Output:

```text
c
```

Why `LinkedHashMap`?

Because we need insertion order.

---

### 28.3 Two Sum

```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int need = target - nums[i];

        if (map.containsKey(need)) {
            return new int[]{map.get(need), i};
        }

        map.put(nums[i], i);
    }

    return new int[]{-1, -1};
}
```

Meaning:

```text
For every nums[i], check if target - nums[i] already exists.
```

---

### 28.4 Group Anagrams

```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for (String str : strs) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);

        String key = new String(arr);

        map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
    }

    return new ArrayList<>(map.values());
}
```

Example:

```text
eat -> aet
tea -> aet
ate -> aet
```

So they go into same group.

---

### 28.5 Sliding Window Frequency Map

Example: check if two strings are anagrams.

```java
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

## 29. Interview-Level Best Practices

### Use interface as reference

Good:

```java
Map<Integer, String> map = new HashMap<>();
```

Less flexible:

```java
HashMap<Integer, String> map = new HashMap<>();
```

Why?

Because later you can change implementation easily:

```java
Map<Integer, String> map = new LinkedHashMap<>();
Map<Integer, String> map = new TreeMap<>();
```

---

### Choose implementation correctly

| Need | Use |
|---|---|
| Fast lookup, no order | `HashMap` |
| Maintain insertion order | `LinkedHashMap` |
| Sorted keys | `TreeMap` |
| Thread-safe high concurrency | `ConcurrentHashMap` |
| Old synchronized map | Avoid `Hashtable` |
| Cache-like weak keys | `WeakHashMap` |

---

### Prefer `containsKey()` over `get() != null`

Bad:

```java
if (map.get(key) != null) {
    // key exists?
}
```

Problem: key may exist with null value.

Example:

```java
map.put(1, null);

System.out.println(map.get(1));          // null
System.out.println(map.containsKey(1));  // true
```

So use:

```java
map.containsKey(key)
```

---

## 30. Quick Summary

```java
Map<K, V>
```

means:

```text
K = key type
V = value type
```

Most important methods:

```java
put()
get()
remove()
containsKey()
containsValue()
getOrDefault()
putIfAbsent()
replace()
compute()
computeIfAbsent()
computeIfPresent()
merge()
keySet()
values()
entrySet()
forEach()
```

Most important implementations:

```java
HashMap
LinkedHashMap
TreeMap
ConcurrentHashMap
WeakHashMap
IdentityHashMap
```

For DSA, the most useful are:

```java
HashMap
LinkedHashMap
TreeMap
```

Interview one-liner:

> A `Map` in Java stores key-value pairs where keys are unique and each key maps to at most one value. `HashMap` gives fast average `O(1)` lookup, `LinkedHashMap` preserves insertion order, and `TreeMap` stores keys in sorted order.
