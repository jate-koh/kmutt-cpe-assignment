package main;

import animal.*;

public class Main {

	public static void main(String args[]) {
		
		/* Exercise */ 
		
		System.out.println("*** Exercise ***");
		Animal[] animal;
		animal = new Animal[10];
		
		Dog dog;
		Cat cat;
		Human human;
		animal[0] = dog = new Dog("Goofy");
		animal[1] = cat = new Cat("Tom");
		animal[2] = human = new Human("Rick");
		
		human.eat(dog);
		animal[0].eat();
		
		dog.eat();
		
		dog.setFood("Human");
		dog.eat();
		
		dog.eat(cat);
		
		((Dog) animal[0]).bark();
		//animal[0].bark();
		//((Dog) animal[1]).bark();
		
		for(Animal itr: animal) {
			if(itr != null) {
				if(itr instanceof Cat) ((Cat) itr).meow();
				else if (itr instanceof Dog) ((Dog) itr).bark();
				else if (itr instanceof Human) ((Human) itr).greet();
			}
		}
		
		/* Assignment */
		
		animal[0] = dog = new Dog();
		animal[1] = dog = new Dog("Ashley");
		animal[2] = cat = new Cat("Tom");
		animal[3] = cat = new Cat("Thomas");
		animal[4] = human = new Human("Rick");
		animal[5] = human = new Human("Finn");
		animal[6] = dog = new Dog("Jake");
		animal[7] = cat = new Cat();
		
		System.out.println("*** Assignment ***");
		int i = 0;
		for(Animal itr: animal) {
			if(itr != null) {
				System.out.printf("[%d] ", i++);
				itr.talk();
			}
		}
		
		
	}
}
