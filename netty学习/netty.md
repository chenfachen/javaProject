https://www.bilibili.com/video/BV1Vr4y1T7SZ?p=7&spm_id_from=pageDriver 

1. 没写客户端，想要测试端口服务时可以用telnet或者curl

**BIO编写Client/Server通信优缺点分析：**

​	优点：

​		模型简单

​		编码简单

​	缺点：

​		性能瓶颈，请求数和线程数 N ：N关系

​		高并发情况下，CPU切换线程上下文损耗大

​	案例：web服务器Tomcat7之前，都是使用BIO，7之后就使用NIO

​	改进：伪NIO，使用线程池去处理

**服务端网络编程常见网络IO模型讲解**

1、最通俗的方式讲解什么是阻塞/非阻塞，什么是同/异步
洗衣机洗衣服
洗衣机洗衣服（无论阻塞式IO还是非阻塞式IO，都是同步IO模型)

同步阻塞:你把衣服丢到洗衣机洗，然后看着洗衣机洗完，洗好后再去晾衣服（你就干等，啥都不做，阻塞在那边)

同步非阻塞:你把衣服丢到洗衣机洗，然后会客厅做其他事情，定时去阳台看洗衣机是不是洗完了，洗好后再去晾衣服(等待期间你可以做其他事情，比如用电脑打开小D课堂看视频学习)

异步阻塞:你把衣服丢到洗衣机洗，然后看着洗衣机洗完，洗好后再去晾衣服（几乎没这个情况，几乎没这个说法，可以忽略)

异步非阻塞:你把衣服丢到洗衣机洗，然后会客厅做其他事情,洗衣机洗好后会自动去晾衣服，晾完成后放个音乐告诉你洗好衣服并晾好了

**Linux网络编程中的五种I/O模型讲解**

网络I/O，用户程序和内核的交互为基础进行讲解

I/O操作分为两步，发起IO请求等待数据准备（洗衣服），实际IO操作，将数据从内核拷贝到进程中【用户空间】（晾衣服）

同步必须要主动读写数据，在读写数据的过程中还是会阻塞（好比晾衣服阻塞了你）

异步仅仅需要I/O操作完毕的通知。并不主动读写数据，由操作系统内核完毕数据的读写（机器人帮你晾衣服）

五种IO的模型：阻塞IO、非阻塞IO、多路复用IO、信号驱动IO和异步IO

前四种都是同步IO，在内核数据copy到用户空间时都是阻塞的（好比晾衣服阻塞了你）

1）阻塞IO

<img src="C:\Users\chenfa\AppData\Roaming\Typora\typora-user-images\image-20210507125703274.png" alt="image-20210507125703274" style="zoom: 33%;" />

2）非阻塞式I/O，拷贝数据时同步等待

<img src="C:\Users\chenfa\AppData\Roaming\Typora\typora-user-images\image-20210507130026075.png" alt="image-20210507130026075" style="zoom:33%;" />

3）多路复用IO（select、poll、epoll……）（一个线程处理多个连接/请求）

I/0多路复用是阻塞在select,epoll这样的系统调用，没有阻塞在真正的I/0系统调用如recvfrom进程受阻于select,等待可能多个套接口中的任一个变为可读
I0多路复用使用两个系统调用(select和recvfrom)
blocking 1O只调用了一个系统调用(recvfrom)
select/epoll
核心是可以同时处理多个connection，而不是更快，所以连接数不高的话，性能不一定比多线程+阻塞IO好多路复用模型中，每一个socket，设置为non-blocking,
阻塞是被select这个函数block，而不是被socket阻塞的

5）异步IO（POSIX的aio_系列函数）	Future-Listener机制

<img src="C:\Users\chenfa\AppData\Roaming\Typora\typora-user-images\image-20210507131542503.png" alt="image-20210507131542503" style="zoom:33%;" />

几个核心点：

​		阻塞非阻塞说的是线程的状态，同步和异步说的是消息的通知机制

​		同步需要主动读写数据，异步是不需要主动读写数据

**高并发编程必备知识IO多路复用技术select、poll讲解**

select:
基本原理:
监视文件3类描述符:writefds、readfds、和exceptfds
调用后select函数会阻塞住，等有数据可读、可写、出异常或者超时就会返回
select函数正常返回后，通过遍历fdset整个数组才能发现哪些句柄发生了事件，来找到就绪的描述符fd，然后进行对应的IO操作
几乎在所有的平台上支持，跨平台支持性好
缺点︰
1) select采用轮询的方式扫描文件描述符，全部扫描，随着文件描述符FD数量增多而性能下降
2）每次调用select()，需要把 fd集合从用户态拷贝到内核态，并进行遍历(消息传递都是从内核到用户空间)
3)最大的缺陷就是单个进程打开的FD有限制，默认是1024(可修改宏定义，但是效率仍然慢)
static final int MAX_FD = 1024

poll:
基本流程:
select()和 poll()系统调用的大体一样，处理多个描述符也是使用轮询的方式，根据描述符的状态进行处理一样需要把fd集合从用户态拷贝到内核态，并进行遍历
最大区别是:poll没有最大文件描述符限制（使用链表的方式存储fd)

epoll:
基本原理:
在2.6内核中提出的，对比select和poll，epoll更加灵活，没有描述符限制，用户态拷贝到内核态只需要一次使用事件通知，通过epoll_ctl注册fd，一旦该fd就绪，内核就会采用callback的回调机制来激活对应的fd
优点:
1)没fd这个限制，所支持的FD上限是操作系统的最大文件句柄数，1G内存大概支持10万个句柄。
2)效率提高，使用回调通知而不是轮询的方式，不会随着FD数目的增加效率下降
3)通过callback机制通知，内核和用户空间mmap同一块内存实现



**大话Netty线程模型和Reactor模式**

很多人说netty是基于JAVA NIO类库实现的异步通讯框架，特点：异步非阻塞、基于事件驱动、性能高，高可靠性和高可定制性

Reactor单线程模型(比较少用)
内容:
1)作为NIO服务端，接收客户端的TCP连接;作为NIO客户端，向服务端发起TCP连接;2)服务端读请求数据并响应;客户端写请求并读取响应
使用场景:
对应小业务则适合，编码简单;对于高负载、大并发的应用场景不适合，一个NIO线程处理太多请求，则负载过高，并且可能响应变慢，导致大量请求超时，而且万一线程挂了，则不可用了

Reactor多线程模型
内容:
1)一个Acceptor线程，一组NIO线程，一般是使用自带的线程池，包含一个任务队列和多个可用的线程
使用场景:
可满足大多数场景，但是当Acceptor需要做复杂操作的时候，比如认证等耗时操作，再高并发情况下则也会有性能问题

**Reactor主从线程模型**
内容:
1) Acceptor不在是一个线程，而是一组NIO线程;IO线程也是一组NIO线程，这样就是两个线程池去处理接入连接和处理IO
使用场景:
满足目前的大部分场景，也是Netty推荐使用的线程模型

BossGroup

WorkGroup



**高并发网络编程Netty的第一个案例**

1. 讲解什么是Echo服务和Netty项目搭建



4、Netty实战之Echo服务演示和整个流程分析
简介:分析整个Echo服务各个组件名称和作用
1) EventLoop和EventLoopGroup  //线程和线程组
2) Bootstrapt启动引导类
3)Channel生命周期，状态变化    //一个socket长连接
4)ChannelHandler和ChannelPipline