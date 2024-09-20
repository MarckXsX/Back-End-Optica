package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.Doctor;
import com.springboot.sv.optica.entities.dto.ConsultaDTO;
import com.springboot.sv.optica.repositories.CitaRepository;
import com.springboot.sv.optica.repositories.ConsultaRepository;
import com.springboot.sv.optica.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //Se usara el soporte de Mockito
class ConsultaServiceImplTest {

    //Permite inyectar las dependencias de los repositorios de los objetos simulados
    @InjectMocks
    private ConsultaServiceImpl consultaService;

    //Objeto simulado
    @Mock
    private ConsultaRepository consultaRepository;

    //Objeto simulado
    @Mock
    private CitaRepository citaRepository;

    //Objeto simulado
    @Mock
    private DoctorRepository doctorRepository;

    private Consulta consulta;
    private ConsultaDTO consultaDTO;
    private Cita cita;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        // Inicialización de objetos de prueba
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setNombres("Dr. House");

        cita = new Cita();
        cita.setId(1L);
        cita.setEstado("Pendiente");

        consulta = new Consulta();
        consulta.setId(1L);
        consulta.setCita(cita);
        consulta.setDoctor(doctor);
        consulta.setDiagnostico("Resfriado común");
        consulta.setInstrucciones("Reposo y paracetamol");

        consultaDTO = new ConsultaDTO();
        consultaDTO.setCita(1L);
        consultaDTO.setDoctor(1L);
        consultaDTO.setDiagnostico("Resfriado común");
        consultaDTO.setInstrucciones("Reposo y paracetamol");
    }

    @Test
    public void testFindById_Exito() {
        //Simula que encuentra la consulta
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        //Se llama el metodo del service
        Optional<Consulta> resultado = consultaService.findById(1L);

        //Verifica que el Optiona este presente
        assertTrue(resultado.isPresent());

        //Verifica que el id de la consulta encontrada sea igual que el esperado
        assertEquals(1L, resultado.get().getId());

        //Verifica que se llamo el metodo findById del repositorio
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave_CitaNoPendiente() {
        // Cambiar estado de la cita a distinto de "Pendiente"
        cita.setEstado("Finalizada");

        //Simula que encuentra la cita y el doctor
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        //when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        //Se llama el metodo de service
        Optional<Consulta> resultado = consultaService.save(consultaDTO);

        //Verifica que la consulta no se guardo
        assertFalse(resultado.isPresent());

        //Verifica que el metodo del repositorio no fue llamado
        verify(consultaRepository, times(0)).save(any(Consulta.class));
    }

    @Test
    public void testUpdate_ConsultaAsociadaAFactura() {
        //Simula que encuentra la consulta
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        //Simula que se verifica que la consulta esta asociada a una factura
        when(consultaRepository.ExisteConsultaFactura(1L)).thenReturn(true);

        //llama al metodo sel service
        Optional<Consulta> resultado = consultaService.update(1L, consultaDTO);

        //Verifica que la actualizacion no se realizo
        assertFalse(resultado.isPresent());

        //Verifica que no se llamo el metodo del repositorio
        verify(consultaRepository, times(0)).save(any(Consulta.class));
    }

}