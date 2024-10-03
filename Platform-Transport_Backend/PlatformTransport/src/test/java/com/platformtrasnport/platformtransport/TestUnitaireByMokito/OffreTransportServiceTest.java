package com.platformtrasnport.platformtransport.TestUnitaireByMokito;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import com.platformtrasnport.platformtransport.mapper.OffreTransportMapper;
import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.model.enul.OffreStatus;
import com.platformtrasnport.platformtransport.repository.EmployeurRepository;
import com.platformtrasnport.platformtransport.repository.OffreTransportRepository;
import com.platformtrasnport.platformtransport.service.impl.OffreTransportServiceImpl;
import com.platformtrasnport.platformtransport.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

 class OffreTransportServiceTest  {

    @InjectMocks
    private OffreTransportServiceImpl offreTransportService;

    @Mock
    private OffreTransportRepository offreTransportRepository;

    @Mock
    private OffreTransportMapper offreTransportMapper;

    @Mock
    private EmployeurRepository employeurRepository;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOffreTransport_ShouldReturnOffreTransportDto_WhenSuccessful() {
        // Arrange
        OffreTransportDto dto = new OffreTransportDto();
        dto.setEmployeurId(1L);
        Employeur employeur = new Employeur();
        employeur.setId(1L);

        OffreTransport offreTransport = new OffreTransport();
        offreTransport.setId(1L);
        offreTransport.setDateOffre(LocalDate.now());
        offreTransport.setStatus(OffreStatus.PENDING);
        offreTransport.setEmployeur(employeur);

        when(jwtService.extractUserId(anyString())).thenReturn(1L);
        when(employeurRepository.findById(1L)).thenReturn(Optional.of(employeur));
        when(offreTransportMapper.dtoToOffreTransport(dto)).thenReturn(offreTransport);
        when(offreTransportRepository.save(offreTransport)).thenReturn(offreTransport);
        when(offreTransportMapper.offreTransportToDto(offreTransport)).thenReturn(dto);

        // Act
        OffreTransportDto result = offreTransportService.createOffreTransport(dto, "Bearer token");

        // Assert
        assertNotNull(result);
        assertEquals(dto.getEmployeurId(), result.getEmployeurId());
        verify(offreTransportRepository).save(offreTransport);
    }

    @Test
    void findPendingOffres_ShouldReturnListOfOffreTransportDto_WhenOffersExist() {
        // Arrange
        OffreTransport offreTransport = new OffreTransport();
        offreTransport.setId(1L);
        offreTransport.setStatus(OffreStatus.PENDING);

        when(offreTransportRepository.findByStatus(OffreStatus.PENDING)).thenReturn(Collections.singletonList(offreTransport));
        when(offreTransportMapper.offreTransportToDto(offreTransport)).thenReturn(new OffreTransportDto());

        // Act
        var result = offreTransportService.findPendingOffres();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(offreTransportRepository).findByStatus(OffreStatus.PENDING);
    }

    @Test
    void findById_ShouldReturnOffreTransportDto_WhenExists() {
        // Arrange
        OffreTransport offreTransport = new OffreTransport();
        offreTransport.setId(1L);
        when(offreTransportRepository.findById(1L)).thenReturn(Optional.of(offreTransport));
        when(offreTransportMapper.offreTransportToDto(offreTransport)).thenReturn(new OffreTransportDto());

        // Act
        Optional<OffreTransportDto> result = offreTransportService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        verify(offreTransportRepository).findById(1L);
    }

    @Test
    void deleteOffreTransport_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(offreTransportRepository.existsById(anyLong())).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            offreTransportService.deleteOffreTransport(1L);
        });

        assertEquals("404 NOT_FOUND \"OffreTransport not found\"", exception.getMessage());
        verify(offreTransportRepository).existsById(1L);
    }
}
