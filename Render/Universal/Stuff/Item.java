package Universal.Stuff;

public abstract class Item {
    private String name;
    private String description;
    private int number = 1;

    public void add(int number){
        this.number=+number;
    }
    public void remove(int number) throws Exception{
        if (number> this.number) throw new Exception("notEnoughItem");
        this.number = this.number-number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
