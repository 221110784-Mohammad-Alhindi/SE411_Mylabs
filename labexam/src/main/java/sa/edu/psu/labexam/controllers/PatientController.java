package sa.edu.psu.labexam.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sa.edu.psu.labexam.model.Patient;
import sa.edu.psu.labexam.services.PatientService;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Retrieve all patients
    @GetMapping
    public ResponseEntity<Iterable<Patient>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    // Retrieve a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Integer id) {
        return patientService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(
            @PathVariable Integer id,
            @RequestBody Patient patient) {

        if (!patientService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Patient updatedPatient = new Patient(
                id,
                patient.name(),
                patient.city(),
                patient.age()
        );

        return ResponseEntity.ok(patientService.save(updatedPatient));
    }

    // Create a patient
    @PostMapping
    public ResponseEntity<Patient> create(
            @jakarta.validation.Valid @RequestBody Patient patient) {

        Patient newPatient = new Patient(
                null,
                patient.name(),
                patient.city(),
                patient.age()
        );

        Patient savedPatient = patientService.save(newPatient);

        return ResponseEntity.status(201).body(savedPatient);
    }
}