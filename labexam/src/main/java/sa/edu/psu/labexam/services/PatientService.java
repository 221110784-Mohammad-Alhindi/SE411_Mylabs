package sa.edu.psu.labexam.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import sa.edu.psu.labexam.model.Patient;
import sa.edu.psu.labexam.repositories.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Integer id) {
        return patientRepository.findById(id);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public boolean existsById(Integer id) {
        return patientRepository.existsById(id);
    }
}