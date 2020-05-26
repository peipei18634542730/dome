## Java集合

### Collection

#### list 

- 有序集合，允许重复，维护元素插入的顺序，访问元素可以根据索引来访问

##### ArrayList

- 是一个动态数组，允许null值添加。初始容量（10）。如果明确所插入元素的多少，最好指定一个初始容量值，避免过多进行扩容操作而浪费效率
- 查询快
- 非同步

##### linkedList

- 是一个双向链表，在列表中索引的操作将从开头或是结尾便利列表（从靠近指定索引的一端）。这样可以通过较低的代价在集合中插入和删除
- 插入和修改快
- 非同步

##### Vector

- 线程安全的动态数组，操作与ArrayList几乎一致

##### stack

- 继承自Vector - 先进后出的堆栈。push/pop/peek/empty/search

#### set 

- 无序集合，集合元素不允许重复，访问元素只能根据元素本身,最多只允许一个null值。虽然Set中元素没有顺序，但是元素在set中的位置是由该元素的HashCode决定的，其具体位置其实是固定的

##### HashSet 

- 由hashmap实现， 不保证顺序，但是由于HashSet底层是基于Hash算法实现的，使用了hashcode，所以HashSet中相应的元素的位置是固定的
- 非同步
- 有很好的存取和查找性能
- 通过Hash Map存储元素，key存放元素，value统一使用一个object对象
- 必须小心操作可变参数。如果一个Set中的可变元素改变了自身状态导致`Object.equals(Object)=true`将导致一些问题。

##### TreeSet

- 有序。底层基于Tree Map实现。**TreeSet支持两种排序方式，自然排序和定制排序，其中自然排序为默认的排序方式。**当我们构造TreeSet时，若使用不带参数的构造函数，则TreeSet的使用自然比较器；若用户需要使用自定义的比较器，则需要使用带比较器的参数。
- 非线程安全
- 通过compare或者comparaeTo函数来判断元素是否相等.compare函数通过判断两个对象的id，相同的id判断为重复元素，不会被加入到集合中。

##### LinkedHashSet

- 继承自HashSet.底层基于LinkedHashMap实现。
- 有序，非同步。LinkedHashSet集合同样是根据元素的hashCode值来决定元素的存储位置，但是它同时使用链表维护元素的次序。这样使得元素看起来像是以插入顺序保存的，也就是说，当遍历该集合时候，**LinkedHashSet将会以元素的添加顺序访问集合的元素。**

### Map  

- key-value对的形式存出元素，访问时也只能通过每项元素的key来访问value

##### HashMap

<E:\GitProjects\JavaFamily\docs\basics\HashMap.md>

##### LinkedHashMap

- 保留插入的顺序，如果需要输出的顺序和输入时的相同，那么就选用LinkedHashMap。

##### TreeMap

- **TreeMap 是一个有序的key-value集合，非同步，基于红黑树（Red-Black tree）实现，每一个key-value节点作为红黑树的一个节点。** 

### 异同点

####  ArrayList和LinkedList

- ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
- 对于随机访问get和set，ArrayList绝对优于LinkedList，因为LinkedList要移动指针。
- 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。

这一点要看实际情况的。**若只对单条数据插入或删除，ArrayList的速度反而优于LinkedList。**但若是批量随机的插入删除数据，LinkedList的速度大大优于ArrayList. 因为ArrayList每插入一条数据，要移动插入点及之后的所有数据。

#### HashTable与HashMap

相同点：

- 都实现了`Map、Cloneable、java.io.Serializable`接口。
- 都是存储"键值对(key-value)"的散列表，而且都是采用拉链法实现的。

不同点：

**（1）历史原因：**HashTable是基于陈旧的Dictionary类的，HashMap是Java 1.2引进的Map接口的一个实现 。

**（2）同步性：**HashTable是线程安全的，也就是说是同步的，而HashMap是线程序不安全的，不是同步的 。

**（3）对null值的处理：**HashMap的key、value都可为null，HashTable的key、value都不可为null 。

**（4）基类不同：**HashMap继承于AbstractMap，而Hashtable继承于Dictionary。

- Dictionary是一个抽象类，它直接继承于Object类，没有实现任何接口。Dictionary类是JDK 1.0的引入的。虽然Dictionary也支持“添加key-value键值对”、“获取value”、“获取大小”等基本操作，但它的API函数比Map少；而且Dictionary一般是通过Enumeration(枚举类)去遍历，Map则是通过Iterator(迭代M器)去遍历。然而由于Hashtable也实现了Map接口，所以，它即支持Enumeration遍历，也支持Iterator遍历。
- AbstractMap是一个抽象类，它实现了Map接口的绝大部分API函数；为Map的具体实现类提供了极大的便利。它是JDK 1.2新增的类。

**（5）支持的遍历种类不同：**HashMap只支持Iterator(迭代器)遍历。而Hashtable支持Iterator(迭代器)和Enumeration(枚举器)两种方式遍历。

#### HashMap、Hashtable、LinkedHashMap和TreeMap比较

Hashmap 是一个最常用的Map，它根据键的HashCode 值存储数据，根据键可以直接获取它的值，具有很快的访问速度。遍历时，取得数据的顺序是完全随机的。**HashMap最多只允许一条记录的键为Null；允许多条记录的值为Null；HashMap不支持线程的同步，即任一时刻可以有多个线程同时写HashMap；可能会导致数据的不一致。**如果需要同步，可以用Collections的synchronizedMap方法使HashMap具有同步的能力。

