package Fight.Alteration;

public class Alteration {
    String name;
    int duration;
    int time=0;
    int strength;

    /**
     * Create alteration based
     * @param name must be poisonI, sleep, death
     */
    public Alteration(String name){
        switch (name) {
            case "poisonI" -> {
                this.name = "poison";
                this.duration = 3;
                this.strength = 1;
            }
            case "sleep" -> {
                this.name = "sleep";
                this.duration = 3;
                this.strength = 1;
            }
            case "death" ->{
                this.name = "dead";
                this.duration = 9999;
            }
            default -> {
                System.out.println("try to create an alteration that not exist");
                this.name="none";
                this.duration= 99999;
                this.strength=0;
            }
        }
    }
    public void addTurn(){
        if (!this.name.equals("dead"))
        this.time++;
    }
    public boolean checkEnd(){
        return  (time >= duration);
    }
}
