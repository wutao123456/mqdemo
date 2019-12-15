package com.wutao.netty.http.helloworld;

import io.netty.channel.*;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/14 22:25
 */
public class MyChannelOutBoundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        promise.setSuccess();该方法可立即返回结果
        promise.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(!channelFuture.isSuccess()){
                    channelFuture.cause();
                    channelFuture.channel().close();
                }
            }
        });
    }
}
