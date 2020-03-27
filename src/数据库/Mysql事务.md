## Mysql事务

一。Read Uncommitted(读未提交)

​		读未提交`指当会话A的数据库操作尚未`commit`时，会话B可以读取到这个未提交的数据。而此时如果会话A因为某些原因`rollback`了，那么会话B读取的数据就是错误的，也就是`脏读`。当隔离级别提高到`读已提交`时，则可以避免`脏读`。	

​		在该隔离级别，所有事务都可以看到其他未提交事务的执行结果。本隔离级别很少用于实际应用，因为它的性能也不比其他级别好多少。读取未提交的数据，也被称之为脏读（Dirty Read）。

二。Read Committed(读已提交)

​		这是大多数数据库系统的默认隔离级别（但不是MySQL默认的）。它满足了隔离的简单定义：一个事务只能看见已经提交事务所做的改变。这种隔离级别 也支持所谓的不可重复读（Nonrepeatable Read），因为同一事务的其他实例在该实例处理其间可能会有新的commit，所以同一select可能返回不同结果。

三。Repeatable Read（重复读）

​		这是MySQL的默认事务隔离级别，它确保同一事务的多个实例在并发读取数据时，会看到同样的数据行。不过理论上，这会导致另一个棘手的问题：幻读 （Phantom Read）。简单的说，幻读指当用户读取某一范围的数据行时，另一个事务又在该范围内插入了新行，当用户再读取该范围的数据行时，会发现有新的“幻影” 行。InnoDB和Falcon存储引擎通过多版本并发控制（MVCC，Multiversion Concurrency Control）机制解决了该问题。

四。Serializable（串行化）

​		这是最高的隔离级别，它通过强制事务排序，使之不可能相互冲突，从而解决幻读问题。简言之，它是在每个读的数据行上加上共享锁。在这个级别，可能导致大量的超时现象和锁竞争。

五。出现的问题

这四种隔离级别采取不同的锁类型来实现，若读取的是同一个数据的话，就容易发生问题。例如：

脏读(Drity Read)：某个事务已更新一份数据，另一个事务在此时读取了同一份数据，由于某些原因，前一个RollBack了操作，则后一个事务所读取的数据就会是不正确的。

不可重复读(Non-repeatable read):在一个事务的两次查询之中数据不一致，这可能是两次查询过程中间插入了一个事务更新的原有的数据。

幻读(Phantom Read):在一个事务的两次查询中数据笔数不一致，例如有一个事务查询了几列(Row)数据，而另一个事务却在此时插入了新的几列数据，先前的事务在接下来的查询中，就会发现有几列数据是它先前所没有的。

在MySQL中，实现了这四种隔离级别，分别有可能产生问题如下所示：

![img](https://img2018.cnblogs.com/blog/1646034/201904/1646034-20190430095830286-1397235000.png)

六。事务的 四个特征（ACID）

​		事务具有四个特征：原子性（ Atomicity ）、一致性（ Consistency ）、隔离性（ Isolation ）和持续性（ Durability ）。这四个特性简称为 ACID 特性。

1 、原子性。事务是数据库的逻辑工作单位，事务中包含的各操作要么都做，要么都不做

2 、一致性。事 务执行的结果必须是使数据库从一个一致性状态变到另一个一致性状态。因此当数据库只包含成功事务提交的结果时，就说数据库处于一致性状态。如果数据库系统 运行中发生故障，有些事务尚未完成就被迫中断，这些未完成事务对数据库所做的修改有一部分已写入物理数据库，这时数据库就处于一种不正确的状态，或者说是 不一致的状态。

3 、隔离性。一个事务的执行不能其它事务干扰。即一个事务内部的操作及使用的数据对其它并发事务是隔离的，并发执行的各个事务之间不能互相干扰。

4 、持续性。也称永久性，指一个事务一旦提交，它对数据库中的数据的改变就应该是永久性的。接下来的其它操作或故障不应该对其执行结果有任何影响。



Java代码通过@transcation（[trænˈzækʃn]）注解添加事务及隔离级别。其中隔离级别通过isolation（ [ˌaɪsəˈleɪʃn]）添加，事务的传播特性通过Propagation属性添加。

isolation 默认添加DEFAULT是跟随jdbc链接走的。其有五个属性。

``` java
	/**
	使用底层数据存储的默认隔离级别。
	所有其他级别都对应于JDBC隔离级别。
	 */
	DEFAULT(TransactionDefinition.ISOLATION_DEFAULT),

	/**
	读未提交
	指示脏读、不可重复读和幻象读的常数
	可能发生。此级别允许读取由一个事务更改的行
	在提交该行中的任何更改之前的另一个事务
	（“肮脏的阅读”）。如果任何更改回滚，则第二个
	事务将检索到无效的行。
	 */
	READ_UNCOMMITTED(TransactionDefinition.ISOLATION_READ_UNCOMMITTED),

	/**
	读已提交
	表示防止脏读的常数；不可重复的读
	可能会出现幻影读取。此级别仅禁止事务
	从读取包含未提交更改的行
	 */
	READ_COMMITTED(TransactionDefinition.ISOLATION_READ_COMMITTED),

	/**
	重复度
	一个常数，指示脏读和不可重复读是
	防止；可能发生幻觉读取。此级别禁止事务
	禁止读取包含未提交更改的行，并且还禁止
	一个事务读取一行第二个事务的情况
	更改行，第一个事务重新读取行，得到
	第二次不同的值（“不可重复读取”）。
	 */
	REPEATABLE_READ(TransactionDefinition.ISOLATION_REPEATABLE_READ),

	/**
	串行化
	一个常数，指示脏读、不可重复读和幻影
	防止读取。此级别包括
	{@code ISOLATION_REPEATABLE_READ}并进一步禁止这种情况
	其中一个事务读取满足{@code where}的所有行
	条件，第二个事务插入满足以下条件的行
	{@code WHERE}条件，第一个事务将重新读取
	同样的条件，在第二次读取中检索额外的“幻影”行。
	 */
	SERIALIZABLE(TransactionDefinition.ISOLATION_SERIALIZABLE);
