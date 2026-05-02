import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Solution {

    public static void main(String[] args){
        NEOM_Core system = new NEOM_Core();
        Scanner input = new Scanner(System.in);

        while(input.hasNext()){
            if(!input.hasNextInt()){
                System.out.println(Colors.RED + "[Error] Invalid commnad\t (1-6)"+ Colors.RESET);
                input.next();
                continue;
            }
            int command = input.nextInt();

            switch(command){
                case 1:
                    String filename = input.next();
                    System.out.println("[System] Reading "+ filename +" ...");
                    int count = bulkAddTasks(system, filename);
                    System.out.println("[System] "+count+ " tasks indexed successfully.");
                    break;

                case 2:
                    system.processNextTask();
                    break;

                case 3:
                    System.out.println(Colors.RED+"[Undo] Reversing last action...");
                    system.undoLastAction();
                    break;

                case 4:
                    System.out.println(Colors.YELLOW+"[Audit] Printing Global Sector Index: \n"+Colors.RESET);
                    system.systemAudit();
                    break;

                case 5:
                    System.out.println(Colors.GREEN+"[Deployment History]"+Colors.RESET);
                    system.printDeploymentHistory();
                    break;

                case 6:
                    int sectorID = input.nextInt();
                    system.searchSector(sectorID);
                    break;

                default:
                    System.out.println("Invalid command");
            }
        }
    }
    private static int bulkAddTasks(NEOM_Core system, String filename){
        int count = 0;
        try{
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()){ 
                String line = fileScanner.nextLine(); 
                if(line.trim().isEmpty()) continue; 
                String[] parts = line.split(",", 3); 
                if(parts.length < 3) continue;
                system.addTask(Integer.parseInt(parts[0].trim()), parts[1].trim(), parts[2].trim());
                count++;
            }
        } catch(FileNotFoundException e){
    
            System.out.println("File not found: " + filename);
    
        }    
        return count;
    }
}