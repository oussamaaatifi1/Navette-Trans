//package com.platformtrasnport.platformtransport.TestUnitaireByMokito;
//
//import com.platformtrasnport.platformtransport.model.Administrateur;
//import com.platformtrasnport.platformtransport.repository.AdministrateurRepository;
//import com.platformtrasnport.platformtransport.service.impl.AdministrateurServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class AdministrateurServiceImplTest {
//
//    @InjectMocks
//    private AdministrateurServiceImpl administrateurService;
//
//    @Mock
//    private AdministrateurRepository administrateurRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAdministrateurCount_ShouldReturnCorrectCount() {
//        // Arrange
//        long expectedCount = 5L;
//        when(administrateurRepository.countAdministrateurs()).thenReturn(expectedCount);
//
//        // Act
//        long actualCount = administrateurService.getAdministrateurCount();
//
//        // Assert
//        assertEquals(expectedCount, actualCount);
//        verify(administrateurRepository, times(1)).countAdministrateurs();
//    }
//
//    @Test
//    void findById_ShouldReturnAdministrateur_WhenFound() {
//        // Arrange
//        Long id = 1L;
//        Administrateur administrateur = new Administrateur(); // Populate fields if necessary
//        when(administrateurRepository.findById(id)).thenReturn(Optional.of(administrateur));
//
//        // Act
//        Administrateur result = administrateurService.findById(id);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(administrateur, result);
//        verify(administrateurRepository, times(1)).findById(id);
//    }
//
//    @Test
//    void findById_ShouldThrowException_WhenNotFound() {
//        // Arrange
//        Long id = 1L;
//        when(administrateurRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
//            administrateurService.findById(id);
//        });
//
//        assertEquals("Administrateur not found with id " + id, thrown.getMessage());
//        verify(administrateurRepository, times(1)).findById(id);
//    }
//}
