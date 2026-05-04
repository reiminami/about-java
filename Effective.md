# Effective

## 1. コンストラクタより静的ファクトリメソッドを優先して使う

静的ファクトリメソッドは、単にクラスのインスタンスを返すだけの静的メソッドです。

❌ コンストラクタ

```java
class User {
    private String role;
    public User(String role) {
        this.role = role;
    }
}

User user = new User("admin");
```

✅ 静的ファクトリメソッド

```java
class User {
    private String role;
    private User(String role) {
        this.role = role;
    }
    public static User createAdmin() {
        return new User("admin");
    }
}

User user = User.createAdmin();
```

## 2. 大量のコンストラクタパラメータよりビルダーを使う

パラメータが多いと、何番目のパラメータが何を表すかなど意識する必要が生じます。
ビルダーを採用すると一つずつ設定する仕組みとなり、引数だらけになる問題を防ぐことができます。
また、必須パラメータのコンストラクタで受け取るパターンも考えるべきです。

❌ 大量のコンストラクタパラメータ

```java
User user = new User("John", 45, "Japan", 180, 50, "AB");
```

✅ ビルダー使用

```java
class User {
    private String name;
    private int age;
    private User(Builder builder) {
        this.name = builder.name;
        this.age  = builder.age;
    }

    public static class Builder {
        private String name;
        private int age;
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}

User user = new User.Builder().setName("John").setAge(45).build();
```

## 3. enumやprivateコンストラクタでシングルトンを強制する

シングルトンは、インスタンスが一つしか作られないクラスのデザインパターンです。
enumで表現するのが最も安全です。

✅ enum

```java
enum GameManager {
    INSTANCE;
    public void start() {
        IO.println("Game Start");
    }
}

GameManager.INSTANCE.start();
```

✅ privateコンストラクタ

```java
public class GameManager {
    private static final GameManager INSTANCE = new GameManager();
    public static GameManager getInstance() {
        return INSTANCE;
    }
    public void start() {
        IO.println("Game Start");
    }
}

GameManager gm = GameManager.getInstance();
gm.start();
```

## 4. privateコンストラクタでインスタンス化不可能を強制する

フィールドやメソッドが静的なクラスはユーティリティクラスといいます。
そのようなクラスはインスタンス化されるべきではありません。

✅ privateコンストラクタ

```java
public class UtilCalc {
    public static int sum(int a, int b) {
        return a + b;
    }
    private UtilCalc() {
        throw new AssertionError("インスタンス化禁止です。");
    }
}
```

## 5. 依存性注入

クラス内部で具体クラスをインスタンス化するよりも外から渡すべきです。

❌ クラス内部でインスタンス化

```java
class Player {
    private KeyInput key = new KeyInput();
}

Player player = new Player();
```

✅ 依存性注入

```java
class Player {
    private keyInput key;

    public Player(KeyInput key) {
        this.key = key;
    }
}

// パターン1
KeyInput key = new KeyInput();
Player player = new Player(key);

// パターン2
Player player = new Player(new KeyInput());
```

## 6. 不必要なオブジェクト生成を避ける

オブジェクトを再利用することで生成コストを節約できます。

❌ 不必要なオブジェクト生成

```java
String msg = new String("Hello");
```

✅ 改善

```java
String msg = "Hello";
```

## 7. 使われなくなったオブジェクト参照を取り除く

ガベージコレクタが認識できない未使用のオブジェクトはnullを代入して参照を取り除くべきです。

## 8. ファイナライザとクリーナーを避ける

以下は実行タイミングが不安且つ実行されない可能性もあるため、使用を避けるべきです。

- `java.lang.Object#finalize()`
- `java.lang.ref.Cleaner`
- 上記をオーバーライドしたメソッド等

## 9. try-finallyよりtry-with-resourcesを使う

自動でcloseされます。

✅ try-with-resources

```java
try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
    IO.println(br.readLine());
} catch (IOException e) {
    e.printStackTrace();
}
```

## 10. equalsのオーバーライドに注意する

equalsをオーバーライドする場合、以下のように記述すべきです。

✅ equals

```java
class User {
    String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;

        User other = (User) obj;
        return Objects.equals(this.name, other.name);
    }
}
```

## 11. equalsとhashCodeは一緒にオーバーライドする

