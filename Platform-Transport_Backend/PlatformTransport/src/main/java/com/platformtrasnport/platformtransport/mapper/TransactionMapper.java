package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.TransactionDto;
import com.platformtrasnport.platformtransport.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "employe.id", target = "employeId")
    @Mapping(source = "reservation.id", target = "reservationId")
    TransactionDto transactionToDto(Transaction transaction);

    @Mapping(target = "employe", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    Transaction dtoToTransaction(TransactionDto dto);
}