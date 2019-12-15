package com.wutao.netty.codec.combine;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/15 22:32
 */
public class CombinedByteToCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder,CharToByteEncoder> {

    public CombinedByteToCharCodec() {
        super(new ByteToCharDecoder(),new CharToByteEncoder());
    }
}
