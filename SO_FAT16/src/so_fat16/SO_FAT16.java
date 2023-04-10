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
        fat_BS fat = new fat_BS();
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
                        
            int desloc = (int)fat.getInit_root_dir();
            
            cd(fat, strShort, listOf8dot3, desloc);
            
            cdAndLsandCat(listOf8dot3, fat, strShort );
            
        }else{
            System.out.println("Erro: Nenhum arquivo indicado!");
        }
    }
    
    static void cdAndLsandCat(ArrayList<oitoDot3> listOf8dot3, fat_BS fat, short[] strShort){
        while(true){
            String readed = read.nextLine();
            readed = readed.toUpperCase();
            if(readed.equals("LS")){
                ls(listOf8dot3);
            }else if(readed.length() > 2 && readed.substring(0, 3).contains("CD ") &&
                     (!readed.contains("."))| readed.equals("CD .") | readed.equals("CD ..")){
                String nmExt = readed.substring(3, readed.length());
                System.out.println("entro");
                int desloc = 0;
                int ttt = 0;
                int indice = 0;
                for(int i = 0; i < listOf8dot3.size(); i++){
                    String convNm = new String();
                    for(int j = 0; j < listOf8dot3.get(i).getName().length; j++){
                        convNm += (char) listOf8dot3.get(i).getName()[j];
                    }
                    
                    if(convNm.substring(0, convNm.length()).contains(nmExt)){
                        if(listOf8dot3.get(i).getFirst_cluster() == 0){
                            desloc = (int)fat.getInit_root_dir();
                        }else{
                            desloc = fat.getInit_data()+((listOf8dot3.get(i).getFirst_cluster()-2)*
                                 fat.getSectors_per_cluster()*fat.getBytes_per_sector());
                        }
                        listOf8dot3.removeAll(listOf8dot3);
                        cd(fat, strShort, listOf8dot3, desloc);
                        indice = i;
                        break;
                    }else{
                        ttt++;
                    }
                }
                int teste = 0;
                for(int i = 0; i < indice; i++){
                    if(!listOf8dot3.get(i).getName().equals(".") && !listOf8dot3.get(i).getName().equals("..")){
                        teste++;
                    }
                }
                if(ttt == listOf8dot3.size() && teste == 0){
                    System.out.println("Este diretorio nao existe");
                    ttt = 0;
                    teste = 0;
                }
            }else if(readed.length() > 3 && readed.substring(0, 4).contains("CAT ") && readed.contains(".")){
                String nmExt = readed.substring(4, readed.length());
                
                int posCluster = 0;
                int clusterSize = fat.getSectors_per_cluster()*fat.getBytes_per_sector();
                
                for(int i = 0; i < listOf8dot3.size(); i++){
                    posCluster = listOf8dot3.get(i).getFirst_cluster()*2+fat.getReserved_sector_count()*fat.getBytes_per_sector();
                  
                    String ext = new String();
                    for(int k = 0; k < listOf8dot3.get(i).getExt().length; k++){
                        if(listOf8dot3.get(i).getExt()[k] != 0){
                            ext += (char)listOf8dot3.get(i).getExt()[k];
                        }
                    }
                    
                    String name = new String();
                    for(int j = 0; j < listOf8dot3.get(i).getName().length; j++){
                        if(listOf8dot3.get(i).getName()[j] != 0){
                            name += (char)listOf8dot3.get(i).getName()[j];
                        }
                    }
                    
                    System.out.println("nmExt ="+nmExt);
                    System.out.println("NameExt ="+listOf8dot3.get(i).getNameExt());
                    //System.out.println(name.contains(nmExt.substring(0, nmExt.length()-4)));
                    //System.out.println(ext.contains(nmExt.substring(nmExt.length()-3, nmExt.length())));
                    
                    String itName = name.concat(".").concat(ext);
                    
                    int desloc = fat.getInit_data()+((listOf8dot3.get(i).getFirst_cluster()-2)*
                                 fat.getSectors_per_cluster()*fat.getBytes_per_sector());
                    
                    if(nmExt.equals(itName)){
                        while(true){
                            //System.out.println("aqui");
                            System.out.println(posCluster);
                            System.out.println(strShort[posCluster]);
                            long  clt = strShort[posCluster+1];
                            clt <<= 8;
                            clt |= strShort[posCluster];
                            System.out.println(clt);
                            if(clt >= 0xFFF8){
                                if(fat.getInit_data()+((listOf8dot3.get(i).getFirst_cluster()-2)*fat.getSectors_per_cluster()*fat.getBytes_per_sector()) != desloc){
                                    for(int x = 0; x < clusterSize; x++){
                                        System.out.print((char)strShort[desloc+x]);
                                    } 
                                }
                                break;
                            }else{
                                for(int x = 0; x < clusterSize; x++){
                                    System.out.print((char)strShort[desloc+x]);
                                }
                                System.out.println("");
                                //System.out.println("desloc init ="+desloc);
                                desloc = fat.getInit_data()+(((int)clt-2)* fat.getSectors_per_cluster()*fat.getBytes_per_sector());
                                //System.out.println("desloc ="+desloc);
                                posCluster = strShort[posCluster]*2+fat.getReserved_sector_count()*fat.getBytes_per_sector();
                            }
                        }
                    }
                }
            }
            if(readed.contains(".") && !readed.equals("CD .") && !readed.equals("CD ..") && !readed.contains("CAT ")){
                System.out.println("Impossivel usar cd em arquivo");
            }
        }
    }
    
    static void ls(ArrayList<oitoDot3> oit){
        System.out.println("Quantidades de entidades no diretorio atual : "+ oit.size());
        for(int i = 0; i < oit.size(); i++){
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.print("| Name : ");
            String name = new String();
            for(int j = 0; j < oit.get(i).getName().length; j++){
                name += (char)oit.get(i).getName()[j];
            }
            System.out.print(name);

            System.out.print(" | Ext : ");
            String ext = new String();
            for(int k = 0; k < oit.get(i).getExt().length; k++){
                ext += (char)oit.get(i).getExt()[k];
            }
            System.out.print(ext);

            System.out.print(" | Type : ");
            System.out.print(oit.get(i).getType());

            System.out.print(" | First cluster : ");
            System.out.print(oit.get(i).getFirst_cluster());

            System.out.print(" | Size : ");
            System.out.print(oit.get(i).getSize()+" |");
            System.out.println("\n...........................................................................................");
        }
    }
    
    static void cd(fat_BS fat, short[] strShort, ArrayList<oitoDot3> listOf8dot3, int desloc){
        int aux = 0;
        System.out.println("desloc ===="+desloc);
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

                desloc += 32;
                aux++;
            }else{
                desloc += 32;
                aux++;
            }
        }
        System.out.println("desloc fi ===="+desloc);
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
