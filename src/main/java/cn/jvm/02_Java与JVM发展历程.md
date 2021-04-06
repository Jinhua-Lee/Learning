# 02_Java与JVM发展历程

## 一、Java发展历程

- 1990年，在Sun计算机公司，领导Green Team项目的几个人开发出新的程序语言命名为oak，后续更名为java。
- 1995年，Sun公司正式发布Java和HotJava产品，Java首次公开亮相。
- 1996年1月23日， Sun MicroSystem发布JDK 1.0.
- 1998年，JDK 1.2发布。同时发布JSP/Servlet、EJB规范，以及将Java分为JavaSE，JavaEE和JavaME，表明Java开始向企业、桌面应用、移动设备三大领域挺进。
- 2000年，JDK 1.3发布，Java Hotspot Virtual Machine正式发布，成为Java默认的虚拟机。
- 2002年，JDK 1.4发布，古老的Classic虚拟机退出。
- 2003年底，Java平台的Scala发布，同年Groovy也加入Java阵营。
- 2004年，JDK1.5发布，同时改名为JavaSE 5.0
- 2006年，JDK 6发布。同年，Java开源并建立OpenJDK，HotSpot虚拟机也成为了OpenJDK中默认虚拟机。
- 2007年，Java平台迎来新伙伴，Clojure。
- 2008年，Oracle收购了BEA，得到了JRockit虚拟机。
- 2009年，Twitter宣布把后台大部分程序从Ruby迁移到Scala，这是Java平台又一次大规模应用。
- 2010年，Oracle收购了Sun，活得Java商标最具价值的HotSpot虚拟机。此时Oracle拥有HotSpot和JRockit虚拟机，计划整合为HotRockit。
- 2011年，JDK 7发布，在JDK      1.7u4中，正式启动了新的垃圾回收器G1。
- 2014年，发布JDK 1.8，HotSpot其实是整合过的虚拟机。
- 2017年，JDK9发布。将G1设置为默认垃圾回收器，替代CMS。同年IBM的J9开源，形成了现在的Open J9社区。
- 2018年，Android的Java侵权案判决，Google赔偿Oracle 88亿美元；同年，Oracle宣布JavaEE成为历史名词，JDBC、JMS、Servlet赠予Eclipse基金会；同年，JDK11发布，LTS版本的JDK，发布革命性的ZGC，调整JDK授权许可。
- 2019年，JDK12发布，加入RedHat领导开发的shenandoah GC。

## 二、JVM发展历程

- Sun Classic VM

- - 于1996年，Java 1.0时候诞生。世界上第一款商用虚拟机。于JDK 1.4时候完全被淘汰。

  - 内部只提供解释器。

  - 如果使用JIT即使编译器，就需要进行外挂。但是一旦用了JIT编译器，JIT就会管理虚拟机的执行系统。解释器就不再工作，不能与JIT配合工作。

  - - 都进行JIT编译执行，启动时暂停时间过长。（公交车：等的时候花时间，但上去之后速度快）
    - 都进行解释执行，则热点代码每次都执行。（步行：一开始就步行。）

  - 现在的HotSpot虚拟机内置了Classic

- Exact VM

- - 为了解决上一个虚拟机的问题，JDK 1.2时候，sun提供了此虚拟机。

  - Exact Memory      Management（准确式内存管理）

  - - 又名，Non-Conservative/Accurate Memory Management；

    - 虚拟机也可以知道内存中某个位置的数据具体是什么类型。

    - - 举例：内存中1234的值，表示一个数，或是引用地址为1234的值

  - 具备现代高性能虚拟机的雏形。

  - - 热点探测。
    - 编译器与解释器混合工作模式。

  - 只在Solaris平台短暂使用，其他平台还是Classic VM。

  - - 英雄气短，终被HotSpot虚拟机替代。

- HotSpot VM

- - HotSpot历史

  - - 最初由一个名为 LongView Technologies 小公司设计。

    - 1997年，被Sun公司收购；2009年，sun公司被Oracle甲骨文收购。

    - JDK 1.3时，HotSpot成为默认虚拟机。

    - - 本课程中默认介绍的虚拟机都是HotSpot。（比如其他两个虚拟机都没有方法区的概念）

  - 目前HotSpot有绝对的市场地位。

  - - Sun/Oracle JDK或是OpenJDK
    - JDK 6或JDK 8
    - 服务器，桌面，移动端，都有嵌入式的应用

  - 名称HotSpot就代表着热点代码的探测技术。

  - - 通过计数器找到最具编译价值的代码，触发即时编译或栈上替换（某些对象可以放到栈上）；
    - 通过编译器与解释器协同工作，在最优化的程序响应时间与最佳执行性能中找到平衡。

- BEA Jrockit VM

- - 专注于服务端的应用

  - - 不关注于程序的启动速度，因此内部不包括解释器，所有代码都依靠即时编译后执行。

  - 大量的行业基准测试显示，JRockit JVM是业界最快的JVM。

  - - 使用JRockit产品，客户已体验到显著的性能提高（一些超过了70%），硬件成本减少（达50%）；

  - 优势，全面的Java运行时解决方案。

  - - JRockit面向延时敏感型的应用解决方案JRockit Real Time提供毫秒或微秒级的JVM响应时间，适合财务，军事指挥，电信网络的需要。
    - Mission Control服务套件，一组以极低开销来监控、管理和分析生产环境中的应用程序的工具。

  - 2008年，被Oracle收购

  - Oracle表达了两大优秀虚拟机的整合工作，大致在JDK 8中完成。整合方式是在HotSpot的基础上，移植了JRockit的优秀特性。

  - Java之父高斯林，目前就职于谷歌，研究人工智能和水下机器人。

