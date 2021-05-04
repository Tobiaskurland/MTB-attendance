package com.example.demo.Service;

import com.example.demo.Model.Clazz;
import com.example.demo.Repository.IClazzRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClazzServiceImpl implements IClazzService{

    @Autowired
    IClazzRepo clazzRepo;

    @Override
    public List<Clazz> findAll() {
        return clazzRepo.findAll();
    }

    @Override
    public List<Clazz> findClassesByUser(int id) {
        return clazzRepo.findClassesByUser(id);
    }

    @Override
    public List<Clazz> findClassByUserAndCourse(int user_id, int course_id) {
        return clazzRepo.findClassesByUserAndCourse(user_id, course_id);
    }

    @Override
    public Clazz findClassByUserId(int id) {
        return clazzRepo.findclassByUserId(id);
    }

    @Override
    public Clazz findById(int id) {
        return clazzRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        clazzRepo.deleteById(id);
    }

    @Override
    public void save(Clazz clazz) {
        clazzRepo.save(clazz);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deleteClass(int id){
        StoredProcedureQuery spInsert = entityManager.createStoredProcedureQuery("SP_DeleteClass");

        spInsert.registerStoredProcedureParameter("Id", int.class, ParameterMode.IN);

        spInsert.setParameter("Id", id);

        spInsert.execute();

    }
}
