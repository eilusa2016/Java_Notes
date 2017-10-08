Spring将事务管理分成了两类:

	* 编程式事务管理
		* 手动编写代码进行事务管理.(很少使用)
	* 声明式事务管理:
		* 基于TransactionProxyFactoryBean的方式.(很少使用)
			* 需要为每个进行事务管理的类,配置一个TransactionProxyFactoryBean进行增强.
		* 基于AspectJ的XML方式.(经常使用)
			* 一旦配置好之后,类上不需要添加任何东西
		* 基于注解方式.(经常使用)
			* 配置简单,需要在业务层上添加一个@Transactional的注解.

事务是逻辑上的一组操作，这组操作要么全部成功，要么全部失败，最为典型的就是银行转账的案例：

A要向B转账，现在A，B各自账户中有1000元，A要给B转200元，那么这个转账就必须保证是一个事务，防止中途因为各种原因导致A账户资金减少而B账户资金未添加，或者B账户资金添加而A账户资金未减少，这样不是用户有损失就是银行有损失，为了保证转账前后的一致性就必须保证转账操作是一个事务。

事务具有的ACID特性，参考[wikipedia](https://zh.wikipedia.org/wiki/ACID)。

首先，这篇文章先提及一些Spring中事务有关的API，然后分别实现编程式事务管理和声明式事务管理，其中声明式事务管理分别使用基于`TransactionProxyFactoryBean`的方式、基于AspectJ的XML方式、基于注解方式进行实现。

首先，我们简单看一下Spring事务管理需要提及的接口，Spring事务管理高层抽象主要包括3个接口

`PlatformTransactionManager ` :事务管理器(用来管理事务，包含事务的提交，回滚)
`TransactionDefinition`       :事务定义信息(隔离，传播，超时，只读)
`TransactionStatus`           :事务具体运行状态

Spring根据事务定义信息(TransactionDefinition)由平台事务管理器(PlatformTransactionManager)真正进行事务的管理，在进行事务管理的过程中，事务会产生运行状态，状态保存在TransactionStatus中

**PlatformTransactionManager**:

Spring为不同的持久化框架提供了不同的PlatformTransactionManager如:
    在使用Spring JDBC或iBatis进行持久化数据时，采用DataSourceTransactionManager
    在使用Hibernate进行持久化数据时使用HibernateTransactionManager

**TransactionDefinition**:

`TransactionDefinition`接口中定义了一组常量，包括事务的隔离级别，事务的传播行为，超时信息，其中还定义了一些方法，可获得事务的隔离级别，超时信息，是否只读。

传播行为**主要解决**业务层方法之间的相互调用产生的事务应该如何传递的问题。

`TransactionDefinition`中定义的属性常量如下：

|Field(属性)|Description(描述)|
|----|----|
|ISOLATION_DEFAULT|使用底层数据存储的默认隔离级别|
|ISOLATION_READ_COMMITTED|表示防止脏读;可能会发生不可重复的读取和幻像读取|
|ISOLATION_READ_UNCOMMITTED|表示可能会发生脏读，不可重复的读取和幻像读取|
|ISOLATION_REPEATABLE_READ|表示禁止脏读和不可重复读;可以发生幻影读取|
|ISOLATION_SERIALIZABLE|表示可以防止脏读，不可重复的读取和幻像读取|
|PROPAGATION_MANDATORY|支持当前交易;如果不存在当前事务，则抛出异常|
|**PROPAGATION_NESTED**|如果当前事务存在，则在嵌套事务中执行，其行为类似于PROPAGATION_REQUIRED|
|PROPAGATION_NEVER|不支持当前交易;如果当前事务存在，则抛出异常|
|PROPAGATION_NOT_SUPPORTED|不支持当前交易;而是总是非事务地执行|
|**PROPAGATION_REQUIRED**|支持当前交易;如果不存在，创建一个新的|
|**PROPAGATION_REQUIRES_NEW**|创建一个新的事务，挂起当前事务（如果存在）|
|PROPAGATION_SUPPORTS|支持当前交易;如果不存在，则执行非事务性的|
|TIMEOUT_DEFAULT|使用底层事务系统的默认超时，如果不支持超时，则为none|

TransationStatus:

在该接口中提供了一些方法:

|Method|Description|
|----|----|
|flush()|将基础会话刷新到数据存储（如果适用）：例如，所有受影响的Hibernate / JPA会话|
|hasSavepoint()|返回此事务是否内部携带保存点，也就是基于保存点创建为嵌套事务|
|isCompleted()|返回此事务是否完成，即是否已经提交或回滚|
|isNewTransaction()|返回当前交易是否是新的（否则首先参与现有交易，或者潜在地不会在实际交易中运行）|
|isRollbackOnly()|返回事务是否已被标记为仅回滚（由应用程序或由事务基础结构）|
|setRollbackOnly()|设置事务回滚|


