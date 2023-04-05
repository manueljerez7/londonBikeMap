package BikePoint.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BikePoint.service.IBikePoint;

@RestController
@RequestMapping("BikePoint")
public class BikePointController {
    Logger logger = LoggerFactory.getLogger(BikePointController.class);

    @Autowired
    private IBikePoint bikePointService;

    public BikePointController() {
    }

    // Devolver todos los puntos de bicicletas
    @GetMapping("/")
    public ArrayList<HashMap> getAllBikePoints() {
        ArrayList<HashMap> lista = bikePointService.obtenerAllBikePoints();
        return lista;
    }

    
}
