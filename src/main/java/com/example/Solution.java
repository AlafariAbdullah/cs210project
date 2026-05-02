package com.example;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Solution {

    public static void main(String[] args){
        NEOM_Core system = new NEOM_Core();
        Scanner input = new Scanner(System.in);

        while(input.hasNext()){
            int command = input.nextInt();

            switch(command){
                case 1:
                    String filename = input.next();
                    bulkAddTasks(system, filename);
                    break;

                case 2:
                    system.processNextTask();
                    break;

                case 3:
                    system.undoLastAction();
                    break;

                case 4:
                    system.systemAudit();
                    break;

                case 5:
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
    private static void bulkAddTasks(NEOM_Core system, String filename){
        try{
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()){ 
                String line = fileScanner.nextLine(); 
                if(line.trim().isEmpty()) continue; 
                String[] parts = line.split(","); 
                system.addTask(Integer.parseInt(parts[0].trim()), parts[1].trim(), parts[2].trim());
            }
            fileScanner.close();
        } catch(FileNotFoundException e){
    
            System.out.println("File not found: " + filename);
    
        }    
    }
}