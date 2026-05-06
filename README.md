# About Java

*Java 25

## トピック

- [Effective](/Effective.md)

## 目次

1. [命名規則](#命名規則)
1. [Hello, World](#hello-world)
1. [データ型](#データ型)
1. [条件分岐](#条件分岐)
1. [ループ](#ループ)
1. [配列](#配列)
1. [メソッド](#メソッド)
1. [クラス](#クラス)
1. [レコード](#レコード)
1. [インターフェース](#インターフェース)
1. [抽象クラス](#抽象クラス)
1. [ジェネリクス](#ジェネリクス)
1. [例外処理](#例外処理)
1. [List](#list)
1. [Set](#set)
1. [Map](#map)
1. [Queue](#queue)
1. [イテレータ](#イテレータ)
1. [アルゴリズム](#アルゴリズム)
1. [Stream API](#stream-api)
1. [メモリ](#メモリ)
1. [文字列メソッド](#文字列メソッド)
1. [数学メソッド](#数学メソッド)
1. [アノテーション](#アノテーション)

## 命名規則

**一覧**

|種類|命名規則|例|
|-|-|-|
|クラス|PascalCase|class `MyClass`|
|フィールド|camelCase|private int `myField`|
|メソッド|camelCase|void `myMethod()`|
|変数|camelCase|int `myVariable`|
|定数|SNAKE_CASE|static final int `MY_CONST`|

[⬆︎目次へ戻る](#目次)

## Hello, World

**サンプルコード**

```java
public class App {
    void main() {
        IO.println("Hello, World!");
    }
}
```

**コンパイル＆実行**

```sh
javac App.java
java App
```

[⬆︎目次へ戻る](#目次)

## データ型

**プリミティブ型**

`int`・`byte`・`short`・`long`・`double`・`float`・`boolean`・`char`

**ラッパークラス**

`Integer`, `Byte`, `Short`, `Long`, `Double`, `Float`, `Boolean`, `Character`, `String`

[⬆︎目次へ戻る](#目次)

## 条件分岐

```java
if (result < 0) {
    // ...
} else if (result > 0) {
    // ...
} else {
    // ...
}

switch (result) {
    case 0:
        // ...
        break;
    case 1:
        // ...
        break;
    default:
        break;
}

Character result = switch (score) {
    case 3  -> 'A';
    case 2  -> 'B';
    default -> 'C';
};
```

[⬆︎目次へ戻る](#目次)

## ループ

```java
while (i < 3) {
    i++;
}

do {
    i++;
} while (i < 3);

for (int j=0; j<3; j++) {
    // ...
}

int[] nums = {10, 20, 30};
for (int num : nums) {
    System.out.println(num);
}
```

[⬆︎目次へ戻る](#目次)

## 配列

```java
// 配列
int[] numbers1 = new int[3];
int[] numbers2 = {10, 20, 30};

// 多次元配列
int[][] numbers3 = new int[2][2];
int[][] numbers4 = { {10, 20}, {30, 40} };

// 配列のコピー
int[] numbers = {10, 20, 30};
int[] clonedNums1 = numbers.clone();
int[] clonedNums2 = Arrays.copyOf(numbers, 3);
```

[⬆︎目次へ戻る](#目次)

## メソッド

**基本**

```java
public int sum(int a, int b) {
    return a + b;
}

void main() {
    int result = sum(10, 20);
    IO.println(result);
}
```

**オーバーロード** - 同じメソッド名で引数の型や数が違うメソッド

```java
public void plus(int x) {
    IO.println(x + 1);
}

public void plus(String s) {
    IO.println(s + "1");
}
```

**可変長引数**

```java
public void display(String... args) {
    for (String arg : args) {
        IO.println(arg);
    }
}
```

[⬆︎目次へ戻る](#目次)

## クラス

**クラス**

```java
public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public int area() {
        return width * height;
    }
}
```

**オブジェクト**

```java
Rectangle r = new Rectangle(10, 20);
IO.println(r.area());
```

**シール・クラス**

```java
public sealed class Enemy permits Goblin, Monolith {}

final class Goblin extends Enemy {}
final class Monolith extends Enemy {}
final class Cop extends Enemy {}    // 拡張できない
```

**OOP**

- オブジェクト指向のメリットは「変更に対して柔軟に対応するため」。
- **継承** - 親クラスのメソッドの上書きを行う。
- **カプセル化** - アクセス修飾子を適切に設定して内部のデータを守る。
- **ポリモーフィズム** - 同じ呼び方で異なる柔軟な動きを行う。

[⬆︎目次へ戻る](#目次)

## レコード

宣言することで以下を自動生成する

- 不変フィールド
- アクセッサ (get不要)
- コンストラクタ
- `equals()`
- `hashCode()`
- `toString()`

```java
public record Person(String name, int age) {
    public Person {
        IO.println(name + "(" + age + ")");
    }
}

// ...
Person person = new Person("John", 20);
```

[⬆︎目次へ戻る](#目次)

## インターフェース

- インターフェースは「クラス仕様の定義」
- "Can Do" = "`Slime`は`attack()`できる"のように考える。
- 多重継承が可能。
- `public`のみ使用可能。
- 変数はクラス定数 (`public static final`)のみ使用可能。
- メソッドは型の定義のみ。

```java
public interface Attackable {
    void attack();
}

public class Slime implements Attackable {
    @Override
    public void attack() {
        IO.println("attack!");
    }
}
```

[⬆︎目次へ戻る](#目次)

## 抽象クラス

- 抽象クラスは「共通処理」
- "Is A" = "`Slime`は`Enemy`である"のように考える。
- 多重継承は不可能。
- `public`または`protected`が使用可能。
- メソッドは具体的な処理定義も可能。

```java
public abstract class Enemy {
    public abstract void attack();
}

public class Slime extends Enemy {
    @Override
    public void attack() {
        // ...
    }
}
```

[⬆︎目次へ戻る](#目次)

## ジェネリクス

```java
// クラス
public class Calc<T> {
    public T number;

    public Calc(T number) {
        this.number = number;
    }
}

Calc i = new Calc<Integer>(5);
Calc d = new Calc<Double>(1.2);

// メソッド
public <T> T check(T text) {
    return text;
}
```

[⬆︎目次へ戻る](#目次)

## 例外処理

```java
try {
    IO.println(1);
    throw new Exception("ERROR!");
} catch (Exception e) {
    IO.println(2 + ": " + e.getMessage());
} finally {
    IO.println(3);
}
```

[⬆︎目次へ戻る](#目次)

## List

順序があるデータを格納するためのデータ構造

- ArrayList - 長さが変更できて高速だが、長さが変わる場合や削除処理に時間がかかる。
- LinkedList - 入力や削除が高速だが、検索に時間がかかる。
- Vector - 長さが変更できるが、ArrayListの方が性能が良いため使われない。

```java
List<Integer> list = new ArrayList<>();
Object x = null;

list.add(10);           // 値[0]を末尾に追加
list.add(1, 15);        // 位置[0]に値[1]を追加
list.set(1, 20);        // 位置[0]を値[1]に変更
list.addFirst(5);       // 値[0]を先頭に追加
list.addLast(30);       // 値[0]を末尾に追加
list.remove(1);         // 位置[0]の値を削除
list.clear();           // 要素を全て削除
x = list.get(0);        // 位置[0]を取得
x = list.contains(10);  // 値[0]が存在するか？
x = list.isEmpty();     // 要素数が0か？
x = list.size();        // 要素数
x = list.indexOf(20);   // 値[0]の位置

List<String> list = List.of("a", "b", "c"); // 不変リスト
```

[⬆︎目次へ戻る](#目次)

## Set

要素が重複しないデータ構造

- HashSet - 要素が自動でソートされない
- TreeSet - 要素が自動でソートされる

```java
Set<String> hash = new HashSet<>();
Object x = null;

hash.add("A");          // 値[0]を追加
hash.remove("A");       // 値[0]を削除
hash.clear();           // 要素を全て削除
x = hash.contains("A"); // 値[0]が存在するか？
x = hash.isEmpty();     // 要素数が0か？
x = hash.size();        // 要素数

Set<String> set = Set.of("a", "b", "c");    // 不変セット
```

[⬆︎目次へ戻る](#目次)

## Map

キーと値の組み合わせを持つデータ構造

- HahsMap - 高速だが、順番が保証されない
- LinkedHashMap - 速度が遅いが、値を設定した順番が保証される
- TreeMap - 要素が自動でソートされる

```java
Map<Integer, String> map = new HashMap<>();
Object x = null;

map.put(3, "C");            // キー[0]と値[1]の組み合わせを追加
map.remove(3);              // キー[0]を削除
map.clear();                // 要素を全て削除
x = map.get(3);             // キー[0]の値を取得
x = map.containsKey(3);     // キー[0]が存在するか？
x = map.containsValue("C"); // 値[0]が存在するか？
x = map.isEmpty();          // 要素数が0か？
x = map.size();             // 要素数
```

[⬆︎目次へ戻る](#目次)

## Queue

FIFOキュー、LIFOキュー、優先度を持つキューなどのデータ構造

- Deque - 両端キュー。スタック及びキューとして扱える。
- LinkedList - 双方向線形リスト。末端要素の追加や削除が高速。スタック及びキューとしても扱える。
- ArrayDeque - 配列型Deque。末端以外の要素削除が遅い。
- PriorityDeque - 自然な順序付けを持つ優先キュー。

```java
Deque<String> deque = new ArrayDeque<>();
Object x = null;

deque.add("A");             // 末尾に値追加
x = deque.offer("B");       // 末尾に値追加 (エラー時false)
x = deque.remove("B");      // 値削除 (エラー時例外)
x = deque.poll();           // 先頭の値を取り出す (エラー時null)
x = deque.getFirst();       // 先頭の値を取得 (エラー時例外)
x = deque.getLast();        // 末尾の値を取得 (エラー時例外)
x = deque.peek();           // 先頭の値を取得 (エラー時null)
x = deque.removeFirstOccurrence("Z");   // 最初に現れる値[0]を削除 (存在しない場合false)
x = deque.removeLastOccurrence("Z");    // 最後に現れる値[0]を削除 (存在しない場合false)
x = deque.contains("E");                // 値[0]が存在するか？
x = deque.iterator();                   // イテレータ
x = deque.descendingIterator();         // 逆順イテレータ
```

[⬆︎目次へ戻る](#目次)

## イテレータ

**Iterator**

```java
List<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);

// while
Iterator<Integer> iter = list.iterator();
while (iter.hasNext()) {
    IO.println(iter.next());
}

// for
for (Iterator<Integer> ite = list.iterator(); ite.hasNext();) {
    IO.println(ite.next());
}
```

**ListIterator**

```java
ListIterator<Integer> it = list.listIterator();
Object x = null;

x = it.hasNext();           // 次のデータがあるか？
x = it.next();              // 次のデータを取得
x = it.hasPrevious();       // 前のデータがあるか？
x = it.previous();          // 前のデータを取得
x = it.nextIndex();         // 次のインデックス
x = it.previousIndex();     // 前のインデックス
it.remove();                // データを削除

while (it.hasNext()) {
    IO.println(it.next());
}
```

[⬆︎目次へ戻る](#目次)

## アルゴリズム

**Collections**

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(4);
numbers.add(1);
numbers.add(3);
numbers.add(9);
numbers.add(1);

IO.println(numbers);

Collections.sort(numbers);          // ソート
Collections.sort(numbers, Collections.reverseOrder());  // 逆順ソート
int x = Collections.max(numbers);   // 最大値
int n = Collections.min(numbers);   // 最小値
Collections.shuffle(numbers);       // シャッフル
int cnt = Collections.frequency(numbers, 1);    // リスト[0]での要素[1]の出現数
Collections.swap(numbers, 0, 2);    // リスト[0]での位置[1],[2]の要素を入れ替える
```

[⬆︎目次へ戻る](#目次)

## Stream API

**中間操作**

```java
List<Integer> list = Arrays.asList(5, 30, 10, 15, 20, 25);

list.stream()
    .filter(num -> num >= 10)           // trueの要素のみ残す
    .map(num -> num + 5)                // 要素を変換する
    .sorted()                           // ソート
    .sorted(Comparator.reverseOrder())  // 逆順ソート
    .forEach(num -> IO.println(num))    // （終端操作）出力
    ;
```

**終端操作**

```java
// 要素を集約
List<Integer> bigList = list.stream()
    .filter(n -> n >= 50)
    .collect(Collectors.toList());

// 要素数
long cnt = list.stream()
    .filter(n -> n >= 15)
    .count();

// いずれかが一致しているか？
boolean isAnyMatch = list.stream().anyMatch(n -> n >= 30);

// 全て一致しているか？
boolean isAllMatch = list.stream().allMatch(n -> n >= 0);

// いずれも一致しないか？
boolean isNoneMatch = list.stream().noneMatch(n -> n > 30);

// １つの値に集約
int sum = list.stream().reduce(0, Integer::sum);
```

[⬆︎目次へ戻る](#目次)

## メモリ

```java
Runtime r = Runtime.getRuntime();
long fm = r.freeMemory();   // 残りメモリ容量
long tm = r.totalMemory();  // 現在のメモリ総容量
long mm = r.maxMemory();    // メモリ総容量の限界値
```

[⬆︎目次へ戻る](#目次)

## 文字列メソッド

**一覧**

```java
String text = "Hello";
Object x = null;

// 文字列切り出し
x = text.charAt(0);                     // 位置[0]の文字
x = text.subSequence(2, 4);             // 文字列の位置[0]~[1]をCharSequenceオブジェクトとして返す
x = text.substring(2, 4);               // 文字列の位置[0]~[1]を部分文字列として返す

// 文字列結合
x = text.concat(" World");              // 末尾に文字列[0]を連結
x = String.join("-", "hi", "world");    // 区切り文字[0]で文字列[1以降]を結合

// 文字列変換
x = text.toLowerCase();                 // 小文字に変換
x = text.toUpperCase();                 // 大文字に変換
x = text.split(" ");                    // 文字列を分割文字列または正規表現[0]で置換
x = text.replace("l", "p");             // 文字列の文字列[0]を文字列[1]に置換
x = text.toCharArray();                 // 文字列を文字配列に変換

// 文字列比較
x = text.equals("Hello");               // 文字列[0]と一致するか？
x = text.equalsIgnoreCase("hello");     // 文字列[0]と一致するか？ (大文字小文字区別なし)
x = text.compareTo("Hello");            // 文字列[0]と比較 (一致する場合0)
x = text.compareToIgnoreCase("hello");  // 文字列[0]と比較 (一致する場合0・大文字小文字区別なし)
x = text.contentEquals("He");           // 文字列が文字列[0]を全て含むか？
x = "abc".matches("abc|def|ghi");       // 文字列が正規表現に一致するか？

// 文字列検索
x = text.contains("He");                // 文字列に文字列[0]を含むか？
x = text.startsWith("H");               // 文字列[0]で始まるか？
x = text.endsWith("llo");               // 文字列[0]で終わるか？
x = text.indexOf("el");                 // 文字列[0]が最初に出現する位置
x = text.lastIndexOf("el");             // 文字列[0]が最後に出現する位置

// その他
x = text.isEmpty();                             // 文字数が0か？
x = text.length();                              // 文字列の長さ
x = text.trim();                                // 文字列の両側の空白文字を削除
x = String.format("Hi %s", "World");            // 書式設定
x = "".copyValueOf("!?!".toCharArray());        // 文字配列[0]を文字列で返す
x = "".copyValueOf("!?!".toCharArray(), 0, 1);  // 文字配列[0]の位置[1]から文字数[2]を文字列で返す
```

**StringBuilder** - 無駄なメモリ割り当てをしないため、文字列結合で使える

```java
StringBuilder builder = new StringBuilder();
String message;

builder.append("AB");
message = builder.toString();
IO.println(message);

builder.append("CD");
message = builder.toString();
IO.println(message);
```

**StringBuffer** - 文字列連結で使える

```java
StringBuffer buffer = new StringBuffer();

for (int i=0; i<5; i++) {
    buffer.append("HELLO! ");
}

IO.println(buffer);
```

[⬆︎目次へ戻る](#目次)

## 数学メソッド

**一覧**

```java
Object x = null;

x = Math.abs(-4.3);         // 絶対値
x = Math.ceil(12.1);        // 最も近い整数に切り上げ
x = Math.floor(14.9);       // 最も近い整数に切り捨て
x = Math.floorDiv(10, 3);   // 除算 (=3)
x = Math.floorMod(10, 3);   // 除算の余り (=1)
x = Math.hypot(3, 4);       // x,yと0, 0の距離
x = Math.max(21, 20);       // 最大値
x = Math.min(11, 12);       // 最小値
x = Math.random();          // 0~1間の乱数
x = Math.rint(10.5);        // 最も近い整数に丸める (doubleで返す)
x = Math.round(10.5);       // 最も近い整数に丸める (intで返す)
```

[⬆︎目次へ戻る](#目次)

## アノテーション

- `@Override` - 親クラスのメソッドをオーバーライドするメソッドに指定
- `@Deprecated` - クラスやメソッドの使用が非推奨の場合に指定
- `@SuppressWarnings` - コンパイル時の警告を抑制する場合に指定
    - `@SuppressWarnings("deprecation)` - 非推奨の機能を使用する際の警告を抑制
    - `@SuppressWarnings("removal")` - 削除予定の機能を使用する際の警告を抑制
    - `@SuppressWarnings("unchecked")` - 型が保証されていない場合の警告を抑制
- `@SafeVarargs` - 可変引数の処理が安全であることを示す。finalかstaticをつける必要あり

[⬆︎目次へ戻る](#目次)
