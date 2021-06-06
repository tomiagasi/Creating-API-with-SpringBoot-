package com.example.demo.controller;

import com.example.demo.model.Jabatan;
import com.example.demo.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jabatan")
public class JabatanController {

    @Autowired
    private JabatanService jabatanService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Jabatan save(@RequestBody Jabatan jabatan) {
        return jabatanService.save(jabatan);
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public List<Jabatan> findAll() {
        return jabatanService.findAll();
    }

    @RequestMapping(value = "/find-by-id", method = RequestMethod.GET)
    public Optional<Jabatan> findById(@RequestParam Integer kdJabatan) {
        return jabatanService.findById(kdJabatan);
    }
}
