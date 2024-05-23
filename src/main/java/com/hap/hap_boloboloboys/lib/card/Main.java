package com.hap.hap_boloboloboys.lib.card;

// public class Main {
//     public static void main(String[] args) {
//         /////////////// Test effect ///////////////
//         // Create some animal objects
//         Animal HiuDarat = new Carnivore("Hiu Darat");
//         Animal Sapi = new Herbivore("Sapi");
//         Sapi.setWeight(40);
//         Animal bear = new Omnivore("Beruang");

//         // Create plant object
//         Plant BijiJagung = new Plant("Biji Jagung");
//         BijiJagung.setAge(10); // Set initial age for demonstration

//         // Create some item objects
//         Item accelerate = new Accelerate("Accelerate");
//         Item delay = new Delay("Delay");
//         Item instantHarvest = new InstantHarvest("Instant Harvest");
//         Item protect = new Protect("Protect");
//         Item trap = new Trap("Trap");

//         // Apply effects to the animals
//         System.out.println("Hiu Darat's weight before Accelerate: " + HiuDarat.getWeight());
//         System.out.println("Applying Accelerate to Hiu Darat:");
//         accelerate.applyEffect(HiuDarat);
//         protect.applyEffect(HiuDarat);
//         System.out.println("Hiu Darat's weight after Accelerate: " + HiuDarat.getWeight());
//         // print effect name applied on hiu darat
//         for (int i = 0; i < 6; i++) {
//             if (HiuDarat.getEffectValue(i) > 0) {
//                 System.out.println("Effect applied: " + HiuDarat.getEffectName(i));
//             }
//         }
//         System.out.println("Hiu Darat's image path: " + HiuDarat.getImgPath());

//         System.out.println("\nSapi's weight before Delay: " + Sapi.getWeight());
//         System.out.println("Applying Delay to Sapi:");
//         delay.applyEffect(Sapi);
//         System.out.println("Sapi's weight after Delay: " + Sapi.getWeight());
//         System.out.println("Sapi's image path: " + Sapi.getImgPath());

//         System.out.println("\nApplying Instant Harvest to Biji Jagung:");
//         instantHarvest.applyEffect(BijiJagung);
//         System.out.println("Biji Jagung's age after Instant Harvest: " + BijiJagung.getAge());
//         System.out.println("Biji Jagung's image path: " + BijiJagung.getImgPath());

//         System.out.println("\nApplying Protect to Hiu Darat:");
//         protect.applyEffect(HiuDarat);
//         System.out.println("Is Hiu Darat protected? " + (HiuDarat.getEffectValue(4) > 0));

//         System.out.println("\nApplying Trap to Beruang:");
//         trap.applyEffect(bear);
//         System.out.println("Is Beruang trapped? " + (bear.getEffectValue(5) > 0));

//         // Test harvesting
//         HiuDarat.setWeight(20); // Set weight to meet harvest target
//         Product hiuDaratProduct = HiuDarat.harvest();
//         printProductDetails(hiuDaratProduct);

//         Sapi.setWeight(10); // Set weight to meet harvest target
//         Product sapiProduct = Sapi.harvest();
//         printProductDetails(sapiProduct);

//         BijiJagung.setAge(3); // Set age to meet harvest target
//         Product jagungProduct = BijiJagung.harvest();
//         printProductDetails(jagungProduct);
//     }

//     private static void printProductDetails(Product product) {
//         if (product != null) {
//             System.out.println("Product: " + product.getCardName());
//             System.out.println("Price: " + product.getPrice());
//             System.out.println("Added Weight: " + product.getAddedWeight());
//             System.out.println("Image Path: " + product.getImgPath());
//             System.out.println();
//         } else {
//             System.out.println("No product harvested.");
//         }
//     }
// }


//         // /////////////// Test harvest ///////////////
//         // // Test Carnivore
//         // Carnivore carnivore = new Carnivore("Hiu Darat");
//         // // set weight until harvestable
//         // carnivore.setWeight(20);
//         // printCreatureDetails(carnivore);
//         // Product carnivoreProduct = carnivore.harvest();
//         // printProductDetails(carnivoreProduct);

//         // // Test Herbivore
//         // Herbivore herbivore = new Herbivore("Sapi");
//         // // set weight until harvestable
//         // herbivore.setWeight(10);
//         // printCreatureDetails(herbivore);
//         // Product herbivoreProduct = herbivore.harvest();
//         // printProductDetails(herbivoreProduct);

//         // // Test Omnivore
//         // Omnivore omnivore = new Omnivore("Domba");
//         // // set weight until harvestable
//         // omnivore.setWeight(12);
//         // printCreatureDetails(omnivore);
//         // Product omnivoreProduct = omnivore.harvest();
//         // printProductDetails(omnivoreProduct);

//         // // Kuda
//         // Herbivore kuda = new Herbivore("Kuda");
//         // // set weight until harvestable
//         // kuda.setWeight(14);
//         // printCreatureDetails(kuda);
//         // Product kudaProduct = kuda.harvest();
//         // printProductDetails(kudaProduct);

//         // // Ayam
//         // Omnivore ayam = new Omnivore("Ayam");
//         // // set weight until harvestable
//         // ayam.setWeight(5);
//         // printCreatureDetails(ayam);
//         // Product ayamProduct = ayam.harvest();
//         // printProductDetails(ayamProduct);

//         // // Beruang
//         // Carnivore beruang = new Carnivore("Beruang");
//         // // set weight until harvestable
//         // beruang.setWeight(25);
//         // printCreatureDetails(beruang);
//         // Product beruangProduct = beruang.harvest();
//         // printProductDetails(beruangProduct);

//         // // Test Plant
//         // Plant jagung = new Plant("Biji Jagung");
//         // // set age until harvestable
//         // jagung.setAge(3);
//         // printCreatureDetails(jagung);
//         // Product jagungProduct = jagung.harvest();
//         // printProductDetails(jagungProduct);

//         // Plant labu = new Plant("Biji Labu");
//         // // set age until harvestable
//         // labu.setAge(5);
//         // printCreatureDetails(labu);
//         // Product labuProduct = labu.harvest();
//         // printProductDetails(labuProduct);

//         // Plant stroberi = new Plant("Biji Stroberi");
//         // // set age until harvestable
//         // stroberi.setAge(4);
//         // printCreatureDetails(stroberi);
//         // Product stroberiProduct = stroberi.harvest();
//         // printProductDetails(stroberiProduct);
// //     }

// //     private static void printCreatureDetails(Creature creature) {
// //         System.out.println("Creature: " + creature.getCardName());
// //         System.out.println("Image Path: " + creature.getImgPath());
// //     }

// //     private static void printProductDetails(Product product) {
// //         if (product != null) {
// //             System.out.println("Product: " + product.getCardName());
// //             System.out.println("Price: " + product.getPrice());
// //             System.out.println("Added Weight: " + product.getAddedWeight());
// //             System.out.println("Image Path: " + product.getImgPath());
// //             System.out.println();
// //         } else {
// //             System.out.println("No product harvested.");
// //         }
// //     }
// // }
