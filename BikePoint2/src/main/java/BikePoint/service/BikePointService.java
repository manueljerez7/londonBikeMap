package BikePoint.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import BikePoint.model.AdditionalProperty;
import BikePoint.model.Root2;

@Service
public class BikePointService implements IBikePoint{
    Logger logger = LoggerFactory.getLogger(BikePointService.class);

    @Autowired
    private WebClient client;

    //public BikePointService(WebClient.Builder builder) {
        //this.client = builder.baseUrl("https://api.tfl.gov.uk").build();
    //}

    public ArrayList<HashMap> obtenerAllBikePoints() {
        
        String response = client.get()
            .uri("https://api.tfl.gov.uk/BikePoint?app_key=b4e67550077f4ad7b4b1c19332de0e3d")
            .retrieve()
            .bodyToMono(String.class)  
            .block();
              
            try {
                ObjectMapper mapper = new ObjectMapper();
                Root2[] jsonObj = mapper.readValue(response, Root2[].class);
                
                ArrayList<HashMap> listaDevolver = new ArrayList<HashMap>();

                for (Root2 elemBase : jsonObj) {
                    HashMap<String, String> lista = new HashMap<String, String>();
                    lista.put("id", elemBase.id);
                    lista.put("commonName", elemBase.commonName);
                    lista.put("lat", "" + elemBase.lat);
                    lista.put("lon", "" + elemBase.lon);
                    

                    ArrayList<AdditionalProperty> propiedades = elemBase.additionalProperties;
                    for (AdditionalProperty propiedad : propiedades) {
                    if (propiedad.key.equals("NbBikes")) {
                        lista.put("NbBikes", propiedad.value);
                    }
                    if (propiedad.key.equals("NbDocks")) {
                        lista.put("NbDocks", propiedad.value);
                    }
                    if (propiedad.key.equals("NbEmptyDocks")) {
                        lista.put("NbEmptyDocks", propiedad.value);
                    }

                   
                    
                }
                listaDevolver.add(lista);
                }

                return listaDevolver;

            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
            
    }  


 
}