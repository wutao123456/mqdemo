package com.wutao.netty.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wutao
 * @date 2019/12/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomProtocol {

    private long header;

    private String content;

}
