//package com.platformtrasnport.platformtransport.TestUnitaireByMokito;
//
//import com.platformtrasnport.platformtransport.dto.RapportDto;
//import com.platformtrasnport.platformtransport.mapper.RapportMapper;
//import com.platformtrasnport.platformtransport.model.Rapport;
//import com.platformtrasnport.platformtransport.repository.RapportRepository;
//import com.platformtrasnport.platformtransport.service.impl.RapportServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class RapportServiceImplTest {
//
//    @InjectMocks
//    private RapportServiceImpl rapportService;
//
//    @Mock
//    private RapportRepository rapportRepository;
//
//    // Create a mock for the RapportMapper
//    @Mock
//    private RapportMapper rapportMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAllRapports_ShouldReturnRapportDtos() {
//        // Arrange
//        Rapport rapport1 = new Rapport(); // Populate fields if necessary
//        Rapport rapport2 = new Rapport(); // Populate fields if necessary
//        List<Rapport> rapports = Arrays.asList(rapport1, rapport2);
//
//        // Mock repository behavior
//        when(rapportRepository.findAll()).thenReturn(rapports);
//
//        // Mock mapper behavior
//        when(rapportMapper.rapportToDto(rapport1)).thenReturn(new RapportDto());
//        when(rapportMapper.rapportToDto(rapport2)).thenReturn(new RapportDto());
//
//        // Act
//        List<RapportDto> result = rapportService.getAllRapports();
//
//        // Assert
//        assertEquals(2, result.size());
//        verify(rapportRepository, times(1)).findAll();
//    }
//
//    @Test
//    void saveRapport_ShouldReturnRapportDto() {
//        // Arrange
//        Rapport rapport = new Rapport(); // Populate fields if necessary
//        Rapport savedRapport = new Rapport(); // Populate fields if necessary
//        when(rapportRepository.save(rapport)).thenReturn(savedRapport);
//
//        // Mock mapper behavior
//        when(rapportMapper.rapportToDto(savedRapport)).thenReturn(new RapportDto());
//
//        // Act
//        RapportDto result = rapportService.saveRapport(rapport);
//
//        // Assert
//        assertEquals(result, rapportMapper.rapportToDto(savedRapport));
//        verify(rapportRepository, times(1)).save(rapport);
//    }
//}