equalsをオーバーライドしたら、必ずhashCodeもオーバーライドすべきです。

✅ hashCode

```java
class User {
    // equalsのオーバーライド

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
```

## 12. toStringをオーバーライドする

デバッグを容易にするため、toStringをオーバーライドして利用者が知るべき情報を極力出力すべきです。

## 13. cloneのオーバーライドに注意する

Cloneableが危険なので触れません。

## 14. Comparableを実装する

コレクションのAPIを使えるようになります。

✅ Comparable実装

```java
class User implements Comparable<User> {
    int id;

    @Override
    public int compareTo(User other) {
        return Integer.compare(this.id, other.id);
    }
}
```

## 15. アクセス修飾子は最小限にする

極力、privateを使うべきです。

## 16. publicクラスではアクセッサーメソッドを使う

privateフィールドとgetter, setterを使うべきです。

## 17. 不変フィールドを使う

極力、finalを使って不変にすべきです。

## 18. 継承よりもコンポジションを使う

is-a よりhas-a を優先すべきです。

❌ 継承

```java
class Actor {
    void move() {}
}

class Enemy extends Actor {}
```

✅ コンポジション

```java
class Movement {
    void to() {
        // ...
    }
}

class Enemy {
    private Movement move = new Movement();

    void moveTo() {
        move.to();
    }
}
```

## 19. 継承のための設計をする、無理なら継承しない

❌ 無計画な継承

```java
class Actor {
    void init() {
        setMoveSprite();
        setAttackSprite();
    }

    abstract void setMoveSprite();
    abstract void setAttackSprite();
}
```

✅ 意図を明確にする

```java
abstract class Actor {
    public final void init() {
        setMoveSprite();
        setAttackSprite();
    }

    protected abstract void setMoveSprite();
    protected abstract void setAttackSprite();
}
```

## 20. 抽象クラスよりインターフェースを使う

インターフェースであれば、多重実装可能で柔軟且つ疎結合になります。

❌ 抽象クラス依存

```java
abstract class Animal {
    abstract void cry();
}

class Dog extends Animal {
    @Override
    public void cry() {
        IO.println("baw");
    }
}
```

✅ インターフェース

```java
interface Animal {
    void cry();
}

class Dog implements Animal {
    @Override
    public void cry() {
        IO.println("baw");
    }
}
```

## 21. 将来のためのインターフェース設計を行う

インターフェースにあとからメソッドを追加すると、そのインターフェースを実装したクラスは
コンパイルエラーになります。defaultメソッドも慎重に使用すべきです。
それらを考慮して設計してください。

## 22. 型定義のためのインターフェース

インターフェースに定数を定義するのは基本NGです。
「振る舞いの契約」に使用してください。

## 23. タグ付きクラスよりクラス階層を使う

一つのクラスで複数の概念を表現するクラスを「タグ付きクラス」といいます。
複数の概念を表すなら、継承やサブタイプ化を利用して階層構造に整理すべきです。

❌ タグ付きクラス

```java
class Shape {
    // 0=Circle, 1=Rectangle
    int type;
}
```

✅ クラス階層

```java
abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double r;
    double area() {
        return Math.PI * r * r;
    }
}
```

## 24. 非staticメンバクラスよりstaticメンバクラスを使う

クラス内のクラスは、外側インスタンスが不要であればstaticにすべきです。

✅ staticメンバークラス

```java
class Outer {
    static class Inner {}
}
```

## 25. 単一ソースファイルには単一クラスを記述する

一つのソースファイルに一つのクラスを書くべきです。

## 26. 原型は使わない

❌ 原型を使う

```java
List list = new ArrayList<>();
```

✅ 原型を使わない

```java
List<String> list = new ArrayList<>();
```

## 27. 無検査警告を取り除く

ジェネリクスは思い通りの操作できるとは限らないため、コンパイラに効果的に伝える必要があります。
放置しないか理由を明示することを選ぶべきです。

✅ SuppressWarnings

```java
@SuppressWarnings("unchecked")
```

## 28. 配列よりリストを使う

配列は不便で型の安全性も弱いため、Listなどを使用すべきです。

## 29. ジェネリック型の使用

型安全なコンテナを作れます。

✅ ジェネリック型

