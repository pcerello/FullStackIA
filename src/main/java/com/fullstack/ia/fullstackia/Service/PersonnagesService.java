package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.Entity.Personnages;
import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import com.fullstack.ia.fullstackia.Repository.PersonnagesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PersonnagesService implements CommandLineRunner {

        private final PersonnagesRepository personnagesRepository;

        public PersonnagesService(PersonnagesRepository personnagesRepository) {
            this.personnagesRepository = personnagesRepository;

        }



        @Override
        public void run(String... args) throws Exception {
            Personnages personnages = new Personnages("Jean", "Dupont",
                    25, Sexe.HOMME, Role.ASSASSIN, "calme", "chez lui", "Jaloux de son voisin");
            personnagesRepository.save(personnages);
        }
}
