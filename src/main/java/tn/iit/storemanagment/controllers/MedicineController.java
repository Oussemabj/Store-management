package tn.iit.storemanagment.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.iit.storemanagment.config.Paths;
import tn.iit.storemanagment.dto.MedicineDto;
import tn.iit.storemanagment.services.MedicineCategoryService;
import tn.iit.storemanagment.services.MedicineService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(Paths.MEDICINE_ROOT_URL)
public class MedicineController {
    private final MedicineService medicineService;
    private final MedicineCategoryService medicineCategoryService;
    private final Logger logger = LoggerFactory.getLogger (MedicineController.class);


    public MedicineController(MedicineService medicineService, MedicineCategoryService medicineCategoryService) {
        this.medicineService = medicineService;
        this.medicineCategoryService = medicineCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineDto>> getAllMedicine(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String pageSort) {

        this.logger.debug ("Getting all medicaments");
        return new ResponseEntity<>(medicineService.getAll(PageRequest.of (pageNo,pageSize, Sort.by (pageSort).ascending ())), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<MedicineDto> getOneMedicine(@PathVariable Long id) {
        return new ResponseEntity<>(medicineService.getOne(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MedicineDto> saveMedicine(@RequestBody MedicineDto medicineDto){
        this.logger.debug ("Adding new Medicament {}", medicineDto);
        return new ResponseEntity<>(medicineService.save(medicineDto), HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<MedicineDto> updateMedicine(@RequestBody MedicineDto medicineDto){
        return new ResponseEntity<>(medicineService.update (medicineDto), HttpStatus.OK);
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<?> getMedicineByCategoryName(@PathVariable(name = "name") String name){
        return ResponseEntity.ok (medicineService.findMedicineByCategoryName (name));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteMedicine(@PathVariable("id") Long id) {
            medicineService.delete(id);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @PostMapping("/search")
    public Collection<MedicineDto> search(@Valid @RequestBody List<Long> ids){
        this.logger.debug ("return all medicines with their ids {}",ids);
        return this.medicineService.findAllByIds(ids);
    }
}
