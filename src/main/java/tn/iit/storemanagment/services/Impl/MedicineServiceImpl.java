package tn.iit.storemanagment.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tn.iit.storemanagment.dto.MedicineDto;
import tn.iit.storemanagment.mapper.Mapper;
import tn.iit.storemanagment.models.Medicine;
import tn.iit.storemanagment.repositories.MedicineRepository;
import tn.iit.storemanagment.services.MedicineService;

import java.util.List;

@Service
@Transactional
public class MedicineServiceImpl extends CommonServiceImpl
        <Medicine, MedicineDto, Long, MedicineRepository> implements MedicineService {


    MedicineServiceImpl(MedicineRepository repository,  Mapper<Medicine, MedicineDto> mapper) {

        super (repository, mapper);

    }

    @Override
    public MedicineDto save(MedicineDto medicineDto) {
        if(medicineDto == null) {
            throw new RuntimeException("this medicine is not exist ");
        }
        if(repository.existsByName (medicineDto.getName ())) {
            throw new RuntimeException("The name of this Medicine already exists.");
        }
        Medicine savedMedicine =repository.save (mapper.map(medicineDto));
        if(savedMedicine == null) {
            throw new RuntimeException ("An error has occurred in save this Medecine");
        }
        return mapper.map(savedMedicine);
    }

    @Override
    public MedicineDto update(MedicineDto medicineDto) {
        return mapper.map (entityManager.merge (mapper.map (medicineDto)));
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, "Id is null");
        repository.deleteById(id);
    }

    @Override
    public MedicineDto getOne(Long id) {
      return mapper.map(repository.getOne(id));

    }

    @Override
    public List<MedicineDto> findMedicineByCategoryId(Long categoryId) {
        return mapper.mapToDtos (repository.findMedicineByCategoryId (categoryId));
    }
}