```java
class Box<T> {
    private T value;
    public void set(T value) {
        this.value = value;
    }
    public T get() {
        return value;
    }
}
```

## 30. ジェネリックメソッドを使う

メソッド単位で型を扱うべきです。

✅ ジェネリックメソッド

```java
public static <T> T first(List<T> list) {
    return list.get(0);
}
```

## 31. 柔軟性向上のために境界ワイルドカードを使う

✅ 境界ワイルドカード

```java
public static void print(List<? extends Number> list) {
    for (Number n : list) {
        IO.println(n);
    }
}
```

## 32. ジェネリクスと可変長引数の同時使用に注意する

`@SafeVarargs` は安全が保証できる時だけ付けてください。

✅ ジェネリクス+可変長引数

```java
@SafeVarargs
static <T> void printAll(T... args) {
    for (T arg : args) {
        IO.println(arg);
    }
}
```

## 33. 型安全な異種コンテナを使う

✅ キーに型情報を持たせる

```java
class Favorites {
    private Map<Class<?>, Object> map = new HashMap<>();

    public <T> void put(Class<T> type, T value) {
        map.put(type, value);
    }

    public <T> T get(Class<T> type) {
        return type.cast(map.get(type));
    }
}
```

## 34. int定数よりenumを使う

❌ int定数

```java
static final int PERFECT = 100;
```

✅ enum

```java
enum Score {
    PERFECT(100),
    HIGH   (80),
    ;

    private int value;

    private Score(int value) {
        this.value = value;
    }
}
```

## 35. ordinalよりインスタンスフィールドを使う

enumの`ordinal`メソッドは、定数が何番目に宣言されたかを返します。
変更に弱いため`ordinal` に依存すべきではありません。

❌ ordinal

```java
int dirCode = Direction.RIGHT.ordinal();
```

✅ インスタンスフィールド

```java
int dirCode = Direction.RIGHT.code;

enum Direction {
    TOP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3)
    ;
    private final int code;
    private Direction(int code) {
        this.code = code;
    }
}
```

## 36. ビットフィールドよりEnumSet

❌ ビットフィールド

```java
private static final int STYLE_BOLD      = 1 << 0;  // 1
private static final int STYLE_ITALIC    = 1 << 1;  // 2
private static final int STYLE_UNDERLINE = 1 << 2;  // 4
int styleBoldItalic = STYLE_BOLD | STYLE_ITALIC;    // 3
```

✅ EnumSet

```java
IO.println(Style.BOLD);
Set<Style> boldItalic = EnumSet.of(Style.BOLD, Style.ITALIC);
IO.println(boldItalic);

enum Style {
    BOLD,
    ITALIC,
    UNDERLINE
    ;
}
```

## 37. 序数インデックスよりEnumMapを使う

列挙定数をキーとし他の値とする場合、EnumMapを使うべきです。

✅ 専用Map

```java
EnumMap<State, String> map = new EnumMap<>(State.class);
```

## 38. 拡張可能なenumをインターフェースで模倣する

enumは継承できないため、共通の振る舞いはインターフェースで定義した方がよいです。

✅ インターフェースでの模倣

```java
interface Operation {
    int apply(int x, int y);
}

enum BasicOperation implements Operation {
    ADD {
        public int apply(int x, int y) {
            return x + y;
        }
    }
}
```

## 39. 命名パターンよりアノテーションを使う

❌ 名前で意味を持たせる

```java
void testLogin() {}
```

✅ アノテーションを使う

```java
@Test
void login() {}
```

## 40. Overrideアノテーションを使う

スペルミスやシグネチャ違いを防ぐため必須です。

## 41. 型定義のためのマーカーインターフェース

マーカーインターフェースは、何も定義されていないインターフェースです。
コードを書かずに、何かをサポートしていることを伝える方法として用いられます。
代表的な`Serializable` は、単に「シリアライズ化できる」ということを示しています。

```java
class App implements Serializable {
    // ...
}
```

## 42. 無名クラスよりラムダを使う

関数を一つ持つだけのクラスなどであれば、ラムダで十分です。

❌ 無名クラス

```java
Runnable runner = new Runnable() {
    @Override
    public void run() {
        IO.println("Hello");
    }
};

runner.run();
```

✅ ラムダ

