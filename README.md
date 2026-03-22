# about-java

*javac 25.0.2

## トピック

[命名規則](naming.md)

## 目次

- [Hello World](#hello-world)
- [変数/データ型](#変数データ型)
- [条件分岐](#条件分岐)
- [ループ](#ループ)
- [配列](#配列)
- [メソッド](#メソッド)
- [文字列操作](#文字列操作)
- [クラスとオブジェクト](#クラスとオブジェクト)
- [OOP](#oop)
- [インターフェース](#インターフェース)
- [抽象クラス](#抽象クラス)
- [ジェネリクス](#ジェネリクス)
- [コレクション](#コレクション)
- [List](#list)
- [Set](#set)
- [Map](#map)
- [Queue](#queue)
- [例外処理](#例外処理)

## Hello World

- サンプルコード

```java
public class Sample {
    void main() {
        IO.println("Hello, World!");
    }
    // Comment
    /*
        Comment
    */
}
```

- コンパイル＆実行

```sh
$ javac Sample.java
$ java Sample
Hello, World!
```

- プロジェクト作成 (VSCode)

1. Ctrl + Shift + P
2. java:Create Java Project を選択
3. No build tools を選択
4. 保存先を選択
5. プロジェクト名 を入力

[目次へ戻る](#目次)

## 変数/データ型

```java
// プリミティブ型
int num = 10;               // 数値
byte byteNum = 20;          // 数値 (8bit)
short shortNum = 30;        // 数値 (16bit)
long longNum = 40;          // 数値 (64bit)
double pi1 = 3.14;          // 浮動小数点数
float pi2 = 3.14f;          // 浮動小数点数 (32bit)
boolean isExists = true;    // 真偽値
char initial = 'A';         // 文字

// 参照型
String name = "John";       // 文字列
Integer age = 22;           // 数値
Double pi = 3.14;           // 浮動小数点数

// 定数
final int MAX_HP = 9999;
```

[目次へ戻る](#目次)

## 条件分岐

```java
// if
if (score == 0) {
} else if (score > 0) {
} else {
}

// switch
switch (score) {
    case 1:
        break;
    case 2:
        break;
    default:
        IO.println("z");
}
```

[目次へ戻る](#目次)

## ループ

```java
// while
while (i < 3) {
    i++;
}

// do-while
do {
    i++;
} while (i < 3);

// for
for (int j=0; j<3; j++) {
}

// 拡張for
int[] numbers = {10, 20, 30};
for (int number : numbers) {
}
```

[目次へ戻る](#目次)

## 配列

```java
// 配列
int[] numbers1 = new int[3];
int[] numbers2 = {10, 20, 30};

// 多次元配列
int[][] nums1 = new int[2][2];
int[][] nums2 = {
    {10, 20},
    {30, 40}
};

// 配列のコピー
int[] numbers = {10, 20, 30};
int[] copiedNumbers1 = numbers.clone();
int[] copiedNumbers2 = Arrays.copyOf(numbers, 3);

// 配列操作
int[] numbers = new int[3];
numbers[2] = 100;
int num = numbers[2];
```

[目次へ戻る](#目次)

## メソッド

- 基本

```java
public int sum(int a, int b) {
    return a + b;
}

void main() {
    int num = sum(10, 20);
    IO.println(num);
}
```

- オーバーロード: 同じメソッド名で引数の型や引数の数が異なるメソッドを定義

```java
public void display(String txt) {
    IO.println(txt);
}

public void display(int number, int loopCount) {
    for (int i=0; i<loopCount; i++) {
        IO.println(number);
    }
}

void main() {
    display("Hello");
    display(100, 3);
}
```

- 可変長引数

```java
public void display(String... args) {
    for (String arg : args) {
        IO.println(arg);
    }
}

void main() {
    display("A", "B");
}
```

[目次へ戻る](#目次)

## 文字列操作

- String

```java

String s = "Hello";
Object x = null;

x = s.length();                     // 文字列の長さ
x = s.charAt(0);                    // [0]の文字
x = s.substring(0, 3);              // [0]から[1]までの文字列
x = s.toUpperCase();                // 大文字化
x = s.toLowerCase();                // 小文字化
x = s.equals("Hello");              // [0]と比較
x = s.equalsIgnoreCase("hello");    // [0]と比較 (大文字小文字区別なし)
x = s.compareTo("Hell");            // [0]と比較 (数値で返す)
x = s + "!";                        // 文字列結合
x = String.join("-", s, "!");       // 区切り文字[0]で[1]に[2]を結合
x = s.replace("H", "h");            // [0]を[1]に置換
String[] a = "AA B C".split(" ");   // 文字[0]で分割し配列で返す
```

- **StringBuilder**: Stringのように、文字列結合で無駄なメモリ割り当てをしない

```java
StringBuilder builder = new StringBuilder();
builder.append("AB");               // 追加
String b = builder.toString();      // 文字列に変換
```

[目次へ戻る](#目次)

## クラスとオブジェクト

- クラス

```java
public class Rectangle {
    private int height;
    private int width;

    public Rectangle(int h, int w) {
        this.height = h;
        this.width = w;
    }

    public int area() {
        return height * width;
    }
}
```

- オブジェクト

```java
Rectangle r = new Rectangle(10, 20);
IO.println(r.area());
```

- **Record**: 簡潔にコードを書ける
    - final (継承禁止)
    - コンストラクタ, getter, equals(), hashCode(), toString() を自動生成

```java
public record Item(String name) {
    public Item {
        IO.println("Item: " + name);
    }
}
```

[目次へ戻る](#目次)

## OOP

オブジェクト指向のメリットは、「変更に対して柔軟に対応する」ため。

- **継承**: @Override (親クラスのメソッドの上書き)

```java
public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    @Override
    public int area() {
        IO.println("*Square Area");
        return super.area();
    }
}
```

- **カプセル化**: 内部のデータを守る

```java
public class Rectangle {
    private int height;
    private int width;

    public int getHeight() {
        return height;
    }
    // ...
}
```

- **ポリモーフィズム**: 同じ呼び方で異なる柔軟な動き

```java
Rectangle r1 = new Rectangle(10, 20);
Rectangle r2 = new Square(10);
IO.println(r1.area());
IO.println(r2.area());
```

[目次へ戻る](#目次)

## インターフェース

- 「クラス仕様の定義」
- "CAN DO" = 「`Slime` は`attack()` できる」
- 多重継承可能
- 全体: `public` のみ使用可能
- 変数: クラス定数 (`public static final`) のみ使用可能
- メソッド: 型定義のみ

```java
// インターフェース
public interface Attackable {
    void attack();
}
```

```java
// 実装
public class Slime implements Attackable {
    // ...

    @Override
    public void attack() {
        IO.println("Slime Attack!");
    }
}
```

[目次へ戻る](#目次)

## 抽象クラス

- 「共通処理」
- "IS A" = 「`Slime` は `Enemy` だ」
- 多重継承不可能
- 全体: `public`, `protected`
- 変数: なんでも使用可能
- メソッド: 具体的な処理の定義も可能

```java
// 抽象クラス
public abstract class Enemy {
    protected String name;
    protected int hp;

    public Enemy(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    // 共通
    public void status() {
        IO.println("%s (HP: %s)".formatted(name, hp));
    }

    // 個別で実装
    public abstract void attack();
}
```

```java
// 具体クラス
public class Slime extends Enemy {
    public Slime(String name, int hp) {
        super(name, hp);
    }

    @Override
    public void attack() {
        IO.println(name + ": take this!");
    }
}
```

[目次へ戻る](#目次)

## ジェネリクス

- クラス

```java
public class Calc<T> {
    public T number;
    public Calc(T number) {
        this.number = number;
    }
    public T getNumber() {
        return number;
    }
}

// main
Calc c1 = new Calc<Integer>(5);
Calc c2 = new Calc<Double>(1.2);
IO.println(c1.number);
IO.println(c2.number);
```

- メソッド

```java
public <T> T check(T txt) {
    // ...
    return txt;
}
```

[目次へ戻る](#目次)

## List

配列

- ArrayList: 参照・検索が高速
- LinedList: 挿入・削除が高速
- Vector: パフォーマンスが悪いためあまり使われない

```java
List<Integer> list = new ArrayList<>();
List<Integer> list2 = new ArrayList<>();
Object x = null;

list.add(10);           // [0]を末尾に追加
list.add(1, 20);        // index[0]に[1]を追加
list.set(1, 30);        // index[0]に[1]を追加
list.remove(1);         // index[0]を削除
list.clear();           // 要素を全て削除
x = list.get(0);        // index[0]を取得
x = list.contains(10);  // 値[0] があるか？
x = list.isEmpty();     // 要素数が0か？
x = list.size();        // 要素数
x = list.indexOf(10);   // 値[0] のインデックス
```

[目次へ戻る](#目次)

## Set

要素が重複しない配列

- HashSet: 要素が自動でソートされない
- TreeSet: 要素が自動でソートされる

```java
Set<String> hash = new HashSet<>();
Object x = null;

hash.add(("A"));        // 値[0] を追加
hash.remove("A");       // 値[0] を削除
hash.clear();           // 要素を全て削除
x = hash.contains("A"); // 値[0] があるか？
x = hash.isEmpty();     // 空か？
x = hash.size();        // 要素数
```

[目次へ戻る](#目次)

## Map

キーと値の組み合わせを持つ配列

- HashMap: 順序が保証されない
- LinkedHashMap: 値を設定した順番が保証される
- TreeMap: 要素が自動でソートされる

```java
Map<Integer, String> map = new HashMap<>();
Object x = null;

map.put(3, "C");            // キーと値の組み合わせを追加
map.remove(3);              // キー[0] を削除
map.clear();                // 要素を全て削除
x = map.get(3);             // キー[0] の値を取得
x = map.containsKey(3);     // キー[0] を持つか？
x = map.containsValue("C"); // 値[0] を持つか？
x = map.isEmpty();          // 空か？
x = map.size();             // 要素数

// ループ
map.forEach((k, v) -> {
    IO.println(k + ": " + v);
});
```

[目次へ戻る](#目次)

# Queue

- Deque: FIFO、FILOのスタック

```java
queue.addFirst("A");        // [0] を先頭に追加
queue.addLast("Z");         // [0] を末尾に追加
x = queue.offerFirst("B");  // [0] を先頭に追加 (エラー時false)
x = queue.offerLast("Y");   // [0] を末尾に追加 (エラー時false)
x = queue.removeFirst();    // 先頭を削除 (エラー時例外)
x = queue.removeLast();     // 末尾を削除 (エラー時例外)
x = queue.pollFirst();      // 先頭を取り出し削除 (エラー時null)
x = queue.getFirst();       // 先頭を取り出す (エラー時例外)
x = queue.getLast();        // 末尾を取り出す (エラー時例外)
x = queue.peekFirst();      // 先頭を取り出す (エラー時null)
x = queue.peekLast();       // 先頭を取り出す (エラー時null)
x = queue.removeFirstOccurrence("A");   // 最初に現れる[0] を削除
x = queue.removeLastOccurrence("A");    // 最後に現れる[0] を削除
queue.push("D");            // addFirst()
x = queue.add("C");         // addLast()
x = queue.offer("E");       // addLast()
x = queue.remove();         // removeFirst()
x = queue.pop();            // removeFirst()
x = queue.poll();           // pollFirst()
x = queue.element();        // getFirst()
x = queue.peek();           // peekFirst()
x = queue.remove("E");      // removeFirstOccurrence()
x = queue.contains("C");    // 値[0] が存在するか？
x = queue.iterator();       // イテレータ
x = queue.descendingIterator();         // イテレータ (逆順)
```

[目次へ戻る](#目次)

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

[目次へ戻る](#目次)
