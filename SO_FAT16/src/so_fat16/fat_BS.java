/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so_fat16;
/**
 *
 * @author macedo
 */
public class fat_BS {
    public short[] bootjmp = new short[3];   //1..3
    public short[] oem_name = new short[8];  //3..8
    public int bytes_per_sector;             //11 2
    public short sectors_per_cluster;        //13 1
    public int reserved_sector_count;        //14 2
    public short table_count;                //16 1
    public int root_entry_count;             //17 2
    public int total_sectors_16;             //22 2
    public long table_size_16;               //total_sectores_16*bytes_per_sector
    public int total_sector;                 //19 2
    public long init_root_dir;
    public int init_data;
    
    public short[] getBootjmp() {
        return bootjmp;
    }

    public void setBootjmp(short[] bootjmp) {
        for(int i = 0; i < 3; i++){
            this.bootjmp[i] = bootjmp[i];
        }
    }

    public short[] getOem_name() {
        return oem_name;
    }

    public void setOem_name(short[] oem_name) {
        for(int i = 0; i < 11; i++){
            if(i > 2){
                this.oem_name[i-3] = oem_name[i];
            }
        }
    }

    public int getBytes_per_sector() {
        return bytes_per_sector;
    }

    public void setBytes_per_sector(short[] bts) {
        long aux = bts[12];
        aux <<= 8;
        this.bytes_per_sector = (int) (aux | bts[11]);
    }

    public short getSectors_per_cluster() {
        return sectors_per_cluster;
    }

    public void setSectors_per_cluster(short[] stp) {
        this.sectors_per_cluster = stp[13];
    }

    public int getReserved_sector_count() {
        return reserved_sector_count;
    }

    public void setReserved_sector_count(short[] rsc) {
        long aux = rsc[15];
        aux <<= 8;
        this.reserved_sector_count = (int) (aux | rsc[14]);
    }

    public short getTable_count() {
        return table_count;
    }

    public void setTable_count(short[] tc) {
        this.table_count = tc[16];
    }

    public int getRoot_entry_count() {
        return root_entry_count;
    }

    public void setRoot_entry_count(short[] rec) {
        long aux = rec[18];
        aux <<= 8;
        this.root_entry_count = (int) (aux | rec[17]);
    }

    public int getTotal_sectors_16() {
        return total_sectors_16;
    }

    public void setTotal_sectors_16(short[] ts) {
        long aux = ts[23];
        aux <<= 8;
        this.total_sectors_16 = (int)(aux | ts[22]);
    }

    public long getTable_size_16() {
        return (long)(total_sectors_16*bytes_per_sector);
    }

    public int getTotal_sector() {
        return total_sector;
    }

    public void setTotal_sector(short[] ts) {
        long aux = ts[20];
        aux <<= 8;
        this.total_sector = (int) (aux | ts[19]);
    }

    public long getInit_root_dir() {
        return reserved_sector_count*bytes_per_sector+total_sectors_16*table_count*bytes_per_sector;
    }

    public int getInit_data() {
        return (int) (root_entry_count*32+(reserved_sector_count*bytes_per_sector+total_sectors_16*table_count*bytes_per_sector));
    }
}
