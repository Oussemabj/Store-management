package tn.iit.storemanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.storemanagment.models.Medicine;

import java.util.List;


@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {
//    Medicine findByName(String name);
//
//    Medicine findByPrice(float price);
    boolean existsByName(String name);
    List<Medicine> findMedicinesByMedicineCategoryName(String categoryName);
    void deleteMedicinesByMedicineCategoryId(Long categoryId);
}