- IBM J9 VM

- - 全称： IBM Technology for Java Virtual Machine，简称 IT4J，内部代号J9；
  - 市场定位与HotSpot接近，服务端，桌面应用，嵌入式等多种用途的VM；
  - 广泛应用于IBM的各种Java产品；
  - 目前是有影响力的三大商用虚拟机之一，也是号称世界最快的Java虚拟机【自己的软件优化】。
  - 2017年左右，IBM发布了开源J9 VM，命名OpenJ9，交给Eclipse基金会管理，称Eclipse OpenJ9

- KVM和CDC/CLDC HotSpot

- - Oracle在Java ME产品线上的两款虚拟机 CDC/CLDC HotSpot Implementation VM

  - KVM（kilobyte）是CLDC-HI的早期产品

  - 目前移动领域地位尴尬，被IOS和Android二分天下，早期在Symbian（塞班）。

  - KVM简单，轻量级，高度可移植。面向更低端的设备害维持着自己的一片市场。

  - - 智能控制器，传感器；
    - 老人手机，经济欠发达地区的功能手机。

  - 所有虚拟机的原则，一次编译，到处运行。

- Azul VM

- - 前面三大高性能虚拟机使用在通用硬件平台上。

  - 这里的Azul VM和BEA Liquid VM是与特定硬件平台绑定、特定软件配合的专有虚拟机

  - - 高性能Java虚拟机中的战斗机。

  - Azul VM是Azul Systems公司在HotSpot上进行大量改进，运行在其公司内部的专有硬件Vega系统的Java虚拟机；

  - 每个Azul VM实例都可以管理至少数十个CPU和数百GB内存的硬件资源，并提供巨大内存范围内实现可控的GC时间的垃圾回收器，专有硬件优化的线程调度等优秀特性。

  - 2010年，Azul Systems公司开始由硬件转向软件，发布了自己的Zing JVM，可以在通用的x86平台上提供接近于vega系统的特性。

- BEA Liquid VM

- - 高性能虚拟机中的战斗机；
  - 直接运行在自己的Hypervisor虚拟机上；
  - Liquid VM，即现在的JRockit VE（virtual Edition）。本身不需要操作系统支持，或者说它本身实现了一个专用操作系统的必要功能，如线程调度，文件系统，网络支持等功能。
  - 随着JRockit VM终止开发，Liquid项目也停止了。

- Apache Harmony

- - Apache曾退出过JDK 1.5 与JDK 1.6兼容的Java运行平台Apache Harmony虚拟机；
  - IBM与Intel联合开发的开源JVM，受到同样开源的OpenJDK的压制，Sun坚决不让Harmony获得JCP认证，最终于2011年退役，IBM转而参与OpenJDK；
  - 类库代码吸纳进入了Android SDK。

- MicroSoft JVM

- - 微软为了在IE 3浏览器中支持Java Applets，开发MicroSoft JVM
  - 只能在Windows平台下运行。确实是Windows平台下最好的虚拟机。
  - 1997 年，Sun公司以侵犯商标，不正当罪名指控微软成功，微软赔了Sun很多钱，微软在Windows XP SP3中抹掉该 JVM。

- Taobao JVM

- - 由Ali JVM团队发布，阿里巴巴是国内使用Java最强大的公司。

  - 覆盖云计算、金融、物流、电商等众多领域，需要解决高并发，高可用，分布式的符合问题，由大量的开源产品；

  - 基于Open JDK HotSpot VM发布的国内第一个优化，深度定制且开源的服务器版Java虚拟机。

  - - 创新的GCIH（GC invisible Heap）技术实现了off-heap，将生命周期较长的Java对象从heap中移到了heap之外，并且GC不能管理GCIH内部的Java对象，以此达到降低GC回收频率和提升GC回收效率的目的。
    - GCIH中的对象能在多个Java虚拟机中实现共享。
    - 使用crc 32指令实现JVM intrinsic降低JNI调用开销。
    - PMU Hardware的Java profiling tool和诊断协调功能。
    - 针对大多数场景的ZenGC。

  - Taobao VM应用在阿里产品上性能搞，严重依赖IntelCPU，损失兼容性来提高性能。

  - - 目前在淘宝，天猫上线，把Oracle官方的JVM全替换了。

- Davlik VM（非Java VM）

- - 谷歌开发，应用于Android，并在Android 2.2中提供JIT

  - 未遵循Java虚拟机规范。

  - 不能直接执行Java的class文件。

  - 基于寄存器的架构，不是jvm的栈架构

  - 执行编译后的dex（Davlik Executable）文件，执行效率比较高

  - - 可以通过class文件转化而来，使用Java语法编写应用程序，直接使用大部分的Java API。

  - Android 5.0使用支持提前编译（Ahead Of Time Compilation，AOT）的ART VM替换了Davlik VM。

- Graal VM（Oracle Labs公司）

- - Run Programs Faster Anywhere.

  - 在HotSpot VM基础上增强，跨语言的全栈虚拟机，包括（Java, Kotlin, Scala, Groovy, C, C++, JavaScript, Ruby, Python, R等）

  - - 支持不同语言混用对方的接口和对象。
    - 支持这些语言使用已编译好的本地库文件。

  - 工作原理是将这些语言的源代码或编译后的中间格式，通过解释器转换为能被Graal接受的中间表示。Graal VM提供Truffle工具集快速构建面向一种新语言的解释器，在运行时还能进行即使编译优化。获得比原生编译器更优秀的执行效率。

  - 如果说HotSpot有一天终将被取代，那么Graal VM希望最大，但是Java的软件生态没有丝毫变化。