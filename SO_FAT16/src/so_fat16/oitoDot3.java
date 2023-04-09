/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so_fat16;

/**
 *
 * @author gusta
 */
public class oitoDot3 {
    public short[] name = new short[8];
    public short[] ext = new short[3];
    public short type;
    public int first_cluster;
    public long size;
    public String nameExt;
    
    public short[] getName() {
        return name;
    }

    public void setName(short[] name, int desloc) {
        for(int i = 0; i < 8; i++){
            if(name[desloc+i] != ' '){
                this.name[i] = name[desloc+i];
            }
        }
    }

    public short[] getExt() {
        return ext;
    }

    public void setExt(short[] ext, int desloc) {
        for(int i = 0; i < 11; i++){
            if(i > 7){
                if(ext[desloc+i] != ' '){
                    this.ext[i-8] = ext[desloc+i];
                }
            }
        }
    }

    public short getType() {
        return type;
    }

    public void setType(short[] type, int desloc) {
        this.type = type[desloc+11];
    }

    public int getFirst_cluster() {
        return first_cluster;
    }

    public void setFirst_cluster(short[] first_cluster, int desloc) {
        this.first_cluster = first_cluster[desloc+26];
    }

    public long getSize() {
        return size;
    }

    public void setSize(short[] size, int desloc) {
        this.size = size[desloc+28];
    }   
    
    public String getNameExt(){
        boolean pst = false;
        for(int i = 0; i < name.length; i++){
            if(name[i] != ' '){
                nameExt += (char)name[i];
            }
        }
        for(int i = 0; i < 8; i++){
            if(name[i] == ' '){
                pst = true;
            }
        }
        if(ext[0] != '.' && ext[1] != '.' && ext[2] == ' '){
            pst = true;
        }else if(ext[0] != '.' && ext[1] == ' ' && ext[2] == ' '){
            pst = true;
        }

        if(pst == true){
            nameExt += ".";
        }
        for(int i = 0; i < ext.length; i++){
            nameExt += (char)ext[i];
        }
        return nameExt;
    }
}
