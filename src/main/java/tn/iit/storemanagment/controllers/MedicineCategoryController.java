package tn.iit.storemanagment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.storemanagment.config.Paths;
import tn.iit.storemanagment.dto.MedicineCategoryDto;
import tn.iit.storemanagment.services.MedicineCategoryService;

import java.util.List;

@RestController
@RequestMapping(Paths.MEDICINE_CATEGORY_ROOT_URL)
public class MedicineCategoryController {
    private final MedicineCategoryService medicineCategoryService;

    public MedicineCategoryController(MedicineCategoryService medicineCategoryService) {
        this.medicineCategoryService = medicineCategoryService;
    }
    @GetMapping("all")
    public ResponseEntity<List<MedicineCategoryDto>> getAllCategories() {

        return new ResponseEntity<>(medicineCategoryService.getAll(), HttpStatus.OK);
    }
    @GetMapping(path = "/getOne/{id}")
    public ResponseEntity<MedicineCategoryDto> getOneCategory(@PathVariable Long id) {
        return new ResponseEntity<>(medicineCategoryService.getOne(id), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<MedicineCategoryDto> save(@RequestBody MedicineCategoryDto medicineCategoryDto){
        return new ResponseEntity<>(medicineCategoryService.save(medicineCategoryDto), HttpStatus.OK);
    }
    @PutMapping(path="/update")
    public ResponseEntity<MedicineCategoryDto> update(@RequestBody MedicineCategoryDto medicineCategoryDto){
        return new ResponseEntity<>(medicineCategoryService.update (medicineCategoryDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            medicineCategoryService.delete(id);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
