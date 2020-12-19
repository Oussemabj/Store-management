package tn.iit.storemanagment.services;



import tn.iit.storemanagment.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    MedicineDto save(MedicineDto medicineDto);

    MedicineDto update(MedicineDto compteDto);

    void delete(Long id);

    List<MedicineDto> getAll();
    MedicineDto getOne(Long id);
    List<MedicineDto> findMedicineByCategoryId(Long categoryId);
}
