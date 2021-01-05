package tn.iit.storemanagment.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.iit.storemanagment.config.Paths;
import tn.iit.storemanagment.dto.MedicineCategoryDto;
import tn.iit.storemanagment.models.MedicineCategory;
import tn.iit.storemanagment.services.MedicineCategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.MEDICINE_CATEGORY_ROOT_URL)
public class MedicineCategoryController {
    private final MedicineCategoryService medicineCategoryService;
    private final Logger logger= LoggerFactory.getLogger (MedicineCategoryController.class);

    public MedicineCategoryController(MedicineCategoryService medicineCategoryService) {
        this.medicineCategoryService = medicineCategoryService;
    }
    @GetMapping
    public ResponseEntity<List<MedicineCategoryDto>> getAllCategories() {
        this.logger.debug ("Getting all categories");

        return new ResponseEntity<>(medicineCategoryService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicineCategoryDto> getOneCategory(@PathVariable Long id) {
        this.logger.debug ("Getting Category {}",id);
        return new ResponseEntity<>(medicineCategoryService.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicineCategoryDto> save(@Valid @RequestBody MedicineCategoryDto medicineCategoryDto){
        this.logger.debug ("Adding new Category {}",medicineCategoryDto.getName ());
        return new ResponseEntity<>(medicineCategoryService.save(medicineCategoryDto), HttpStatus.OK);
    }
    @PutMapping

    public ResponseEntity<MedicineCategoryDto> update(@Valid  @RequestBody MedicineCategoryDto medicineCategoryDto){
        this.logger.debug ("Updating Category {} with {}",medicineCategoryDto.getId (),medicineCategoryDto.getName ());
        return new ResponseEntity<>(medicineCategoryService.update (medicineCategoryDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        this.logger.debug ("Deleting Category {}",id);
        try {
            medicineCategoryService.delete(id);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
