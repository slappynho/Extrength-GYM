package extrengthsupplements.extrength;

import extrengthsupplements.extrength.Services.ImgFileServices;
import extrengthsupplements.extrength.models.*;
import extrengthsupplements.extrength.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class ExtrengthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtrengthApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData (ClientRepository clientRepository, ProductStorageRepository productStorageRepository, ProductRepository productRepository, WorkoutRepository workoutRepository, BillRepository billRepository,
									   BillSubscriptionRepository billSubscriptionRepository, SubscriptionRepository subscriptionRepository, ImgFileServices imgFIleServices){
		return (args) -> {

			imgFIleServices.init();
			Client Admin = new Client("Admin","Admin","admin@extrength.com",passwordEncoder.encode("admin"),true);
			Admin.setVerification(true);
			Client client = new Client("hola","hola","atorresmarambio@gmail.com",passwordEncoder.encode("hola123"),false);
			client.setVerification(true);
			Client client1 = new Client("maxi","maxi","maximiranda.95@gmail.com",passwordEncoder.encode("maxi"),true);
			client1.setVerification(true);
			Client client2 = new Client("enzo","maxi","enzito@gmail.com",passwordEncoder.encode("Enzito123"),true);
			client2.setVerification(true);

			Product sumplement0 = new Product("BCAA Molecular level","It is an excellent recuperator and also improves muscle growth. Provides metabolic energy to muscles and decreases protein breakdown during intense exercise" ,"/web/assets/images/products/bcaa.png",ProductCategory.SUPPLEMENTS,11,30,true);
			Product sumplement1 = new Product("Protein bar","Protein Bar, Chocolate Chip Cookie Dough Quest Bar 100% WHEY " ,"/web/assets/images/products/prote2.png",ProductCategory.SUPPLEMENTS,7,80,true);
			Product sumplement2 = new Product("WHEY GOLD PROTEIN BAR","WHEY GOLD STANDARD CHOCOLATE PROTEIN BAR DOUBLE RICH CHOCOLATE" ,"/web/assets/images/products/barraProteinaON.webp",ProductCategory.SUPPLEMENTS,8,60,true);
			Product sumplement3 = new Product("Creatine STRENGTH AND ENDURANCE","Creatine for more strength and energy for all day long" ,"/web/assets/images/products/creatinaON.png",ProductCategory.SUPPLEMENTS,25,50,true);
			Product sumplement4 = new Product("Amino Energy","Citric Energetic drink for a boost of energy in any time!" ,"/web/assets/images/products/aminoenergyON.png",ProductCategory.SUPPLEMENTS,5,100,true);
			Product sumplement5 = new Product("Serious Mass","Weight gainer vanilla flavor, supports body building and weight gain goals " ,"/web/assets/images/products/ganadorDePesoON2.webp",ProductCategory.SUPPLEMENTS,20,30,true);
			Product sumplement6 = new Product("MultiVitamin","Vitamin C molecular level with omega 3" ,"/web/assets/images/products/multivitaminico.png",ProductCategory.SUPPLEMENTS,15,30,true);
			Product sumplement7 = new Product("Pre-Workout","Pre-Workout GOLD STANDARD Fruit Punch Flavor, for perfomance energy and focus " ,"/web/assets/images/products/preentrenoON.webp",ProductCategory.SUPPLEMENTS,18,80,true);
			Product sumplement8 = new Product("3Pack Pre Workout","A 3 pack pre-workout from Extrength factories, it comes with Lime,Fruit punch and blueberry" ,"/web/assets/images/products/preEntrenos.png",ProductCategory.SUPPLEMENTS,15,35,true);
			Product sumplement9 = new Product("Pre-workout Amino Energy","A pre-workout fueler for an instant energy booster" ,"/web/assets/images/products/preaminoenergyON.png",ProductCategory.SUPPLEMENTS,16,40,true);
			Product sumplement10 = new Product("Protein Powder Extrength","Protein is an essential macronutrient that helps build muscle, repair tissue, and make enzymes and hormones. Using protein powder may also aid weight loss and help people tone their muscles." ,"/web/assets/images/products/prote1.png",ProductCategory.SUPPLEMENTS,22,55,true);
			Product sumplement11 = new Product("GOLD Standard WHEY","For muscle support and repair, RICH CHOCOLATE" ,"/web/assets/images/products/proteinaON2.webp",ProductCategory.SUPPLEMENTS,18,45,true);
			productRepository.save(sumplement0);
			productRepository.save(sumplement1);
			productRepository.save(sumplement2);
			productRepository.save(sumplement3);
			productRepository.save(sumplement4);
			productRepository.save(sumplement5);
			productRepository.save(sumplement6);
			productRepository.save(sumplement7);
			productRepository.save(sumplement8);
			productRepository.save(sumplement9);
			productRepository.save(sumplement10);
			productRepository.save(sumplement11);


			Product menClothes0 = new Product("Training Hall Black Hoodie","Black Hoodie Training Hall 100% polliester","/web/assets/images/products/buzonegro1.png",ProductCategory.CLOTHES,39.95,25,true);
			Product menClothes1 = new Product("Training Hall White Hoodie","White Hoodie Training Hall 100% polliester","/web/assets/images/products/buzoblanco1.png",ProductCategory.CLOTHES,39.95,25,true);
			Product menClothes2 = new Product("Just Lift It Cap","Just Lift It Beige Nike Cap","/web/assets/images/products/gorra1.png",ProductCategory.CLOTHES,14.49,25,true);
			Product menClothes3 = new Product("Just Lift It Cap","Just Lift It Camo Extrength Cap","/web/assets/images/products/gorracam3.png",ProductCategory.CLOTHES,14.49,25,true);
			Product menClothes4 = new Product("Conquer White Tank Top","Conquer White Tank Top 100% polliester ","/web/assets/images/products/tank2.png",ProductCategory.CLOTHES,10,25,true);
			Product menClothes5 = new Product("Extrength Gray Tank Top","Extrength Gray Tank Top 100% polliester","/web/assets/images/products/tank5.png",ProductCategory.CLOTHES,10,25,true);
			Product menClothes6 = new Product("Extrength White Short","Extrength White Short 'No Excuses' ¡Dont skip leg day!","/web/assets/images/products/short4.png",ProductCategory.CLOTHES,12,25,true);
			productRepository.save(menClothes0);
			productRepository.save(menClothes1);
			productRepository.save(menClothes2);
			productRepository.save(menClothes3);
			productRepository.save(menClothes4);
			productRepository.save(menClothes5);
			productRepository.save(menClothes6);

			Product womenClothes0 = new Product("Training white short","Training white short 'extrength' 100% polliester","/web/assets/images/products/shortM13.png",ProductCategory.CLOTHES,39.95,25,true);
			Product womenClothes1 = new Product("Training black short","Training black short 'extrength' 100% polliester","/web/assets/images/products/shortM12.png",ProductCategory.CLOTHES,39.95,25,true);
			Product womenClothes2 = new Product("White tank top","Extrength Gray Tank Top 100% polliester","/web/assets/images/products/tankM3.png",ProductCategory.CLOTHES,14.49,25,true);
			Product womenClothes3 = new Product("Blue navy yogger","Navy Blue 'extrength' yogger ","/web/assets/images/products/pantalonM6.png",ProductCategory.CLOTHES,14.49,25,true);
			Product womenClothes4 = new Product("Blue navy yogger","Brown 'extrength' yogger ","/web/assets/images/products/tank8.png",ProductCategory.CLOTHES,10,25,true);

			productRepository.save(womenClothes0);
			productRepository.save(womenClothes1);
			productRepository.save(womenClothes2);
			productRepository.save(womenClothes3);
			productRepository.save(womenClothes4);




			Product equipments0 = new Product("Black Extrength Bottle","Black Extrength Bottle","/web/assets/images/products/botella.png",ProductCategory.EQUIPMENT,14.99,25,true);
			Product equipments1 = new Product("Olympic bar","4K Olympic bar, comes with grapplers","/web/assets/images/products/barraolimpica.png",ProductCategory.EQUIPMENT,44.99,55,true);
			Product equipments2 = new Product("Power Belt 'Universal'","Universal power belt","/web/assets/images/products/cintopower.png",ProductCategory.EQUIPMENT,14.99,15,true);
			Product equipments3 = new Product("10k Weigths","10K weigths","/web/assets/images/products/discofundicion.png",ProductCategory.EQUIPMENT,24.99,15,true);
			Product equipments4 = new Product("Yoga floors","A very soft floor for doing yoga","/web/assets/images/products/matyoga.png",ProductCategory.EQUIPMENT,11.99,19,true);
			Product equipments5 = new Product("10k Dumbbell","10K dumbbells","/web/assets/images/products/mancuernas.png",ProductCategory.EQUIPMENT,20,55,true);
			Product equipments6 = new Product("Shaker","Plastic shaker bottle, put your protein here!","/web/assets/images/products/shakerON.webp",ProductCategory.EQUIPMENT,12,50,true);
			Product equipments7 = new Product("Soga Speed","3 Meters regulable jumping rope","/web/assets/images/products/sogaspeed.png",ProductCategory.EQUIPMENT,12,50,true);


			productRepository.save(equipments0);
			productRepository.save(equipments1);
			productRepository.save(equipments2);
			productRepository.save(equipments3);
			productRepository.save(equipments4);
			productRepository.save(equipments5);
			productRepository.save(equipments6);
			productRepository.save(equipments7);
			
			Workout lesson0 = new Workout("Body Building","Michael Logan","Bodybuilding is the use of progressive resistance exercise to control and develop one's muscles (muscle building) by muscle hypertrophy for aesthetic purposes","Monday","9:00 AM");

			Workout lesson1 = new Workout("Yoga","Lisa Douglas"," Yoga is an ancient practice that involves physical poses, concentration, and deep breathing. A regular yoga practice can promote endurance, strength, calmness, flexibility, and well-being.","Tuesday","10:00 AM");

			Workout lesson2 = new Workout("Body Building","Michael Logan","Bodybuilding is the use of progressive resistance exercise to control and develop one's muscles (muscle building) by muscle hypertrophy for aesthetic purposes","Tuesday","12:00 AM");

			Workout lesson3 = new Workout("Spinning","Sarah Lennox","Indoor cycling, often called spinning, is a form of exercise with classes focusing on endurance, strength, intervals, high intensity (race days) and recovery, and involves using a special stationary exercise bicycle with a weighted flywheel in a classroom setting","Wednesday","9:30 AM");

			Workout lesson4 = new Workout("Kick-boxing","David Robinson","Kickboxing is a group of stand-up combat sports and a form of boxing based on kicking and punching. The combat takes place in a boxing ring, normally with boxing gloves, mouthguards, shorts, and bare feet to favor the use of kicks. Kickboxing is practiced for self-defense, general fitness, or for competition.","Thursday","11:00 AM");

			Workout lesson5 = new Workout("Crossfit","Sarah Lennox","A form of high intensity interval training, CrossFit is a strength and conditioning workout that is made up of functional movement performed at a high intensity level. These movements are actions that you perform in your day-to-day life, like squatting, pulling, pushing etc","Friday","9:00 AM");

			workoutRepository.save(lesson0);
			workoutRepository.save(lesson1);
			workoutRepository.save(lesson2);
			workoutRepository.save(lesson3);
			workoutRepository.save(lesson4);
			workoutRepository.save(lesson5);




			Subscription sub1 = new Subscription(SubscriptionTypes.BASIC,29, LocalDate.now(),LocalDate.now().plusMonths(1));
			Subscription sub2 = new Subscription(SubscriptionTypes.STANDARD,49,LocalDate.now(),LocalDate.now().plusMonths(1));
			Subscription sub3 = new Subscription(SubscriptionTypes.VIP,99,LocalDate.now(),LocalDate.now().plusMonths(1));
			Bill bill = new Bill(client1, 20.0, "000-087-543");
			BillSubscription billSubscription = new BillSubscription(client,sub1);

			clientRepository.save(Admin);
			clientRepository.save(client);
			clientRepository.save(client1);
			clientRepository.save(client2);
/*		client1.addWorkouts(lesson0);
		lesson0.addClients(client1);*/




			subscriptionRepository.save(sub1);
			subscriptionRepository.save(sub2);
			subscriptionRepository.save(sub3);
			billSubscriptionRepository.save(billSubscription);
			billRepository.save(bill);
			client1.setBillSubscription(billSubscription);
			clientRepository.save(client1);

		};
	}
	}

