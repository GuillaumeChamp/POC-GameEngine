package OutDoor.Loader;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public abstract class Loader {
    /**
     * Create an object from the file name and save it
     * @param name name of the file to read
     */
    abstract void load(String name);

    /**
     * Save the specified object in a file. Called during load.
     * @param obj object to save
     * @param name name of the file (most of the time the name of the object)
     */
    protected void save(Object obj,String name){
        try {
            FileOutputStream save = new FileOutputStream(name);
            ObjectOutputStream saver =new ObjectOutputStream(save);
            saver.writeObject(obj);
            save.close();
        }catch (Exception ee){
            ee.printStackTrace();
        }
    }

    /**
     * Convert the specific String list from a reader to an int list
     * @param string String list to convert
     * @return the converted int list
     */
    protected int[] convert(String[] string){
        int[] ans = new int[string.length];
        for (int i=0;i<string.length;i++) {
            ans[i] = Integer.parseInt(string[i]);
        }
        return ans;
    }
}
//public static void main(String[] args) throws ParserConfigurationException,
//    SAXException, IOException {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document document = builder.parse(new File("employee.xml"));
//        List<Employee> employees = new ArrayList<>();
//        NodeList nodeList = document.getDocumentElement().getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                Element elem = (Element) node;
//                String firstname = elem.getElementsByTagName("firstname")
//                        .item(0).getChildNodes().item(0).getNodeValue();
//                String lastname = elem.getElementsByTagName("lastname").item(0)
//                        .getChildNodes().item(0).getNodeValue();
//                Double salary = Double.parseDouble(elem.getElementsByTagName("salary")
//                        .item(0).getChildNodes().item(0).getNodeValue());
//                employees.add(new Employee(firstname, lastname, salary));
//            }
//        }
//        for (Employee empl: employees)
//            System.out.println(empl.toString());
//    }
