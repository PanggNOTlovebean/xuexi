### JAVA核心技术

long int short byte 8 4 2 1 字节

char 2字节 16为Utf-16编码 表示utf-16的一个代码单元 大多数常用Unicode字符使用一个代码单元表示 辅助字符需要一对代码单元表示

所谓的输入流读nextInt,nextDouble就是按照基本数据类型规定的大小读内存中的内容

不可变字符串的优点 编译器可以共享字符串

java无重载运算符  字符串加法的重载来自于语法糖 (stringbuild+append+tostring)

使用工厂方法而不使用构造器的原因：

1. 无法命名构造器
2. 当使用构造器时，无法改变所构造的类型

同一个方法名不同参数叫重载，方法名+参数类型=方法的签名

包作用域 如果没有指定private 或 Public 可以被同一个package中的其他类访问 例如可以通过向这个包添加类来而已修改，但java.*不允许插入新的类，“包密封“机制也可以防止这一情况

jar用的就是zip格式组织文件和子目录。

可以将子类对象赋给父类变量，父类变量引用子类对象，这说明对象变量是多态的，通过该引用调用方法，如果子类覆盖（重写）（override) 过该方法，会调用子类的该方法，这叫做动态绑定，但是如果调用一个父类没有的方法，编译器报错，如果有这种需求，可以考虑将变量强制转换为子类，再调用，要提前使用Instance of判断，其次一般不这么干，更有可能的是类设计出现问题了。

父类的私有方法、私有域，子类不可访问，设置成Protect可以访问

> private 仅对本类可见
>
> public 对所有类可见
>
> Protected 对本包和所有子类可见
>
> 默认 对本包可见

Arrays.toString()

list.toArray()

Integer Double等包装类是放置parseInt 这种方法的好地方 

参数数量可变的方法 用... 接受的实际是一个数组

数组可以转化为Object类 Object类可以转化为数组 但A类数组不能转化为B类数组

接口的方法自动是public 接口的域自动是Public static final

为什么object的clone方法是protect 因为子类的数据域可能不仅仅是数据基本类型，可能包含对象的引用

Throwable派生出Error 和 Exception，Exception派生IOException,Runtime Exception

其中Error和Runtime Exception为非受查异常，IOException为受查异常

方法必须声明所有可能发生的受查异常，而对于非受查异常，Error不可控制(系统错误，内部错误），Runtime Exception（越界，空指针）应该避免发生

不能使用list<int>是因为【类型擦除】同理不能通过instancof getclass比较泛型类型，不能创建泛型数组，但声明和强制转换泛型数组是合法的，

