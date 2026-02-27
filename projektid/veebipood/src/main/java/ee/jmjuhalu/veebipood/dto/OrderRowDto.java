package ee.jmjuhalu.veebipood.dto;

public record OrderRowDto( //DTO --> data transfer object, mudel kuidas andmed liiguvad
        Long productId,
        int quantity
) {
}
