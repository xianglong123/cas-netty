package com.cas.protocol;

import com.cas.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 11:07 下午
 * @desc
 */
@ChannelHandler.Sharable
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {

    private static final Logger log = LoggerFactory.getLogger(MessageCodecSharable.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> outObj) throws Exception {
        ByteBuf out = ctx.alloc().buffer();
        // 1. 4 字节魔数
        out.writeBytes(new byte[]{1,2,3,4});
        // 2. 1 字节版本号
        out.writeByte(1);
        // 3. 1 序列化方式 jdk 0, json 1
        out.writeByte(1);
        // 4. 1 字节的指令类型
        out.writeByte(msg.getMessageType());
        // 5. 4 个字节
        out.writeInt(0);
        // 对齐，无意义
        out.writeByte(0xff);
        // 6. 获取内容的字节数组
        byte[] bytes = SerializerAlgorithm.JSON.serialize(msg);
        // 7. 长度
        out.writeInt(bytes.length);
        // 8. 写入内容
        out.writeBytes(bytes);
        outObj.add(out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNum = in.readInt();
        byte version = in.readByte();
        byte serializerAlgorithm = in.readByte();
        byte messageType = in.readByte();
        int sequenceId = in.readInt();
        in.readByte();
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes, 0, len);
        SerializerAlgorithm algorithm = SerializerAlgorithm.values()[serializerAlgorithm];
        Class<?> messageClass = Message.getMessageClass(messageType);
        Object deserialize = algorithm.deserialize(messageClass, bytes);
//        log.debug("{}, {}, {}, {}, {}, {}", magicNum, version, serializerAlgorithm, messageType, sequenceId, len);
        out.add(deserialize);
    }
}
