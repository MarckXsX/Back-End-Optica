package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.FacturaCita;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.entities.dto.CitaDTO;
import com.springboot.sv.optica.repositories.CitaRepository;
import com.springboot.sv.optica.repositories.PacienteRepository;
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
class CitaServiceImplTest {

    @InjectMocks //Permite inyectar automaticamente las dependencias de los objetos con @Mock
    private CitaServiceImpl citaService;

    @Mock //Objetos simulado
    private CitaRepository citaRepository;

    @Mock //Objetos simulado
    private PacienteRepository pacienteRepository;

    private Cita cita;
    private CitaDTO citaDTO;
    private Paciente paciente;

    @BeforeEach //Se ejecutara antes de cada prueba unitaria
    void setUp() {
        // Inicialización de objetos de prueba
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombres("Juan");
        paciente.setApellidos("Pérez");

        cita = new Cita();
        cita.setId(1L);
        cita.setPaciente(paciente);
        cita.setFecha_cita("2024-09-01");
        cita.setHora_cita("10:00");
        cita.setEstado("Programada");
        cita.setCosto(100.0);

        citaDTO = new CitaDTO();
        citaDTO.setPaciente(1L);
        citaDTO.setFecha_cita("2024-09-01");
        citaDTO.setHora_cita("10:00");
        citaDTO.setEstado("Programada");
        citaDTO.setCosto(100.0);
    }

    @Test
    public void testFindById() {
        // Simular que el repositorio encuentra la cita y devuelve la cita
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));

        //Se llamada al metodo
        Optional<Cita> resultado = citaService.findById(1L);

        // Verificar que se encontró la cita correctamente
        assertTrue(resultado.isPresent());

        //Comprueba que el id de la cita encontrada sea el esperado
        assertEquals( 1L, resultado.get().getId());

        //Comprueba que se llamo el metodo del repositorio
        verify(citaRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_NoEncontrada() {
        // Simular que no se encuentra la cita, retorna un Optiona vacio
        when(citaRepository.findById(1L)).thenReturn(Optional.empty());

        //Se llamada al metodo
        Optional<Cita> resultado = citaService.findById(1L);

        // Verificar que el resultado está vacío
        assertFalse(resultado.isPresent());

        //Comprueba que se llamo el metodo del repositorio
        verify(citaRepository, times(1)).findById(1L);
    }

    @Test
    public void testDelete_CitaConFactura() {
        // Simular que la cita tiene una factura
        FacturaCita facturaCita = new FacturaCita();
        cita.setFacturaCita(facturaCita);

        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));

        //Se llama al metodo delete
        Optional<Cita> resultado = citaService.delete(1L);

        // Verificar que no se eliminó la cita
        assertFalse(resultado.isPresent());

        //Comprueba que se llamo el metodo del repositorio
        verify(citaRepository, times(1)).findById(1L);
        verify(citaRepository, times(0)).delete(any(Cita.class));
    }
}