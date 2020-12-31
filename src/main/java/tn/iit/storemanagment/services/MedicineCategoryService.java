package tn.iit.storemanagment.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tn.iit.storemanagment.dto.MedicineCategoryDto;
import tn.iit.storemanagment.exception.ResourceAlreadyFoundException;
import tn.iit.storemanagment.exception.ResourceNotFoundException;
import tn.iit.storemanagment.mapper.MedicineCategoryMapper;
import tn.iit.storemanagment.models.MedicineCategory;
import tn.iit.storemanagment.repositories.MedicineCategoryRepository;
import tn.iit.storemanagment.repositories.MedicineRepository;

import java.util.List;

@Service
@Transactional
public class MedicineCategoryService {

    private final MedicineCategoryRepository medicineCategoryRepository;
    private final MedicineCategoryMapper medicineCategoryMapper;
    private final MedicineRepository medicineRepository ;

    MedicineCategoryService(MedicineCategoryRepository repository, MedicineCategoryMapper mapper, MedicineRepository medicineRepository) {
        this.medicineCategoryRepository=repository;
        this.medicineCategoryMapper=mapper;
        this.medicineRepository=medicineRepository;
    }


    public MedicineCategoryDto save(MedicineCategoryDto medicineCategoryDto) {
        if(medicineCategoryDto == null) {
            throw new ResourceNotFoundException ("this category "+medicineCategoryDto.getName ()+"is not exist ");
        }
        if(medicineCategoryRepository.existsByName (medicineCategoryDto.getName ())) {
            throw new ResourceAlreadyFoundException ("This "+medicineCategoryDto.getName ()+" is already exists.");
        }
        //System.out.println (medicineCategoryDto);
        MedicineCategory savedCategory =medicineCategoryRepository.save(medicineCategoryMapper.mapToEntity (medicineCategoryDto));
        if(savedCategory == null) {
            throw new ResourceNotFoundException ("An error has occurred in save this category");
        }
        return medicineCategoryMapper.mapToDto (savedCategory);
    }

    public MedicineCategoryDto update(MedicineCategoryDto medicineCategoryDto) {
        return medicineCategoryMapper.mapToDto (medicineCategoryRepository.save (medicineCategoryMapper.mapToEntity (medicineCategoryDto)));

    }

    public void delete(Long id) {
        Assert.notNull(id, "Id is null");
        medicineRepository.deleteMedicinesByMedicineCategoryId (id);
        medicineCategoryRepository.deleteById(id);
    }

    public MedicineCategoryDto getOne(Long id) {
        return medicineCategoryMapper.mapToDto (medicineCategoryRepository.getOne(id));
    }

    public List<MedicineCategoryDto> getAll() {

        return medicineCategoryMapper.mapToDtos(medicineCategoryRepository.findAll());
    }
}
