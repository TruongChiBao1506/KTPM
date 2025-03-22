package iuh.fit.se.bai3;

public class Test {
    public static void main(String[] args) {
        XMLDataSystemOld xmlDataSystemOld = new XMLDataSystemOldImpl();
        XMLToJsonAdapter xmlToJsonAdapter = new XMLToJsonAdapter(xmlDataSystemOld);
        try {
            System.out.println(xmlToJsonAdapter.getJsonData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
