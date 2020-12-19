package tn.iit.storemanagment.mapper;


import org.mapstruct.Mapping;
import tn.iit.storemanagment.dto.MedicineDto;
import tn.iit.storemanagment.models.Medicine;

@org.mapstruct.Mapper(componentModel = Mapper.SPRING)
public interface MedicineMapper extends Mapper <Medicine, MedicineDto> {

    @Override
    @Mapping(source = "MedicineCategory.id",target = "medicineCategoryId")
    MedicineDto map(Medicine medicine);

    @Override
    @Mapping(source = "medicineCategoryId",target = "MedicineCategory.id")
    Medicine map(MedicineDto medicineDto) ;

}