Hashtable 与 HashMap类似，不同的是：**它不允许记录的键或者值为空；它支持线程的同步**，即任一时刻只有一个线程能写Hashtable，因此也导致了Hashtale在写入时会比较慢。

LinkedHashMap保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的，也可以在构造时用带参数，按照应用次数排序。在遍历的时候会比HashMap慢，不过有种情况例外，**当HashMap容量很大，实际数据较少时，遍历起来可能会比LinkedHashMap慢，因为LinkedHashMap的遍历速度只和实际数据有关，和容量无关，而HashMap的遍历速度和他的容量有关。**

如果需要输出的顺序和输入的相同，那么用LinkedHashMap可以实现，它还可以按读取顺序来排列，像连接池中可以应用。**LinkedHashMap实现与HashMap的不同之处在于，后者维护着一个运行于所有条目的双重链表**。此链接列表定义了迭代顺序，该迭代顺序可以是插入顺序或者是访问顺序。对于LinkedHashMap而言，它继承与HashMap、底层使用哈希表与双向链表来保存所有元素。其基本操作与父类HashMap相似，它通过重写父类相关的方法，来实现自己的链接列表特性。

**TreeMap实现SortMap接口，内部实现是红黑树。**能够把它保存的记录根据键排序，默认是按键值的升序排序，也可以指定排序的比较器，当用Iterator 遍历TreeMap时，得到的记录是排过序的。TreeMap不允许key的值为null。非同步的。

一般情况下，我们用的最多的是HashMap，HashMap里面存入的键值对在取出的时候是随机的，它根据键的HashCode值存储数据，根据键可以直接获取它的值，具有很快的访问速度。在Map 中插入、删除和定位元素，HashMap 是最好的选择。

TreeMap取出来的是排序后的键值对。但如果您要按自然顺序或自定义顺序遍历键，那么TreeMap会更好。

LinkedHashMap 是HashMap的一个子类，如果需要输出的顺序和输入的相同，那么用LinkedHashMap可以实现，它还可以按读取顺序来排列，像连接池中可以应用。

#### HashSet、LinkedHashSet、TreeSet比较

Set接口

**Set不允许包含相同的元素**，如果试图把两个相同元素加入同一个集合中，add方法返回false。

**Set判断两个对象相同不是使用==运算符，而是根据equals方法。**也就是说，只要两个对象用equals方法比较返回true，Set就不会接受这两个对象。

HashSet

HashSet有以下特点：

- 不能保证元素的排列顺序，顺序有可能发生变化。
- 不是同步的。
- 集合元素可以是null，但只能放入一个null。

当向HashSet结合中存入一个元素时，HashSet会调用该对象的hashCode()方法来得到该对象的hashCode值，然后根据 hashCode值来决定该对象在HashSet中存储位置。简单的说，**HashSet集合判断两个元素相等的标准是两个对象通过equals方法比较相等，并且两个对象的hashCode()方法返回值也相等。**

> 注意，如果要把一个对象放入HashSet中，重写该对象对应类的equals方法，也应该重写其hashCode()方法。其规则是如果两个对象通过equals方法比较返回true时，其hashCode也应该相同。另外，对象中用作equals比较标准的属性，都应该用来计算 hashCode的值。


LinkedHashSet集合同样是根据元素的hashCode值来决定元素的存储位置，但是它同时使用链表维护元素的次序。这样使得元素看起来像是以插入顺序保存的，也就是说，当遍历该集合时候，LinkedHashSet将会以元素的添加顺序访问集合的元素。

**LinkedHashSet在迭代访问Set中的全部元素时，性能比HashSet好，但是插入时性能稍微逊色于HashSet。**

TreeSet类

TreeSet是SortedSet接口的唯一实现类，TreeSet可以确保集合元素处于排序状态。TreeSet支持两种排序方式，自然排序和定制排序，其中自然排序为默认的排序方式。向TreeSet中加入的应该是同一个类的对象。

**TreeSet判断两个对象不相等的方式是两个对象通过equals方法返回false，或者通过CompareTo方法比较没有返回0。**

**自然排序**

自然排序使用要排序元素的`CompareTo（Object obj）`方法来比较元素之间大小关系，然后将元素按照升序排列。

> Java提供了一个Comparable接口，该接口里定义了一个`compareTo(Object obj)`方法，该方法返回一个整数值，实现了该接口的对象就可以比较大小。`obj1.compareTo(obj2)`方法如果返回0，则说明被比较的两个对象相等，如果返回一个正数，则表明obj1大于obj2，如果是负数，则表明obj1小于obj2。如果我们将两个对象的equals方法总是返回true，则这两个对象的compareTo方法返回应该返回0。

**定制排序**

自然排序是根据集合元素的大小，以升序排列，如果要定制排序，应该使用Comparator接口，实现 `int compare(T o1,T o2)`方法。

HashSet：哈希表是通过使用称为散列法的机制来存储信息的，元素并没有以某种特定顺序来存放；  
LinkedHashSet：以元素插入的顺序来维护集合的链接表，允许以插入的顺序在集合中迭代；  
TreeSet：提供一个使用树结构存储Set接口的实现，对象以升序顺序存储，访问和遍历的时间很快。



