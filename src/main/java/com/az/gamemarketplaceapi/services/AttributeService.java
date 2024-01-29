package com.az.gamemarketplaceapi.services;

import com.az.gamemarketplaceapi.models.Attribute;
import com.az.gamemarketplaceapi.repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AttributeService{

    @Autowired
    private AttributeRepository attributeRepository;

    public List<Attribute> getAllAtribute(){
        return attributeRepository.findAll();
    }

    public Attribute findByName(String name){
        return attributeRepository.findByName(name);
    }
}