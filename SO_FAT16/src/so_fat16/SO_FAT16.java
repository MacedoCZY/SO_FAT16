package so_fat16;
import java.io.*;
import java.nio.file.*;

/**
 *
 * @author macedo
 */
public class SO_FAT16 {

    /**
     * @param args the command line arguments
     */
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
            
            fat.setMedia_type(strShort);
            
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
            
            System.out.println("Media type");
            System.out.println(fat.getMedia_type());
            
            System.out.println("Table size 16");
            System.out.println(fat.getTable_size_16());
            
            
        }else{
            System.out.println("Erro: Nenhum arquivo indicado!");
        }
    }
    
}
