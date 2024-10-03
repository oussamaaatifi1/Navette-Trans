package com.platformtrasnport.platformtransport.TestUnitaireByMokito;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.mapper.EmployeurMapper;
import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.repository.EmployeurRepository;
import com.platformtrasnport.platformtransport.service.impl.EmployeurServiceImpl;
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

class EmployeurServiceImplTest {

    @InjectMocks
    private EmployeurServiceImpl employeurService;

    @Mock
    private EmployeurRepository employeurRepository;

    @Mock
    private EmployeurMapper employeurMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployeurs_ShouldReturnEmployeurDtos() {
        // Arrange
        Employeur employeur1 = new Employeur(); // Populate fields if necessary
        Employeur employeur2 = new Employeur(); // Populate fields if necessary
        List<Employeur> employeurs = Arrays.asList(employeur1, employeur2);
        when(employeurRepository.findAll()).thenReturn(employeurs);
        when(employeurMapper.employeurToDto(employeur1)).thenReturn(new EmployeurDto());
        when(employeurMapper.employeurToDto(employeur2)).thenReturn(new EmployeurDto());

        // Act
        List<EmployeurDto> result = employeurService.getAllEmployeurs();

        // Assert
        assertEquals(2, result.size());
        verify(employeurRepository, times(1)).findAll();
    }

    @Test
    void createEmployeur_ShouldReturnEmployeurDto() {
        // Arrange
        EmployeurDto employeurDto = new EmployeurDto(); // Populate fields if necessary
        Employeur employeur = new Employeur(); // Populate fields if necessary
        Employeur createdEmployeur = new Employeur(); // Populate fields if necessary

        when(employeurMapper.dtoToEmployeur(employeurDto)).thenReturn(employeur);
        when(employeurRepository.save(employeur)).thenReturn(createdEmployeur);
        when(employeurMapper.employeurToDto(createdEmployeur)).thenReturn(employeurDto);

        // Act
        EmployeurDto result = employeurService.createEmployeur(employeurDto);

        // Assert
        assertEquals(result, employeurDto);
        verify(employeurRepository, times(1)).save(employeur);
    }

    @Test
    void updateEmployeur_ShouldReturnUpdatedEmployeurDto() {
        // Arrange
        Long id = 1L;
        EmployeurDto employeurDto = new EmployeurDto(); // Populate fields if necessary
        Employeur existingEmployeur = new Employeur(); // Populate fields if necessary
        Employeur updatedEmployeur = new Employeur(); // Populate fields if necessary

        when(employeurRepository.findById(id)).thenReturn(Optional.of(existingEmployeur));
        when(employeurMapper.dtoToEmployeur(employeurDto)).thenReturn(updatedEmployeur);
        when(employeurMapper.employeurToDto(updatedEmployeur)).thenReturn(employeurDto);

        // Act
        Optional<EmployeurDto> result = employeurService.updateEmployeur(id, employeurDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get(), employeurDto);
        verify(employeurRepository, times(1)).findById(id);
        verify(employeurRepository, times(1)).save(updatedEmployeur);
    }

    @Test
    void deleteEmployeur_ShouldReturnTrueWhenDeleted() {
        // Arrange
        Long id = 1L;
        when(employeurRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = employeurService.deleteEmployeur(id);

        // Assert
        assertTrue(result);
        verify(employeurRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteEmployeur_ShouldReturnFalseWhenNotExists() {
        // Arrange
        Long id = 1L;
        when(employeurRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = employeurService.deleteEmployeur(id);

        // Assert
        assertFalse(result);
        verify(employeurRepository, never()).deleteById(id);
    }
}
