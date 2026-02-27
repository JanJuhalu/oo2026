package ee.jmjuhalu.filmrent.dto;

import ee.jmjuhalu.filmrent.entity.FilmType;

public record FilmSaveDto(
        String title,
        FilmType type
) {

}
