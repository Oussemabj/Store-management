package tn.iit.storemanagment.mapper;


import org.mapstruct.Mapping;
import tn.iit.storemanagment.dto.MedicineDto;
import tn.iit.storemanagment.models.Medicine;

@org.mapstruct.Mapper(componentModel = Mapper.SPRING)
public interface MedicineMapper extends Mapper <Medicine, MedicineDto> {

    @Override
    @Mapping(source = "medicineCategory.id",target = "categoryId")
    MedicineDto mapToDto(Medicine medicine);

    @Override
    @Mapping(source = "categoryId",target = "medicineCategory.id")
    Medicine mapToEntity(MedicineDto medicineDto) ;

}