import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class RoyalFamily
{
    static TNode<String> root = null;
  
    public static void main(String[] args)
    {
        //Define a variable to store the root node
        System.out.println('\u000C');
         root = null;
        
        //TODO: SETUP TREE DATA
        //1. Use Scanner to read the data.txt file
        //2. The first line in data.txt is the root node
        //3. For each line in data.txt in the format A > B
        //      - *find* the A node
        //      - add B as a child of A
        TNode<String> prev = null;
        File file = new File("data.txt");
        try{
            Scanner s = new Scanner(file);
            root = new TNode<String>(s.nextLine());
            prev = root;
            while(s.hasNextLine()){
                String line = s.nextLine();
               
                String[] splitLine = line.split(">");
                //splitLine[0] = parent
                //splitLine[1] = child
                // first search for parent
                TNode<String> curr = new TNode<String>(splitLine[1].trim());
                TNode<String> currParent = find(root, splitLine[0].trim());
                if(root.getData().equals(curr.getData()))
                    continue;
                if( currParent == null){
                    root.addChild(curr);
        
                }else if(currParent !=null){
                    // it was found and add a child to it. 
                    currParent.addChild(curr);
                }
            }

        }
        catch(java.io.FileNotFoundException e){
          
        }  
        //TODO: test printPath method
        TNode<String> child = find(root, "Princess Beatrice of York");

        String path = getPath( child );

    }
    
    /**
     *  @return node if its data matches name, or return a child node with data that matches name
     */
    public static TNode<String> find(TNode<String> node, String name)
    {
        //use recursion to check this node and all of its children to see if their data matches the specified name
        List<TNode<String>> x = node.getChildren();
        if(node.getData().equals(name)){
            return node;
        }
        for(TNode<String> nodex : x){
            TNode<String> c = find(nodex, name);
            if(c!=null)
                return c;
        }
        
        
        return null;
    }
    
    /**
     *  @return a String containing the path from the root node to the specified node, separated by ->
     */

    public static String getPath(TNode<String> node)
    {
        String path = "";
    if(node.getParent() == null){
        path += node.getData();
        return path;
    }else{
        path += " -> " + node.getData();
        return getPath(node.getParent()) + path;
    }
      
    }
    
}