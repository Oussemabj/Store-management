package tn.iit.storemanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.storemanagment.models.MedicineCategory;

import java.util.List;

@Repository
public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory,Long>{


    boolean existsByName(String name);

}
