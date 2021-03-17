package softuni.unisports.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.unisports.enums.CategoryEnum;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.repository.CategoryRepository;
import softuni.unisports.repository.NewsRepository;
import softuni.unisports.repository.UserRepository;
import softuni.unisports.service.RoleService;
import softuni.unisports.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;

    public DBInit(UserService userService, RoleService roleService, UserRepository userRepository, NewsRepository newsRepository, CategoryRepository categoryRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initAdminUser();
        initCategories();
        initNews();
    }

    private void initCategories() {
        if (categoryRepository.count() == 0) {
            CategoryEntity football = new CategoryEntity();
            football.setName(CategoryEnum.FOOTBALL);
            this.categoryRepository.save(football);
        }
    }

    private void initNews() {
        if (this.newsRepository.count() == 0) {
            NewsEntity first = new NewsEntity();
            NewsEntity second = new NewsEntity();
            NewsEntity third = new NewsEntity();
            first.
                    setAddedOn(LocalDateTime.now()).
                    setAuthor(userRepository.findByUsername("admin").get()).
                    setCategory(this.categoryRepository.findByName(CategoryEnum.FOOTBALL).get()).
                    setViews(0).
                    setTitle("My First News Article").
                    setContent("This is my first news article.");

            second.
                    setAddedOn(LocalDateTime.now()).
                    setAuthor(userRepository.findByUsername("admin").get()).
                    setCategory(this.categoryRepository.findByName(CategoryEnum.FOOTBALL).get()).
                    setViews(0).
                    setTitle("My Second News Article").
                    setContent("Borussia Dortmunds U23 festigt mit einem Sieg beim FC Wegberg-Beeck die Tabellenführung in der Regionalliga West. Richmond Tachie und Florian Krebs trafen in der Schlussphase der zweiten Hälfte zum umjubelten Sieg. \n" +
                            "%n" +
                            "Ausgangslage: Der BVB ging als Tabellenführer in den 29. Spieltag der Regionalliga West. Die Gastgeber stecken im Abstiegskampf und belegten vor Anpfiff Rang 15. Mit einem Sieg konnte der BVB den Abstand auf RW Essen, die erst am Sonntag spielen, auf sechs Punkte ausbauen.  %n" +
                            "%n" +
                            "Personal: BVB-Trainer Enrico Maaßen musste auf einige Stammkräfte verzichten. Alaa Bakir hatte gegen Wiedenbrück die fünfte Gelbe Karte gesehen. Steffen Tigges war mit den Profis nach München geflogen und Dominik Wanner, Patrick Osterhage, Moritz Broschinski sowie Taylan Duman fehlten verletzungsbedingt. %n" +
                            "%n" +
                            "Spielverlauf: Bei strahlendem Sonnenschein im Beecker Waldstadion bestimmte die Dortmunder Mannschaft schon früh das Spielgeschehen. Die Gastgeber konzentrierten sich zunächst auf die Defensivarbeit, der BVB hatte viel Ballbesitz in den ersten Minuten. Fünf Minuten dauerte es bis zum ersten Torabschluss von Kapitän Franz Pfanne. Sein Versuch ging knapp über das Tor. Während der BVB mutig den Weg nach vorne suchte, spielten die Gastgeber nach zehn absolvierten Minuten bereits auf Zeit und versuchten, den Spielrhythmus auch durch kleine Fouls zu stören. Nach einer knappen halben Stunde traf Richmond Tachie per Kopf nach einer Ecke die Querlatte. Bis dahin die beste Möglichkeit im Spiel. Aber auch die Gastgeber hatten in dieser Phase ihre erste große Torchance. Der Schuss von Jannik Mause ging ebenfalls an die Querlatte und von dort ins Toraus. Die Spielminuten verstrichen, sodass es mit 0:0 in die Pause ging. %n" +
                            "%n" +
                            "Der BVB startete druckvoll und mit viel Zug zum gegnerischen Tor in die zweiten 45 Minuten. Kolbeinn Finnsson verzog nach nur wenigen Augenblicken aus spitzem Winkel. Die Maaßen-Elf arbeitete am Führungstreffer, setzte sich in der gegnerischen Hälfte fest. Florian Krebs und Richmond Tachie scheiterten in Minute 52 kurz nacheinander am stark reagierenden Wegberger Torhüter. Die jungen Borussen rannten unermüdlich an, in den gefährlichen Situationen fehlte aber das Spielglück. Immer wieder war ein gegnerischer Gegenspieler dazwischen. Wegberg kam kaum noch aus der eigenen Hälfte heraus, Luca Unbehaun im Dortmunder Tor blieb nahezu beschäftigungslos. Als Ansgar Knauff nach 75 Minuten den Ball knapp am Tor vorbei setzte, blieb Schwarzgelb noch eine Viertelstunde, um zu einem Treffer zu kommen. Als sich Richmond Tachie noch einmal auf den Weg in Richtung Wegberger Tor aufmachte, wurde der Dortmunder Angreifer fünf Meter vor dem Tor zu Fall gebracht – Elfmeter. Tachie trat selbst an und verwandelte sicher zum längst verdienten 1:0. Die Gastgeber warfen nochmal alles nach vorne. In der Nachspielzeit eroberte Henri Weigelt den Ball, steckte durch auf Richmond Tachie, der vor dem Tor auf Florian Krebs querlegte, der das Spiel mit dem Treffer zum 2:0 entschied.%n" +
                            "%n" +
                            "Stimmen zum Spiel:%n" +
                            "%n" +
                            "Enrico Maaßen: „Das wichtigste ist, dass wir das Spiel gewonnen haben. Das war unser oberstes Ziel. Wir wussten, dass es eine schwierige Angelegenheit wird. Wir hatten ein paar Veränderungen bei uns durch die vielen Ausfälle. Wir hatten einen guten Plan, konnten den Gegner auf dem Platz aber nicht so gut bewegen. Es ist eine große Weiterentwicklung von uns, dass wir unser Spiel auf Knopfdruck umstellen konnten. Wenn du so viele Situationen im letzten Drittel hast, dann fällt auch das Tor.“%n" +
                            "%n" +
                            "Florian Krebs: „Wir sind glücklich, dass wir hier die drei Punkte mitnehmen können. Es ist natürlich immer etwas anderes, wenn Spieler auf neuen Positionen zum Einsatz kommen. Wir haben das alle gut gemacht und so angenommen. Der Trainer ist auch glücklich. Wir haben verdient gewonnen.“%n" +
                            "%n" +
                            "Ausblick: Für Schwarzgelb geht es am kommenden Samstag zu RW Ahlen. Anstoß ist um 14 Uhr.%n" +
                            "%n" +
                            "Team & Tore %n" +
                            "%n" +
                            "BVB U23: Unbehaun, Maloney, Hippe, Hober (78. Ercan), Tachie (89. Mackrekis), Finnsson (78. Bah-Traoré), Pfanne, Krebs, Dams, Knauff, Raschl (70. Weigelt) %n" +
                            "%n" +
                            "Tore: 0:1 Tachie (87. FE), 0:2 Krebs (90.)" +
                            "%n" +
                            "Source: https://www.bvb.de/News/Uebersicht/Ein-hartes-Stueck-Arbeit-U23-schlaegt-Wegberg-Beeck");

            third.
                    setAddedOn(LocalDateTime.now()).
                    setAuthor(userRepository.findByUsername("admin").get()).
                    setCategory(this.categoryRepository.findByName(CategoryEnum.FOOTBALL).get()).
                    setViews(0).
                    setTitle("My Third News Article").
                    setContent("This is my third news article.");

            this.newsRepository.saveAll(List.of(first, second, third));
        }
    }

    private void initRoles() {
        this.roleService.seedRoles();
    }

    private void initAdminUser() {
        if (userRepository.count() == 0) {
            UserEntity adminUser = new UserEntity();
            RoleEntity adminRole = this.roleService.getRole(RoleEnum.ADMIN);
            RoleEntity moderatorRole = this.roleService.getRole(RoleEnum.MODERATOR);
            RoleEntity userRole = this.roleService.getRole(RoleEnum.USER);

            adminUser.
                    setUsername("admin").
                    setEmail("admin@unisports.com").
                    setFirstName("Pesho").
                    setLastName("Peshov").
                    setPassword(passwordEncoder.encode("12345")).
                    setRoles(List.of(adminRole, moderatorRole, userRole));

            this.userService.seedUsers(List.of(adminUser));
        }
    }
}
