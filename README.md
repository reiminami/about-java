# About Java

*javac 25

## 目次

- [命名規則](#命名規則)
- [Hello, World](#hello-world)
- [データ型](#データ型)
- [条件分岐](#条件分岐)
- [ループ](#ループ)
- [配列](#配列)
- [メソッド](#メソッド)
- [クラス](#クラス)
- [レコード](#レコード)
- [インターフェース](#インターフェース)
- [抽象クラス](#抽象クラス)
- [ジェネリクス](#ジェネリクス)
- [例外処理](#例外処理)
- [List](#list)
- [Set](#set)
- [Map](#map)
- [メモリ](#メモリ)
- [文字列操作](#文字列操作)
- []()
- []()
- []()

## 命名規則

|種類|命名規則|例|
|-|-|-|
|クラス名|PascalCase|`MyClass`|
|メソッド名|camelCase|`myMethod()`|
|getter|camelCase|`getName()`|
|setter|camelCase|`setName()`|
|変数名|camelCase|`myVariable`|
|定数名|SNAKE_CASE|`MY_CONST`|

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

**参照型**

`String`・`Integer`・`Double`

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

## メモリ

```java
Runtime r = Runtime.getRuntime();
long fm = r.freeMemory();   // 残りメモリ容量
long tm = r.totalMemory();  // 現在のメモリ総容量
long mm = r.maxMemory();    // メモリ総容量の限界値
```

[⬆︎目次へ戻る](#目次)

## 文字列操作

**前提**

```java
String text = "Hello World";
Object x = null;
```

**文字列切り出し**

```java
x = text.charAt(6);                 // 位置[0]の文字
x = text.substring(2, 5);           // 位置[0] ~ [1]までの文字列
```

**文字列結合**

```java
x = text + "!";                     // 単純結合
x = String.join("-", text, "!");    // 区切り文字[0]で文字列[1]に文字列[2]を結合
```

**文字列変換**

```java
x = text.toUpperCase();             // 大文字化
x = text.toLowerCase();             // 小文字化
x = text.replace("Hello", "Hi");    // 文字列[0]を文字列[1]に置換
```

**文字列比較**

```java
x = text.equals("Hello");           // 文字列[0]と一致するか？
x = text.equalsIgnoreCase("Ay");    // 文字列[0]と一致するか？ (大文字小文字区別なし)
x = text.compareTo("Hello Worlf");  // 文字列[0]と比較 (数値で返す、0なら一致)
```

**文字列検索**

```java
x = text.contains("llo");       // 文字列[0]を含むか？
x = text.startsWith("H");       // 文字列[0]で始まるか？
x = text.endsWith("d");         // 文字列[0]で終わるか？
x = text.indexOf("lo");         // 文字列[0]の位置 (存在しない場合-1)
x = text.lastIndexOf("lo");     // 文字列[0]の位置 (後ろから検索・存在しない場合-1)
```

**その他**

```java
x = text.length();                  // 文字列の長さ
x = text.split(" ");                // 文字[0]で分割し配列で返す
```

**StringBuilder** - 無駄なメモリ割り当てをしないため、文字列結合で使える

```java
StringBuilder sb = new StringBuilder();
String s;
sb.append("AB");
s = sb.toString();
sb.append("CDE");
s = sb.toString();
```

**StringBuffer** - 文字列連結で使える

```java
StringBuffer sb = new StringBuffer();
for (int i=0; i<5; i++) {
    sb.append("HELLO! ");
}
System.out.println(sb);
```

[⬆︎目次へ戻る](#目次)
