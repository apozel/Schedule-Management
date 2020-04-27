package fr.isen.m1.schedule.random;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;
import fr.isen.m1.schedule.utilities.SocialDetails;
import fr.isen.m1.schedule.builder.AppointementBuilder;
import fr.isen.m1.schedule.builder.SocialDetailsBuilder;

public class RandomBuilder {

    Random rand = new Random();
    List<String> fisrtNamesList = new ArrayList<String>(Arrays.asList("thomas", "marie", "xavier",
            "jule", "flavien", "david", "antoine", "johan", "lucien", "benjamin", "julien", "Aaren",
            "Aarika", "Abagael", "Abagail", "Abbe", "Abbey", "Abbi", "Abbie", "Abby", "Abbye",
            "Abigael", "Abigail", "Abigale", "Abra", "Ada", "Adah", "Adaline", "Adan", "Adara",
            "Adda", "Addi", "Addia", "Addie", "Addy", "Adel", "Adela", "Adelaida", "Adelaide",
            "Adele", "Adelheid", "Adelice", "Adelina", "Adelind", "Adeline", "Adella", "Adelle",
            "Adena", "Adey", "Adi", "Adiana", "Adina", "Adora", "Adore", "Adoree", "Adorne",
            "Adrea", "Adria", "Adriaens", "Adrian", "Adriana", "Adriane", "Adrianna", "Adrianne",
            "Adriena", "Adrienne", "Aeriel", "Aeriela", "Aeriell", "Afton", "Ag", "Agace", "Agata",
            "Agatha", "Agathe", "Aggi", "Aggie", "Aggy", "Agna", "Agnella", "Agnes", "Agnese",
            "Agnesse", "Agneta", "Agnola", "Agretha", "Aida", "Aidan", "Aigneis", "Aila", "Aile",
            "Ailee", "Aileen", "Ailene", "Ailey", "Aili", "Ailina", "Ailis", "Ailsun", "Ailyn",
            "Aime", "Aimee", "Aimil", "Aindrea", "Ainslee", "Ainsley", "Ainslie", "Ajay", "Alaine",
            "Alameda", "Alana", "Alanah", "Alane", "Alanna", "Alayne", "Alberta", "Albertina",
            "Albertine", "Albina", "Alecia", "Aleda", "Aleece", "Aleen", "Alejandra", "Alejandrina",
            "Alena", "Alene", "Alessandra", "Aleta", "Alethea", "Alex", "Alexa", "Alexandra",
            "Alexandrina", "Alexi", "Alexia", "Alexina", "Alexine", "Alexis", "Alfi", "Alfie",
            "Alfreda", "Alfy", "Ali", "Alia", "Alica", "Alice", "Alicea", "Alicia", "Alida",
            "Alidia", "Alie", "Alika", "Alikee", "Alina", "Aline", "Alis", "Alisa", "Alisha",
            "Alison", "Alissa", "Alisun", "Alix", "Aliza", "Alla", "Alleen", "Allegra", "Allene",
            "Alli", "Allianora", "Allie", "Allina", "Allis", "Allison", "Allissa", "Allix",
            "Allsun", "Allx", "Ally", "Allyce", "Allyn", "Allys", "Allyson", "Alma", "Almeda",
            "Almeria", "Almeta", "Almira", "Almire", "Aloise", "Aloisia", "Aloysia", "Alta",
            "Althea", "Alvera", "Alverta", "Alvina", "Alvinia", "Alvira", "Alyce", "Alyda", "Alys",
            "Alysa", "Alyse", "Alysia", "Alyson", "Alyss", "Alyssa", "Amabel", "Amabelle", "Amalea",
            "Amalee", "Amaleta", "Amalia", "Amalie", "Amalita", "Amalle", "Amanda", "Amandi",
            "Amandie", "Amandy", "Amara", "Amargo", "Amata", "Amber", "Amberly", "Ambur", "Ame",
            "Amelia", "Amelie", "Amelina", "Ameline", "Amelita", "Ami", "Amie", "Amii", "Amil",
            "Amitie", "Amity", "Ammamaria", "Amy", "Amye", "Ana", "Anabal", "Anabel", "Anabella",
            "Anabelle", "Analiese", "Analise", "Anallese", "Anallise", "Anastasia", "Anastasie",
            "Anastassia", "Anatola", "Andee", "Andeee", "Anderea", "Andi", "Andie", "Andra",
            "Andrea", "Andreana", "Andree", "Andrei", "Andria", "Andriana", "Andriette",
            "Andromache", "Andy", "Anestassia", "Anet", "Anett", "Anetta", "Anette", "Ange",
            "Angel", "Angela", "Angele", "Angelia", "Angelica", "Angelika", "Angelina", "Angeline",
            "Angelique", "Angelita", "Angelle", "Angie", "Angil", "Angy", "Ania", "Anica", "Anissa",
            "Anita", "Anitra", "Anjanette", "Anjela", "Ann", "Ann-Marie", "Anna", "Anna-Diana",
            "Anna-Diane", "Anna-Maria", "Annabal", "Annabel", "Annabela", "Annabell", "Annabella",
            "Annabelle", "Annadiana", "Annadiane", "Annalee", "Annaliese", "Annalise", "Annamaria",
            "Annamarie", "Anne", "Anne-Corinne", "Anne-Marie", "Annecorinne", "Anneliese",
            "Annelise", "Annemarie", "Annetta", "Annette", "Anni", "Annice", "Annie", "Annis",
            "Annissa", "Annmaria", "Annmarie", "Annnora", "Annora", "Anny", "Anselma", "Ansley",
            "Anstice", "Anthe", "Anthea", "Anthia", "Anthiathia", "Antoinette", "Antonella",
            "Antonetta", "Antonia", "Antonie", "Antonietta", "Antonina", "Anya", "Appolonia",
            "April", "Aprilette", "Ara", "Arabel", "Arabela", "Arabele", "Arabella", "Arabelle",
            "Arda", "Ardath", "Ardeen", "Ardelia", "Ardelis", "Ardella", "Ardelle", "Arden",
            "Ardene", "Ardenia", "Ardine", "Ardis", "Ardisj", "Ardith", "Ardra", "Ardyce", "Ardys",
            "Ardyth", "Aretha", "Ariadne", "Ariana", "Aridatha", "Ariel", "Ariela", "Ariella",
            "Arielle", "Arlana", "Arlee", "Arleen", "Arlen", "Arlena", "Arlene", "Arleta",
            "Arlette", "Arleyne", "Arlie", "Arliene", "Arlina", "Arlinda", "Arline", "Arluene",
            "Arly", "Arlyn", "Arlyne", "Aryn", "Ashely", "Ashia", "Ashien", "Ashil", "Ashla",
            "Ashlan", "Ashlee", "Ashleigh", "Ashlen", "Ashley", "Ashli", "Ashlie", "Ashly", "Asia",
            "Astra", "Astrid", "Astrix", "Atalanta", "Athena", "Athene", "Atlanta", "Atlante",
            "Auberta", "Aubine", "Aubree", "Aubrette", "Aubrey", "Aubrie", "Aubry", "Audi", "Audie",
            "Audra", "Audre", "Audrey", "Audrie", "Audry", "Audrye", "Audy", "Augusta", "Auguste",
            "Augustina", "Augustine", "Aundrea", "Aura", "Aurea", "Aurel", "Aurelea", "Aurelia",
            "Aurelie", "Auria", "Aurie", "Aurilia", "Aurlie", "Auroora", "Aurora", "Aurore",
            "Austin", "Austina", "Austine", "Ava", "Aveline", "Averil", "Averyl", "Avie", "Avis",
            "Aviva", "Avivah", "Avril", "Avrit", "Ayn", "Bab", "Babara", "Babb", "Babbette",
            "Babbie", "Babette", "Babita", "Babs", "Bambi", "Bambie", "Bamby", "Barb", "Barbabra",
            "Barbara", "Barbara-Anne", "Barbaraanne", "Barbe", "Barbee", "Barbette", "Barbey",
            "Barbi", "Barbie", "Barbra", "Barby", "Bari", "Barrie", "Barry", "Basia", "Bathsheba",
            "Batsheva", "Bea", "Beatrice", "Beatrisa", "Beatrix", "Beatriz", "Bebe", "Becca",
            "Becka", "Becki", "Beckie", "Becky", "Bee", "Beilul", "Beitris", "Bekki", "Bel",
            "Belia", "Belicia", "Belinda", "Belita", "Bell", "Bella", "Bellanca", "Belle",
            "Bellina", "Belva", "Belvia", "Bendite", "Benedetta", "Benedicta", "Benedikta",
            "Benetta", "Benita", "Benni", "Bennie", "Benny", "Benoite", "Berenice", "Beret",
            "Berget", "Berna", "Bernadene", "Bernadette", "Bernadina", "Bernadine", "Bernardina",
            "Bernardine", "Bernelle", "Bernete", "Bernetta", "Bernette", "Berni", "Bernice",
            "Bernie", "Bernita", "Berny", "Berri", "Berrie", "Berry", "Bert", "Berta", "Berte",
            "Bertha", "Berthe", "Berti", "Bertie", "Bertina", "Bertine", "Berty", "Beryl", "Beryle",
            "Bess", "Bessie", "Bessy", "Beth", "Bethanne", "Bethany", "Bethena", "Bethina",
            "Betsey", "Betsy", "Betta", "Bette", "Bette-Ann", "Betteann", "Betteanne", "Betti",
            "Bettina", "Bettine", "Betty", "Bettye", "Beulah", "Bev", "Beverie", "Beverlee",
            "Beverley", "Beverlie", "Beverly", "Bevvy", "Bianca", "Bianka", "Bibbie", "Bibby",
            "Bibbye", "Bibi", "Biddie", "Biddy", "Bidget", "Bili", "Bill", "Billi", "Billie"));
    List<String> lastNamesList = new ArrayList<String>(Arrays.asList("dupont", "Smith", "Johnson",
            "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson",
            "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez", "Moore", "Martin", "Jackson",
            "Thompson", "White", "Lopez", "Lee", "Gonzalez", "Harris", "Clark", "Lewis", "Robinson",
            "Walker", "Perez", "Hall", "Young", "Allen", "Sanchez", "Wright", "King", "Scott",
            "Green", "Baker", "Adams", "Nelson", "Hill", "Ramirez", "Campbell", "Mitchell",
            "Roberts", "Carter", "Phillips", "Evans", "Turner", "Torres", "Parker", "Collins",
            "Edwards", "Stewart", "Flores", "Morris", "Nguyen", "Murphy", "Rivera", "Cook",
            "Rogers", "Morgan", "Peterson", "Cooper", "Reed", "Bailey", "Bell", "Gomez", "Kelly",
            "Howard", "Ward", "Cox", "Diaz", "Richardson", "Wood", "Watson", "Brooks", "Bennett",
            "Gray", "James", "Reyes", "Cruz", "Hughes", "Price", "Myers", "Long", "Foster",
            "Sanders", "Ross", "Morales", "Powell", "Sullivan", "Russell", "Ortiz", "Jenkins",
            "Gutierrez", "Perry", "Butler", "Barnes", "Fisher", "Henderson", "Coleman", "Simmons",
            "Patterson", "Jordan", "Reynolds", "Hamilton", "Graham", "Kim", "Gonzales", "Alexander",
            "Ramos", "Wallace", "Griffin", "West", "Cole", "Hayes", "Chavez", "Gibson", "Bryant",
            "Ellis", "Stevens", "Murray", "Ford", "Marshall", "Owens", "Mcdonald", "Harrison",
            "Ruiz", "Kennedy", "Wells", "Alvarez", "Woods", "Mendoza", "Castillo", "Olson", "Webb",
            "Washington", "Tucker", "Freeman", "Burns", "Henry", "Vasquez", "Snyder", "Simpson",
            "Crawford", "Jimenez", "Porter", "Mason", "Shaw", "Gordon", "Wagner", "Hunter",
            "Romero", "Hicks", "Dixon", "Hunt", "Palmer", "Robertson", "Black", "Holmes", "Stone",
            "Meyer", "Boyd", "Mills", "Warren", "Fox", "Rose", "Rice", "Moreno", "Schmidt", "Patel",
            "Ferguson", "Nichols", "Herrera", "Medina", "Ryan", "Fernandez", "Weaver", "Daniels",
            "Stephens", "Gardner", "Payne", "Kelley", "Dunn", "Pierce", "Arnold", "Tran", "Spencer",
            "Peters", "Hawkins", "Grant", "Hansen", "Castro", "Hoffman", "Hart", "Elliott",
            "Cunningham", "Knight", "Bradley", "Carroll", "Hudson", "Duncan", "Armstrong", "Berry",
            "Andrews", "Johnston", "Ray", "Lane", "Riley", "Carpenter", "Perkins", "Aguilar",
            "Silva", "Richards", "Willis", "Matthews", "Chapman", "Lawrence", "Garza", "Vargas",
            "Watkins", "Wheeler", "Larson", "Carlson", "Harper", "George", "Greene", "Burke",
            "Guzman", "Morrison", "Munoz", "Jacobs", "Obrien", "Lawson", "Franklin", "Lynch",
            "Bishop", "Carr", "Salazar", "Austin", "Mendez", "Gilbert", "Jensen", "Williamson",
            "Montgomery", "Harvey", "Oliver", "Howell", "Dean", "Hanson", "Weber", "Garrett",
            "Sims", "Burton", "Fuller", "Soto", "Mccoy", "Welch", "Chen", "Schultz", "Walters",
            "Reid", "Fields", "Walsh", "Little", "Fowler", "Bowman", "Davidson", "May", "Day",
            "Schneider", "Newman", "Brewer", "Lucas", "Holland", "Wong", "Banks", "Santos",
            "Curtis", "Pearson", "Delgado", "Valdez", "Pena", "Rios", "Douglas", "Sandoval",
            "Barrett", "Hopkins", "Keller", "Guerrero", "Stanley", "Bates", "Alvarado", "Beck",
            "Ortega", "Wade", "Estrada", "Contreras", "Barnett", "Caldwell", "Santiago", "Lambert",
            "Powers", "Chambers", "Nunez", "Craig", "Leonard", "Lowe", "Rhodes", "Byrd", "Gregory",
            "Shelton", "Frazier", "Becker", "Maldonado", "Fleming", "Vega", "Sutton", "Cohen",
            "Jennings", "Parks", "Mcdaniel", "Watts", "Barker", "Norris", "Vaughn", "Vazquez",
            "Holt", "Schwartz", "Steele", "Benson", "Neal", "Dominguez", "Horton", "Terry", "Wolfe",
            "Hale", "Lyons", "Graves", "Haynes", "Miles", "Park", "Warner", "Padilla", "Bush",
            "Thornton", "Mccarthy", "Mann", "Zimmerman", "Erickson", "Fletcher", "Mckinney", "Page",
            "Dawson", "Joseph", "Marquez", "Reeves", "Klein", "Espinoza", "Baldwin", "Moran",
            "Love", "Robbins", "Higgins", "Ball", "Cortez", "Le", "Griffith", "Bowen", "Sharp",
            "Cummings", "Ramsey", "Hardy", "Swanson", "Barber", "Acosta", "Luna", "Chandler",
            "Blair", "Daniel", "Cross", "Simon", "Dennis", "Oconnor", "Quinn", "Gross", "Navarro",
            "Moss", "Fitzgerald", "Doyle", "Mclaughlin", "Rojas", "Rodgers", "Stevenson", "Singh",
            "Yang", "Figueroa", "Harmon", "Newton", "Paul", "Manning", "Garner", "Mcgee", "Reese",
            "Francis", "Burgess", "Adkins", "Goodman", "Curry", "Brady", "Christensen", "Potter",
            "Walton", "Goodwin", "Mullins", "Molina", "Webster", "Fischer", "Campos", "Avila",
            "Sherman", "Todd", "Chang", "Blake", "Malone", "Wolf", "Hodges", "Juarez", "Gill",
            "Farmer", "Hines", "Gallagher", "Duran", "Hubbard", "Cannon", "Miranda", "Wang",
            "Saunders", "Tate", "Mack", "Hammond", "Carrillo", "Townsend", "Wise", "Ingram",
            "Barton", "Mejia", "Ayala", "Schroeder", "Hampton", "Rowe", "Parsons", "Frank",
            "Waters", "Strickland", "Osborne", "Maxwell", "Chan", "Deleon", "Norman", "Harrington",
            "Casey", "Patton", "Logan", "Bowers", "Mueller", "Glover", "Floyd", "Hartman",
            "Buchanan", "Cobb", "French", "Kramer", "Mccormick", "Clarke", "Tyler", "Gibbs",
            "Moody", "Conner", "Sparks", "Mcguire", "Leon", "Bauer", "Norton", "Pope", "Flynn",
            "Hogan", "Robles", "Salinas", "Yates", "Lindsey", "Lloyd", "Marsh", "Mcbride", "Owen",
            "Solis", "Pham", "Lang", "Pratt", "Lara", "Brock", "Ballard", "Trujillo", "Shaffer",
            "Drake", "Roman", "Aguirre", "Morton", "Stokes", "Lamb", "Pacheco", "Patrick",
            "Cochran", "Shepherd", "Cain", "Burnett", "Hess", "Li", "Cervantes", "Olsen", "Briggs",
            "Ochoa", "Cabrera", "Velasquez", "Montoya", "Roth", "Meyers", "Cardenas", "Fuentes",
            "Weiss", "Hoover", "Wilkins", "Nicholson", "Underwood", "Short", "Carson", "Morrow",
            "Colon", "Holloway", "Summers", "Bryan", "Petersen", "Mckenzie", "Serrano", "Wilcox",
            "Carey", "Clayton", "Poole", "Calderon", "Gallegos", "Greer", "Rivas", "Guerra",
            "Decker", "Collier", "Wall", "Whitaker", "Bass", "Flowers", "Davenport", "Conley",
            "Houston", "Huff", "Copeland", "Hood", "Monroe", "Massey", "Roberson", "Combs",
            "Franco", "Larsen", "Pittman", "Randall", "Skinner", "Wilkinson", "Kirby", "Cameron",
            "Bridges", "Anthony", "Richard", "Kirk", "Bruce", "Singleton", "Mathis", "Bradford",
            "Boone", "Abbott", "Charles", "Allison", "Sweeney", "Atkinson", "Horn", "Jefferson",
            "Rosales", "York", "Christian", "Phelps", "Farrell", "Castaneda", "Nash", "Dickerson",
            "Bond", "Wyatt", "Foley", "Chase", "Gates", "Vincent", "Mathews", "Hodge", "Garrison",
            "Trevino", "Villarreal", "Heath", "Dalton", "Valencia", "Callahan", "Hensley", "Atkins",
            "Huffman", "Roy", "Boyer", "Shields", "Lin", "Hancock", "Grimes", "Glenn", "Cline",
            "Delacruz", "Camacho", "Dillon", "Parrish", "Oneill", "Melton", "Booth", "Kane", "Berg",
            "Harrell", "Pitts", "Savage", "Wiggins", "Brennan", "Salas", "Marks", "Russo", "Sawyer",
            "Baxter", "Golden", "Hutchinson", "Liu", "Walter", "Mcdowell", "Wiley", "Rich",
            "Humphrey", "Johns", "Koch", "Suarez", "Hobbs", "Beard", "Gilmore", "Ibarra", "Keith",
            "Macias", "Khan", "Andrade", "Ware", "Stephenson", "Henson", "Wilkerson", "Dyer",
            "Mcclure", "Blackwell", "Mercado", "Tanner", "Eaton", "Clay", "Barron", "Beasley",
            "Oneal", "Preston", "Small", "Wu", "Zamora", "Macdonald", "Vance", "Snow", "Mcclain",
            "Stafford", "Orozco", "Barry", "English", "Shannon", "Kline", "Jacobson"));

