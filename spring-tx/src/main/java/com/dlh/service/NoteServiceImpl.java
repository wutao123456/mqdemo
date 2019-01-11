package com.dlh.service;

import com.dlh.dao.NoteDao;
import com.dlh.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/4 20:53
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int save(Note note) {
        return noteDao.save(note);
    }
}
