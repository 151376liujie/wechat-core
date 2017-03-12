# wechat-core
    一款轻量级的微信消息处理框架
## 项目介绍
1. 封装了微信消息接收与发送，可以使用**注解驱动**开发，方便的进行业务开发而不需要再关注消息接收和消息发送的细节。

2. 内部封装了消息处理器抽象类，该类提供处理消息前的消息过滤、默认行为日志等功能，用户可继承该类实现自己的业务。

3. 只需加入注解就可将处理各个类型的消息处理器分开了,更支持将不同事件类型的消息处理器分开，避免了在处理业务逻辑的代码中使用大段的if elseif elseif 来判断消息得类型的方式。每个接口职责清晰明了，实现更解耦；

## 所用技术介绍
1. 使用Java语言，集成了springMVC 和maven，没有使用什么高大上得技术，代码中的注释也很详细，相信读者朋友能很容易看得懂。

## 项目架构剖析
![微信消息接收与响应示意图](微信消息接收与响应示意图.png)

## 快速入门（不含微信公众平台申请及接口url、token等配置步骤）

1. 在classpath根路径下创建**wechat.properties**属性文件，配置appId、appsecret、token、encodingAESKey(**名字必须跟这个一样**)，
   示例如下图：![wechat.properties配置文件示例](wechat.properties配置文件示例.png)

2. 编写消息处理器类，继承**AbstractMessageHandler**抽象类，实现**doHandleMessage**方法，在该类上加上@**MessageProcessor**的注解，并指明要处理的消息类型，属性messageType指明要处理得消息类型，eventType指明要处理得事件类型。当消息类型是普通消息时，eventType属性可不用指定（即使指定也无效）
示例代码如下：     
    
    * 基本消息类型的处理（文本消息处理器）         
    ```java
    @Component
    @MessageProcessor(messageType = MessageType.TEXT_MESSAGE)    
    public class TextMessageHandlerExample extends AbstractMessageHandler {    
        private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandlerExample.class);    
    
        public BaseResponseMessage doHandleMessage(BaseRequestMessage requestMessage) {    
                //在这里实现你自己的业务逻辑    
            return MessageUtils.buildTextResponseMessage(requestMessage, "hello,world");     
        }    
    }    
    ```
    * 关注事件类型的消息处理         
    ```java
    @Component
    @MessageProcessor(messageType = MessageType.EVENT,eventType = EventType.EVENT_SUBSCRIBE)    
    public class SubscribeEventMessageHandlerExample extends AbstractMessageHandler {    
    
        private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeEventMessageHandlerExample.class);
    
        @Autowired
        private WechatUserService wechatUserService;
    
        @Override
        public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
            //在这里实现你自己的业务逻辑
            SubOrUnSubEventRequestMessage subOrUnSubEventRequestMessage = (SubOrUnSubEventRequestMessage) baseRequestMessage;
        }
    }
   ```      
3. just run your application !! have fun...

## 示例代码

**各种消息类型的接收与回复的示例代码链接如下，供读者朋友们参考**

* 基本消息类型
    * [文本消息处理器示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example/TextMessageHandlerExample.java)
    * [图片消息处理器示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example/ImageMessageHandlerExample.java)
    * [链接消息处理器示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example/LinkMessageHandlerExample.java)
    * [语音消息处理示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example/VoiceMessageHandlerExample.java)
    * [短视频消息处理示例代码](https://github.com/151376liujie/wechat-core/blob/master/src/main/java/com/jonnyliu/proj/wechat/example/ShortVideoMessageHandlerExample.java)
    * [视频消息处理示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example/VideoMessageHandlerExample.java)
    * [地理位置消息处理示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example/LocationMessageHandlerExample.java)

* 事件消息类型
    * [关注事件消息处理器示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example/SubscribeEventMessageHandlerExample.java)
    * [取消关注事件消息处理器示例代码](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/example/UnSubscribeEventHandlerExample.java)
    * [上传地理位置事件消息处理器示例代码](https://github.com/151376liujie/wechat-core/blob/master/src/main/java/com/jonnyliu/proj/wechat/example/UploadLocationEventHandlerExample.java)
    * [扫描二维码事件消息处理器示例代码](https://github.com/151376liujie/wechat-core/blob/master/src/main/java/com/jonnyliu/proj/wechat/example/ScanWithParameterEventHandlerExample.java)
    * [自定义菜单跳转事件消息处理器示例代码](https://github.com/151376liujie/wechat-core/blob/master/src/main/java/com/jonnyliu/proj/wechat/example/CustomMenuViewEventHandlerExample.java)
    * [自定义菜单点击事件消息处理器示例代码](https://github.com/151376liujie/wechat-core/blob/master/src/main/java/com/jonnyliu/proj/wechat/example/CustomMenuClickEventHandlerExample.java)

## 已实现的功能列表
1. 对基本消息类型（文本、图片、音频、视频、位置、短视频、链接消息）和事件消息（关注、取消关注、上传地理位置、扫描二维码）的接收和响应的封装。
2. 对access_token的封装,请参考[AccessTokenService](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/service/accesstoken/)。
3. 整个用户接口API的封装,请参考[WechatUserService](https://github.com/151376liujie/wechat-core/tree/master/src/main/java/com/jonnyliu/proj/wechat/service/user)
4. **下一步准备做自定义菜单、redis实现accesstoken存储**

## 联系作者
邮箱：980463316@qq.com ,欢迎提bug和建议。

## 求职
在线简历：https://151376liujie.github.io/resume/
