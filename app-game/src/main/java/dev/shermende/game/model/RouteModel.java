package dev.shermende.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(value = "item", collectionRelation = "data")
public class RouteModel extends RepresentationModel<RouteModel> {

    private Long id;

    private ReasonModel reason;

    private PointModel target;

    @NotNull
    @Override
    @JsonIgnore
    public Links getLinks() {
        return super.getLinks();
    }

}
