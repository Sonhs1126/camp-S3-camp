//package com.sparta.campproject.mapper;
//
//import com.sparta.campproject.dto.CampDto;
//import com.sparta.campproject.entity.Camp;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper // 1
//public interface CampMapper {
//    CampMapper INSTANCE = Mappers.getMapper(CampMapper.class); // 2
//
//    @Mapping(target = "commentList", constant = "Null")
//        // 3
//    Camp campDtoToEntity(CampDto campDto); // orderDto -> order
//
//
////    @Mapping(target = "urlPath", ignore = true) // 4
//
//    @Mapping(target = "commentList", ignore = true)
//    CampDto campToDto(Camp camp); // order -> OrderDto
//}
//
