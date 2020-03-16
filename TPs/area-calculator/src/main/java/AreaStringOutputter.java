public class AreaStringOutputter{
    SumProvider area;

    public AreaStringOutputter(SumProvider area){
        this.area = area;
    }

    public String output() {
        return "Sum of areas: " + area.sum();
    }

}
