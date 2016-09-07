# wechat-core

##项目介绍
1. 封装了微信消息接收与发送模块，可以使用注解方便的进行业务开发而不用关注消息接收和消息发送的细节。

2. 内部封装了消息处理器抽象类，该类提供处理消息前的消息过滤、默认行为日志等功能，用户可继承该类实现自己的业务。

3. 将处理各个类型的消息处理器分开了,更支持将不同事件类型的消息处理器分开，只需在MessageWorker注解中加入eventType属性指明你要处理的事件类型即可，避免大段的if elseif elseif，每个接口职责清晰明了，实现更解耦；

##所用技术介绍
1. 所用编程语言为java，代码中的注释非常详细，相信读者一定能看懂。目前只集成了springmvc，后续可能会增加对其他组件的支持。

##项目架构剖析
![微信消息接收与响应示意图](微信消息接收与响应示意图.png)

##快速入门（不含微信公众平台申请及接口url、token等配置步骤）

1. 在classpath根路径下创建**wechat.properties**属性文件，配置appId、appsecret、token、encodingAESKey(**名字必须跟这个一样**)，
   示例如下图：![wechat.properties配置文件示例](wechat.properties配置文件示例.png)

2. 编写消息处理器类，继承**AbstractMessageHandler**抽象类，实现**doHandleMessage**方法，在该类上加上@**MessageWorker**的注解，并指明要处理的消息类型，
示例代码如下：     
    //基本消息类型的处理         
    
    @MessageWorker(messageType = MessageType.TEXT_MESSAGE)    
    public class TextMessageHandler extends AbstractMessageHandler {    
        private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandler.class);    
    
        public BaseResponseMessage doHandleMessage(BaseRequestMessage requestMessage) {    
                //在这里实现你自己的业务逻辑    
            return MessageUtils.buildTextResponseMessage(requestMessage, "hello,world");     
        }    
    }    
    
    //事件类型的消息处理         
    
    @MessageWorker(messageType = MessageType.EVENT,eventType = EventType.EVENT_SUBSCRIBE)    
    public class EventMessageHandlerExample extends AbstractMessageHandler {    
    
        private static final Logger LOGGER = LoggerFactory.getLogger(EventMessageHandlerExample.class);
    
        @Autowired
        private WechatUserService wechatUserService;
    
        @Override
        public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        
        }
         
3. just run your application !! have fun...

##示例代码***

1. **各种消息类型的接收与回复的示例代码放在[示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example)包下，供读者朋友们参考**

##已实现的功能列表
1. 对基本消息类型（文本、图片、音频、视频、位置、短视频、链接消息）和事件消息（关注、取消关注、上传地理位置、扫描二维码）的接收和响应的封装。
2. 对access_token的封装。
3. 整个用户接口API
4. **下一步准备做账号管理**
   
##不足之处
   
1. 目前的功能还不够丰富，目前正在逐步追加中。

##联系作者
邮箱：980463316@qq.com ,欢迎相互交流，共同进步。




