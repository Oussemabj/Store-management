package tn.iit.storemanagment.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tn.iit.storemanagment.dto.MedicineCategoryDto;
import tn.iit.storemanagment.mapper.MedicineCategoryMapper;
import tn.iit.storemanagment.models.MedicineCategory;
import tn.iit.storemanagment.repositories.MedicineCategoryRepository;
import tn.iit.storemanagment.repositories.MedicineRepository;
import tn.iit.storemanagment.services.MedicineCategoryService;

import java.util.List;

@Service
@Transactional
public class MedicineCategoryServiceImpl  extends CommonServiceImpl
        <MedicineCategory, MedicineCategoryDto, Long, MedicineCategoryRepository> implements MedicineCategoryService {

    @Autowired
    private MedicineRepository medicineRepository;

    MedicineCategoryServiceImpl(MedicineCategoryRepository repository, MedicineCategoryMapper mapper) {

        super (repository, mapper);

    }

    @Override
    public MedicineCategoryDto save(MedicineCategoryDto medicineCategoryDto) {
        if(medicineCategoryDto == null) {
            throw new RuntimeException("this category is not exist ");
        }
        if(repository.existsByName (medicineCategoryDto.getName ())) {
            throw new RuntimeException("This name of category  is already exists.");
        }
        System.out.println (medicineCategoryDto);
        MedicineCategory savedCategory =repository.save(mapper.map(medicineCategoryDto));
        if(savedCategory == null) {
            throw new RuntimeException ("An error has occurred in save this CIN");
        }
        return mapper.map(savedCategory);
    }

    @Override
    @Transactional
    public MedicineCategoryDto update(MedicineCategoryDto medicineCategoryDto) {
        return mapper.map (entityManager.merge (mapper.map (medicineCategoryDto)));

    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, "Id is null");
        medicineRepository.deleteMedicineByCategoryId (id);
        repository.deleteById(id);
    }

    @Override
    public MedicineCategoryDto getOne(Long id) {
        return mapper.map(repository.getOne(id));
    }

    @Override
    public List<MedicineCategoryDto> getAll() {

        return mapper.mapToDtos(repository.findAll());
    }
}
