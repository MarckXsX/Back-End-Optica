package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceImplTest {

    //Permite inyectar las dependencias de los repositorios de los objetos simulados
    @InjectMocks
    private PacienteServiceImpl pacienteService;

    //Objeto simulado
    @Mock
    private PacienteRepository pacienteRepository;

    private Paciente paciente;

    @BeforeEach
    public void setUp() {
        // Inicialización de objeto de prueba
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombres("Juan");
        paciente.setApellidos("Perez");
        paciente.setCorreo("juan.perez@gmail.com");
        paciente.setTelefono("123456789");
        paciente.setFecha_nacimiento("2000-01-01");
        paciente.setCitas(new ArrayList<>());
        paciente.setFacturaCitaList(new ArrayList<>());
        paciente.setFacturaProductoList(new ArrayList<>());
    }

    @Test
    public void testFindAll() {

        //Simula el llamado al metodo findAll
        List<Paciente> pacientes = Collections.singletonList(paciente);
        when(pacienteRepository.findAll()).thenReturn(pacientes);

        //llamando al servicio
        List<Paciente> resultado = pacienteService.findAll();

        System.out.println("resultado = " + resultado);

        //Verifica que el resultado no sea null
        assertNotNull(resultado);
        //Verifica que la lista tenga un paciente
        assertEquals(1, resultado.size());
        //Verifica si se a llamado al metodo findAll del repo
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        //Simula que encuentra el paciente
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        //llama al metodo del servicio
        Optional<Paciente> resultado = pacienteService.findById(1L);

        //Verifica que el paciente fue encontrado
        assertTrue(resultado.isPresent());

        //Compara el nombre del paciente encontrado con el esperado
        assertEquals("Pedro", resultado.get().getNombres());
        //Verifica que el metodo del repositorio se haya llamado
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        //Simula cuando llaman al metodo save del repositorio, regrese el paciente guardado
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        //Se llama el service
        Paciente resultado = pacienteService.save(paciente);

        //Verifica que el resultado no sea null
        assertNotNull(resultado);
        //Verifica el nombre del paciente guardado sea igual al esperado
        assertEquals("Juan", resultado.getNombres());
        //Verifica que se haya llamado el metodo save
        verify(pacienteRepository, times(1)).save(paciente);
    }


    @Test
    public void testDeletePacienteConCitasYFacturas() {
        //Asignandole una cita al paciente
        paciente.getCitas().add(new Cita());
        //Al llamar al metodo del repositorio retorne el paciente
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        //llamando al service
        Optional<Paciente> resultado = pacienteService.delete(1L);

        //verifica que no se haya eliminado
        assertFalse(resultado.isPresent());
        //verifica que se haya llamado el metodo del repositorio
        verify(pacienteRepository, times(1)).findById(1L);
        //verifica que se haya llamado el metodo delete del repositorio
        verify(pacienteRepository, times(0)).delete(any(Paciente.class));
    }
}