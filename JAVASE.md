# JAVASE

## 反射

> 万物皆对象
>
> 由于任何一个类  均有 属性、方法、构造器等 因此可以将其抽象 形成Class类
>
> Class类的对象是 具体类的字节码信息 得到了Class类的对象 就可以获取对象内容：类的属性、方法、构造器、注解、泛型等

为啥要用反射创建对象？ 当运行过程中才能确定具体的类，这时需要用反射去创建对象 

反射是否破坏了面向对象的封装性？  封装：隐藏内部实现，只提供对外接口，包括方法和属性  反射如果设置setAccessible(true) 将关闭安全检查 能够提升反射速度 但破坏了面向对象的封装性 

JAVA反射机制是在**运行状态**中，我们通过该机制知道任意一个类的方法、属性，也可以进行调用、更改。

这种动态获取信息以及动态调用对象的方法称为java语言的反射机制。

在编译器把源代码编译成.class文件后，类加载器加载class文件到jvm中，内存中会创建一个Class类的实例对象，这个对象包含着这个class文件的字节码信息，这个对象就是程序访问方法区中这个 类 的各种数据的外部接口。

### 获取 字节码信息/class对象 的四种方式

```java
//方式1 getClass()方法
Person p=new Person();
Class c1= p.getClass();
//方式2 内置class属性
Class c2 = Person.class;
//方式1 方式2一般不用 都有类了 还要class干啥


//方式3 用的最多 调用class类提供的静态方法
Class c3 = Class.forName("com.lipan.test02.Person");
//方式4 利用类加载器
ClassLoader loader = Test.class.getClassLoader();
Class c4=loader.loadClass("com.lipan.test02.Person");
```

### Class类的具体实例

外部类、内部类、接口、注解、数组、基本数据类型、void

## 注解

就是特么修饰一下 到时候好利用反射机制查看我修饰了些啥

元注解 @ Retention 注解生命周期

@Target 能在什么上挂注解

@Inherited  父类被注解了子类是否也带该注解

## 枚举

类实例能被一一列举出来 比如春夏秋冬、男女这些

实际上类似于语法糖在类中提供几个new好的public static final成员

 Enum 写的自定义枚举类 继承java.lang.Enum 他内部的有个name ordinal属性 就是这个枚举类实例的名字（大写的那个东西）还有序号

 当然枚举类也可以有自己的方法、属性 以及实现接口 需要在每个枚举里面都写接口的实现

H
