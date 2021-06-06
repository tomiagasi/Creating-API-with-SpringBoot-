package com.example.demo.service;

import com.example.demo.dao.JabatanDao;
import com.example.demo.model.Jabatan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JabatanService {

    @Autowired
    private JabatanDao jabatanDao;

    public List<Jabatan> findAll(){
        return jabatanDao.findAll();
    }

    public Jabatan save(Jabatan jabatan){
        return jabatanDao.save(jabatan);
    }

    public Optional<Jabatan> findById(Integer kdJabatan){
        return jabatanDao.findById(kdJabatan);
    }
}
