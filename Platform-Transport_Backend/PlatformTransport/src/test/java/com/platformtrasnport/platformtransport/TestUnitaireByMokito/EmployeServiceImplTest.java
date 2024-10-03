package com.platformtrasnport.platformtransport.TestUnitaireByMokito;

import com.platformtrasnport.platformtransport.dto.EmployeDto;
import com.platformtrasnport.platformtransport.mapper.EmployeMapper;
import com.platformtrasnport.platformtransport.model.Employe;
import com.platformtrasnport.platformtransport.repository.EmployeRepository;
import com.platformtrasnport.platformtransport.service.Impl.EmployeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeServiceImplTest {

    @InjectMocks
    private EmployeServiceImpl employeService;

    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private EmployeMapper employeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployes_ShouldReturnEmployeDtos() {
        // Arrange
        Employe employe1 = new Employe(); // Populate fields if necessary
        Employe employe2 = new Employe(); // Populate fields if necessary
        List<Employe> employes = Arrays.asList(employe1, employe2);
        when(employeRepository.findAll()).thenReturn(employes);
        when(employeMapper.employeToDto(employe1)).thenReturn(new EmployeDto());
        when(employeMapper.employeToDto(employe2)).thenReturn(new EmployeDto());

        // Act
        List<EmployeDto> result = employeService.getAllEmployes();

        // Assert
        assertEquals(2, result.size());
        verify(employeRepository, times(1)).findAll();
    }

    @Test
    void createEmploye_ShouldReturnEmployeDto() {
        // Arrange
        EmployeDto employeDto = new EmployeDto(); // Populate fields if necessary
        Employe employe = new Employe(); // Populate fields if necessary
        Employe createdEmploye = new Employe(); // Populate fields if necessary

        when(employeMapper.dtoToEmploye(employeDto)).thenReturn(employe);
        when(employeRepository.save(employe)).thenReturn(createdEmploye);
        when(employeMapper.employeToDto(createdEmploye)).thenReturn(employeDto);

        // Act
        EmployeDto result = employeService.createEmploye(employeDto);

        // Assert
        assertEquals(result, employeDto);
        verify(employeRepository, times(1)).save(employe);
    }

    @Test
    void getEmployeById_ShouldReturnOptionalEmployeDto() {
        // Arrange
        Long id = 1L;
        Employe employe = new Employe(); // Populate fields if necessary
        EmployeDto employeDto = new EmployeDto(); // Populate fields if necessary

        when(employeRepository.findById(id)).thenReturn(Optional.of(employe));
        when(employeMapper.employeToDto(employe)).thenReturn(employeDto);

        // Act
        Optional<EmployeDto> result = employeService.getEmployeById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get(), employeDto);
        verify(employeRepository, times(1)).findById(id);
    }

    @Test
    void updateUtilisateur_ShouldReturnUpdatedEmployeDto() {
        // Arrange
        Long id = 1L;
        EmployeDto employeDto = new EmployeDto(); // Populate fields if necessary
        Employe existingEmploye = new Employe(); // Populate fields if necessary
        Employe updatedEmploye = new Employe(); // Populate fields if necessary

        when(employeRepository.findById(id)).thenReturn(Optional.of(existingEmploye));
        when(employeMapper.employeToDto(updatedEmploye)).thenReturn(employeDto);
        when(employeRepository.save(existingEmploye)).thenReturn(updatedEmploye);

        // Act
        Optional<EmployeDto> result = employeService.updateUtilisateur(id, employeDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get(), employeDto);
        verify(employeRepository, times(1)).findById(id);
        verify(employeRepository, times(1)).save(existingEmploye);
    }

    @Test
    void deleteUtilisateur_ShouldReturnTrueWhenDeleted() {
        // Arrange
        Long id = 1L;
        when(employeRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = employeService.deleteUtilisateur(id);

        // Assert
        assertTrue(result);
        verify(employeRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteUtilisateur_ShouldReturnFalseWhenNotExists() {
        // Arrange
        Long id = 1L;
        when(employeRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = employeService.deleteUtilisateur(id);

        // Assert
        assertFalse(result);
        verify(employeRepository, never()).deleteById(id);
    }
}
