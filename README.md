# wechat-core

##项目介绍
1. 封装了微信消息接收与发送模块，可以使用注解方便的进行业务开发而不用关注消息接收和消息发送的细节。

2. 内部封装了消息处理器抽象类，该类提供处理消息前的消息过滤、默认行为日志等功能，用户可继承该类实现自己的业务。

3. 将处理各个类型的消息处理器分开了，避免大段的if elseif elseif，每个接口职责清晰明了，实现更解耦；
  
##所用技术介绍
1. 所用编程语言为java，代码中的注释非常详细，详细读者一定能看懂。目前只集成了springmvc，后续可能会增加对其他组件的支持。

##项目架构剖析
![微信消息接收与响应示意图](微信消息接收与响应示意图.png)

##快速入门（不含微信公众平台申请及接口url、token等配置步骤）

1. 在项目类路径下创建**wechat.properties**属性文件，配置token;

2. 在**applicationContext.xml**中将**wechat.properties**属性文件注入到spring中,代码如下：   

    ```<util:properties id="wechatProperty" location="classpath:wechat.properties" />```

3. 编写消息处理器类，继承**AbstractMessageHandler**抽象类，实现**doHandleMessage**方法，在该类上加上@**MessageWorker**的注解，并指明要处理的消息类型，
示例代码如下：     
    
    ```@MessageWorker(type = MessageType.TEXT_MESSAGE)    
        public class TextMessageHandler extends AbstractMessageHandler {    
            private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandler.class);    
    
            public BaseResponseMessage doHandleMessage(BaseRequestMessage requestMessage) {    
                if (requestMessage instanceof TextRequestMessage) {    
                    //在这里实现你自己的业务逻辑    
                    return MessageUtils.buildTextResponseMessage(requestMessage, "hello,world");    
                }    
                return null;    
            }    
        }   
         
4. just run your application !! have fun...

##TODO

1. 各个消息类型接收与回复实例代码

##联系作者
邮箱：980463316@qq.com ,欢迎相互交流，共同进步。




