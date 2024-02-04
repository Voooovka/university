package com.vovka.university.service.impl;

import com.vovka.university.exeption.AppException;
import com.vovka.university.exeption.EventType;
import com.vovka.university.model.Lector;
import com.vovka.university.model.enums.Degree;
import com.vovka.university.repository.LectorRepository;
import com.vovka.university.service.LectorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {

    private final LectorRepository lectorRepository;


    @Override
    public List<Lector> getLectorsByUserQuery(String userQuery) {
        return lectorRepository.getLectorsByUserQuery(userQuery);
    }

    @Override
    public Boolean promoteLector(Long lectorId) {
        Lector lector = lectorRepository.findById(lectorId)
                .orElseThrow(() -> new AppException(EventType.NOT_FOUND_EXCEPTION, "Lector not found with id: " + lectorId));
        if (lector.getDegree() != Degree.PROFESSOR) {
            lector.setDegree(getNextDegree(lector.getDegree()));
            lectorRepository.save(lector);
            return true;
        }
        return false;
    }

    @Override
    public Lector updateLectorName(Long lectorId, String newName) {
        Lector lector = lectorRepository.findById(lectorId)
                .orElseThrow(() -> new AppException(EventType.NOT_FOUND_EXCEPTION, "Lector not found with id: " + lectorId));
        lector.setName(newName);
        return lectorRepository.save(lector);
    }

    private Degree getNextDegree(Degree currentDegree) {
        return switch (currentDegree) {
            case ASSISTANT -> Degree.ASSOCIATE_PROFESSOR;
            case ASSOCIATE_PROFESSOR -> Degree.PROFESSOR;
            default -> currentDegree;
        };
    }
}
