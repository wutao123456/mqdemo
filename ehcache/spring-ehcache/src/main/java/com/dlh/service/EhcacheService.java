package com.dlh.service;

import com.dlh.entity.User;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/11 21:18
 */
 public interface EhcacheService {

     String getTimestamp(String param);

     String getDataFromDB(String key);

     void removeDataAtDB(String key);

     String refreshData(String key);


     User findById(String userId);

     boolean isReserved(String userId);

     void removeUser(String userId);

     void removeAllUser();
}
