package tn.iit.storemanagment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.storemanagment.config.Paths;
import tn.iit.storemanagment.dto.MedicineDto;
import tn.iit.storemanagment.services.MedicineCategoryService;
import tn.iit.storemanagment.services.MedicineService;

import java.util.List;

@RestController
@RequestMapping(Paths.MEDICINE_ROOT_URL)
public class MedicineController {
    private final MedicineService medicineService;
    private final MedicineCategoryService medicineCategoryService;


    public MedicineController(MedicineService medicineService, MedicineCategoryService medicineCategoryService) {
        this.medicineService = medicineService;
        this.medicineCategoryService = medicineCategoryService;
    }

    @GetMapping("all")
    public ResponseEntity<List<MedicineDto>> getAllMedicine() {

        return new ResponseEntity<>(medicineService.getAll(), HttpStatus.OK);
    }
    @GetMapping(path = "/getOne/{id}")
    public ResponseEntity<MedicineDto> getOneMedicine(@PathVariable Long id) {
        return new ResponseEntity<>(medicineService.getOne(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MedicineDto> saveMedicine(@RequestBody MedicineDto medicineDto){
        return new ResponseEntity<>(medicineService.save(medicineDto), HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<MedicineDto> updateMedicine(@RequestBody MedicineDto medicineDto){
        return new ResponseEntity<>(medicineService.update (medicineDto), HttpStatus.OK);
    }

    @GetMapping("/MedicineCategories/{id}")
    public ResponseEntity<?> getMedicineByCategoryId(@PathVariable(name = "id") Long id){
//        if (!clientService.exi (id)) {
//            return ResponseEntity
//                    .status (HttpStatus.NOT_FOUND).body ("Client doesn't exist ");
//        }
        return ResponseEntity.ok (medicineService.findMedicineByCategoryId (id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteMedicine(@PathVariable("id") Long id) {
        try {
            medicineService.delete(id);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
