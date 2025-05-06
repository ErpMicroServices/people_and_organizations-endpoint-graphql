package org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryAssociationEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.GeographicBoundaryAssociationRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.GeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.models.GeographicBoundaryAssociation;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class GeographicBoundaryAssociationController {

    private final GeographicBoundaryAssociationRepository geographicBoundaryAssociationRepository;
    private final GeographicBoundaryRepository geographicBoundaryRepository;

    public GeographicBoundaryAssociationController(GeographicBoundaryAssociationRepository geographicBoundaryAssociationRepository,
                                                  GeographicBoundaryRepository geographicBoundaryRepository) {
        this.geographicBoundaryAssociationRepository = geographicBoundaryAssociationRepository;
        this.geographicBoundaryRepository = geographicBoundaryRepository;
    }

    @SchemaMapping(typeName = "GeographicBoundary", field = "madeUpOf")
    public List<GeographicBoundaryAssociation> madeUpOf(@Argument PageInfo pageInfo, GeographicBoundary geographicBoundary) {
        Page<GeographicBoundaryAssociationEntity> associations = geographicBoundaryAssociationRepository.findByWithinBoundary(
                geographicBoundary.getId(), pageInfoToPageable(pageInfo));

        return associations.stream()
                .map(entity -> {
                    GeographicBoundaryEntity inBoundaryEntity = geographicBoundaryRepository.findById(entity.getInBoundary())
                            .orElse(null);

                    GeographicBoundary inBoundary = null;
                    if (inBoundaryEntity != null) {
                        inBoundary = GeographicBoundary.builder()
                                .id(inBoundaryEntity.getId())
                                .name(inBoundaryEntity.getName())
                                .geoCode(inBoundaryEntity.getGeoCode())
                                .abbreviation(inBoundaryEntity.getAbbreviation())
                                .build();
                    }

                    return GeographicBoundaryAssociation.builder()
                            .id(entity.getId())
                            .withinBoundary(geographicBoundary)
                            .inBoundary(inBoundary)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @SchemaMapping(typeName = "GeographicBoundary", field = "partOf")
    public GeographicBoundary partOf(GeographicBoundary geographicBoundary) {
        List<GeographicBoundaryAssociationEntity> associations = geographicBoundaryAssociationRepository.findByInBoundary(
                geographicBoundary.getId(), pageInfoToPageable(PageInfo.builder().pageSize(1).build())).getContent();

        if (!associations.isEmpty()) {
            GeographicBoundaryAssociationEntity association = associations.get(0);
            return geographicBoundaryRepository.findById(association.getWithinBoundary())
                    .map(entity -> GeographicBoundary.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .geoCode(entity.getGeoCode())
                            .abbreviation(entity.getAbbreviation())
                            .build())
                    .orElse(null);
        }

        return null;
    }
}
