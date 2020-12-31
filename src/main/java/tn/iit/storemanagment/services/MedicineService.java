package tn.iit.storemanagment.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.iit.storemanagment.dto.MedicineDto;
import tn.iit.storemanagment.exception.ResourceNotFoundException;
import tn.iit.storemanagment.mapper.MedicineMapper;
import tn.iit.storemanagment.models.Medicine;
import tn.iit.storemanagment.repositories.MedicineRepository;

import java.util.List;

@Service
@Transactional
public class MedicineService {

private final MedicineRepository medicineRepository;
private final MedicineMapper medicineMapper;
    MedicineService(MedicineRepository medicineRepository, MedicineMapper medicineMapper) {


        this.medicineRepository = medicineRepository;
        this.medicineMapper = medicineMapper;
    }


    public MedicineDto save(MedicineDto medicineDto) {
        if(medicineDto == null) {
            throw new RuntimeException("this medicine is not exist ");
        }
        if(medicineRepository.existsByName (medicineDto.getName ())) {
            throw new RuntimeException("The name of this Medicine already exists.");
        }
        Medicine savedMedicine =medicineRepository.save (medicineMapper.mapToEntity (medicineDto));
        if(savedMedicine == null) {
            throw new RuntimeException ("An error has occurred in save this Medecine");
        }
        return medicineMapper.mapToDto(savedMedicine);
    }

    public MedicineDto update(MedicineDto medicineDto) {
        return medicineMapper.mapToDto (medicineRepository.save (medicineMapper.mapToEntity (medicineDto)));
    }

    public void delete(Long id) {
        try {
            medicineRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new  ResourceNotFoundException("no medicine exists with id = "+id);
        }

    }

    public MedicineDto getOne(Long id) {
      return medicineMapper.mapToDto (medicineRepository.getOne(id));

    }
    public List<MedicineDto> findMedicineByCategoryName(String categoryName) {
        return medicineMapper.mapToDtos (medicineRepository.findMedicinesByMedicineCategoryName (categoryName));
    }
    public List<MedicineDto> getAll(Pageable pageable) {

        return medicineMapper.mapToDtos(medicineRepository.findAll(pageable).getContent ());
    }
    public List<MedicineDto> findAllByIds (List<Long>ids){
        return medicineMapper.mapToDtos (medicineRepository.findAllById (ids));

    }
}