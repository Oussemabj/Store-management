package tn.iit.storemanagment.services;

import tn.iit.storemanagment.dto.MedicineCategoryDto;


import java.util.List;

public interface MedicineCategoryService {

    MedicineCategoryDto save(MedicineCategoryDto medicineCategoryDto);

    MedicineCategoryDto update(MedicineCategoryDto medicineCategoryDto);

    void delete(Long id);

    List<MedicineCategoryDto> getAll();
    MedicineCategoryDto getOne(Long id);
}
