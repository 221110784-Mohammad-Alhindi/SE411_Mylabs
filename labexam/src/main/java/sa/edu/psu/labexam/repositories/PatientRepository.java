package sa.edu.psu.labexam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sa.edu.psu.labexam.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {
}