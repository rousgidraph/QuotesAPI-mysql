package com.example.quotesapi.Mappers;

import com.example.quotesapi.DTOs.SearchTagDTO;
import com.example.quotesapi.Domains.SearchTag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class  SearchTagMapper {

    public abstract SearchTagDTO toDTO(SearchTag searchTag);

    public abstract SearchTag toEntity(SearchTagDTO searchTagDTO);
}
