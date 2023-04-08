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
    public byte[] bootjmp = new byte[3];
    public byte[] oem_name = new byte[8];
    public short bytes_per_sector;
    public byte sectors_per_cluster;
    public short reserved_sector_count;
    public byte table_count;
    public short root_entry_count;
    public short total_sectors_16;
    public byte media_type;
    public short table_size_16;
    public short sectors_per_track;
    public short head_side_count;
    public int hidden_sector_count;
    public int total_sectors_32;
    public byte[] extended_section =  new byte[54];

    public byte[] getBootjmp() {
        return bootjmp;
    }

    public void setBootjmp(byte[] bootjmp) {
        this.bootjmp = bootjmp;
    }

    public byte[] getOem_name() {
        return oem_name;
    }

    public void setOem_name(byte[] oem_name) {
        this.oem_name = oem_name;
    }

    public short getBytes_per_sector() {
        return bytes_per_sector;
    }

    public void setBytes_per_sector(short bytes_per_sector) {
        this.bytes_per_sector = bytes_per_sector;
    }

    public byte getSectors_per_cluster() {
        return sectors_per_cluster;
    }

    public void setSectors_per_cluster(byte sectors_per_cluster) {
        this.sectors_per_cluster = sectors_per_cluster;
    }

    public short getReserved_sector_count() {
        return reserved_sector_count;
    }

    public void setReserved_sector_count(short reserved_sector_count) {
        this.reserved_sector_count = reserved_sector_count;
    }

    public byte getTable_count() {
        return table_count;
    }

    public void setTable_count(byte table_count) {
        this.table_count = table_count;
    }

    public short getRoot_entry_count() {
        return root_entry_count;
    }

    public void setRoot_entry_count(short root_entry_count) {
        this.root_entry_count = root_entry_count;
    }

    public short getTotal_sectors_16() {
        return total_sectors_16;
    }

    public void setTotal_sectors_16(short total_sectors_16) {
        this.total_sectors_16 = total_sectors_16;
    }

    public byte getMedia_type() {
        return media_type;
    }

    public void setMedia_type(byte media_type) {
        this.media_type = media_type;
    }

    public short getTable_size_16() {
        return table_size_16;
    }

    public void setTable_size_16(short table_size_16) {
        this.table_size_16 = table_size_16;
    }

    public short getSectors_per_track() {
        return sectors_per_track;
    }

    public void setSectors_per_track(short sectors_per_track) {
        this.sectors_per_track = sectors_per_track;
    }

    public short getHead_side_count() {
        return head_side_count;
    }

    public void setHead_side_count(short head_side_count) {
        this.head_side_count = head_side_count;
    }

    public int getHidden_sector_count() {
        return hidden_sector_count;
    }

    public void setHidden_sector_count(int hidden_sector_count) {
        this.hidden_sector_count = hidden_sector_count;
    }

    public int getTotal_sectors_32() {
        return total_sectors_32;
    }

    public void setTotal_sectors_32(int total_sectors_32) {
        this.total_sectors_32 = total_sectors_32;
    }

    public byte[] getExtended_section() {
        return extended_section;
    }

    public void setExtended_section(byte[] extended_section) {
        this.extended_section = extended_section;
    }
    
    
}