            /**
             * the position of the appointement is randomly generated
             * @return random Appointement
             */
    public Appointement buildRandomAppointement() {
        AppointementBuilder appointement = new AppointementBuilder();
        return appointement.setPatient(buildRandomPatient()).setDoctor(buildRandomDoctor())
                .setDiagnosis(buildRandomDiagnosis())
                .setConsultTime(Duration.ofMinutes(rand.nextInt(60)))
                .setLocation(buildRandomPosition())
                .setDate(LocalDate.of(2020, rand.nextInt(12), rand.nextInt(28))).build();
    }

    public Diagnosis buildRandomDiagnosis() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setCriticite(rand.nextInt(10));
        diagnosis.setDescription("maladie");
        diagnosis.setPatientConserne(buildRandomPatient());
        return diagnosis;
    }

    public Doctor buildRandomDoctor() {
        Doctor doctor = new Doctor();
        doctor.setDetails(buildRandomSocialDetails());
        doctor.setEmplacement(buildRandomPosition());
        doctor.setCdhp(Integer.toString(rand.nextInt()));
        doctor.setLieuDeDepart(buildRandomPosition());
        return doctor;
    }

    public Patient buildRandomPatient() {
        Patient patient = new Patient();
        patient.setLieuDeVie(buildRandomPosition());
        patient.setDetails(buildRandomSocialDetails());
        return patient;
    }

    public Position buildRandomPosition() {
        return new Position(rand.nextDouble(), rand.nextDouble());
    }

    public SocialDetails buildRandomSocialDetails() {
        return new SocialDetailsBuilder().setGender(rand.nextBoolean())
                .setBirthZipCode(Integer.toString(rand.nextInt()))
                .setBirthDate(
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setBirthAddress("aix").setPhoneNumber(Integer.toString(rand.nextInt()))
                .setFirstName(fisrtNamesList.get(rand.nextInt(fisrtNamesList.size())))
                .setLastName(lastNamesList.get(rand.nextInt(lastNamesList.size()))).build();
    }

}
