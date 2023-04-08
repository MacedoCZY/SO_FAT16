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
            for(int i = 0; i < strBytes.length; i++){
                System.out.println(strBytes[i]);
            }
        }else{
            System.out.println("Erro: Nenhum arquivo indicado!");
        }
    }
    
}
