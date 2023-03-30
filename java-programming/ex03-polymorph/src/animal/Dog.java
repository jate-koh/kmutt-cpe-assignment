package animal;

public class Dog extends Animal {

	public Dog() {
		super();
		this.setVoice("Woof");
		this.setFood("Cat");
	}
	
	public Dog(String name) {
		super(name);
		this.setVoice("Woof");
		this.setFood("Cat");
	}
	
	public void bark() {
		System.out.println(this.getName()+": "+this.getVoice());
	}
}
