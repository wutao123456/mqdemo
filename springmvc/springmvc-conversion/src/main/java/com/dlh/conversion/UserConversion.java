package com.dlh.conversion;

import com.dlh.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/5 21:33
 */
@Component
public class UserConversion implements Converter<String, User>{


    @Override
    public User convert(String source) {
        if(source!=null){
            String[] arr = source.split("-");
            int id = Integer.parseInt(arr[0]);
            String name = arr[1];
            String gender = arr[2];
            String job = arr[3];
            User user = new User(id,name,gender,job,new Date());
            return user;
        }
        return null;
    }
}
