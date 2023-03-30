package animal;

public class Human extends Animal {

	public Human() {
		super();
		this.setVoice("Hello");
		this.setFood("Everything");
		this.setTalk(true);
	}
	
	public Human(String name) {
		super(name);
		this.setVoice("Hello");
		this.setFood("Everything");
		this.setTalk(true);
	}
	
//	public void talk() {
//		System.out.println(this.getName()+this.getClass().getSimpleName()+" Can't Talk!");
//	}
	
	public void greet() {
		System.out.println(this.getName()+": "+this.getVoice());
	}
}
