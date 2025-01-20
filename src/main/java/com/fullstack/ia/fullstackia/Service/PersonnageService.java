package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.Controller.OllamaController;
import com.fullstack.ia.fullstackia.Dto.PersonnageDto;
import com.fullstack.ia.fullstackia.Entity.PersonnagesEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import com.fullstack.ia.fullstackia.Repository.PersonnagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonnageService {

    private final PersonnagesRepository personnagesRepository;
    private final ObjectMapper objectMapper;
    private final OllamaController ollama;

    public List<PersonnagesEntity> createPersonnage() throws IOException {
//            Requete a l'IA pour la génération des personnages.
            String fileContent = ollama.mainIaAskingFunction("/prompts/promptCreateCharacteres.txt");

//            Pour eviter la requête le resustat est dans un fichier texte
//            Path filePath = Path.of("src/main/resources/recordJson/presonnage.txt");
//            String fileContent = Files.readString(filePath);

//            Mise en majuscule du Sexe et du Role
            fileContent = fileContent.replace("homme", "HOMME");
            fileContent = fileContent.replace("femme", "FEMME");
            fileContent = fileContent.replace("suspect", "SUSPECT");
            fileContent = fileContent.replace("victime", "VICTIME");
            fileContent = fileContent.replace("temoin", "TEMOIN");
            fileContent = fileContent.replace("assassin", "ASSASSIN");

//            Récuprère uniquement le contenu qui ira dans le Json
            int start = fileContent.indexOf("[");
            int end = fileContent.indexOf("]");
//            String substring = fileContent.substring(start, end + 1);
            String substring = fileContent.substring(start + 1, end);
            substring = substring.replace("},", "};");
            String[] strings = substring.split(";");

//            List pour la récuperation le json de sortie.
            List<PersonnagesEntity> savedPersonnages = new ArrayList<>() ;

            for (String string : strings) {
                // Enregistrement des entrés Json
                PersonnageDto personnagesDto = objectMapper.readValue(string, PersonnageDto.class);
//                 Persistence des données
                PersonnagesEntity personnage = PersonnagesEntity.builder()
                        .nom(personnagesDto.nom())
                        .prenom(personnagesDto.prenom())
                        .age(personnagesDto.age())
                        .sexe(personnagesDto.sexe())
                        .role(personnagesDto.role())
                        .build();
                savedPersonnages.add(personnagesRepository.save(personnage));
                System.out.println(personnage);

            }
        return savedPersonnages;
    }






    // Requete a l'IA pour la génération des personnages.
//        String fileContent = ollama.mainIaAskingFunction("/prompts/promptCreateCharacteres.txt");

    // Pour eviter la requête le resustat est dans un fichier texte
//        Path filePath = Path.of("src/main/resources/recordJson/presonnage.txt");
//        String fileContent = Files.readString(filePath);
//
//        // Remplacement numérisation de homme et femme dans les catégorie sexe
//        fileContent = fileContent.replace("homme", "HOMME");
//        fileContent = fileContent.replace("femme", "FEMME");
//
//        // Récuprère uniquement le contenu qui ira dans le Json
//        int start = fileContent.indexOf("[");
//        int end = fileContent.indexOf("]");
//        String substring = fileContent.substring(start, end + 1);
//        // Tranforme le String en Objet Json
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonObject =  mapper.readTree(substring);
//
//        // Boucle pour la percistence des données
//        for (int i = 0; i < jsonObject.size(); i++) {
////            PersonnageDto personnageDto = mapper.treeToValue(jsonObject.get(i), PersonnageDto.class);
////            PersonnagesEntity personnagesEntity = new PersonnagesEntity.builder()
////                    .nom(personnagesDto.nom())
////                    .prenom(personnagesDto.prenom())
////                    .age(personnagesDto.age())
////                    .sexe(personnagesDto.sexe())
////                    .role(personnagesDto.role())
////                    .mobile(personnagesDto.mobile())
////                    .alibi(personnagesDto.alibi())
////                    .caractere(personnagesDto.caractere())
////                    .build();
////
////            return personnagesRepository.save(personnages);
////        }
////        return jsonObject.size();
//
//
//////        return mapper.readValue(substring, PersonnageDto.class);
////        List<PersonnageDto> listPersonnage;
////        listPersonnage = mapper.readValue(substring, new TypeReference<List<PersonnageDto>>(){});
////        return listPersonnage.get(0);
//
////        ObjectMapper mapper = new ObjectMapper();
////        List<PersonnageDto> listPersonnage = mapper.readValue(substring, new TypeReference<>() {});
////        return listPersonnage;
//
////        ObjectMapper mapper = new ObjectMapper();
////        JsonNode jsonNode = mapper.readTree(substring);
////        String name = jsonNode.get("name").asText();
////        int age = jsonNode.get("age").asInt();
////        return "Name : " + name + " Age : " + age ;
////        } else {
////            return "Les indices sont invalides.";
////        }
//

}
