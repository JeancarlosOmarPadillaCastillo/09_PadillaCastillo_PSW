package com.gestion.activities;


import com.gestion.activities.domain.dto.ActivitiesRequestDto;
import com.gestion.activities.domain.dto.ActivitiesResponseDto;
import com.gestion.activities.service.ActivitiesService;
import com.gestion.activities.web.ActivitiesController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class ActivitiesSoaCaneteApplicationTests {
    @Mock
    private ActivitiesService activitiesService;

    @InjectMocks
    private ActivitiesController activitiesController;

    @Test
    void getDataActivitiesActive() {
        when(activitiesService.findAllActive()).thenReturn(Flux.just(new ActivitiesResponseDto(), new ActivitiesResponseDto()));

        Flux<ActivitiesResponseDto> result = activitiesController.getDataActivitiesActive();

        verify(activitiesService, times(1)).findAllActive();
        assertEquals(result.collectList().block(), Flux.just(new ActivitiesResponseDto(), new ActivitiesResponseDto()).collectList().block());
    }

    @Test
    void getDataActivitiesInactive() {
        when(activitiesService.findAllInactive()).thenReturn(Flux.just(new ActivitiesResponseDto(), new ActivitiesResponseDto()));

        Flux<ActivitiesResponseDto> result = activitiesController.getDataActivitiesInactive();

        verify(activitiesService, times(1)).findAllInactive();
        assertEquals(result.collectList().block(), Flux.just(new ActivitiesResponseDto(), new ActivitiesResponseDto()).collectList().block());
    }

    @Test
    void findById() {
        when(activitiesService.findById(1)).thenReturn(Mono.just(new ActivitiesResponseDto()));

        Mono<ActivitiesResponseDto> result = activitiesController.getDataActivitiesById(1);

        verify(activitiesService, times(1)).findById(1);
        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findAll() {
        when(activitiesService.findAll()).thenReturn(Flux.just(new ActivitiesResponseDto(), new ActivitiesResponseDto()));

        Flux<ActivitiesResponseDto> result = activitiesController.getDataActivitiesComplete();

        verify(activitiesService, times(1)).findAll();
        StepVerifier.create(result)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void reactiveLogicalActivities() {
        when(activitiesService.reactiveLogicalActivities(1)).thenReturn(Mono.just(new ActivitiesResponseDto()));

        Mono<ActivitiesResponseDto> result = activitiesController.reactiveLogicalActivities(1);

        verify(activitiesService, times(1)).reactiveLogicalActivities(1);
        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void deleteActivities() {
        when(activitiesService.deleteActivities(1)).thenReturn(Mono.empty());

        Mono<Void> result = activitiesController.deleteTotalActivities(1);

        verify(activitiesService, times(1)).deleteActivities(1);
        StepVerifier.create(result)
                .verifyComplete();
    }

}