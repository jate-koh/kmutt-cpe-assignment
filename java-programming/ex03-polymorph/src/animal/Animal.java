package animal;

public abstract class Animal {
	
	private String voice;
	private String name;
	private String food;
	private boolean canTalk = false;
	
	public Animal() {
		this.name = "Noname";
	}
	
	public Animal(String name) {
		this.name = name;
	}
	
	public void talk() {
		if(canTalk) System.out.println(this.name+" ( "+this.getClass().getSimpleName()+" ) Can Talk!");
		else System.out.println(this.name+" ( "+this.getClass().getSimpleName()+" ) Can't Talk");
	}
	
	public void eat() {
		System.out.println(this.name+": "+this.getClass().getSimpleName()+" Eat "+this.food);
	}
	
	public void eat(Animal animal) {
		System.out.println(this.name+": "+this.getClass().getSimpleName()+" Eat "+animal.getClass().getSimpleName());
	}
	
	public void setTalk(boolean talk) {
		this.canTalk = talk;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setVoice(String voice) {
		this.voice = voice;
	}
	
	public void setFood(String food) {
		this.food = food;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getVoice() {
		return this.voice;
	}
	
	public String getFood() {
		return this.food;
	}
	
}
