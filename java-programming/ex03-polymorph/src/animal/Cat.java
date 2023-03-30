package animal;

public class Cat extends Animal {

	public Cat() {
		super();
		this.setVoice("Meow");
		this.setFood("None");
	}
	
	public Cat(String name) {
		super(name);
		this.setVoice("Meow");
		this.setFood("None");
	}
	
	public void meow() {
		System.out.println(this.getName()+": "+this.getVoice());
	}
}