```java
Runnable runner = () -> { IO.println("Hello"); };
runner.run();
```

## 43. ラムダよりメソッド参照を使う

そのまま呼ぶだけなら、メソッド参照でさらに短くできます。

❌ ラムダ

```java
list.forEach(num -> IO.println(num));
```

✅ メソッド参照

```java
list.forEach(IO::println);
```

## 44. 標準で用意された関数型インターフェースを使う

自作せずに`java.util.function` を使うべきです。

✅ 関数型インターフェース

```java
Function<String, Integer> fn = str -> str.length();
IO.println(fn.apply("Hello"));
```

## 45. Streamの使用に注意する

ストリームAPIはコードが簡潔になりますが、可読性が下がる場合は他の方法を検討すべきです。

## 46. Streamメソッドの副作用に注意する

ストリームAPIの中間操作は前のステージの変換結果にのみアクセスできます。
外部状態を変更することは可能ですが、副作用が発生するため避けるべきです。

## 47. 戻り値にはStreamよりもCollectionを使う

呼び出し側に優しいAPIにすべきです。

❌ Stream

```java
class User {
    Stream<User> getUsers() {
        // ...
    }
}
```

✅ List

```java
class User {
    List<User> getUsers() {
        // ...
    }
}
```

## 48. Streamの並列化に注意する

パイプラインをマルチスレッドとして実行できる`Stream.parallel()`があります。
理解せずに使用すると処理が遅くなる場合があり、そもそも理解するのも難しいため
理由がない限り並列化すべきではないです。

## 49. パラメータの正当性を検査する

入り口でパラメータの正当性を検査すべきです。

✅ 正当性検査

```java
public void setAge(int age) {
    if (age < 0) throw new IllegalArgumentException();

    this.age = age;
}
```

## 50. 防御的コピーを使う

利用者が不適切な使い方をしてもデータが壊されないように工夫すべきです。

✅ 防御的コピー

```java
public List<String> getUsers() {
    return new ArrayList<>(users);
}
```

## 51. メソッドのシグネチャ設計に注意する

- 名前を簡潔且つ理解可能にする
- パラメータは4個までにする。収まらない場合は
    - 複数のメソッドに分割する
    - パラメータを保持したヘルパークラスを作る
    - Builderパターンを採用する
- パラメータの型は可能な限りインターフェースを採用する
- 「boolean」か「２要素を持つenum」のどちらかを選ぶ

## 52. オーバーロードは注意して使う

処理を片方に転送したり、staticファクトリメソッドで実装するなどを考えるべきです。

## 53. 可変長引数は注意して使う

必須パラメータがある場合は指定しないとコンパイルエラーとなるため、別で定義すべきです。

## 54. nullではなく空コレクションか空配列を返す

シーケンスを返すメソッドでnullを返すと、nullチェックの処理が増え手間となるため
nullを返すべきではありません。

❌ null

```java
return null;
```

✅ 空コレクション

```java
return Collections.emptyList();
```

## 55. オプショナルは注意して返す

Optionalは「値が無い可能性がある」ことを型で表します。
まず、従来はnullを返す+呼び出し元でnullチェックでしたが、最近はOptionalを使用します。

❌ 従来

```java
User findUser(int id) {
    return null;
}
```

✅ 最近

```java
Optional<User> findUser(int id) {
    return Optional.empty();
}
```

注意点として、

✅ フィールドや引数には使用せず、戻り値だけに使う

```java
Optional<User> findUser(int id) { /* ... */ }
```

✅ Optional型にnullを代入しない

```java
Optional<User> user = Optional.empty();
```

## 56. 全ての公開API要素にドキュメントを書く

Javadocは明記すべきです。

## 57. ローカル変数のスコープは最小限にする

すべきです。

## 58. forループよりもfor-each

✅ for-each

```java
for (Integer i : list) {
    IO.println(i);
}
```

## 59. ライブラリを知りライブラリを使う

標準で搭載されている機能は自作する必要はありません。

```java
Collections.sort(list);
```

## 60. 正確な答えを求めるならfloatやdoubleを避ける

floatやdoubleでは誤差が出るため、BigDecimalを使うべきです。

❌ double

```java
double price = 0.1 + 0.2;
```

✅ BigDecimal

