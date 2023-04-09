package so_fat16;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author macedo
 */
public class SO_FAT16 {

    /**
     * @param args the command line arguments
     */
              
    public static Scanner read = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        fat_BS fat = new fat_BS();
        //BufferedReader readFile = new BufferedReader(new FileReader(args[0]));
        
        if(args.length == 1){
            byte[] strBytes = Files.readAllBytes(Paths.get(args[0]));
            int[] strInt = new int[strBytes.length];
            short[] strShort = new short[strBytes.length];
            for(int i = 0; i < strBytes.length; i++){
                strShort[i] = (short)Byte.toUnsignedInt(strBytes[i]);
            }
            fat.setBootjmp(strShort);
            
            fat.setOem_name(strShort);
            
            fat.setBytes_per_sector(strShort);
           
            fat.setSectors_per_cluster(strShort);
            
            fat.setReserved_sector_count(strShort);
            
            fat.setTable_count(strShort);
            
            fat.setRoot_entry_count(strShort);
            
            fat.setTotal_sectors_16(strShort);
            
            fat.setTotal_sector(strShort);
            
            ArrayList<oitoDot3> listOf8dot3 = new ArrayList<oitoDot3>();
                        
            getRootDir(fat, strShort, listOf8dot3);
            
            while(true){
                String readed = read.next();
                readed = readed.toLowerCase();
                if(readed.equals("ls")){
                    ls(listOf8dot3);
                }
            }
            
        }else{
            System.out.println("Erro: Nenhum arquivo indicado!");
        }
    }
    
    static void ls(ArrayList<oitoDot3> oit){
        for(int i = 0; i < oit.size(); i++){
            System.out.println("Name");
            String name = new String();
            for(int j = 0; j < oit.get(i).getName().length; j++){
                name += (char)oit.get(i).getName()[j];
            }
            System.out.println(name);

            System.out.println("Ext");
            String ext = new String();
            for(int k = 0; k < oit.get(i).getExt().length; k++){
                ext += (char)oit.get(i).getExt()[k];
            }
            System.out.println(ext);

            System.out.println("Type");
            System.out.println(oit.get(i).getType());

            System.out.println("First cluster");
            System.out.println(oit.get(i).getFirst_cluster());

            System.out.println("Size");
            System.out.println(oit.get(i).getSize());
            System.out.println("\n-------------------------------------\n");
        }
    }
    
    static void getRootDir(fat_BS fat, short[] strShort, ArrayList<oitoDot3> listOf8dot3){        
        int desloc = (int)fat.getInit_root_dir();
            
        int aux = 0;
        
        while(strShort[desloc] != 0 && aux <= fat.getRoot_entry_count()){
            if(strShort[desloc] != 229 && 
               strShort[desloc+11] != 15){
                oitoDot3 date8dot3 = new oitoDot3();

                date8dot3.setName(strShort, desloc);
                date8dot3.setExt(strShort, desloc);
                date8dot3.setType(strShort, desloc);
                date8dot3.setFirst_cluster(strShort, desloc);
                date8dot3.setSize(strShort, desloc);

                listOf8dot3.add(date8dot3);

                //debug(fat, date8dot3);

                desloc += 32;
                aux++;
            }else{
                desloc += 32;
                aux++;
            }
        }
    }
    
    static void debug(fat_BS fat, oitoDot3 oit){
        /*
        System.out.println("boot jmp");
        for(int i = 0; i < fat.getBootjmp().length; i++){
            System.out.println(fat.getBootjmp()[i]);
        }

        System.out.println("oem");
        for(int i = 0; i < fat.getOem_name().length; i++){
            System.out.println(fat.getOem_name()[i]);
        }

        System.out.println("bytes per sector");
        System.out.println(fat.getBytes_per_sector());

        System.out.println("sectors per cluster");
        System.out.println(fat.getSectors_per_cluster());

        System.out.println("reserved sector count");
        System.out.println(fat.getReserved_sector_count());

        System.out.println("table count");
        System.out.println(fat.getTable_count());

        System.out.println("root entry count");
        System.out.println(fat.getRoot_entry_count());

        System.out.println("total sectors 16");
        System.out.println(fat.getTotal_sectors_16());

        System.out.println("Table size 16");
        System.out.println(fat.getTable_size_16());

        System.out.println("Total sectors");
        System.out.println(fat.getTotal_sector());

        System.out.println("Init root dir");
        System.out.println(fat.getInit_root_dir());
        */
        System.out.println("\n\n\n8.3 par ==========================\n\n\n");
        
        System.out.println("Name");
        String name = new String();
        for(int i = 0; i < oit.getName().length; i++){
            name += (char)oit.getName()[i];
        }
        System.out.println(name);
        
        System.out.println("Ext");
        String ext = new String();
        for(int i = 0; i < oit.getExt().length; i++){
            ext += (char)oit.getExt()[i];
        }
        System.out.println(ext);
        
        System.out.println("Type");
        System.out.println(oit.getType());
        
        System.out.println("First cluster");
        System.out.println(oit.getFirst_cluster());
        
        System.out.println("Size");
        System.out.println(oit.getSize());
    }
}