```

Propagation默认REQUIRED



``` java
/**
	支持当前事务，如果不存在则创建新事务。
	类似于同名的EJB事务属性。
	这是事务注释的默认设置。
	 */
	REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED),

	/**
	支持当前事务，如果不存在，则非事务性执行。
	类似于同名的EJB事务属性。
	注意：对于具有事务同步的事务管理器，
	传播支持与完全没有事务略有不同，
	因为它定义了同步将要应用的事务范围。
	因此，相同的资源（JDBC连接、Hibernate会话等）
	将在整个指定范围内共享。注意，这取决于
	事务管理器的实际同步配置。
	 */
	SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS),

	/**
	支持当前事务，如果不存在异常，则引发异常。
	类似于同名的EJB事务属性。
	 */
	MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY),

	/**
	创建一个新事务，如果当前事务存在，则暂停当前事务。
	类似于同名的EJB事务属性。
	<p><b>注意：</b>实际交易暂停不会立即生效
	在所有事务管理器上。
	 */
	REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW),

	/**
	非事务性执行，如果当前事务存在，则挂起当前事务。
	类似于同名的EJB事务属性。
	<p><b>注意：</b>实际交易暂停不会立即生效
	在所有事务管理器上。这尤其适用于
	 */
	NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED),

	/**
	以非事务方式执行，如果事务存在，则引发异常。
	类似于同名的EJB事务属性。
	 */
	NEVER(TransactionDefinition.PROPAGATION_NEVER),

	/**
	如果当前事务存在，则在嵌套事务中执行，
	表现得像其他需要的传播。在EJB中没有类似的特性。
	<p>注意：嵌套事务的实际创建只在特定的
	事务管理器。开箱即用，这只适用于JDBC
	在处理JDBC 3.0驱动程序时使用数据源TransactionManager。
	一些JTA提供者也可能支持嵌套事务。
	 */
	NESTED(TransactionDefinition.PROPAGATION_NESTED);
```

