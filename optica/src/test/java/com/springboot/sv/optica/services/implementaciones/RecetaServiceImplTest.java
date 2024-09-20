package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.Medicamento;
import com.springboot.sv.optica.entities.Receta;
import com.springboot.sv.optica.entities.dto.RecetaDTO;
import com.springboot.sv.optica.repositories.ConsultaRepository;
import com.springboot.sv.optica.repositories.MedicamentoRepository;
import com.springboot.sv.optica.repositories.RecetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaServiceImplTest {

    //Objeto simulado
    @Mock
    private RecetaRepository recetaRepository;

    //Objeto simulado
    @Mock
    private ConsultaRepository consultaRepository;

    //Objeto simulado
    @Mock
    private MedicamentoRepository medicamentoRepository;

    //Se le inyectan las dependencias de los objetos simulados
    @InjectMocks
    private RecetaServiceImpl recetaService;

    private Receta receta;
    private RecetaDTO recetaDTO;
    private Consulta consulta;
    private Medicamento medicamento;

    @BeforeEach
    public void setUp() {
        // Inicialización de objetos de prueba
        consulta = new Consulta();
        consulta.setId(1L);

        medicamento = new Medicamento();
        medicamento.setId(1L);
        medicamento.setStock(6);

        recetaDTO = new RecetaDTO();
        recetaDTO.setConsulta(1L);
        recetaDTO.setMedicamento(1L);
        recetaDTO.setCantidad(5);
    }

    @Test
    public void testSave_RecetaConStockDisponible() {
        //Al llamar al Repo, regrese la consulta y medicamento
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(medicamento));
        //regresa que no existe una consulta asociada a una factura
        when(consultaRepository.ExisteConsultaFactura(1L)).thenReturn(false);

        // Crear una nueva receta con la cantidad esperada
        Receta recetaGuardada = new Receta();
        recetaGuardada.setConsulta(consulta);
        recetaGuardada.setMedicamento(medicamento);
        recetaGuardada.setCantidad(recetaDTO.getCantidad());

        //retorna la receta guardada al llamar al repo
        when(recetaRepository.save(any(Receta.class))).thenReturn(recetaGuardada);

        //Se llama al service
        Optional<Receta> resultado = recetaService.save(recetaDTO);

        //Verifica que la rece se guardo con exito
        assertTrue(resultado.isPresent());
        //Compara la cantidad de medicamento de la receta guardada con el valor esperado
        assertEquals(5, resultado.get().getCantidad());
        //Verifica que se hayan llamado los diferentes metodos
        verify(medicamentoRepository, times(1)).save(medicamento);
        verify(recetaRepository, times(1)).save(any(Receta.class));
    }

}