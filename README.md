### IO流的特点
    IO的特点：
    1、IO流用来处理设备之间的数据传输；
    2、Java对数据的操作是通过流（系统资源）的方式；
    3、Java用来操作流的对象都在java.io包中；
    4、流按操作数据分为两种：字节流和字符流；
    5、流按流向分为：输入流和输出流
    
 流即系统资源，Windows系统本身就可以操作设备，各种语言只是使用了系统平台上的这个资源，并对外提供了各种语言自己的操作功能，这些功能
 最终调用的系统资源，当我们使用完资源后一定要记住释放。
 
### IO流的三种分类方式
    
### 引导Bootstrap、内置通信传输模式及ChannelOption
    1、ChannelOption 可以用来给 Channel来设置参数的，该ChannelOption参数其实为TCP/IP相关的参数，其常用的参数类型如下：
    
#### ChannelOption.SO_BACKLOG
    对应的是TCP/IP协议listen函数中的backlog参数，函数listen(int socketfd, int backlog) 用来初始化服务端可连接队列
    
    服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求
    放在队列中等待处理，backlog参数指定了队列的大小。
    
#### ChannelOption.SO_REUSEADDR
    对应于套接字选项中的SO_REUSEADDR, 这个参数表示允许重复使用本地地址和端口
    
    比如，某个服务器进程占用了TCP的80端口进行监听，此时再次监听该端口就会返回错误，使用该参数就可以解决问题，该参数允许工用该端口。
    这个在服务器程序中比较常使用，比如某个进程非正常退出，该程序占用的端口可能要被占用一段时间才能允许其他进程使用，而且程序死掉以后，内核需要一定的时间才能够释放此端口，不设置SO_REUSEADDR就无法正常使用该端口。
    
#### ChannelOption.SO_KEEPALIVE
    Channeloption.SO_KEEPALIVE 参数对应于套接字选项中的SO_KEEPALIVE，该参数用于设置TCP连接，当设置该选项以后，连接会测试链接的状态，这个选项用于可能长时间没有数据交流的连接。
    当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文。
    
#### ChannelOption.SO_SNDBUF 和 ChannelOption.SO_RCVBUF
    ChannelOption.SO_SNDBUF 参数对应于套接字选项中的SO_SNDBUF，ChannelOption.SO_RCVBUF参数对应于套接字选项中的SO_RCVBUF这两个参数用于操作接收缓冲区和发送缓冲区的大小
    
    接收缓冲区用于保存网络协议站内收到的数据，直到应用程序读取成功，发送缓冲区用于保存发送数据，直到发送成功。

#### ChannelOption.SO_LINGER
    ChannelOption.SO_LINGER 参数对应于套接字选项中的SO_LINGER，Linux内核默认的处理方式是当用户调用close() 方法的时候，函数返回，
    在可能的情况下，尽量发送数据，不一定保证会发送剩余的数据，造成了数据的不确定性，使用SO_LINGER可以阻塞close()的调用时间，直到数据完全发送。

#### ChannelOption.TCP_NODELAY
    ChannelOption.TCP_NODELAY 参数对应于套接字选项中的TCP_NODELAY，该参数的使用与Nagle算法有关，Nagle算法是将小的数据包组装为更大的帧然后进行发送，
    而不是输入一次发送一次，因此在数据包不足的时候会等待其他数据的到了，组装成大的数据包进行发送，虽然该方式有效提高网络的有效负载，但是却造成了延时，而该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输，于TCP_NODELAY相对应的是TCP_CORK，
    该选项是需要等到发送的数据量最大的时候，一次性发送数据，适用于文件传输。


### Netty 组建
    1、EventLoop
#### 2、Channel
    * close() 可以用来关闭channel
    * closeFuture() 用来处理channel的关闭
    * sync 方法作用是同步等待channel关闭
    * addListener方法是异步等待channel关闭
    * pipeline() 方法添加处理器
    * write() 方法将数据写入
    * writeAndFlush 方法将数据写入并刷出
    
    3、Future & Promise
    4、Handler & Pipline
    5、ByteBuf