```java
BigDecimal price = new BigDecimal("0.3");
```

## 61. ボクシングされたデータより基本データ型を使う

Doubleよりdoubleを使うべきです。

## 62. 適切な型を選ぶ

数値はint、文字列はStringを使うべきです。

## 63. 文字列結合に注意する

Stringは不変のため、+で連結される度に新たなインスタンスを生成します。
何度も文字列結合する場合はStringBuilderを使うべきです。

## 64. インターフェースでオブジェクトを参照する

実装ではなく抽象に依存することで、変更やテストが容易になります。

✅ インターフェースでオブジェクト参照

```java
List<String> list = new ArrayList<>();
```

## 65. リフレクションよりインターフェースを使う

実行時にクラスやメソッドを文字列から操作する仕組みをリフレクションと言います。
無駄に使うよりはインターフェースを使った方がよいです。

❌ リフレクション

```java
Class<?> cls = Class.forName("User");
Object obj = cls.getDeclaredConstructor().newInstance();
```

✅ インターフェース

```java
interface UserService {
    void process();
}

class DefaultUserService implements UserService {
    public void process() {}
}

UserService service = new DefaultUserService();
```

## 66. ネイティブメソッドの使用に注意する

JNI (Java Native Interface) という、Java以外で書かれたコードと連携する仕様があります。
デバッグの困難さや移植性の低さから、本当に必要な時だけ使うべきです。

## 67. 最適化に注意する

処理を速くするために最適化は行いますが、原則として以下の順番を守りましょう。

1. 動かす
2. 計測する
3. 必要なら最適化する

## 68. 一般的な命名規約を守る

自分が用意したルールではなく、一般的なものを使うべきです。

## 69. 例外的状態にのみ例外を使う

通常フローにわざわざ例外を使わないでください。

## 70. 例外を使い分ける

回復可能な状態にはチェック例外、バグには実行時例外を使うべきです。

## 71. チェックされる例外を不必要に使うのを避ける

try-catchだらけになり可読性が低下するため、乱用はやめましょう。

## 72. 標準的な例外を使う

自作の例外をなるべく使わないようにしましょう。

## 73. 抽象概念に適した例外をスローする

抽象レベルの例外を使うべきです。

## 74. 例外を文書化する

Javadocを書こうという話です。

## 75. 詳細メッセージにエラー記録情報を含める

何が原因かをメッセージに含めるべきです。

## 76. エラーアトミック性に務める

失敗しても中途半端な状態になるのを避けるべきです。

## 77. 例外を無視しない

例外を捕捉したら何かしらすべきです。

## 78. 共有された可変データへのアクセスを同期する

複数スレッドで同じデータを扱う場合は同期すべきです。

## 79. 過剰な同期は避ける

同期しすぎると遅くなります。

## 80. スレッドよりエグゼキュータ、タスク、ストリームを使う

独自のThreadクラスではなく、java.util.concurrent.Executorsを使うべきです。

## 81. waitやnotifyより並行処理ユーティリティを使う

低レベルAPIを使わず、BlockingQueue, CountDownLatch, Semaphoreなどを使うべきです。

## 82. スレッド安全性を文書化する

Javadocを書くべきです。

## 83. 遅延初期化に注意する

フィールドの値が必要になるまでそのフィールドの初期化を遅らせることを遅延初期化といいます。
最適化のためや循環処理のために使われますが、効果が出ない場合などは対策した方がよいです。

## 84. スレッドスケジューラに依存しない

JVMの構成要素にスレッドをスケジューリングするものがあります。
たまたま動くことに依存せずに同期やロック、並行APIに頼るべきです。

## 85. シリアライズより代替手段を選ぶ

標準シリアライズは危険なため、JSONなどを使用すべきです。

## 86. Serializableに注意する

変更のしづらさやセキュリティリスクを考慮し、慎重に実装すべきです。

## 87. カスタムシリアライズに注意する

保存形式＝APIとして扱うべきです。

## 88. readObjectは防御的に書く

nullチェック、範囲チェック、不変条件などで不正データを避けるべきです。

## 89. インスタンス制御にreadResolveを使う

シングルトンの崩壊を防ぐべきです。

## 90. 直列化プロキシパターンを使う

安全なシリアライズをする場合は検討すべきです。
