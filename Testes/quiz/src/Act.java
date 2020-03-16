public abstract class Act {
    String name, country;


    public Act(String name, String country){
        this.name = name;
        this.country = country;
    }

    public String getName(){
        return name;
    }

    public String getCountry(){
        return country;
    }

}
