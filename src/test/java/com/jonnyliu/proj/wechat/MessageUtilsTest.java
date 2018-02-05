package com.jonnyliu.proj.wechat;

import com.jonnyliu.proj.wechat.message.request.PhotoMenuEventRequestMessage;
import com.jonnyliu.proj.wechat.message.request.TextRequestMessage;
import com.jonnyliu.proj.wechat.message.request.VoiceRequestMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.junit.Test;

/**
 * Author: jonny
 * Time: 2018-02-04 23:28.
 */
public class MessageUtilsTest {

    @Test
    public void xml2Object() {

//        String xml = "<xml><toUserName><![CDATA[gh_e136c6e50636]]></toUserName>\n" +
//                "<fromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></fromUserName>\n" +
//                "<createTime>1408090816</createTime>\n" +
//                "<msgType><![CDATA[event]]></msgType>\n" +
//                "<Event><![CDATA[pic_weixin]]></Event>\n" +
//                "<EventKey><![CDATA[6]]></EventKey>\n" +
//                "<SendPicsInfo>\n" +
//                "\t<Count>2</Count>\n" +
//                "\t<PicList>\n" +
//                "\t\t<item>\n" +
//                "\t\t\t<PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum>\n" +
//                "\t\t</item>\n" +
//                "\t\t<item>\n" +
//                "\t\t\t<PicMd5Sum><![CDATA[5a75aaca956d97fdgawrfsfsdfgsdfhs]]></PicMd5Sum>\n" +
//                "\t\t</item>\n" +
//                "\t</PicList>\n" +
//                "</SendPicsInfo>\n" +
//                "</xml>";
//        PhotoMenuEventRequestMessage requestMessage = MessageUtils.xml2Message(xml, PhotoMenuEventRequestMessage.class);
//        System.out.println(requestMessage);


        String xml3 = "<xml><toUserName><![CDATA[gh_60374b838090]]></toUserName>\n" +
                "<fromUserName><![CDATA[oHcaSt095yszSEw3dDSPBHpePfXo]]></fromUserName>\n" +
                "<createTime>1517759459</createTime>\n" +
                "<msgType><![CDATA[voice]]></msgType>\n" +
                "<MediaId><![CDATA[vaigysI1e1vAD9GGJ2CCvfnUk9IHkoXSu2aBENqskKHldFx0mOLCG_GNCW0_ZLSb]]></MediaId>\n" +
                "<Format><![CDATA[amr]]></Format>\n" +
                "<MsgId>6518727239599652864</MsgId>\n" +
                "<Recognition><![CDATA[hello。]]></Recognition>\n" +
                "</xml>";

        VoiceRequestMessage voiceRequestMessage = MessageUtils.xml2Message(xml3, VoiceRequestMessage.class);
        System.out.println(voiceRequestMessage);


        String xml2 = "<xml><toUserName><![CDATA[gh_60374b838090]]></toUserName>\n" +
                "<fromUserName><![CDATA[oHcaSt095yszSEw3dDSPBHpePfXo]]></fromUserName>\n" +
                "<createTime>1517758841</createTime>\n" +
                "<msgType><![CDATA[event]]></msgType>\n" +
                "<Event><![CDATA[pic_weixin]]></Event>\n" +
                "<eventKey><![CDATA[pic_weixin]]></eventKey>\n" +
                "<SendPicsInfo><Count>0</Count>\n" +
                "<PicList></PicList>\n" +
                "</SendPicsInfo>\n" +
                "</xml>";
        PhotoMenuEventRequestMessage message = MessageUtils.xml2Message(xml2, PhotoMenuEventRequestMessage.class);
        System.out.println(message);


        String xml4 = "<xml>" +
                "       <ToUserName><![CDATA[toUser]]></ToUserName>" +
                "       <FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "       <CreateTime>1348831860</CreateTime>" +
                "       <MsgType><![CDATA[text]]></MsgType>" +
                "       <Content><![CDATA[this is a test]]></Content>" +
                "       <MsgId>1234567890123456</MsgId>" +
                "       </xml>";

        TextRequestMessage textRequestMessage = MessageUtils.xml2Message(xml4, TextRequestMessage.class);
        System.out.println(textRequestMessage);

        textRequestMessage.setContent("hello,world");
        System.out.println(MessageUtils.toXml(textRequestMessage));
    }
}
