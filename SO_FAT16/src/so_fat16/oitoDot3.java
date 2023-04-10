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
        long aux = size[desloc+31];
        aux <<= 24;
        long aux1 = size[desloc+30];
        aux1 <<= 16;        
        long aux2 = (aux | aux1);
        aux = size[desloc+29];
        aux <<= 8;
        aux1 = (aux | size[desloc+28]);
        this.size = (int)(aux2 | aux1);
    }   
    
    public String getNameExt(){
        //String name = getName();
        String name = new String();
        for(int j = 0; j < getName().length; j++){
            name += (char)getName()[j];
        }
        String ext = new String();
        for(int k = 0; k < getExt().length; k++){
            ext += (char)getExt()[k];
        }
        if(getExt()[0] != 0 && getExt()[1] != 0 && getExt()[2] != 0){
            nameExt = name.concat(".").concat(ext);
        }else{
            nameExt = name;
        }
        return nameExt;
    }
}
