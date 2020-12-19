package tn.iit.storemanagment.mapper;

import tn.iit.storemanagment.dto.MedicineCategoryDto;
import tn.iit.storemanagment.dto.MedicineDto;
import tn.iit.storemanagment.models.Medicine;
import tn.iit.storemanagment.models.MedicineCategory;

@org.mapstruct.Mapper(componentModel = Mapper.SPRING)
public interface MedicineCategoryMapper extends Mapper <MedicineCategory, MedicineCategoryDto>{

    MedicineCategoryDto map(MedicineCategory medicineCategory);
}
