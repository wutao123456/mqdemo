package com.dlh.service;

import com.dlh.dao.UserDao;
import com.dlh.entity.Note;
import com.dlh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/3 23:21
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private TransactionTemplate template;

    public TransactionTemplate getTemplate() {
        return template;
    }

    public void setTemplate(TransactionTemplate template) {
        this.template = template;
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private NoteService noteService;
    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    @Transactional
    public void save(User user) {
        //note事务是单独事务,此事务中note保存成功,user保存失败,note事务不回滚
        Note note = new Note();
        note.setCn_note_id("12345");
        note.setCn_notebook_id("123456789012345");
        noteService.save(note);

        userDao.save(user);
    }

    //测试编程式事务
    public void test(final User user) {
        //TransactionCallback  有返回值
        //TransactionCallbackWithoutResult 无返回值
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    Note note = new Note();
                    note.setCn_note_id("12345");
                    note.setCn_notebook_id("123456789012345");
                    noteService.save(note);

                    userDao.save(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionStatus.setRollbackOnly();
                }
            }
        });


    }
}
