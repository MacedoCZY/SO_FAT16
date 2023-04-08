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
    public int bytes_per_sector;             //11
    public short sectors_per_cluster;        //13
    public int reserved_sector_count;        //14
    public short table_count;                //16
    public int root_entry_count;             //17
    public int total_sectors_16;             //22
    public char media_type;                 //21
    public long table_size_16;               //total_sectores_16*bytes_per_sector
    public int sectors_per_track;
    public int head_side_count;
    public long hidden_sector_count;
    public long total_sectors_32;
    public long[] extended_section =  new long[54];

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
        short aux = (short)bts[12];
        aux <<= 8;
        this.bytes_per_sector = (short)(aux | bts[11]);
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
        short aux = (short)rsc[15];
        aux <<= 8;
        this.reserved_sector_count = (short)(aux | rsc[14]);
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
        short aux = (short)rec[18];
        aux <<= 8;
        this.root_entry_count = (short)(aux | rec[17]);
    }

    public int getTotal_sectors_16() {
        return total_sectors_16;
    }

    public void setTotal_sectors_16(short[] ts) {
        short aux = (short)ts[23];
        aux <<= 8;
        this.total_sectors_16 = (short)(aux | ts[22]);
    }

    public int getMedia_type() {
        return media_type;
    }

    public void setMedia_type(short[] mt) {
        this.media_type = (char)mt[21];
    }

    public long getTable_size_16() {
        return (long)(total_sectors_16*bytes_per_sector);
    }

    public int getSectors_per_track() {
        return sectors_per_track;
    }

    public void setSectors_per_track(short sectors_per_track) {
        this.sectors_per_track = sectors_per_track;
    }

    public int getHead_side_count() {
        return head_side_count;
    }

    public void setHead_side_count(short head_side_count) {
        this.head_side_count = head_side_count;
    }

    public long getHidden_sector_count() {
        return hidden_sector_count;
    }

    public void setHidden_sector_count(int hidden_sector_count) {
        this.hidden_sector_count = hidden_sector_count;
    }

    public long getTotal_sectors_32() {
        return total_sectors_32;
    }

    public void setTotal_sectors_32(int total_sectors_32) {
        this.total_sectors_32 = total_sectors_32;
    }

    public long[] getExtended_section() {
        return extended_section;
    }

    public void setExtended_section(short[] extended_section) {
        this.extended_section = Short.toUnsignedLong(extended_section);
    }
    
    
}
