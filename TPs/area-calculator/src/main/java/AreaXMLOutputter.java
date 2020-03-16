public class AreaXMLOutputter{
    SumProvider area;

    public AreaXMLOutputter(SumProvider area){
        this.area = area;
    }

    public String output(){
        return "<area>" + area.sum() + "</area>";
    }
}
