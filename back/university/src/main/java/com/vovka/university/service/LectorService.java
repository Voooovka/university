package com.vovka.university.service;

import com.vovka.university.model.Lector;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface LectorService {

    List<Lector> getLectorsByUserQuery(String userQuery);
    Boolean promoteLector(Long lectorId);
    Lector updateLectorName(Long lectorId, String newName);
}
